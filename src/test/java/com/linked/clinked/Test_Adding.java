package com.linked.clinked;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import io.restassured.http.ContentType;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.refEq;

import java.util.HashMap;
import java.util.Map;

import com.linked.clinked.controllers.ClientController;
import com.linked.clinked.models.ArticleRepository;
import com.linked.clinked.models.data.Article;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
//import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.net.*;
import java.net.http.HttpResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
// @RunWith(SpringRunner.class)
// @SpringBootTest( webEnvironment = webEnvironment.RANDOM_PORT )
public class Test_Adding {

    @Autowired
    private MockMvc mvc;

    ClientController stat = null;
    ArticleRepository articleRepo = mock(ArticleRepository.class);

    // NameService c = null;
    // innerMock inner = mock(innerMock.class);

    @Before
    public void setUp() {
        stat = new ClientController( articleRepo );
  //      c = new NameService( inner );
    }

    // @Test
    // public void testPerform() {
    //     when(inner.add(2,3)).thenReturn(5);
    //     assertEquals( 10 , c.perform(2,3));
    //     verify(inner).add(2,3);
    // }

    // @Test
    // public void testPerform2() {
    //     when(inner.add(2,3)).thenReturn(5);
    //     assertEquals( 10 , c.perform(2,3));
    //     verify(inner).add(2,3);
    // }

    // Test when the article cannot be added
    @Test
    public void Fail_1_ArticleArgumentCheck() throws Exception {
        ModelAndView mv = new ModelAndView();
        String res = stat.articleArgumentsCheck( mv , "" , "" , "" );
        assertNotEquals( res , "" );
    }

    // Test when the article cannot be added
    @Test
    public void Fail_2_ArticleArgumentCheck() throws Exception {
        ModelAndView mv = new ModelAndView();
        String res = stat.articleArgumentsCheck( mv , "DummyTitle" , "" , "" );
        assertNotEquals( res , "" );
    }

    // Test when the article cannot be added
    @Test
    public void Succeed_ArticleArgumentCheck() throws Exception {
        ModelAndView mv = new ModelAndView();
        String res = stat.articleArgumentsCheck( mv , "DummyTitle" , "DummyAuthor" , "DummyContent" );
        assertEquals( res , "" );
    }

    // Test when the article can be added
    @Test
    public void Fail_AddArticle() throws Exception {
        ModelAndView mv = new ModelAndView();
        Article a = new Article();
        a.setTitle("t");
        a.setAuthor("a");
        a.setContent("c");
        Mockito.when(articleRepo.findByTitleAndAuthor("t","a")).thenReturn(a);
        int testName = stat.addArticleToRepo( mv , a );
        assertEquals( testName , -1 );

    }

    // Test when the article cannot be added
    @Test
    public void Succeed_AddArticle() throws Exception {
        ModelAndView mv = new ModelAndView();
        Article a = new Article();
        a.setTitle("t");
        a.setAuthor("a");
        a.setContent("c");
        Mockito.when(articleRepo.findByTitleAndAuthor("t","a")).thenReturn(null);
    //    int testName = stat.refreshArticleList2();
    //     // System.out.println( testName );
        int testName = stat.addArticleToRepo( mv , a );
        assertEquals( testName , 1 );
    }

    @Test
    public void addArticle() throws Exception {
        HttpUriRequest request = new HttpGet("localhost:8080/articles/add3/?title=title&author=author2&content=content");
    //    HttpUriRequest request = new HttpGet("/articles/add2/title/author2/content");

        HttpResponse httpResponse = (HttpResponse) HttpClientBuilder.create().build().execute(request);

        System.out.println(httpResponse.body());
        // URL url = new URL("/articles");
        // MockHttpServletRequestBuilder req = MockMvcRequestBuilders.get(
        // "/articles/add2/p/p/p" );
        // MvcResult res = mvc.perform(req).andReturn();
        // long count = stat.countArticles();
        // // String res = stat.saveArticle( "tttt" , "aa" , "cc");
        // assertEquals( count , 1 );
        // when(article.getAuthor())
        // MvcResult res = mvc.perform( MockMvcRequestBuilders.get("/example")
        // ).andReturn();
        // MockMvcRequestBuilders.get("http://localhost:8080/example").then()
        // .assertThat()
        // .statusCode(HttpStatus.OK.value());
        // assertEquals("String", mvc.perform(
        // MockMvcRequestBuilders.get("http://localhost:8080/example")
        // ).andReturn().getResponse().getContentAsString());

        // Map<String, String> request = new HashMap<>();
        // request.put("title" , "11");
        // request.put("author" , "11");
        // request.put("content" , "11");

        // int articleTitle = given().contentType("application/json")
        // .body(request)
        // .post("articles/add")
        // .assertThat()
        // .statusCode( HttpStatus.OK.value() );
        // .extract()
        // .path("title");
        // get("articles/add3").then()
        // .assertThat()
        // .statusCode( HttpStatus.OK.value());

        assertEquals(1, 1);
    }

    @Test
    public void addArticle2() throws Exception {

        assertEquals(1, 1);
    }
}
