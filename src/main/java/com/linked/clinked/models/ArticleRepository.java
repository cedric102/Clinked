package com.linked.clinked.models;

import java.time.LocalDateTime;
import java.util.List;

import com.linked.clinked.models.data.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    // SELECT * FROM article
    Page<Article> findAll(Pageable pageable);

    // SELECT * FROM article WHERE Title = title && Author = author
    Article findByTitleAndAuthor(String title, String author);

    // SELECT * FROM article WHERE Author = author
    Article findByAuthor(String author);

    // @Query("FROM article a WHERE published_date BETWEEN :start AND :end")
    // @Modifying
    // @Query("SELECT * published_date FROM article WHERE DATE(published_date) "+ "BETWEEN :start AND :end")
    // List<Article> findAllBetweenDates( @Param("start")String start , @Param("end")String end );
    List<Article> findByPublishedDateBetween(String start, String end);

    List<Article> findAllByPublishedDateBetween(LocalDateTime start, LocalDateTime end);
}
