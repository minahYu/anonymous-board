package com.sparta.anonymousboard.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequestDto {
    private final String title;
    private final String writer;
    private String password;
    private final String contents;
}
