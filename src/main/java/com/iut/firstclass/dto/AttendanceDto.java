package com.iut.firstclass.dto;

import com.iut.firstclass.models.enums.AttendanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {
    private Long studentId;
    private LocalDate date;
    private AttendanceType attendanceType;
}
