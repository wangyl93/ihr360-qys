package com.ihr360.applet.customization.qys.repository.po;

import com.ihr360.applet.customization.qys.model.ihr.response.IhrStaffsBasic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_staff_basic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffBasic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(40) COMMENT '员工ID'")
    private String staffId;

    @Column(columnDefinition = "varchar(40) COMMENT '工号'")
    private String staffNo;

    @Column(columnDefinition = "varchar(100) COMMENT '姓名'")
    private String staffName;

    @Column(columnDefinition = "varchar(20) COMMENT '手机号'")
    private String mobileNo;

    @Column(columnDefinition = "varchar(20) COMMENT '员工状态'")
    private String staffStatus;

    @Column(columnDefinition = "varchar(20) COMMENT '证件类型'")
    private String idCardType;

    @Column(columnDefinition = "varchar(40) COMMENT '证件号码'")
    private String idCardNo;

    @Column(columnDefinition = "bigint COMMENT '部门id'")
    private Long departmentId;

    @Column(columnDefinition = "varchar(100) COMMENT '部门'")
    private String departmentName;

    @Column(columnDefinition = "varchar(40) COMMENT '职位ID'")
    private String positionId;

    @Column(columnDefinition = "varchar(100) COMMENT '职位名称'")
    private String positionName;

    @Column(columnDefinition = "varchar(10) COMMENT '性别'")
    private String sex;

    @Column(columnDefinition = "varchar(40) COMMENT '员工类型'")
    private String staffType;

    @Column(columnDefinition = "varchar(40) COMMENT '婚姻状态'")
    private String marryStatus;

    @Column(columnDefinition = "varchar(50) COMMENT '最高学历'")
    private String highestEducation;

    @Column(columnDefinition = "varchar(255) COMMENT '联系地址'")
    private String workPlace;

    @Column(columnDefinition = "varchar(20) COMMENT '出生日期，格式：yyyy-MM-dd'")
    private String birthday;

    @Column(columnDefinition = "varchar(20) COMMENT '合同开始日期，格式：yyyy-MM-dd'")
    private String contractBeginDate;

    @Column(columnDefinition = "varchar(20) COMMENT '合同结束日期，格式：yyyy-MM-dd'")
    private String contractEndDate;

    @Column(columnDefinition = "varchar(20) COMMENT '入职日期，格式：yyyy-MM-dd'")
    private String enrollInDate;

    @Column(columnDefinition = "varchar(20) COMMENT '离职日期，格式：yyyy-MM-dd'")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String leaveDate;

    @Column(columnDefinition = "varchar(20) COMMENT '试用期结束日期，格式：yyyy-MM-dd'")
    private String probationEndDate;

    @Column(columnDefinition = "varchar(20) COMMENT '创建日期，格式：yyyy-MM-dd HH:mm:ss'")
    private String createdDate;

    @Column(columnDefinition = "varchar(20) COMMENT '最后修改日期，格式：yyyy-MM-dd HH:mm:ss'")
    private String lastUpdateDate;

    @Column(columnDefinition = "varchar(20) COMMENT '用户编号'")
    private String employeeNo;

    public void setStaffBasic(IhrStaffsBasic ihrStaffsBasic){
        this.staffId = ihrStaffsBasic.getId();
        this.staffNo = ihrStaffsBasic.getStaffNo();
        this.staffName = ihrStaffsBasic.getStaffName();
        this.mobileNo = ihrStaffsBasic.getMobileNo();
        this.staffStatus = ihrStaffsBasic.getStaffStatus();
        this.idCardType = ihrStaffsBasic.getIdCardType();
        this.idCardNo = ihrStaffsBasic.getIdCardNo();
        this.departmentId = ihrStaffsBasic.getDepartmentId();
        this.departmentName = ihrStaffsBasic.getDepartmentName();
        this.positionId = ihrStaffsBasic.getPositionId();
        this.positionName = ihrStaffsBasic.getPositionName();
        this.sex = ihrStaffsBasic.getSex();
        this.staffType = ihrStaffsBasic.getStaffType();
        this.marryStatus = ihrStaffsBasic.getMarryStatus();
        this.highestEducation = ihrStaffsBasic.getHighestEducation();
        this.workPlace = ihrStaffsBasic.getWorkPlace();
        this.birthday = ihrStaffsBasic.getBirthday();
        this.contractBeginDate = ihrStaffsBasic.getContractBeginDate();
        this.contractEndDate = ihrStaffsBasic.getContractEndDate();
        this.enrollInDate = ihrStaffsBasic.getEnrollInDate();
        this.leaveDate = ihrStaffsBasic.getLeaveDate();
        this.probationEndDate = ihrStaffsBasic.getProbationEndDate();
        this.createdDate = ihrStaffsBasic.getCreatedDate();
        this.lastUpdateDate = ihrStaffsBasic.getLastUpdateDate();

    }
}
