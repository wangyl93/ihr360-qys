package com.ihr360.applet.customization.qys.service.impl;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import com.landray.kmss.sys.organization.webservice.in.ISysSynchroSetOrgWebService;
import com.landray.kmss.sys.organization.webservice.in.SysSynchroSetOrgContext;
import com.landray.kmss.sys.organization.webservice.in.SysSynchroSetResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {

    // 定义组织架构接入WebService对象
    private ISysSynchroSetOrgWebService setOrgWebService=null;

    /**
     * 同步需要更新的组织架构信息
     * @param orgContext 组织架构信息接入上下文
     * @return
     * @throws Exception
     */
    public SysSynchroSetResult syncOrgElements(SysSynchroSetOrgContext setOrgContext) throws Exception{
        return getService().syncOrgElements(setOrgContext);
    }

    /**
     * 获取组织架构接入WebService对象
     * @return
     */
    private ISysSynchroSetOrgWebService getService() {
        if(setOrgWebService==null){
            // WebService配置信息对象（读取自client.properties配置文件）
            WebServiceConfig serviceConfig = WebServiceConfig.getInstance();
            // 使用 Apache CXF 框架创建组织架构接入WebService对象
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.getInInterceptors().add(new LoggingInInterceptor());
            factory.getOutInterceptors().add(new LoggingOutInterceptor());
            factory.getOutInterceptors().add(new AddSoapHeader());
            factory.setServiceClass(ISysSynchroSetOrgWebService.class);
            // 设置服务请求的URL地址
            String servicePath = serviceConfig.getAddress();
            factory.setAddress(servicePath);
            setOrgWebService= (ISysSynchroSetOrgWebService) factory.create();
        }
        return setOrgWebService;
    }


    /**
     * 伪造测试数据
     * @return
     */
    public JSONArray getTestOrgJsonData(){
        JSONArray resultArray = new JSONArray();

        // 定义一个 《机构》 数据对象
        JSONObject orgObj = new JSONObject();
        orgObj.put("id", "12d552352ac1dd47c57c307401f958f7");    // 唯一标识  (不允许为空)
        orgObj.put("lunid", "12d552352ac1dd47c57c307401f958f7"); // 唯一标识 (若不为空，直接作为主健存入数据库,允许为空)
        orgObj.put("name", "深圳蓝凌软件股份有限公司"); // 名称 (不允许为空)
        orgObj.put("type", "org");    // 组织架构类型  (不允许为空  可选项：  org(机构)、dept(部门)、group(群组)、post(岗位)、person(人员))
        orgObj.put("no", "10001");    // 编号   (可为空)
        orgObj.put("order", "10000"); // 排序号 (可为空)
        orgObj.put("keyword", "18391297432nm"); // 关键字  (内容格式不限制，可为空)
        orgObj.put("memo", "蓝凌软件全称深圳市蓝凌软件股份有限公司，于2001年在深圳科技园成立"); // 组织架构说明  (可为空)
        orgObj.put("isAvailable", true);  // 是否有效 (该属性决定该组织架构是否删除)
        orgObj.put("parent", null);       // 设置该组织架构的父组织id (当type为org,dept,post,person时起作用)
        orgObj.put("thisLeader", null);   // 设置组织架构领导人员id (当type为org,dept,post时起作用，可为空)
        orgObj.put("superLeader", null);  // 设置部门上级领导人员id (当type为org,dept时起作用，可为空)

        // 定义一个 《部门》 数据对象
        JSONObject deptObj = new JSONObject();
        deptObj.put("id", "166e8a4b278c197e7bc632447799183f");    // 唯一标识  (不允许为空)
        deptObj.put("lunid", "166e8a4b278c197e7bc632447799183f"); // 唯一标识 (若不为空，直接作为主健存入数据库,允许为空)
        deptObj.put("name", "技术研发部"); // 名称 (不允许为空)
        deptObj.put("type", "dept");    // 组织架构类型  (不允许为空  可选项：  org(机构)、dept(部门)、group(群组)、post(岗位)、person(人员))
        deptObj.put("no", "1000005");   // 编号   (可为空)
        deptObj.put("order", "10000");  // 排序号 (可为空)
        deptObj.put("keyword", "testDeptKeyword"); // 关键字  (内容格式不限制，可为空)
        deptObj.put("memo", "技术研发部门拥有一批专业、职业素养高的人才团队"); // 组织架构说明  (可为空)
        deptObj.put("isAvailable", true);  // 是否有效 (该属性决定该组织架构是否删除)
        deptObj.put("parent", "12d552352ac1dd47c57c307401f958f7"); // 设置该组织架构的父组织id (当type为org,dept,post,person时起作用)
        deptObj.put("thisLeader", null);   // 设置组织架构领导人员id (当type为org,dept,post时起作用，可为空)
        deptObj.put("superLeader", null);  // 设置部门上级领导人员id (当type为org,dept时起作用，可为空)

        // 定义一个 《岗位》 数据对象
        JSONObject postObj = new JSONObject();
        postObj.put("id", "166e6c3a3007da530389c374afd92c78");    // 唯一标识  (不允许为空)
        postObj.put("lunid", "166e6c3a3007da530389c374afd92c78"); // 唯一标识 (若不为空，直接作为主健存入数据库,允许为空)
        postObj.put("name", "开发岗");    // 名称 (不允许为空)
        postObj.put("type", "post");   // 组织架构类型  (不允许为空  可选项：  org(机构)、dept(部门)、group(群组)、post(岗位)、person(人员))
        postObj.put("no", "1000055");   // 编号   (可为空)
        postObj.put("order", "10000");  // 排序号 (可为空)
        postObj.put("keyword", "testPostKeyword"); // 关键字  (内容格式不限制，可为空)
        postObj.put("memo", "开发岗负责公司的产品研发工作"); // 组织架构说明  (可为空)
        postObj.put("isAvailable", true);  // 是否有效 (该属性决定该组织架构是否删除)
        postObj.put("parent", "166e8a4b278c197e7bc632447799183f"); // 设置该组织架构的父组织id (当type为org,dept,post,person时起作用)
        postObj.put("thisLeader", null);   // 设置组织架构领导人员id (当type为org,dept,post时起作用，可为空)
        String[] persons = {"1689d276373e6e444b1a8df466783ab0","1689d2779b9f983f3abec864d16ae6c1","1689d27b2280c2bded38b6d45b09b9df"};
        postObj.put("persons", persons);  // 岗位成员id数组 (仅对type为post时起作用，可为空)

        // 定义一个 《群组》 数据对象
        JSONObject groupObj = new JSONObject();
        groupObj.put("id", "166e8a3d3dcc6433fcae7bb47ff9a7e5");    // 唯一标识  (不允许为空)
        groupObj.put("lunid", "166e8a3d3dcc6433fcae7bb47ff9a7e5"); // 唯一标识 (若不为空，直接作为主健存入数据库,允许为空)
        groupObj.put("name", "前端开发组");  // 名称 (不允许为空)
        groupObj.put("type", "group");    // 组织架构类型  (不允许为空  可选项：  org(机构)、dept(部门)、group(群组)、post(岗位)、person(人员))
        groupObj.put("no", "1000009");    // 编号   (可为空)
        groupObj.put("order", "10000");   // 排序号 (可为空)
        groupObj.put("keyword", "testGroupKeyword"); // 关键字  (内容格式不限制，可为空)
        groupObj.put("memo", "小组专攻主流前端技术"); // 组织架构说明  (可为空)
        groupObj.put("isAvailable", true);  // 是否有效 (该属性决定该组织架构是否删除)
        String[] members= {"1689d276373e6e444b1a8df466783ab0","1689d2779b9f983f3abec864d16ae6c1","1689d27b2280c2bded38b6d45b09b9df"};
        groupObj.put("members", members);  // 群组成员id数组 (仅对type为group时起作用，可为空)

        // 定义三个 《人员》 数据对象
        JSONObject personObj_1 = new JSONObject();
        personObj_1.put("id", "1689d276373e6e444b1a8df466783ab0");    // 唯一标识  (不允许为空)
        personObj_1.put("lunid", "1689d276373e6e444b1a8df466783ab0"); // 唯一标识 (若不为空，直接作为主健存入数据库,允许为空)
        personObj_1.put("name", "孙悟空");     // 名称 (不允许为空)
        personObj_1.put("type", "person");   // 组织架构类型  (不允许为空  可选项：  org(机构)、dept(部门)、group(群组)、post(岗位)、person(人员))
        personObj_1.put("no", "1000091");    // 编号   (可为空)
        personObj_1.put("order", "10001");   // 排序号 (可为空)
        personObj_1.put("keyword", "sunwukong(孙悟空)"); // 关键字  (内容格式不限制，可为空)
        personObj_1.put("memo", null);       // 组织架构说明  (可为空)
        personObj_1.put("isAvailable", true);  // 是否有效 (该属性决定该组织架构是否删除)
        personObj_1.put("parent", "166e8a4b278c197e7bc632447799183f");  // 设置该组织架构的父组织id (当type为org,dept,post,person时起作用)
        String[] posts_1 = {"166e6c3a3007da530389c374afd92c78"};
        personObj_1.put("posts", posts_1);  // 所属岗位id数组 (仅当type为person时起作用，可为空)
        personObj_1.put("loginName", "sunwukong"); // 登录名  (仅当type为person时,有此信息)
        personObj_1.put("password", "c4ca4238a0b923820dcc509a6f75849b"); // 密码 (base64加密后的信息,仅当type为person时,有此信息)
        personObj_1.put("mobileNo", "13700998811"); // 手机号  (仅当type为person时,有此信息)
        personObj_1.put("email", "sunwukong@landray.com"); // 电子邮箱  (仅当type为person时,有此信息)
        personObj_1.put("attendanceCardNumber", "20080901"); // 考勤号 (仅当type为person时,有此信息)
        personObj_1.put("workPhone", "0755878787782"); // 办公电话 (仅当type为person时,有此信息)
        personObj_1.put("rtx", "952701"); // rtx账号 (仅当type为person时,有此信息)

        JSONObject personObj_2 = new JSONObject();
        personObj_2.put("id", "1689d2779b9f983f3abec864d16ae6c1");    // 唯一标识  (不允许为空)
        personObj_2.put("lunid", "1689d2779b9f983f3abec864d16ae6c1"); // 唯一标识 (若不为空，直接作为主健存入数据库,允许为空)
        personObj_2.put("name", "猪八戒");     // 名称 (不允许为空)
        personObj_2.put("type", "person");  // 组织架构类型  (不允许为空  可选项：  org(机构)、dept(部门)、group(群组)、post(岗位)、person(人员))
        personObj_2.put("no", "1000092");   // 编号   (可为空)
        personObj_2.put("order", "10002");  // 排序号 (可为空)
        personObj_2.put("keyword", "zhubajie(猪八戒)"); // 关键字  (内容格式不限制，可为空)
        personObj_2.put("memo", null);     // 组织架构说明  (可为空)
        personObj_2.put("isAvailable", true);  // 是否有效 (该属性决定该组织架构是否删除)
        personObj_2.put("parent", "166e8a4b278c197e7bc632447799183f");  // 设置该组织架构的父组织id (当type为org,dept,post,person时起作用)
        String[] posts_2 = {"166e6c3a3007da530389c374afd92c78"};
        personObj_2.put("posts", posts_2);  // 所属岗位id数组 (仅当type为person时起作用，可为空)
        personObj_2.put("loginName", "zhubajie"); // 登录名  (仅当type为person时,有此信息)
        personObj_2.put("password", "c4ca4238a0b923820dcc509a6f75849b"); // 密码 (base64加密后的信息,仅当type为person时,有此信息)
        personObj_2.put("mobileNo", "15800229966"); // 手机号  (仅当type为person时,有此信息)
        personObj_2.put("email", "zhubajie@landray.com"); // 电子邮箱  (仅当type为person时,有此信息)
        personObj_2.put("attendanceCardNumber", "20080902"); // 考勤号 (仅当type为person时,有此信息)
        personObj_2.put("workPhone", "0755878787783"); // 办公电话 (仅当type为person时,有此信息)
        personObj_2.put("rtx", "952702"); // rtx账号 (仅当type为person时,有此信息)

        JSONObject personObj_3 = new JSONObject();
        personObj_3.put("id", "1689d27b2280c2bded38b6d45b09b9df");    // 唯一标识  (不允许为空)
        personObj_3.put("lunid", "1689d27b2280c2bded38b6d45b09b9df"); // 唯一标识 (若不为空，直接作为主健存入数据库,允许为空)
        personObj_3.put("name", "沙僧");     // 名称 (不允许为空)
        personObj_3.put("type", "person");  // 组织架构类型  (不允许为空  可选项：  org(机构)、dept(部门)、group(群组)、post(岗位)、person(人员))
        personObj_3.put("no", "1000093");   // 编号   (可为空)
        personObj_3.put("order", "10003");  // 排序号 (可为空)
        personObj_3.put("keyword", "shaseng(沙僧)"); // 关键字  (内容格式不限制，可为空)
        personObj_3.put("memo", null);     // 组织架构说明  (可为空)
        personObj_3.put("isAvailable", true);  // 是否有效 (该属性决定该组织架构是否删除)
        personObj_3.put("parent", "166e8a4b278c197e7bc632447799183f");  // 设置该组织架构的父组织id (当type为org,dept,post,person时起作用)
        String[] posts_3 = {"166e6c3a3007da530389c374afd92c78"};
        personObj_3.put("posts", posts_3);  // 所属岗位id数组 (仅当type为person时起作用，可为空)
        personObj_3.put("loginName", "shaseng"); // 登录名  (仅当type为person时,有此信息)
        personObj_3.put("password", "c4ca4238a0b923820dcc509a6f75849b"); // 密码 (base64加密后的信息,仅当type为person时,有此信息)
        personObj_3.put("mobileNo", "18066779911"); // 手机号  (仅当type为person时,有此信息)
        personObj_3.put("email", "shaseng@landray.com"); // 电子邮箱  (仅当type为person时,有此信息)
        personObj_3.put("attendanceCardNumber", "20080903"); // 考勤号 (仅当type为person时,有此信息)
        personObj_3.put("workPhone", "0755878787788"); // 办公电话 (仅当type为person时,有此信息)
        personObj_3.put("rtx", "952703"); // rtx账号 (仅当type为person时,有此信息)


        resultArray.add(orgObj);   // 机构
        resultArray.add(deptObj);  // 部门
        resultArray.add(postObj);  // 岗位
        resultArray.add(groupObj); // 群组
        resultArray.add(personObj_1); // 人员1
        resultArray.add(personObj_2); // 人员2
        resultArray.add(personObj_3); // 人员3

        return resultArray;
    }



    public static void main(String[] args) {
        Test test = new Test();

        // 定义组织架构信息接入上下文
        SysSynchroSetOrgContext setOrgContext = new SysSynchroSetOrgContext();

        // 获取测试组织数据
        JSONArray orgJsonData = test.getTestOrgJsonData();

        // 将组织数据设置到组织架构信息接入上下文
        setOrgContext.setOrgJsonData(orgJsonData.toString());

        try {
            // 调用WebService接口，并接收请求返回的数据
            SysSynchroSetResult result = test.syncOrgElements(setOrgContext);
            System.out.println("请求状态:\n"+result.getReturnState()); // 0:未操作、1:失败、2:成功
            System.out.println("消息:\n"+result.getMessage());     // 返回状态值为0时，该值返回空,返回状态值为1时，该值错误信息,返回状态值为2时，该值返回空
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
