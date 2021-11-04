package com.jeeen.webservice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsFormDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsFormDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
