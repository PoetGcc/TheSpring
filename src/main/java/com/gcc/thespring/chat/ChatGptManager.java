package com.gcc.thespring.chat;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.models.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: gcc
 * @date: 2023/6/27 17:34
 */
public class ChatGptManager {

    public static ChatGptManager getInstance() {
        return ChatGptManager.Holder.INSTANCE;
    }

    private static class Holder {
        private static final ChatGptManager INSTANCE = new ChatGptManager();
    }


    private OpenAiClient mClient;

    private ChatGptManager() {
    }


    public List<Model> getModelList() {
        List<Model> models = mClient.models();
        if (models != null) {
            return new ArrayList<>(models);
        }
        return new ArrayList<>();
    }

    private void ensureClient() {
        if (mClient == null) {
            mClient = OpenAiClient.builder()
                    .apiKey(Arrays.asList("sk-***********", "sk-*********"))
                    // 自定义key的获取策略：默认KeyRandomStrategy
                    // .keyStrategy(new KeyRandomStrategy())
                    // .okHttpClient(okHttpClient)
                    // 自己做了代理就传代理地址，没有可不不传（(关注公众号回复：openai ，获取免费的测试代理地址)）
                    // .apiHost("https://自己代理的服务器地址/")
                    .build();
        }
    }
}
