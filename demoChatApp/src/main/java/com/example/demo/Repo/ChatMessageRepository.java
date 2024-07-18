package com.example.demo.Repo;


import com.example.demo.Entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
