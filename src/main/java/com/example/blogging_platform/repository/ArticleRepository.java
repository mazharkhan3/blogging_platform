package com.example.blogging_platform.repository;

import com.example.blogging_platform.data.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    Iterable<Article> findByTagsContaining(String tags);
    Iterable<Article> findByCreatedOn_Date(String date);
    Iterable<Article> findByTagsContainingAndCreatedOn_Date(String tags, String date);
}
