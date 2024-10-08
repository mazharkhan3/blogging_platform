package com.example.blogging_platform.controllers;

import com.example.blogging_platform.data.Article;
import com.example.blogging_platform.models.ArticleDTO;
import com.example.blogging_platform.repository.ArticleRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "articles")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostMapping()
    public ResponseEntity<String> addNewArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        var article = new Article();

        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setCreatedOn(LocalDateTime.now());
        article.setTags(articleDTO.getTags());

        articleRepository.save(article);
        return ResponseEntity.ok("Saved");
    }

    @GetMapping()
    public ResponseEntity<Iterable<Article>> getAllArticles(@RequestParam(required = false) String tags, @RequestParam(required = false) LocalDate date) {
        LocalDateTime start = null;
        LocalDateTime end = null;

        if (date != null) {
            start = date.atStartOfDay();
            end = date.atTime(LocalTime.MAX);
        }

        Iterable<Article> articles = articleRepository.findArticles(tags, date, start, end);

        var articleDTOs = new ArrayList<Article>();

        for (Article article : articles) {
            articleDTOs.add(article);
        }

        return ResponseEntity.ok(articleDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Article>> getAllArticles(@PathVariable Long id) {
        var article = articleRepository.findById(id);

        if (article.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        var article = articleRepository.findById(id);

        if (article.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        articleRepository.delete(article.get());

        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@Valid @RequestBody ArticleDTO articleDTO, @PathVariable Long id) {
        var article = articleRepository.findById(id);

        if (article.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        article.get().setTitle(articleDTO.getTitle());
        article.get().setContent(articleDTO.getContent());
        article.get().setTags(articleDTO.getTags());

        articleRepository.save(article.get());

        return ResponseEntity.ok("Saved");
    }
}
