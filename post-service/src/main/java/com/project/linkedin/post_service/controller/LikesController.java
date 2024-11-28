package com.project.linkedin.post_service.controller;

import com.project.linkedin.post_service.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {
    private final PostLikeService postLikeService;
    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePosts(@PathVariable long postId) throws BadRequestException {
        postLikeService.likePost(postId,1L);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePosts(@PathVariable long postId) throws BadRequestException {
        postLikeService.unlikePosts(postId,1L);
        return ResponseEntity.noContent().build();
    }
}
