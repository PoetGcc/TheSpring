package com.gcc.thespring.feg;

import com.gcc.thespring.bean.git.Contributor;
import feign.Feign;
import feign.codec.StringDecoder;
import feign.okhttp.OkHttpClient;

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
                .target(IGitHub.class, BASE_URL);
    }

    public void getContributors() {
        if (mIGithub == null) {
            return;
        }

        List<Contributor> contributors = mIGithub.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.getLogin() + " (" + contributor.getContributions() + ")");
        }
    }

    public String getContributorsJson(String owner, String repo) {
        if (mIGithub == null) {
            return "";
        }

        String json = mIGithub.contributorsJson(owner, repo);
        return json;
    }
}
