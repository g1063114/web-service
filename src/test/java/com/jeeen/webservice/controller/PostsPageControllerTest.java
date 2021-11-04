package com.jeeen.webservice.controller;

import com.jeeen.webservice.domain.posts.Posts;
import com.jeeen.webservice.domain.posts.PostsRepository;
import com.jeeen.webservice.web.dto.PostsFormDto;
import com.jeeen.webservice.web.dto.PostsUpdateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsPageControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private PostsRepository repository;

    @Test
    public void 게시물저장(){
        // given
        String title = "테스트";
        String content ="테스트";
        String author = "진호";

        PostsFormDto dto = PostsFormDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + port + "/posts/save";

        HttpEntity<PostsFormDto> requestEntity = new HttpEntity<>(dto);

        // when
        ResponseEntity<Long> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);

        // then
        List<Posts> all = repository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo("테스트");
    }
}