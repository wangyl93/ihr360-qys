package com.ihr360.applet.customization.qys.service.impl;


import com.ihr360.applet.customization.qys.api.KqServiceApi;
import com.ihr360.applet.customization.qys.api.WebClientApi;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrStaffsBasic;
import com.ihr360.applet.customization.qys.model.ihr.response.ReturnT;
import com.ihr360.applet.customization.qys.model.thirdpart.response.KqUser;
import com.ihr360.applet.customization.qys.repository.StaffBasicDao;
import com.ihr360.applet.customization.qys.repository.po.StaffBasic;
import com.ihr360.applet.customization.qys.service.StaffBasicSyncService;
import com.ihr360.applet.customization.qys.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StaffBasicSyncServiceImpl implements StaffBasicSyncService {

    @Resource
    WebClientApi webClientApi;

    @Resource
    StaffBasicDao staffBasicDao;

    @Resource
    KqServiceApi kqServiceApi;


    @Override
    public Object syncStaffBasic() throws Exception {
        //
        log.info("开始同步员工");
        long startTime = System.currentTimeMillis();
        List<String> idList = webClientApi.getStaffIdList();
        List<IhrStaffsBasic> ihrStaffsBasicList = webClientApi.getStaffsBasicList(idList);
        log.info("ihrStaffsBasicList size:{}", ihrStaffsBasicList.size());

        List<StaffBasic> staffsBasicList = staffBasicDao.findAll();
        Map<String, StaffBasic> staffBasicMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(staffsBasicList)) {
            staffBasicMap = staffsBasicList.stream()
                    .collect(Collectors.toMap(StaffBasic::getStaffId, staffBasic -> staffBasic));
        }

        List<StaffBasic> staffBasicList = new ArrayList<>();

        for (IhrStaffsBasic ihrStaffsBasic : ihrStaffsBasicList) {
            String staffId = ihrStaffsBasic.getId();
            String lastUpdateDate = StringUtils.toString(ihrStaffsBasic.getLastUpdateDate());
            StaffBasic staffBasicEntity = staffBasicMap.get(staffId);
            StaffBasic staffBasic = new StaffBasic();
            if (staffBasicEntity != null) {
                staffBasic = staffBasicEntity;
                if (!staffBasic.getLastUpdateDate().equals(lastUpdateDate)) {
                    staffBasic.setStaffBasic(ihrStaffsBasic);
                    staffBasicList.add(staffBasic);
                }
            } else {
                staffBasic.setStaffBasic(ihrStaffsBasic);
                staffBasicList.add(staffBasic);
            }
        }
        staffBasicDao.saveAll(staffBasicList);

        // step2: 获取考勤系统考勤编号与员工工号的映射关系
        List<StaffBasic> staffBasicNumberList = new ArrayList<>();
        List<KqUser> kqUserList = kqServiceApi.getUserList();
        log.info("kqUserList size:{}", kqUserList.size());
        for (KqUser kqUser : kqUserList) {
            String userName = kqUser.getRealName();
            String employeeNo = kqUser.getEmployeeNo();
            List<StaffBasic> staffBasicList1 = staffBasicDao.findByStaffName(userName);
            if (!CollectionUtils.isEmpty(staffBasicList1)) {
                if (staffBasicList1.size() == 1) {
                    StaffBasic staffBasic = staffBasicList1.get(0);
                    if (staffBasic.getEmployeeNo() == null || !employeeNo.equals(staffBasic.getEmployeeNo())) {
                        staffBasic.setEmployeeNo(employeeNo);
                        staffBasicNumberList.add(staffBasic);
                   }
                }else {
                    List<StaffBasic> staffBasicList2 = staffBasicDao.findByStaffNameAndMobile(userName, kqUser.getMobile());
                    if (!CollectionUtils.isEmpty(staffBasicList2)) {
                        StaffBasic staffBasic = staffBasicList2.get(0);
                        if (staffBasic.getEmployeeNo() == null || !employeeNo.equals(staffBasic.getEmployeeNo())) {
                            staffBasic.setEmployeeNo(employeeNo);
                            staffBasicNumberList.add(staffBasic);
                        }
                    }
                }
            }
        }
        staffBasicDao.saveAll(staffBasicNumberList);
        log.info("staffBasicNumberList size:{}", staffBasicNumberList.size());
        long endTime = System.currentTimeMillis();
        log.info("同步员工完成,耗时:{}ms", endTime - startTime);
        return new ReturnT<>(ReturnT.SUCCESS_CODE, "成功!");
    }

}
