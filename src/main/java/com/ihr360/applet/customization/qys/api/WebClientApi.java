package com.ihr360.applet.customization.qys.api;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ihr360.applet.customization.qys.consts.Constants;
import com.ihr360.applet.customization.qys.enums.IhrUrl;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrApiDataResponse;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrScheduleData;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrScheduleSearch;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrSchedulesPageData;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrSignRecord;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrStaffIdList;
import com.ihr360.applet.customization.qys.model.ihr.response.IhrStaffsBasic;
import com.ihr360.applet.customization.qys.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class WebClientApi {
    @Resource
    WebClientUtil webClientUtil;

    @Value("${ihr360.openapi.provider.server.url}")
    private String ihrApiServer;

    @Value("${spring.security.oauth2.client.registration.ihrOAuth2Client.client-id}")
    private String AppID;
    @Value("${spring.security.oauth2.client.registration.ihrOAuth2Client.client-secret}")
    private String AppSecret;


    public List<IhrSignRecord> getSignRecordList(String specifiedDay) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("startTime", specifiedDay + " 00:00:00");
        params.add("endTime", specifiedDay + " 23:59:59");
        List<IhrSignRecord> ihrSignRecordList;
        ihrSignRecordList = webClientUtil.getListData(IhrUrl.SIGNS_SEARCH.getUrl(),params, IhrSignRecord.class);
        //log.info("获取签卡记录结果：{}", ihrSignRecordList);
        return  ihrSignRecordList;
    }

    public void createSignRecordList(List<IhrSignRecord> ihrSignRecordList){

        String result = webClientUtil.post(IhrUrl.SIGNS_CREATE.getUrl(),null,ihrSignRecordList, String.class);
        log.info("创建签卡记录结果：{}", result);
    }

    /**
     * 获取排班V2
     *
     * @param specifiedDay 指定日期
     * @return 排班数据列表
     */

    public List<IhrScheduleData> getSchedulesV2(String specifiedDay) {
        log.info(IhrUrl.V2_SCHEDULES.getName() + ":" + specifiedDay);
        List<IhrScheduleData> schedules = new ArrayList<>();
        int pageNo = 0;
        boolean hasMore = true;
        while (hasMore) {
            IhrScheduleSearch ihrScheduleSearch = new IhrScheduleSearch();
            if(specifiedDay != null && !specifiedDay.isEmpty()) {
                ihrScheduleSearch.setPlanDate(specifiedDay);
            } else {
                ihrScheduleSearch.setPlanDate(DateUtil.format(new Date(), "yyyy-MM-dd"));
            }
            ihrScheduleSearch.setPage(pageNo++);
            ihrScheduleSearch.setSize(Constants.PAGE_SIZE1000);
            IhrApiDataResponse<IhrSchedulesPageData> ihrSchedulesPageData =webClientUtil.post(IhrUrl.V2_SCHEDULES.getUrl(), ihrScheduleSearch,
                    new ParameterizedTypeReference<IhrApiDataResponse<IhrSchedulesPageData>>() {
            });

            int totalPages = ihrSchedulesPageData.getData().getTotalPages();
            schedules.addAll(ihrSchedulesPageData.getData().getContent());
            if(totalPages == pageNo) {
                hasMore = false;
            }
        }

        return schedules;
    }

    /**
     * 获取所有员工ID列表
     *
     * @return 包含所有员工ID的列表
     */
    public  List<String> getStaffIdList()  {
        log.info(IhrUrl.STAFFID.getName());
        List<String> allStaffList = new ArrayList<>();
        boolean hasMore = true;
        int pageNo = 1;
        while (hasMore) {
            log.info(IhrUrl.STAFFID.getName() + ":" + pageNo);
            int curPageNo = pageNo;
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("pageNo", String.valueOf(curPageNo));
            IhrStaffIdList ihrIdList = webClientUtil.get(IhrUrl.STAFFID.getUrl(),params, IhrStaffIdList.class);
            if (ihrIdList == null || (null != ihrIdList.getErrorResult() && ihrIdList.getErrorResult())) {
                log.warn("查询员ID 出错了！");
                break;
            } else {
                allStaffList.addAll(ihrIdList.getData().getDataList());
            }

            int totalPages = ihrIdList.getData().getPageInfo().getTotalPages();

            if (pageNo >= totalPages) {
                // 已获取所有数据，退出循环
                hasMore = false;
            }
            pageNo++;
        }

        return allStaffList;
    }

    /**
     * 获取员工基本信息列表
     *
     * @param staffList 员工ID列表
     * @return 包含所有员工基本信息的列表
     */
    public List<IhrStaffsBasic> getStaffsBasicList(List<String> staffList) {

        log.info(IhrUrl.STAFFBASIC.getName() + ";员工ID列表 :" + staffList.size());
        List<IhrStaffsBasic> staffDataList = new ArrayList<>();
        List<List<String>> idListList = ListUtil.split(staffList, Constants.PAGE_SIZE1000);
        for (List<String> idList : idListList) {
            List<IhrStaffsBasic> allStaffBasicResponse = webClientUtil.postListData(IhrUrl.STAFFBASIC.getUrl(),null, idList
                    ,IhrStaffsBasic.class);
            staffDataList.addAll(allStaffBasicResponse);
        }
        return staffDataList;
    }



    public int deleteActualDataSetEntityList(List<String> ids) {


        String delete =webClientUtil.delete(IhrUrl.SMARTAPP_DELETE.getUrl(),"dataSetCode",Constants.ACTUAL_DATASETCODE,ids);

        log.info("delete:" + delete);

        return 0;

    }



    public String getToken() throws UnsupportedEncodingException {
        String url =   StrUtil.format(IhrUrl.ACCESSTOKEN.getUrl(),ihrApiServer);
        String str = webClientUtil.postToken(url,null,null,String.class);
        log.info("str:" + str);
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(str);
        String token = StringUtils.toString(jsonObject.get("access_token"));
        log.info("token:" + token);
        //return TokenUtils.getHrToken(url, AppID, AppSecret);
        return token;
    }

    public String getRefreshTokenValue() throws UnsupportedEncodingException {
        String url =   StrUtil.format(IhrUrl.ACCESSTOKEN.getUrl(),ihrApiServer);
        String str = webClientUtil.postToken(url,null,null,String.class);
        log.info("str:" + str);
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(str);
        String token = StringUtils.toString(jsonObject.get("refresh_token"));
        log.info("refresh_token:" + token);
        //return TokenUtils.getHrToken(url, AppID, AppSecret);
        return token;
    }

    public Object getRefreshToken() throws UnsupportedEncodingException {
        String url =   StrUtil.format(IhrUrl.RFRRESHTOKEN.getUrl(),ihrApiServer,getRefreshTokenValue());
        log.info("url:" + url);
        String str = webClientUtil.postToken(url,null,null,String.class);
        log.info("str:" + str);
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(str);
        String token = StringUtils.toString(jsonObject.get("refresh_token"));
        log.info("refresh_token:" + token);
        //return TokenUtils.getHrToken(url, AppID, AppSecret);
        return token;
    }


}
