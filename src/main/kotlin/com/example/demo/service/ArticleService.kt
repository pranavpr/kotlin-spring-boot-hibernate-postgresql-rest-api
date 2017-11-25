package com.example.demo.service

import com.example.demo.model.Article
import com.example.demo.repository.ArticleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArticleService(val articleRepository: ArticleRepository) {

    fun getAllArticles(): List<Article> = articleRepository.findAll()

    fun createNewArticle(article: Article): Article =
            articleRepository.save(article)

    fun getArticleById(articleId: Long): Optional<Article> =
            articleRepository.findById(articleId)


    fun updateArticle(article: Article,
                          newArticle: Article): Article {
        val updatedArticle: Article = article
                .copy(title = newArticle.title, content = newArticle.content)
        return articleRepository.save(updatedArticle)
    }

    fun deleteArticle(article: Article) =
            articleRepository.delete(article)

}