package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    DepartmentRepository departmentRepository ;



    @Override

    public void run(String... strings) throws Exception {
        Department department;

        department= new Department("Accounting",  "Marvin Gaye");
        departmentRepository.save(department);

        department= new Department("HR",  "Russell Baker");
        departmentRepository.save(department);
        department= new Department("Management",  "Han Shan");
        departmentRepository.save(department);



    }


}
