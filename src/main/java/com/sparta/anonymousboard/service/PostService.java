package com.sparta.anonymousboard.service;

import com.sparta.anonymousboard.dto.PostRequestDto;
import com.sparta.anonymousboard.dto.PostResponseDto;
import com.sparta.anonymousboard.entity.Post;
import com.sparta.anonymousboard.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        Post savePost = postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;
    }

    public List<PostResponseDto> getPost() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }
}
