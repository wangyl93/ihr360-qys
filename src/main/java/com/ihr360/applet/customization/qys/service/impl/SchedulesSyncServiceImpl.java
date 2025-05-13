package com.ihr360.applet.customization.qys.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.ihr360.applet.customization.qys.api.KqServiceApi;
import com.ihr360.applet.customization.qys.api.WebClientApi;
import com.ihr360.applet.customization.qys.consts.Constants;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrSignRecord;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrStaffsBasic;
import com.ihr360.applet.customization.qys.model.thirdpart.response.Check;
import com.ihr360.applet.customization.qys.model.thirdpart.response.DiffUser;
import com.ihr360.applet.customization.qys.model.thirdpart.response.DiffUserList;
import com.ihr360.applet.customization.qys.model.thirdpart.response.KqUser;
import com.ihr360.applet.customization.qys.service.SchedulesSyncService;
import com.ihr360.applet.customization.qys.utils.DateUtils;
import com.ihr360.applet.customization.qys.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SchedulesSyncServiceImpl implements SchedulesSyncService {

    @Resource
    WebClientApi webClientApi;
    @Resource
    KqServiceApi kqServiceApi;

    @Override
    public Object syncSchedules(String specifiedDay) {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        if(specifiedDay==null || specifiedDay.isEmpty()){
            specifiedDay = DateUtils.getLastNDate(-2);
        }
        
        List<String> dateList = DateUtils.getDatesFromGivenDateToNow(specifiedDay);
        //step1: 获取i人事员工数据
        List<String> idList = webClientApi.getStaffIdList();
        List<IhrStaffsBasic> ihrStaffsBasicList = webClientApi.getStaffsBasicList(idList);
        log.info("ihrStaffsBasicList size:{}", ihrStaffsBasicList.size());
        //生成map, key 为员工工号，value 为员工信息
        Map<String, IhrStaffsBasic> ihrStaffsBasicMap = ihrStaffsBasicList.stream()
                .collect(Collectors.toMap(IhrStaffsBasic::getStaffNo, Function.identity(),(a, b)->b));

        // step2: 获取考勤系统考勤编号与员工工号的映射关系
        List<KqUser> kqUserList = kqServiceApi.getUserList();
        Map<Integer,String> kqUserIdMap = new HashMap<>();
        for (KqUser kqUser : kqUserList) {
            Integer userId = kqUser.getUserID();  // 考勤编号
            String employeeNo = kqUser.getEmployeeNo(); // 员工工号
            kqUserIdMap.put(userId, employeeNo);
        }
        // step3 获取考勤系统设备地址映射关系
        Map<String, String> kqDeviceMap = kqServiceApi.getDeviceAddressMap();


        //step4 遍历日期列表，进行数据处理
        for(String date : dateList){
            processByDay(kqUserIdMap,date,ihrStaffsBasicMap,kqDeviceMap);
        }
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        // 计算运行时间
        long duration = endTime - startTime;
        // 日志输出运行时间
        log.info("SchedulesSync 方法运行时间: {} 毫秒", duration);
        return "同步打卡记录成功!";
    }



    private void processByDay(Map<Integer,String> userIdMap, String specifiedDay,Map<String, IhrStaffsBasic> ihrStaffsBasicMap,Map<String, String> deviceMap) {
        //step1: 获取浩顺云考勤数据
        List<Check> checkList =kqServiceApi.getCheckList(userIdMap, specifiedDay, specifiedDay);
        if(checkList.isEmpty()){
            log.info("checkList is empty");
            return;
        }
        log.info("checkList size:{}", checkList.size());

        //step2: 获取i人事考勤数据
        List<IhrSignRecord> ihrSignRecordList = webClientApi.getSignRecordList(specifiedDay);
        List<IhrSignRecord> ihrSignRecordFilterList = ihrSignRecordList.stream()
                .filter(ihrSignRecord -> "打卡考勤".equals(ihrSignRecord.getSourceDescription()))
                .collect(Collectors.toList());
        //生成Map , key 为 员工id + "|" + signTime, value 为打卡记录
        Map<String, IhrSignRecord> ihrSignRecordMap = ihrSignRecordFilterList.stream()
                .collect(Collectors.toMap(ihrSignRecord -> ihrSignRecord.getStaffId() + "|" + ihrSignRecord.getSignTime(), Function.identity(),(a, b)->b));

        if(!ihrSignRecordFilterList.isEmpty()){
            log.info("ihrSignRecordList size:{}", ihrSignRecordFilterList.size());
        }else{
            log.info("ihrSignRecordList is empty");
        }

        //step3: 比较数据，进行新增数据
        List<Check> CheckFilterList = checkList.stream()
                .filter(check -> ihrStaffsBasicMap.containsKey(check.getUserID()))
                .collect(Collectors.toList());
        List<IhrSignRecord> addSignRecordList = new ArrayList<>();
        if(!CheckFilterList.isEmpty()){
            boolean hasChange = true;
            if(!ihrSignRecordFilterList.isEmpty()){
                if(CheckFilterList.size() == ihrSignRecordFilterList.size()){
                    hasChange =false;
                }
            }
            if(hasChange){
                for(Check check : CheckFilterList) {
                    //获取打卡时间
                    IhrSignRecord ihrSignRecord = new IhrSignRecord();

                    String staffId = ihrStaffsBasicMap.get(check.getUserID()).getId();
                    String checkTime = DateUtils.parseDateToMillis(check.getCheckTime());
                    if(!CollectionUtils.isEmpty(ihrSignRecordMap) && ihrSignRecordMap.containsKey(staffId + "|" + checkTime)){
                        continue;
                    }
                    //log.info("userID:{}", check.getUserID());
                    ihrSignRecord.setStaffId(staffId);
                    ihrSignRecord.setSignTime(check.getCheckTime());
                    ihrSignRecord.setSignAddress(deviceMap.get(check.getDevice_id()));
                    ihrSignRecord.setSourceDescription(check.getRemark());
                    ihrSignRecord.setSignType("MACHINE");
                    addSignRecordList.add(ihrSignRecord);
                    //测试只增一条数据
                    //break;
                }
            }
        }

        // 新增打卡记录V2
        //单次最多新增100条打卡纪录
        log.info("addSignRecordList size:{}", addSignRecordList.size());
        if(!addSignRecordList.isEmpty()){
            List<List<IhrSignRecord>> partitions = ListUtil.partition(addSignRecordList, Constants.BATCH_SIZE100);
            for(List<IhrSignRecord> subList : partitions){
                webClientApi.createSignRecordList(subList);
            }
        }else{
            log.info("addSignRecordList is empty");
        }
    }

    @Override
    public Object checkStaffDiff() {

        //step1: 获取i人事员工数据
        List<String> idList = webClientApi.getStaffIdList();
        List<IhrStaffsBasic> ihrStaffsBasicList = webClientApi.getStaffsBasicList(idList);
        log.info("ihrStaffsBasicList size:{}", ihrStaffsBasicList.size());
        //step1.2 生成map, key 为员工工号，value 为员工信息
        Map<String, IhrStaffsBasic> ihrStaffNoBasicMap = ihrStaffsBasicList.stream()
                .filter(ihrStaffsBasic -> !StringUtils.isEmpty(ihrStaffsBasic.getStaffNo()))
                .collect(Collectors.toMap(IhrStaffsBasic::getStaffNo, Function.identity(),(a, b)->b));

        Map<String, IhrStaffsBasic> ihrStaffNameBasicMap = ihrStaffsBasicList.stream()
                .collect(Collectors.toMap(IhrStaffsBasic::getStaffName, Function.identity(),(a, b)->b));

        List<KqUser> kqUserList = kqServiceApi.getUserList();
        //Map<Integer,String> kqUserIdMap = new HashMap<>();

        Map<String,KqUser> kqEmployeeNoMap = new HashMap<>();
        Map<String,KqUser> kqEmployeeNameMap = new HashMap<>();
        for (KqUser kqUser : kqUserList) {
            Integer userId = kqUser.getUserID();  // 考勤编号
            String userName = kqUser.getRealName();// 员工姓名
            String employeeNo = kqUser.getEmployeeNo(); // 员工工号
            if(!StringUtils.isEmpty(employeeNo)){
                kqEmployeeNoMap.put(employeeNo, kqUser);
            }
            kqEmployeeNameMap.put(userName, kqUser);
        }
        log.info("kqUserList size:{}", kqUserList.size());
        log.info("kqEmployeeNoMap size:{}", kqEmployeeNoMap.size());
        log.info("kqEmployeeNameMap size:{}", kqEmployeeNameMap.size());

        //先遍历 i 人事员工数据


        List<DiffUser> sameUsersList = new ArrayList<>();
        List<DiffUser> diffUsersList = new ArrayList<>();
        List<DiffUser> ihrUsersList = new ArrayList<>();
        List<DiffUser> kqUsersList = new ArrayList<>();

        for (IhrStaffsBasic ihrStaffsBasic : ihrStaffsBasicList) {
            String staffNo = ihrStaffsBasic.getStaffNo();
            String staffName = ihrStaffsBasic.getStaffName();
            KqUser kqUserByNo = kqEmployeeNoMap.get(staffNo);
            KqUser kqUserByName = kqEmployeeNoMap.get(staffName);
            if(kqUserByNo != null){
                //根据员工工号查找, 员工姓名是否相同
                // 员工姓名相同
                if(kqUserByNo.getRealName().equals(staffName)){
                    //sameUsersList = diffUserList.getSameUserList();
                    sameUsersList.add(new DiffUser(staffName , staffNo, kqUserByNo.getEmployeeNo(), "员工工号相同"));
                }else{
                    // 员工姓名不相同, 按照员工姓名去查找, 如果存在则说明考勤编号不同
                    if(kqUserByName != null){
                        //diffUsersList = diffUserList.getDiffUserList();
                        diffUsersList.add(new DiffUser(staffName , staffNo, kqUserByName.getEmployeeNo(), "员工工号不同"));
                    }else{
                        // 员工姓名不相同, 按照员工姓名去查找, 如果不存在则说明只在i人事系统中存在,考勤系统中不存在
                        //ihrUsersList = diffUserList.getIhrUserList();
                        ihrUsersList.add(new DiffUser(staffName, staffNo, null, "i人事系统中存在,考勤系统中不存在"));
                    }

                }
            }else if(kqUserByName != null){
                // 根据员工姓名查找, 如果存在则说明考勤编号不同
                //sameUsersList = diffUserList.getSameUserList();
                sameUsersList.add(new DiffUser(staffName , staffNo, kqUserByName.getEmployeeNo(), "员工工号不同"));
            }else {
                //ihrUsersList = diffUserList.getIhrUserList();
                ihrUsersList.add(new DiffUser(staffName, staffNo, null, "i人事系统中存在,考勤系统中不存在"));
            }
        }

        for (KqUser kqUser : kqUserList) {
            String employeeNo = kqUser.getEmployeeNo();
            String realName = kqUser.getRealName();
            IhrStaffsBasic ihrStaffsBasicByName = ihrStaffNameBasicMap.get(realName);

            // 如果考勤系统中存在该员工, 在i人事系统中不存在, 则添加到 kqUserList 中
            if( ihrStaffsBasicByName == null){
                //kqUsersList = diffUserList.getKqUserList();
                kqUsersList.add(new DiffUser(realName, null, employeeNo, "i人事系统中不存在,考勤系统中存在"));
            }
        }

        return new DiffUserList(sameUsersList, diffUsersList, ihrUsersList, kqUsersList);
    }









}
