package com.linked.clinked.models.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "article")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 200, message = "username must be at most 100 characters long")
    private String title;

    @Size(max = 200, message = "username must be at least 2 characters long")
    private String author;

    @Size(max = 200, message = "username must be at least 2 characters long")
    private String content;

    private LocalDateTime publishedDate;

}
