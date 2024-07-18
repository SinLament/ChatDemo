package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password; // Lưu ý: Luôn băm (hash) mật khẩu trước khi lưu trữ

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> messages;

    @ManyToMany(mappedBy = "members")
    private List<ChatRoom> chatRooms;

    // ... Constructor, getter, setter
}