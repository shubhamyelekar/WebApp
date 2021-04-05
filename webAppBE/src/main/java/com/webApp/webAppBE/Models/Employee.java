package com.webApp.webAppBE.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "Name")
    private String employeeName;

    @Column(name = "Age")
    private int employeeAge;
}
