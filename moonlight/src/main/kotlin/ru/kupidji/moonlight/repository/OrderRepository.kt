package ru.kupidji.moonlight.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.kupidji.moonlight.model.Order

interface OrderRepository : JpaRepository<Order, Long>