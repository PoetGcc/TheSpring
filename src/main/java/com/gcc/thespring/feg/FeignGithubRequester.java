package com.gcc.thespring.feg;

import feign.Feign;
import feign.codec.StringDecoder;
import feign.okhttp.OkHttpClient;

import java.util.List;

public class FeignGithubRequester {

    private final IGitHub mIGithub;


    public static FeignGithubRequester getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final FeignGithubRequester INSTANCE = new FeignGithubRequester();
    }

    private FeignGithubRequester() {
        mIGithub = Feign.builder()
                .client(new OkHttpClient())
                // .decoder(new GsonDecoder())
                .decoder(new StringDecoder())
                .target(IGitHub.class, "https://api.github.com");
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

    public String getContributorsJson() {
        if (mIGithub == null) {
            return "";
        }

        String json = mIGithub.contributorsJson("OpenFeign", "feign");
        return json;
    }
}
