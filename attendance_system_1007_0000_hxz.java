// 代码生成时间: 2025-10-07 00:00:28
package com.example.attendance;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import java.sql.Timestamp;
import java.util.List;

// 定义实体类
class AttendanceRecord {
    private int id;
    private String employeeId;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private boolean isPunched;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Timestamp checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public boolean isPunched() {
        return isPunched;
    }

    public void setPunched(boolean punched) {
        isPunched = punched;
    }
}

// 定义Mapper接口
@Mapper
interface AttendanceMapper {
    @Select("SELECT * FROM attendance_record WHERE employeeId = #{employeeId}")
    List<AttendanceRecord> findRecordsByEmployeeId(@Param("employeeId") String employeeId);

    @Insert("INSERT INTO attendance_record(employeeId, checkInTime, checkOutTime, isPunched) VALUES(#{employeeId}, #{checkInTime}, #{checkOutTime}, #{isPunched})")
    int insertRecord(AttendanceRecord record);

    @Update("UPDATE attendance_record SET checkOutTime = #{checkOutTime} WHERE id = #{id} AND isPunched = false")
    int markAsPunched(AttendanceRecord record);
}

// 服务类
public class AttendanceService {
    private AttendanceMapper attendanceMapper;

    public AttendanceService(AttendanceMapper attendanceMapper) {
        this.attendanceMapper = attendanceMapper;
    }

    // 打卡方法
    public void punchCard(String employeeId, Timestamp checkInTime, boolean isCheckIn) {
        try {
            AttendanceRecord record = new AttendanceRecord();
            record.setEmployeeId(employeeId);
            record.setCheckInTime(checkInTime);
            record.setPunched(isCheckIn);

            if (isCheckIn) {
                attendanceMapper.insertRecord(record);
            } else {
                record.setCheckOutTime(new Timestamp(System.currentTimeMillis()));
                attendanceMapper.markAsPunched(record);
            }
        } catch (Exception e) {
            // 错误处理
            System.out.println("Error during punching card: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 获取员工打卡记录
    public List<AttendanceRecord> getEmployeeRecords(String employeeId) {
        return attendanceMapper.findRecordsByEmployeeId(employeeId);
    }
}