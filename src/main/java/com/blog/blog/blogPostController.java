package com.blog.blog;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class blogPostController {

    private final blogPostRepository blogPostRepository;

    @GetMapping("/new")
    public String showblogPostForm(blogPost blogPost) {
        return "add";
    }

    @PostMapping("/add")
    public String getblogPosts(@Valid blogPost blogPost, BindingResult result){
        if (result.hasErrors()){
            return "add";
        }
        blogPostRepository.save(blogPost);
        return "redirect:blogPost";
    }

    @GetMapping("/blogPost")
    public String getblogPosts(Model model){
        List<blogPost> allblogPosts = blogPostRepository.findAll();
        model.addAttribute("blogPosts", allblogPosts.isEmpty() ? null : allblogPosts);
        return "blogPosts";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        blogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid blogPost Id:" + id));
        model.addAttribute("blogPost", blogPost);
        return "update";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid blogPost blogPost, BindingResult result) {
        if (result.hasErrors()) {
            blogPost.setId(id);
            return "update";
        }
        blogPostRepository.save(blogPost);
        return "redirect:/blogPost";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        blogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid blogPost Id:" + id));
        blogPostRepository.delete(blogPost);
        return "redirect:/blogPost";
    }
}