package com.example.demo.model

import org.hibernate.validator.constraints.NotBlank
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Article (
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @get: NotBlank
        val title: String = "",

        @get: NotBlank
        val content: String = ""
)