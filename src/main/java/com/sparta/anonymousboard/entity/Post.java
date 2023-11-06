package com.sparta.anonymousboard.entity;

import com.sparta.anonymousboard.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }
}
