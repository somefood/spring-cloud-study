package com.ecommerce.order.client

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProductServiceClientFallback : ProductServiceClient {

    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * 이 메서드는 ProductServiceClient.getProduct() 호출이 실패하면
     * (서킷 OPEN, 타임아웃 등) 대신 호출됩니다.
     */
    override fun getProduct(productId: Long): ProductResponse {
        log.warn("Fallback for getProduct(productId={}) triggered.", productId)

        // 여기에 대체 로직을 구현합니다.
        // 1. 미리 정의된 기본(Default) 응답 반환
        return ProductResponse(
            id = productId,
            name = "상품 정보 조회 불가", // 사용자에게 표시될 메시지
            price = -1L, // 에러를 나타내는 값
            stockQuantity = 0
        )

        // 2. (더 발전된 방식) Redis 같은 캐시에서 오래된(Stale) 데이터라도 조회하여 반환
        // val cachedProduct = redisCache.get("product:$productId")
        // return cachedProduct ?: defaultResponse
    }
}