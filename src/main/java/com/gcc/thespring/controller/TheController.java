package com.gcc.thespring.controller;

import com.gcc.thespring.IRouter;
import com.gcc.thespring.bean.Student;
import com.gcc.thespring.feg.FeignGithubRequester;
import com.gcc.thespring.utils.StringUtils;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

    @GetMapping(IRouter.CONTRIBUTORS)
    public String contributors() {
        String json = FeignGithubRequester.getInstance().getContributorsJson();
        if (StringUtils.isEmpty(json)) {
            json = "{}";
        }
        return json;
    }

    @GetMapping(IRouter.STUDENT_INFO)
    public String studentInfo(@RequestParam(value = "age") int age,
                              @RequestParam(value = "name", defaultValue = "") String name) {
        Student student = new Student();
        student.setAge(age);
        student.setName(name);
        String json = new Gson().toJson(student);
        if (StringUtils.isEmpty(json)) {
            json = "{}";
        }
        return json;
    }

}
