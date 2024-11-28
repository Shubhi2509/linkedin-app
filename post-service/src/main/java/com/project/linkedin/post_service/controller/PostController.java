package com.project.linkedin.post_service.controller;

import com.project.linkedin.post_service.dto.PostCreateRequestDto;
import com.project.linkedin.post_service.dto.PostDto;
import com.project.linkedin.post_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postsService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postDto, HttpServletRequest httpServletRequest){
        PostDto createdPost=postsService.createPost(postDto,1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);


    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto postDto=postsService.getPostById(postId);
        return postDto!=null ? ResponseEntity.ok(postDto):ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(){
        List<PostDto> postDtos=postsService.getPosts();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Long userId){
        List<PostDto> postDtos=postsService.getPostByUserId(userId);
        return ResponseEntity.ok(postDtos);
    }
}
