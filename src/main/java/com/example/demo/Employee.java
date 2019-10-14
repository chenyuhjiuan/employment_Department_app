package com.example.demo;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    @Column(name = "employee_name", length = 250)
    private String name;
    @NotBlank
    @Column(name = "employee_obtitle", length = 250)
    private String jobtitle;
    private String headshot;
    @ManyToOne(fetch = FetchType.EAGER)
    /*@ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)*/
    @JoinColumn(name="department_id")
    private Department department;

    public Employee() {
    }

    public Employee(String name, String jobtitle) {
        this.name = name;
        this.jobtitle = jobtitle;
    }

    public Employee(String name, String jobtitle, Department department) {
        this.name = name;
        this.jobtitle = jobtitle;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
