package com.sparta.anonymousboard.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private Long id;

    public NotFoundException(Long id) {
        this.id = id;
    }
}
