package com.iut.firstclass.repositories;

import com.iut.firstclass.dto.AttendanceDto;
import com.iut.firstclass.models.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT new com.iut.firstclass.dto.AttendanceDto(a.student.id, a.date, a.attendanceType) " +
            "FROM Attendance a " +
            "WHERE a.student.id = :studentId " +
            "AND a.date BETWEEN :startDate AND :endDate")
    List<AttendanceDto> findAllAttendanceByStudentBetweenDates(
            @Param("studentId") Long studentId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}