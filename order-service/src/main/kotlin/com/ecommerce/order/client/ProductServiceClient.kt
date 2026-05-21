package com.ecommerce.order.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

// name: 호출할 서비스의 Eureka/Consul 등록 이름 (서비스 ID)
@FeignClient(name = "product-service")
interface ProductServiceClient {

    // 호출할 'product-service'의 API 시그니처와 동일하게 메서드를 선언
    @GetMapping("/api/v1/products/{productId}")
    fun getProductById(@PathVariable productId: Long): ProductResponse
}