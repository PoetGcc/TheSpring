package com.gcc.thespring.controller;

import com.gcc.thespring.IRouter;
import com.gcc.thespring.bean.Student;
import com.gcc.thespring.utils.StringUtils;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: gcc
 * @date: 2023/5/26 17:40
 * 测试
 */
@RestController
public class StudentController {

    @GetMapping(IRouter.STUDENT_INFO)
    public String studentInfo(@RequestParam(value = "age") int age,
                              @RequestParam(value = "name", defaultValue = "") String name) {
        Student student = new Student();
        student.setAge(age);
        student.setName(name);
        String json = new Gson().toJson(student);
        if (StringUtils.isEmpty(json)) {
            json = StringUtils.errorJson();
        }
        return json;
    }
}
