package com.example.demo.repository

import com.example.demo.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long>