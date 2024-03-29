package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CloudinaryConfig cloudc;


    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());

        return "index";
    }



    @GetMapping("/adddepartment")

    public String departmentForm(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }


    @PostMapping("/process")
    public String processDepartmentForm(@RequestParam(value = "file", required = true) MultipartFile file,@Valid Department department, BindingResult result){

        if (result.hasErrors()){
            return "redirect:/departmentform"; }

        if (file.isEmpty()){
            return "redirect:/adddepartment";
        }
        try {
            Map uploadResult =cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            department.setHeadshot(uploadResult.get("url").toString());
            departmentRepository.save(department);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/adddepartment";
        }
        departmentRepository.save(department);

        return "redirect:/departmentlist";
    }


    @RequestMapping("/departmentlist")

    public String departmentList(Model model){

        model.addAttribute("departments", departmentRepository.findAll());

        return "departmentlist";
    }

   /* @RequestMapping("/departmentform")
    public String departmentForm(){
        return "departmentform";
    }*/
    @GetMapping("/addemployee")

    public String employeeForm(Model model){

        model.addAttribute("departments", departmentRepository.findAll());

        model.addAttribute("employee", new Employee());

        return "employeeform";
    }


    @PostMapping("/processemployee")

<<<<<<< HEAD
    public String processEmployeeForm(@Valid Employee employee,  BindingResult result,@RequestParam("file") MultipartFile file, Model m){

        if (result.hasErrors()){
            //m.addAttribute(employeeRepository.findAll());
=======
    public String processEmployeeForm(@RequestParam(value = "file") MultipartFile file, @Valid Employee employee,BindingResult result,Model m){//, required = true

        if (result.hasErrors()){
//        m.addAttribute(employee);
//            return "redirect:/employeeform";
            m.addAttribute("departments", departmentRepository.findAll());
>>>>>>> 43be9a8064e29fac6a53a2dfdb659a1fdedc327e
            return "employeeform";
        }
       if (file.isEmpty()){
//            return "redirect:/addemployee";
//           return "redirect:/employeeform";
           return "employeeform";
        }
        try {
            Map uploadResult =cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            employee.setHeadshot(uploadResult.get("url").toString());
            employeeRepository.save(employee);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/employeeform";
        }

        m.addAttribute("department", departmentRepository.findAll());
        employeeRepository.save(employee);

        return "redirect:/employeelist";
    }


    @RequestMapping("/employeelist")

    public String employeeList(Model model){

        model.addAttribute("employees", employeeRepository.findAll());

        return "employeelist";
    }


    @RequestMapping("/detail_department/{id}")

    public String showDepartment(@PathVariable("id") long id, Model model){

        model.addAttribute("department", departmentRepository.findById(id).get());
        return "showdepartment";
    }



    @RequestMapping("/update_department/{id}")

    public String updateDepartment(@PathVariable("id") long id, Model model){



        model.addAttribute("department", departmentRepository.findById(id).get());
        //model.addAttribute("employees", employeeRepository.findAll());
        /*fetch department record from database
        Department department = departmentRepository.findById(id).get();
        //create new employee
        Employee employee=new Employee();
        //create new employees list;
        List employees = new ArrayList();
        //add employee to the list of employees
        employees.add(employee);
        //clear existing employees list so that they are removed from the database
        department.getEmployees().clear();
        //add the new employees list created above to the existing list
        department.getEmployees().addAll(employees);*/


        return "departmentform";
    }


    @RequestMapping("/delete_department/{id}")

    public String delDepartment(@PathVariable("id") long id){

        departmentRepository.deleteById(id);
        return "redirect:/";
    }


    @RequestMapping("/detail_employee/{id}")

    public String showEmployee(@PathVariable("id") long id, Model model){

        model.addAttribute("employee", employeeRepository.findById(id).get());

        return "showemployee";

    }


    @RequestMapping("/update_employee/{id}")

    public String updateEmployee(@PathVariable("id") long id, Model model, Employee employee){

        model.addAttribute("employee", employeeRepository.findById(id).get());
        //employeeRepository.save(employee);
        model.addAttribute("departments",departmentRepository.findAll());

        return "employeeform";

    }



    @RequestMapping("/delete_employee/{id}")

    public String delEmployee(@PathVariable("id") long id){

        employeeRepository.deleteById(id);

        return "redirect:/";

    }
}
