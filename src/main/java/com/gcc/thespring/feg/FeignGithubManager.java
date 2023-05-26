package com.gcc.thespring.feg;

import com.gcc.thespring.bean.git.Contributor;
import feign.Feign;
import feign.Logger;
import feign.codec.StringDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.List;

/**
 * GitHub 信息请求
 */
public class FeignGithubManager {

    private static final String BASE_URL = "https://api.github.com";

    private final IGitHub mIGithub;


    public static FeignGithubManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final FeignGithubManager INSTANCE = new FeignGithubManager();
    }

    private FeignGithubManager() {
        mIGithub = Feign.builder()
                .client(new OkHttpClient())
                // .decoder(new GsonDecoder())
                .decoder(new StringDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(IGitHub.class, BASE_URL);
    }

    @Deprecated
    public void getContributors() {
        if (mIGithub == null) {
            return;
        }

        List<Contributor> contributors = mIGithub.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.getLogin() + " (" + contributor.getContributions() + ")");
        }
    }

    /**
     * 贡献者信息
     */
    public String getContributorsJson(String owner, String repo) {
        if (mIGithub == null) {
            return "";
        }

        return mIGithub.contributorsJson(owner, repo);
    }

    /**
     * 分支
     */
    public String getBranchesJson(String owner, String repo) {
        if (mIGithub == null) {
            return "";
        }

        return mIGithub.branchesJson(owner, repo);
    }
}
