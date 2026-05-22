package com.ecommerce.order.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

// 1. @FeignClient 어노테이션
// name: 호출할 서비스의 Eureka/Consul 등록 이름 (서비스 ID)
@FeignClient(
    name = "product-service",
    fallback = ProductServiceClientFallback::class // 1. Fallback 클래스 등록
)
interface ProductServiceClient {

    // 2. 호출할 'product-service'의 API 시그니처와 동일하게 메서드를 선언
    // Spring MVC 어노테이션을 그대로 사용
    @GetMapping("/api/v1/products/{productId}")
    fun getProduct(@PathVariable productId: Long): ProductResponse

    // (예시) 상품 재고 차감을 위한 API 호출
    // @PostMapping("/api/v1/products/decrease-stock")
    // fun decreaseStock(@RequestBody request: DecreaseStockRequest)
}