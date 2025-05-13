package com.ihr360.applet.customization.qys.model.ihr.response;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class StaffDetailInfoWithSuperior {
    
    /**
     * 员工ID
     */
    String	id;
    /**
     * 公司ID
     */
    String	companyId;
    /**
     * 年龄
     */
    Integer	age;
    /**
     * 生日
     */
    String	birthday;
    /**
     * 血型
     * A
     * AB
     * B
     * O
     */
    String	bloodType;
    /**
     * 孩子姓名（最大长度30位）
     */
    String	childName;
    /**
     * 工作地点ID（最大长度36位）
     */
    String	companySiteId;
    /**
     * 工作地点名称（最大长度100位）
     */
    String	companySiteName;
    /**
     * 当前合同起始日
     */
    String	contractBeginDate;
    /**
     * 当前合同结束日
     */
    String	contractEndDate;
    /**
     * 合同类型
     * LABOR_CONTRACT：劳动合同
     * SERVICE_CONTRACT：劳务合同
     * TRAINING_CONTRACT：实习合同
     * NO_LIMIT_CONTRACT：无固定期限劳动合同
     * OTHER_CONTRACT：其他合同
     */
    String	contractType;
    /**
     * 劳动合同甲方ID（最大长度36位）
     */
    String	corporationId;
    /**
     * 劳动合同甲方（最大长度128位）
     */
    String	corporationName;
    /**
     * 国籍（最大长度40位）
     */
    String	country;
    /**
     * 员工删除时间
     */
    String	deleteDate;
    /**
     * 部门ID
     */
    Long	departmentId;
    /**
     * 部门名称
     */
    String	departmentName;
    /**
     * 部门全路径
     */
    String	fullDepartmentName;
    /**
     * 邮箱
     */
    String	email;
    /**
     * 紧急联系人号码（最大长度30位）
     */
    String	emergencyContactMobile;
    /**
     * 紧急联系人姓名（最大长度30位）
     */
    String	emergencyContactName;
    /**
     * 入职日期
     */
    String	enrollInDate;
    /**
     * 入职本公司的工龄
     */
    Double	enrollWorkYears;
    /**
     * 名（最大长度20位）
     */
    String	firstName;
    /**
     * 最高学历
     * DOCTOR：博士
     * MASTER：硕士/MBA/EMBA
     * UNDER_GRADUATE：本科
     * JUNIOR：大专/高职
     * SENIOR_MIDDLE：高中/中专/中技
     * JUNIOR_MIDDLE：初中
     * PRIMARY_SCHOOL：小学
     * OTHER：其他
     */
    String	highestEducation;
    /**
     * 户口所在地（最大长度100位）
     */
    String	hukouAddress;
    /**
     * 户口所在地市编码（最大长度6位）
     */
    String	hukouCityCode;
    /**
     * 户口所在地省编码（最大长度6位）
     */
    String	hukouProvinceCode;
    /**
     * 户口类型
     * TOWN：城镇
     * NON_TOWN：非城镇
     */
    String	hukouType;
    /**
     * 证件号
     */
    String	idCardNo;
    /**
     * 证件类型
     * IDENTITY_CARD：居民身份证
     * CHINA_PASSPORT：中国护照
     * FOREIGN_PASSPORT：外国护照
     * MTP：台湾居民来往大陆通行证
     * MTPFHKAMR：港澳居民来往内地通行证
     * OTHER：其他
     */
    String	idCardType;
    /**
     * 进入公司之前的历史工龄
     */
    Double	initialWorkYears;
    /**
     * 是否被删除
     */
    Boolean	isDeleted;
    /**
     * 是否处于试用期
     */
    Boolean	isProbation;
    /**
     * 职务ID（最大长度36位）
     */
    String	jobTitleId;
    /**
     * 	职务名称（最大长度100位）
     */
    String	jobTitleName;
    /**
     * 姓（最大长度20位）
     */
    String	lastName;
    /**
     * 最后工作日
     */
    String	lastWorkDay;
    /**
     * 	离职日期
     */
    String	leaveDate;
    /**
     * 证件姓名（最大长度40位）
     */
    String	legalName;
    /**
     * 现在居住地址（最大长度100位）
     */
    String	livingAddress;
    /**
     * 现在居住地市编码（最大长度6位）
     */
    String	livingCityCode;
    /**
     * 现在居住省编码（最大长度6位）
     */
    String	livingProvinceCode;
    /**
     * 农历生日天（最大长度8位）
     */
    String	lunarBirthdayDay;
    /**
     * 农历生日月（闰月用r加月份表示，最大长度8位）
     */
    String	lunarBirthdayMonth;
    /**
     * 农历生日年（最大长度8位）
     */
    String	lunarBirthdayYear;
    /**
     * 婚姻状况
     * UNMARRIED：未婚
     * MARRIED：已婚
     */
    String	marryStatus;
    /**
     * 手机号码
     */
    String	mobileNo;
    /**
     * 民族（最大长度40位）
     */
    String	nationality;
    /**
     * 籍贯（最大长度100位）
     */
    String	nativePlace;
    /**
     * 籍贯市编码（最大长度6位）
     */
    String	nativePlaceCityCode;
    /**
     * 籍贯省编码（最大长度6位）
     */
    String	nativePlaceProvinceCode;
    /**
     * 下一阳历生日
     */
    String	nextSolarBirthday;
    /**
     * 花名（最大长度30位）
     */
    String	nickName;
    /**
     * 政治面貌
     * DEMOCRATICPARTY：民主党派
     * MASSESS：群众
     * MEMBER：团员
     * OTHER：其他
     * PARTYMEMBER：党员
     * PRO_PARTYMEMBER：预备党员
     */
    String	politicalStatus;
    /**
     * 职位ID（最大长度36位）
     */
    String	positionId;
    /**
     * 职位名称
     */
    String	positionName;
    /**
     * 职级ID（最大长度36位）
     */
    String	positionLevelId;
    /**
     * 	职级名称（最大长度30位）
     */
    String	positionLevelName;
    /**
     * 转正日期
     */
    String	positiveDate;
    /**
     * 试用期结束日
     */
    String	probationEndDate;
    /**
     * 试用期月数
     */
    Integer	probationLength;
    /**
     * 试用期状态
     * PASSED：已转正
     * ONPROBATION：试用期
     */
    String	probationStatus;
    /**
     * QQ号（最大长度40位）
     */
    String	qqNo;
    /**
     * 离职原因（最大长度1200位）
     */
    String	quitReason;
    /**
     * 离职原因类型
     * COMPENSATION：薪资福利
     * WORK_ENVIRONMENT：工作环境
     * WORK_TIME：工作时间
     * HEALTHY：健康原因
     * PROMOTION：晋升机会
     * FAMILY：家庭原因
     * TO_MUCH_OVERTIME：加班太多
     * SELF_STARTUP：自行创业
     * LACK_OF_TRAINING：缺少培训机会
     * PURSUE_NEW_CAREER：追求新的职业发展
     * PASSIVE：被动离职
     * PROBATION：试用期内离职
     * RETIRE：退休
     * OTHER：其他
     */
    String	quitReasonType;
    /**
     * 离职要提醒的人（text类型）
     */
    String	quitRemindStaff;
    /**
     * 离职类型
     * ACTIVE：主动离职
     * PASSIVE：被动离职
     * RETIRE：其他
     */
    String	quitType;
    /**
     * 复职次数
     */
    Integer	reinstateNumber;
    /**
     * 性别
     * MALE：男
     * FEMALE：女
     */
    String sex;
    /**
     * 配偶姓名
     */
    String	spouseName;
    /**
     * 姓名
     */
    String	staffName;
    /**
     * 英文姓名
     */
    String	englishName;
    /**
     * 首次参加工作日期
     */
    String	startWorkingDate;
    /**
     * 工号
     */
    String	staffNo;
    /**
     * 员工来源
     */
    String	staffOrigin;
    /**
     * 花名册备注
     */
    String	staffRemark;
    /**
     * 员工状态
     * IN_SERVICE：在职
     * QUIT：离职
     */
    String staffStatus;
    /**
     * 员工类型
     * FULLTIME：全职
     * PARTTIME：兼职
     * INTERSHIP：实习
     * EXPATRIATE：外派
     * TEMPORARY：临时工
     * REHIRE_RETIREMENT：退休返聘
     */
    String	staffType;
    /**
     * 微信号（最大长度100位）
     */
    String	weChatNo;
    /**
     * 工作邮箱（最大长度128位）
     */
    String	workEmail;
    /**
     * 工作手机号（最大长度64位）
     */
    String	workPhone;
    /**
     * 联系地址（最大长度255位）
     */
    String	workPlace;
    /**
     * 直属领导(行政、业务、财务)
     */
    List<SuperiorsInfo> superiorsInfo;
    /**
     * 员工信息自定义字段
     */
    Map<String, Object> flexAttributes;

    @Data
    public static class SuperiorsInfo {
        String staffId;
        String superiorId;
        String reportToType;
    }
}
