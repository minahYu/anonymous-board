package com.sparta.anonymousboard.service;

import com.sparta.anonymousboard.dto.PostRequestDto;
import com.sparta.anonymousboard.dto.PostResponseDto;
import com.sparta.anonymousboard.entity.Post;
import com.sparta.anonymousboard.repository.PostRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        if(checkPW(id, requestDto))
            post.update(requestDto);
        return id;
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }

    public boolean checkPW(Long id, PostRequestDto requestDto) {
        Post getPost = postRepository.getById(id);

        return getPost.getPassword().equals(requestDto.getPassword());
    }
}
