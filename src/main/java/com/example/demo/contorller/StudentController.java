package com.example.demo.contorller;


import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.example.demo.untils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    private Environment env;

    @RequestMapping("/selectByPrimaryKey")
    public JsonResult selectByPrimaryKey(Integer stuId) {
        Student student = studentService.selectById(stuId);
        JsonResult student1 = JsonResult.ok().put("student", student);
        return student1;
    }

    @RequestMapping("/listData")
    public String listData(Model model) {
        List<Student> students = studentService.listData();
        model.addAttribute("students", students);
        return "student/studentInfo";
    }

}