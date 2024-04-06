package com.blog.blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<blogPost, Long> {
    // You can add custom query methods here if needed
}
