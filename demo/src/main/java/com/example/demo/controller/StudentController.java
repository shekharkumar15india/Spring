package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String listStudents(Model model) {
        model.addAttribute("students", repo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add_student";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        repo.save(student);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        repo.deleteById(id);

        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, Model model) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "update_student";
    
        
    
    }
 @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @ModelAttribute Student student) {
        student.setId(id);
        repo.save(student);
        return "redirect:/";
    }
}
