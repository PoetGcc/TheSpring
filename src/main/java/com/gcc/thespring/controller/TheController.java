package com.gcc.thespring.controller;

import com.gcc.thespring.IRouter;
import com.gcc.thespring.feg.FeignGithubManager;
import com.gcc.thespring.utils.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

    /** 仓库贡献人 */
    @GetMapping(IRouter.CONTRIBUTORS)
    public String contributors(@RequestParam(value = "owner", defaultValue = "OpenFeign") String owner,
                               @RequestParam(value = "repo", defaultValue = "feign") String repo) {
        String json = FeignGithubManager.getInstance().getContributorsJson(owner, repo);
        if (StringUtils.isEmpty(json)) {
            json = StringUtils.errorJson();
        }
        return json;
    }

    /** 仓库分支 */
    @GetMapping(IRouter.BRANCHES)
    public String branchesJson(@RequestParam(value = "owner", defaultValue = "OpenFeign") String owner,
                               @RequestParam(value = "repo", defaultValue = "feign") String repo) {
        String json = FeignGithubManager.getInstance().getBranchesJson(owner, repo);
        if (StringUtils.isEmpty(json)) {
            json = StringUtils.errorJson();
        }
        return json;
    }
}
