package com.project.linkedin.post_service.service;

import com.project.linkedin.post_service.dto.PostCreateRequestDto;
import com.project.linkedin.post_service.dto.PostDto;
import com.project.linkedin.post_service.entity.Post;
import com.project.linkedin.post_service.exception.ResourceNotFoundException;
import com.project.linkedin.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postDto, Long userId) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setUserId(userId);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post does not found with this id"));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getPosts() {
        List<Post> post=postRepository.findAll();
        return post
                .stream()
                .map(element->modelMapper.map(element,PostDto.class))
                .collect(Collectors.toList());
    }

    public List<PostDto> getPostByUserId(Long userId) {
        List<Post> post=postRepository.findByUserId(userId);
        return post
                .stream()
                .map(element->modelMapper.map(element,PostDto.class))
                .collect(Collectors.toList());
    }
}
