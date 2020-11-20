package com.example.demo.contorller;


import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.example.demo.untils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    private Environment env;

    private String intPort;

    @RequestMapping("/selectByPrimaryKey")
    public JsonResult selectByPrimaryKey(Integer stuId) {
        Student student = studentService.selectById(stuId);
        JsonResult student1 = JsonResult.ok().put("student", student);
        return student1;
    }

}

