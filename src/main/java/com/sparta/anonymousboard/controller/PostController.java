package com.sparta.anonymousboard.controller;

import com.sparta.anonymousboard.dto.PostRequestDto;
import com.sparta.anonymousboard.dto.PostResponseDto;
import com.sparta.anonymousboard.entity.Post;
import com.sparta.anonymousboard.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    @ResponseBody
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) { // 게시물 작성
        return postService.createPost(requestDto);
    }

    @GetMapping("{id}")
    @ResponseBody
    public Post getPost(@PathVariable Long id) { // 선택 게시물 조회
        return postService.findPost(id);
    }

    @GetMapping()
    @ResponseBody
    public List<PostResponseDto> getPosts() { // 게시물 목록 조회
        return postService.getPost();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) { // 선택 게시물 수정
        return postService.updatePost(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deletePost(@PathVariable Long id, @RequestHeader(value = "password") String password) { // 선택 게시물 삭제
        return postService.deletePost(id, password);
    }
}
