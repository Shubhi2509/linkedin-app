package com.project.linkedin.post_service.service;

import com.project.linkedin.post_service.entity.PostLike;
import com.project.linkedin.post_service.exception.ResourceNotFoundException;
import com.project.linkedin.post_service.repository.PostLikeRepository;
import com.project.linkedin.post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public void likePost(Long postId, Long userId) throws BadRequestException {
        log.info("Attempting to like the post with post Id: " + postId);
        boolean exists = postRepository.existsById(postId);
        if (!exists) throw new ResourceNotFoundException("Post does not found with id: " + postId);
        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyLiked) throw new BadRequestException("Can not like the same post again");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        log.info("Post: {} liked successfully", postLike.toString());
        postLikeRepository.save(postLike);
        log.info("Post with id: {} liked successfully", postId);
    }

    public void unlikePosts(long postId, long userId) throws BadRequestException {
        log.info("Attempting to unlike the post with post Id: " + postId);
        boolean exists = postRepository.existsById(postId);
        if (!exists) throw new ResourceNotFoundException("Post does not found with id: " + postId);
        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (!alreadyLiked)
            throw new BadRequestException("Can not unlike the post which does not liked");
        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
        log.info("Post with id: {} unliked successfully", postId);
    }
}
