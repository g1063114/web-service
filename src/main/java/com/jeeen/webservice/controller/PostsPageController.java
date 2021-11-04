package com.jeeen.webservice.controller;

import com.jeeen.webservice.domain.posts.Posts;
import com.jeeen.webservice.service.PostsService;
import com.jeeen.webservice.web.dto.PostsFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostsPageController {

    private final PostsService postsService;

    @GetMapping("/posts/save")
    public String saveForm(Model model){
        model.addAttribute("postsForm", new PostsFormDto());
        return "/posts/save";
    }

    @PostMapping("/posts/save")
    public String postsSave(PostsFormDto formDto, BindingResult result){
        if(result.hasErrors()){
            return "/posts/save";
        }

        Posts post = Posts.builder()
                .title(formDto.getTitle())
                .content(formDto.getContent())
                .author(formDto.getAuthor())
                .build();

        postsService.savePost(post);
        return "redirect:/";
    }
}
