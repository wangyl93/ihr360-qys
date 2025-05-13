package com.ihr360.applet.customization.qys.repository;

import com.ihr360.applet.customization.qys.repository.po.StaffBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @program:  ihr360-applet
 * @description:
 * @author:  Sam.wang
 **/
public interface StaffBasicDao extends JpaRepository<StaffBasic, Long> {
    // 使用 JPQL 查询

    List<StaffBasic> findByStaffId(@Param("staffId") String staffId);

    @Query("SELECT cr FROM StaffBasic cr WHERE cr.staffName = :accountName AND cr.mobileNo = :mobile")
    List<StaffBasic> findByStaffNameAndMobile(@Param("accountName") String accountName, @Param("mobile") String mobile);

    @Query("SELECT cr FROM StaffBasic cr WHERE cr.staffName = :accountName")
    List<StaffBasic> findByStaffName(@Param("accountName") String accountName);
}