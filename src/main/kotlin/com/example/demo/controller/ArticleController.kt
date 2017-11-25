package com.example.demo.controller

import com.example.demo.model.Article
import com.example.demo.service.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ArticleController(private val articleService: ArticleService) {

    @GetMapping("/articles")
    fun getAllArticles(): List<Article> =
            articleService.getAllArticles()


    @PostMapping("/articles")
    fun createNewArticle(@Valid @RequestBody article: Article): Article =
            articleService.createNewArticle(article)


    @GetMapping("/articles/{id}")
    fun getArticleById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Article> {
        return articleService.getArticleById(articleId).map { article ->
            ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/articles/{id}")
    fun updateArticleById(@PathVariable(value = "id") articleId: Long,
                          @Valid @RequestBody newArticle: Article): ResponseEntity<Article> {
        return articleService.getArticleById(articleId).map { article ->
            ResponseEntity.ok().body(articleService.updateArticle(article, newArticle))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/articles/{id}")
    fun deleteArticleById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Void> {
        return articleService.getArticleById(articleId).map { article ->
            articleService.deleteArticle(article)
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }.orElse(ResponseEntity.notFound().build())
    }
}