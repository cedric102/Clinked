
package com.linked.clinked.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import com.linked.clinked.DatabaseManagement;
import com.linked.clinked.models.ArticleRepository;
import com.linked.clinked.models.data.Article;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/articles")
public class ClientController extends DatabaseManagement {


      public ClientController(ArticleRepository articleRepo) {
            this.articleRepo = articleRepo;
      }

      @GetMapping()
      public ModelAndView index(Model model, @RequestParam(value = "page", required = false) Integer p) {

            LocalDateTime d1_sql = LocalDateTime.parse("2008-01-01T00:00:01");
            LocalDateTime d2_sql = LocalDateTime.parse("2016-01-01T00:00:00");

            ModelAndView mv = new ModelAndView();
            mv.setViewName("articles/view");
            refreshArticleList(mv , p);
                  
            return mv;

      }

      @GetMapping("/add2/{title}/{author}/{content}")
      @ResponseBody
      public String add2(@PathVariable("title") String title, @PathVariable("author") String author,
                  @PathVariable("content") String content) {
            // &author=BLU&content=Stuff
            System.out.println("Request Param Title: " + title);
            System.out.println("Request Param Author: " + author);
            System.out.println("Request Param Content: " + content);

            return "articles/view";
      }

      @GetMapping("/add3")
      public String add3(@RequestParam("title") String title, @RequestParam("author") String author,
                  @RequestParam("content") String content) {
            // &author=BLU&content=Stuff
            System.out.println("Request Param Title: " + title);
            System.out.println("Request Param Author: " + author);
            System.out.println("Request Param Content: " + content);

            return "articles/view";
      }
      ////////////////////////////
      // ADD ARTICLE TO REPOSITORY
      ////////////////////////////
      // Endpoint : "/admin/stat/add"
      // Report the articles for the corresponding page.
      // The Statistic can be visualized in a table ( No Graph )
      ////////////////////////////
      @PostMapping("/add")
      public ModelAndView add(@Valid Article article, @RequestParam("title") String title2,
                  @RequestParam("author") String author2, @RequestParam("content") String content2, Model model,
                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
            // &author=BLU&content=Stuff

            // Create the ModelAndView Object
            // Set the View Name
            ModelAndView mv = new ModelAndView();
            mv.setViewName("articles/view");

            procedureToAddArticle( mv , article , bindingResult );

            // Return the View
            return mv;

      }
      
}