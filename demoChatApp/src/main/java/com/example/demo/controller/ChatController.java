package com.example.demo.controller;

import com.example.demo.Entity.ChatMessage;
import com.example.demo.Entity.ChatRoom;
import com.example.demo.Repo.ChatMessageRepository;
import com.example.demo.Repo.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageRepository messageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
       return chatMessage;
    }


//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
//        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
//        return chatMessage;
//    }
    @MessageMapping("/chat/createGroupChat")
    public String createGroupChat(@Payload ChatMessage message) {

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setType("group");
        chatRoom.setName(message.getContent());
        chatRoom = chatRoomRepository.save(chatRoom);
        return chatRoom.getRoomId().toString();
    }
    @MessageMapping("/chat/createPrivateChat")
    public String createPrivateChat(@Payload ChatMessage message) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setType("private");
        chatRoom = chatRoomRepository.save(chatRoom);
        return chatRoom.getRoomId().toString();
    }
}
