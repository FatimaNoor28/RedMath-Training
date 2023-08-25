package org.example.news;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.example.news.News;
import org.example.news.NewsRepository;
import org.example.news.NewsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private NewsService newsService;

    @Mock
    private NewsRepository repository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    //private ApplicationContext applicationContext;

    @Test
    @Order(1)
    public void testFindAll() throws Exception {
        //List<News> news = repository.findAll();
        String expectedJsonResponse = "[{\"id\":1,\"title\":\"title 1\",\"details\":\"details 1\",\"tags\":\"tags 1\",\"reportedAt\":\"2000-01-01T12:00:00\"},{\"id\":2,\"title\":\"title 2\",\"details\":\"details 2\",\"tags\":\"tags 2\",\"reportedAt\":\"2000-01-01T12:00:00\"}]";
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/news"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo(expectedJsonResponse)));
    }

    @Test
    @Order(5)
    public void testFindById() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/news/1"))
                //.with(testUser("reporter", "REPORTER")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is("details 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags", Matchers.is("tags 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reportedAt", Matchers.notNullValue()));
    }

    @Test
    //@Order(2)
    public void testcreate() throws Exception {
        String expectedJsonResponse = "{\"id\":3,\"title\":\"title 3\",\"details\":\"details 3\",\"tags\":\"tags 3\"}";
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/news")
                //.with(testUser("reporter"))
                .with(SecurityMockMvcRequestPostProcessors.user("reporter ").authorities(new SimpleGrantedAuthority("REPORTER")))
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType("application/json")
                .content(expectedJsonResponse))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());


        mvc.perform(MockMvcRequestBuilders.post("/api/v1/news")
                        //.with(testUser("reporter", "REPORTER"))
                        .with(SecurityMockMvcRequestPostProcessors.user("reporter").authorities(new SimpleGrantedAuthority("REPORTER")))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType("application/json")
                        .content("{\"title\":\"title 456\",\"details\":\"details 456\",\"tags\":\"tags 456\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title 456")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is("details 456")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags", Matchers.is("tags 456")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reportedAt", Matchers.notNullValue()));
    }

//    @Test
//    @Order(3)
//    public void testPut() throws Exception{
//        mvc.perform(MockMvcRequestBuilders.put("/api/v1/news/1")
//                        .contentType("application/json")
//                        .content("{\"title\":\"title 3\",\"details\":\"details 3\",\"tags\":\"tags 3\"}"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
//
//
//    }

//    @Test
//    @Order(4)
//    public void testDelete() throws Exception{
//        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/news/2").contentType("application/json"))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
//                .andExpect(MockMvcResultMatchers.content().string(""));
//    }

    private RequestPostProcessor testUser(String username, String authority){
        return SecurityMockMvcRequestPostProcessors.user(username).authorities(new SimpleGrantedAuthority(authority));
    }


}