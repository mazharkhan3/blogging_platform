package com.example.blogging_platform.repository;

import com.example.blogging_platform.data.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Query("select a from Article a " +
            "where (:tags is null or a.tags like concat('%', :tags, '%')) AND " +
            "(:date is null or a.createdOn between :start AND :end)")
    Iterable<Article> findArticles(
            @Param("tags") String tags,
            @Param("date") LocalDate date,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
