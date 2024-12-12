package org.examination.iasf.controller;

import lombok.RequiredArgsConstructor;
import org.examination.iasf.models.entity.Employee;
import org.examination.iasf.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/12/12
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping
    public String saveEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        Optional<Employee> employee1 = employeeService.getEmployeeByName(employee.getName());

        if (employee1.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error while creating User. Unable to create. A User with name " + employee1.get().getName() + " already exists");
        } else {
            employeeService.saveEmployee(employee);
            redirectAttributes.addFlashAttribute("successMessage", "User created successfully");
        }

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee-form";
        }
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/search")
    public String searchEmployees(@RequestParam String keyword, Model model) {
        List<Employee> employees = employeeService.searchEmployees(keyword);
        model.addAttribute("employees", employees);
        return "fragments/employeeTable :: employeeRows";
    }
}
