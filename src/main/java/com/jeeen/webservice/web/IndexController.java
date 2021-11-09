package com.jeeen.webservice.web;

import com.jeeen.webservice.config.auth.dto.SessionUser;
import com.jeeen.webservice.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if( user != null ){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}
