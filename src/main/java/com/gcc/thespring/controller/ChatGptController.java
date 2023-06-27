package com.gcc.thespring.controller;

import com.gcc.thespring.IRouter;
import com.gcc.thespring.chat.ChatGptManager;
import com.unfbx.chatgpt.entity.models.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: gcc
 * @date: 2023/6/27 17:23
 * Chat-Gpt Java
 */
@RestController
public class ChatGptController {


    private ChatGptController() {

    }

    @GetMapping(IRouter.CHAT_MODELS)
    public List<Model> getChatModelsList() {
      return ChatGptManager.getInstance().getModelList();
    }

}
