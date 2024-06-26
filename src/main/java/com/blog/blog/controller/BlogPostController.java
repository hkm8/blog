package com.blog.blog.controller;

import com.blog.blog.post.blogPost;
import com.blog.blog.repository.blogPostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogposts")
public class BlogPostController {

    private final blogPostRepository postRepository;

    @Autowired
    public BlogPostController(blogPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Create a new blog post
    @PostMapping("/add") // The additional path is specified here
    public ResponseEntity<blogPost> createPost(@Valid @RequestBody blogPost post) {
        blogPost savedPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }


    // Retrieve all blog posts
    @GetMapping("/all")
    public ResponseEntity<List<blogPost>> getAllPosts() {
        List<blogPost> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    // Retrieve a single blog post by its ID
    @GetMapping("/{postId}")
    public ResponseEntity<blogPost> getPostById(@PathVariable Long postId) {
        Optional<blogPost> postOptional = postRepository.findById(postId);
        return postOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing blog post
    @PutMapping("/{postId}")
    public ResponseEntity<blogPost> updatePost(@PathVariable Long postId, @Valid @RequestBody blogPost updatedPost) {
        if (!postRepository.existsById(postId)) {
            return ResponseEntity.notFound().build();
        }
        updatedPost.setId(postId);
        blogPost savedPost = postRepository.save(updatedPost);
        return ResponseEntity.ok(savedPost);
    }

    // Delete a blog post
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        if (!postRepository.existsById(postId)) {
            return ResponseEntity.notFound().build();
        }
        postRepository.deleteById(postId);
        return ResponseEntity.noContent().build();
    }
}
