package com.example.blogging_platform.models;

import jakarta.validation.constraints.NotNull;

public class ArticleDTO {

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Content cannot be null")
    private String content;

    @NotNull(message = "Tags cannot be null")
    private String tags;

    public @NotNull(message = "Title cannot be null") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(message = "Title cannot be null") String title) {
        this.title = title;
    }

    public @NotNull(message = "Content cannot be null") String getContent() {
        return content;
    }

    public void setContent(@NotNull(message = "Content cannot be null") String content) {
        this.content = content;
    }

    public @NotNull(message = "Tags cannot be null") String getTags() {
        return tags;
    }

    public void setTags(@NotNull(message = "Tags cannot be null") String tags) {
        this.tags = tags;
    }
}
