package ru.kupidji.web_labs.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.kupidji.web_labs.model.Product


interface ProductRepository : JpaRepository<Product, Long>