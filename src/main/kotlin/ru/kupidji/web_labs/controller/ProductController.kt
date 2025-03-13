//package ru.kupidji.web_labs.controller
//
//import jakarta.validation.Valid
//import org.springframework.web.bind.annotation.*
//import ru.kupidji.web_labs.model.*
//import ru.kupidji.web_labs.repository.ProductRepository
//
//@RestController
//@RequestMapping("/api/products")
//class ProductController(private val productRepository: ProductRepository) {
//
//    @GetMapping
//    fun getAllProducts(): List<ProductResponse> =
//        productRepository.findAll().map { it.toResponse() }
//
//    @PostMapping
//    fun createProduct(
//        @Valid @RequestBody request: CreateProductRequest
//    ): ProductResponse {
//        return productRepository.save(request.toEntity())
//            .toResponse()
//    }
//
//    @GetMapping("/{id}")
//    fun getProductById(@PathVariable id: Long): ProductResponse {
//        return productRepository.findById(id)
//            .orElseThrow { RuntimeException("Product not found with id: $id") }
//            .toResponse()
//    }
//
//    @PutMapping("/{id}")
//    fun updateProduct(
//        @PathVariable id: Long,
//        @Valid @RequestBody request: CreateProductRequest
//    ): ProductResponse {
//        val product = productRepository.findById(id)
//            .orElseThrow { RuntimeException("Product not found with id: $id") }
//
//        return productRepository.save(
//            product.copy(
//                name = request.name,
//                price = request.price,
//                imageUrl = request.imageUrl
//            )
//        ).toResponse()
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteProduct(@PathVariable id: Long): Map<String, Boolean> {
//        val product = productRepository.findById(id)
//            .orElseThrow { RuntimeException("Product not found with id: $id") }
//
//        productRepository.delete(product)
//        return mapOf("deleted" to true)
//    }
//}