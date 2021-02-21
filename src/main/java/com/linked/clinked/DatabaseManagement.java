package com.linked.clinked;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.linked.clinked.models.ArticleRepository;
import com.linked.clinked.models.data.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class DatabaseManagement {

    protected long count;
    protected int perPage = 5;
    protected Integer page = 0;

    @Autowired
    protected ArticleRepository articleRepo;


       ////////////////////////////
       // STATISTIC FUNCTION
       ////////////////////////////
       // Return: TreeMap containning the dates ordered choronologically
       //         and the number of articles puclished for each day
       ////////////////////////////
       protected TreeMap<String, Integer> dailyBasisStat() {

        LocalDateTime d2_sql = LocalDateTime.now();
        LocalDateTime d1_sql = d2_sql.minusWeeks(1);
        List<Article> articleListBetweenTimes = articleRepo.findAllByPublishedDateBetween(d1_sql, d2_sql);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Map<String, Integer> mp = new HashMap<String, Integer>();
        Map<String, Integer> mp2 = new HashMap<String, Integer>();
        for (Article a : articleListBetweenTimes) {
               String key = a.getPublishedDate().format(formatter).toString();

               // Keep the list of all the times if it is needed in future evolution of the application
               mp2.put(a.getPublishedDate().toString(), 1);

               // Populate the Map<"Date" , count>
               if (!mp.containsKey(key))
                      mp.put(key, 1);
               else
                      mp.put(key, mp.get(key) + 1);
        }
        
        // Convert from Map to TreeMap ( TreeMap is sorted for the DateTime )
        TreeMap<String, Integer> tree = new TreeMap<>(mp);
        TreeMap<String, Integer> tree2 = new TreeMap<>(mp2);

        return tree;

 }

    protected void procedureToAddArticle( ModelAndView mv , Article article , BindingResult bindingResult ) {

        if (bindingResult.hasErrors()) {
              // Return the view with the updated fields
              refreshArticleList(mv, page);
              return;
        }

        // Check the Validity of the input fields
        String res = articleArgumentsCheck(mv, article.getTitle(), article.getAuthor(), article.getContent());
        if (!res.equals("")) {
              // Return the view with the updated fields
              refreshArticleList(mv , page);
              return;
        }

        // Add the article to the Repo
        addArticleToRepo(mv, article);

        // Refresh the List
        refreshArticleList(mv, page);
  }

    // Refresh the Article List for the View
    protected void refreshArticleList(ModelAndView mv , Integer p ) {
        count = articleRepo.count();
        if (p != null) page = p;
        Pageable pageable = PageRequest.of(page, perPage);
        Page<Article> articleList = articleRepo.findAll(pageable);

        mv.addObject("articleList", articleList);
        mv.addObject("count", count);
        mv.addObject("perPage", perPage);
        mv.addObject("page", page);
        mv.addObject("pageCount", count / perPage);
    }

    //////////////////////
    // VALIDATE ARTICLE FIELDS
    //////////////////////
    // Requirements :
    // - No Field should be empty
    // - Title should not exceed 100 characters
    //////////////////////
    protected String articleArgumentsCheck(ModelAndView mv, String title, String author, String content) {

        boolean errorFlag = false;

        // Check if Author Field has been filled
        if (author.length() == 0) {
            // Inform the user that the Author field is empty
            mv.addObject("authorWarning", "Please, insert an Author Name");
            errorFlag = true;
        }
        // Check if Title Field has been filled
        if (title.length() == 0) {
            // Inform the user that the Title field is empty
            mv.addObject("titleWarning", "Please, insert an Title");
            errorFlag = true;
        }

        // Check if Title Field has been filled
        if (title.length() >= 100) {
            // Inform the user that the Title field is empty
            mv.addObject("titleWarning",
                    "Cautious: The Title is " + title.length() + " characters long. The limit is 100.");
            errorFlag = true;
        }
        // Check if Content Field has been filled
        if (content.length() == 0) {
            // Inform the user that the Content field is empty
            mv.addObject("contentWarning", "Please, insert an Content");
            errorFlag = true;
        }
        if (errorFlag == true) {
            // Ensure the input fields have not been reset
            mv.addObject("author", author);
            mv.addObject("title", title);
            mv.addObject("content", content);

            return "articles/view";
        }
        return "";
    }

    //////////////////////
    // ADD ARTICLE TO REPOSITORY
    //////////////////////
    // - Ensure the article does not already exist before adding it
    // - RETURN: 0 -> Article cannot be added
    // 1 -> Article has been added
    //////////////////////
    protected int addArticleToRepo(ModelAndView mv, Article article) {
        String title = article.getTitle();
        String author = article.getAuthor();
        Article articleExists = articleRepo.findByTitleAndAuthor(title, author);

        System.out.println(0);

        if (articleExists != null) {

            System.out.println(1);

            // Inform the USER that the Article Already exists
            mv.addObject("AlreadyExists", "Article already exists");

            // Ensure the input fields have not been reset
            mv.addObject("author", article.getAuthor());
            mv.addObject("title", article.getTitle());
            mv.addObject("content", article.getContent());

            return -1;

        } else {

            System.out.println(2);
            // Provide the Current Date to the Publishing Date of the article to be added
            LocalDateTime date = LocalDateTime.now();
            article.setPublishedDate(date);

            try {
                // Add the Article to the Repository
                articleRepo.save(article);
            } catch (Exception e) {
                System.out.println("ERROR : Article cannot be saved");
                return 0;
            }

        }
        return 1;
    }
}
