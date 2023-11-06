package com.sparta.anonymousboard.controller;

import com.sparta.anonymousboard.dto.PostRequestDto;
import com.sparta.anonymousboard.dto.PostResponseDto;
import com.sparta.anonymousboard.entity.Post;
import com.sparta.anonymousboard.repository.PostRepository;
import com.sparta.anonymousboard.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


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

   @GetMapping("/post/{postId}")
   @ResponseBody
    public PostResponseDto getPost(@PathVariable Long postId) { // 선택 게시물 조회
        return postService.getPost().stream()
                .filter(post -> Objects.equals(post.getId(), postId))
                .toList().get(0);
    }

     @GetMapping("/posts")
     @ResponseBody
    public List<PostResponseDto> getPosts() { // 게시물 목록 조회
        return postService.getPost();
    }

    /*@GetMapping("/post/{postId}")
    public void updatePost() { // 선택 게시물 수정
        
    }*/
}
