package com.linked.clinked.controllers;

import java.util.Map;

import javax.validation.Valid;

import com.linked.clinked.DatabaseManagement;
import com.linked.clinked.models.data.Article;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


///////////////////////////////////////
// ADMIN CONTROLLER
///////////////////////////////////////
// The endpoints of this controller class can only be run by the ADMIN
// LIST ARTICLE : The ADMIN can monitor the list of the articles
// ADD ARTICLE :  The ADMIN can add an article to the list
// STATISTIC :    The ADMIN can can see the overview of the added articles for the past week from the current time.
//                Each day would represent the count of the added article for each day.
///////////////////////////////////////
@RestController
@RequestMapping("/admin/stat")
public class AdminStatisticController extends DatabaseManagement {

       // Endpoint : "/admin/stat"
       // The Statistics can be returned by this endpoint
       @GetMapping()
       public Map<String, Integer> index(Model model, @RequestParam(value = "page", required = false) Integer p) {
              return dailyBasisStat();
       }

       ////////////////////////////
       // VIEW THE STATISTIC
       ////////////////////////////
       // Endpoint : "/admin/stat/view"
       // Report the articles for the corresponding page.
       // The Statistic can be visualized in a table ( No Graph )
       ////////////////////////////
       @GetMapping("/view")
       public ModelAndView index2(Model model, @RequestParam(value = "page", required = false) Integer p) {

              page = (p != null) ? p : 0;
              ModelAndView mv = new ModelAndView();

              // Refresh the List
              mv.addObject("articleListBetweenTimes", dailyBasisStat());
              refreshArticleList(mv, p);

              mv.setViewName("admin/statistic");

              return mv;

       }

       ////////////////////////////
       // ADD ARTICLE TO REPOSITORY
       ////////////////////////////
       // Endpoint : "/admin/stat/add"
       // Report the articles for the corresponding page.
       // The Statistic can be visualized in a table ( No Graph )
       ////////////////////////////
       @PostMapping("/add")
       public ModelAndView add(@Valid Article article, Model model, BindingResult bindingResult,
                     RedirectAttributes redirectAttributes) {

              // Create the ModelAndView Object
              // Set the View Name
              ModelAndView mv = new ModelAndView();
              mv.setViewName("admin/statistic");
              mv.addObject("articleListBetweenTimes", dailyBasisStat());

              procedureToAddArticle( mv , article , bindingResult );
              
              // Return the View
              return mv;

       }
}
