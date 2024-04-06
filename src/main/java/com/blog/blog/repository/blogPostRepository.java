package com.blog.blog.repository;

import com.blog.blog.post.blogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface blogPostRepository extends JpaRepository<blogPost, Long> {
    // You can add custom query methods here if needed
}
