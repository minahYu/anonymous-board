package com.sparta.anonymousboard.controller;

import com.sparta.anonymousboard.dto.PostRequestDto;
import com.sparta.anonymousboard.dto.PostResponseDto;
import com.sparta.anonymousboard.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    @ResponseBody
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) { // 게시물 작성
        return postService.createPost(requestDto);
    }

   /* @GetMapping("/post/{postId}")
    public void getPost(@PathVariable Long postId) { // 선택 게시물 조회

    }

    @GetMapping("/posts")
    public void getPosts() { // 게시물 목록 조회

    }*/

    /*@GetMapping("/post/{postId}")
    public void updatePost() { // 선택 게시물 수정
        
    }*/
}
