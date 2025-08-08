package com.iut.firstclass.controllers;

import com.iut.firstclass.dto.AttendanceDto;
import com.iut.firstclass.models.entity.Attendance;
import com.iut.firstclass.models.entity.Student;
import com.iut.firstclass.models.enums.AttendanceType;
import com.iut.firstclass.models.enums.Dept;
import com.iut.firstclass.models.enums.Programs;
import com.iut.firstclass.services.StudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent() {
        return studentService.findAll();
    }

    @PostMapping("/addNewStudent")
    public Student addNewStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/getStudentID")
    public Student getStudentID(@RequestParam("id") Long id) {
        return studentService.findById(id);
    }
    @GetMapping("/getAllAttendanceByDate")
    public List<AttendanceDto> getStudentAttendanceByDates(
            @RequestParam("studentId") Long studentId,
            @RequestParam("startDate") LocalDate startDate,
            @RequestParam("endDate") LocalDate endDate) {
        return studentService.findAllAttendanceByStudentBetweenDates(
                studentId,
                startDate,
                endDate
        );
    }

    /**
     * This method seeds the database with random students and attendance records.
     * It will be executed automatically when the application starts.
     */
    @PostConstruct
    public void seedData() {
        // Check if data already exists to prevent duplicate seeding
        if (!studentService.findAll().isEmpty()) {
            return; // Skip seeding if data already exists
        }

        // Create random students
        List<Student> students = createRandomStudents(10);
        List<Student> savedStudents = new ArrayList<>();

        // Save students
        for (Student student : students) {
            savedStudents.add(studentService.save(student));
        }

        // Create random attendance records for each student
        createRandomAttendance(savedStudents);
    }

    /**
     * Optional endpoint to manually trigger the database seeding process
     */
    @PostMapping("/seedData")
    public String manualSeedData(@RequestParam(defaultValue = "10") int studentCount) {
        List<Student> students = createRandomStudents(studentCount);
        List<Student> savedStudents = new ArrayList<>();

        for (Student student : students) {
            savedStudents.add(studentService.save(student));
        }

        createRandomAttendance(savedStudents);

        return "Successfully seeded " + studentCount + " students with attendance records";
    }

    /**
     * Creates a list of random students
     */
    private List<Student> createRandomStudents(int count) {
        List<Student> students = new ArrayList<>();
        String[] names = {"Alex", "Blake", "Casey", "Dana", "Emery", "Finley", "Gray", "Harper",
                          "Indigo", "Jordan", "Kendall", "Logan", "Morgan", "Noor", "Oakley",
                          "Parker", "Quinn", "Riley", "Sage", "Taylor"};

        Random random = new Random();
        Dept[] departments = Dept.values();
        Programs[] programs = Programs.values();

        for (int i = 0; i < count; i++) {
            Student student = new Student();
            student.setName(names[random.nextInt(names.length)] + " " +
                           names[random.nextInt(names.length)]);
            student.setDept(departments[random.nextInt(departments.length)]);
            student.setProgram(programs[random.nextInt(programs.length)]);
            students.add(student);
        }

        return students;
    }

    /**
     * Creates random attendance records for a list of students
     */
    private void createRandomAttendance(List<Student> students) {
        Random random = new Random();
        AttendanceType[] attendanceTypes = AttendanceType.values();

        // Generate attendance for the last 30 days
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        for (Student student : students) {
            // For each day in the range
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                // Skip weekends
                if (date.getDayOfWeek().getValue() > 5) {
                    continue;
                }

                Attendance attendance = new Attendance();
                attendance.setStudent(student);
                attendance.setDate(date);
                attendance.setAttendanceType(attendanceTypes[random.nextInt(attendanceTypes.length)]);

                // Save the attendance record
                studentService.saveAttendance(attendance);
            }
        }
    }
}
