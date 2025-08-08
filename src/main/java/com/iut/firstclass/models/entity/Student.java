package com.iut.firstclass.models.entity;
import com.iut.firstclass.models.enums.Dept;
import com.iut.firstclass.models.enums.Programs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Dept dept;
    @Enumerated(EnumType.STRING)
    private Programs program;

//    public Student() {
//
//    }
}
