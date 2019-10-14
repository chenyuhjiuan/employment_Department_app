package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    @Column(name = "department_depname", length = 250)
    private String depname;
    @NotBlank
    @Column(name = "department_headname", length = 250)
    private String headname;

    private String headshot;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    public Set<Employee> employees;

    public Department() {
    }

    public Department(String depname, String headname) {
        this.depname = depname;
        this.headname = headname;
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

   /* public  void addEmployee(Employee employee){
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        this.employees.remove((employee));
    }*/


}
