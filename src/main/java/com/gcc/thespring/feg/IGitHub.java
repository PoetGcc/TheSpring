package com.gcc.thespring.feg;

import com.gcc.thespring.bean.git.Contributor;
import com.gcc.thespring.bean.git.Issue;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface IGitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    String contributorsJson(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);
}
