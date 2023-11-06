package com.sparta.anonymousboard.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String writer;
    private String password;
    private String contents;
}
