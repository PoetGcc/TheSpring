package com.gcc.thespring.controller;

import com.gcc.thespring.IRouter;
import com.gcc.thespring.bean.Student;
import com.gcc.thespring.feg.FeignGithubManager;
import com.gcc.thespring.utils.StringUtils;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

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


    /** 仓库贡献人 */
    @GetMapping(IRouter.CONTRIBUTORS)
    public String contributors(@RequestParam(value = "owner", defaultValue = "OpenFeign") String owner,
                               @RequestParam(value = "repo", defaultValue = "feign") String repo) {
        String json = FeignGithubManager.getInstance().getContributorsJson(owner, repo);
        if (StringUtils.isEmpty(json)) {
            json = "{}";
        }
        return json;
    }

    /** 仓库分支 */
    @GetMapping(IRouter.BRANCHES)
    public String branchesJson(@RequestParam(value = "owner", defaultValue = "OpenFeign") String owner,
                               @RequestParam(value = "repo", defaultValue = "feign") String repo) {
        String json = FeignGithubManager.getInstance().getBranchesJson(owner, repo);
        if (StringUtils.isEmpty(json)) {
            json = "{}";
        }
        return json;
    }
}
