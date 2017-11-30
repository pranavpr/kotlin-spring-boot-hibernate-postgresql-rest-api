package com.example.demo.service

import com.example.demo.model.Article
import com.example.demo.repository.ArticleRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArticleService(val articleRepository: ArticleRepository) {

    @Cacheable(value = "articles")
    fun getAllArticles(): List<Article> = articleRepository.findAll()

    @CacheEvict(value = "articles", allEntries = true)

    @Caching(
            evict = arrayOf(
                    CacheEvict(value = "articles", allEntries = true)
            ),
            put = arrayOf(
                    CachePut(value = "article-single", key = "#result.id")
            )
    )
    fun createNewArticle(article: Article): Article =
            articleRepository.save(article)

    @Cacheable(value = "article-single", key = "#articleId")
    fun getArticleById(articleId: Long): Optional<Article> =
            articleRepository.findById(articleId)

    @CachePut(value = "article-single", key = "#article.id")
    fun updateArticle(article: Article,
                      newArticle: Article): Article {
        val updatedArticle: Article = article
                .copy(title = newArticle.title, content = newArticle.content)
        return articleRepository.save(updatedArticle)
    }

    @Caching(
            evict = arrayOf(
                    CacheEvict(value = "articles", allEntries = true),
                    CacheEvict(value = "article-single", key = "#article.id")
            )
    )
    fun deleteArticle(article: Article) =
            articleRepository.delete(article)

}