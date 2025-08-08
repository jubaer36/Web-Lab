package com.iut.firstclass.services;


import com.iut.firstclass.dto.AttendanceDto;
import com.iut.firstclass.models.entity.Attendance;
import com.iut.firstclass.models.entity.Student;
import com.iut.firstclass.repositories.AttendanceRepository;
import com.iut.firstclass.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<AttendanceDto> findAllAttendanceByStudentBetweenDates(Long studentId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findAllAttendanceByStudentBetweenDates(studentId, startDate, endDate);
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
}
