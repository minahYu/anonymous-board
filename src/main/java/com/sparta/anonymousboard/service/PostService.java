package com.sparta.anonymousboard.service;

import com.sparta.anonymousboard.Exception.MismatchException;
import com.sparta.anonymousboard.Exception.NotFoundException;
import com.sparta.anonymousboard.dto.PostRequestDto;
import com.sparta.anonymousboard.dto.PostResponseDto;
import com.sparta.anonymousboard.entity.Post;
import com.sparta.anonymousboard.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
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
    public String updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        String msg = "글이 수정되었습니다.";
        if (post == null)
            throw new NotFoundException(id);

        try {
            checkPW(id, requestDto);
            post.update(requestDto);
        } catch (MismatchException e) {
            msg = e.getMessage();
        }
        return msg;
    }

    public String deletePost(Long id, PostRequestDto requestDto) {
        Post findPost = findPost(id);
        String msg = "글이 삭제되었습니다.";
        if (findPost == null)
            throw new NotFoundException(id);

        try {
            checkPW(id, requestDto);
            postRepository.delete(findPost);
        } catch (MismatchException e) {
            msg = e.getMessage();
        }
        return msg;
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
    }

    public void checkPW(Long id, PostRequestDto requestDto) throws MismatchException {
        Post getPost = postRepository.getById(id);

        if (!getPost.getPassword().equals(requestDto.getPassword())) {
            throw new MismatchException("비밀번호가 일치하지 않습니다.");
        }
    }
}
