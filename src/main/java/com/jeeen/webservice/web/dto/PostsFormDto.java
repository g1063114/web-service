package com.jeeen.webservice.web.dto;

import com.jeeen.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class PostsFormDto {

    @NotEmpty(message = "제목은 필수 입니다.")
    private String title;

    @NotEmpty(message = "작성자는 필수 입니다.")
    private String author;

    @NotEmpty(message = "내용은 필수 입니다.")
    private String content;

    @Builder
    public PostsFormDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
