package com.jeeen.webservice.controller;

import com.jeeen.webservice.domain.posts.Posts;
import com.jeeen.webservice.service.PostsService;
import com.jeeen.webservice.web.dto.PostsFormDto;
import com.jeeen.webservice.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostsPageController {

    private final PostsService postsService;

    @GetMapping("/posts/save")
    public String saveForm(Model model){
        model.addAttribute("postsFormDto", new PostsFormDto());
        return "posts/save";
    }

    @PostMapping("/posts/save")
    public String create(@Valid PostsFormDto postsFormDto, BindingResult result){
        if(result.hasErrors()){
            return "posts/save";
        }

        postsService.savePost(postsFormDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/posts")
    public String postList(Model model){
        List<PostsListResponseDto> list = postsService.postsList();
        model.addAttribute("list",list);
        return "posts/postsList";
    }

}
