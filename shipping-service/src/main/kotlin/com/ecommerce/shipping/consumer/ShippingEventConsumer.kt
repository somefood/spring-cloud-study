// shipping-service/src/main/kotlin/com/ecommerce/shipping/consumer/ShippingEventConsumer.kt
package com.ecommerce.shipping.consumer

import com.ecommerce.shipping.event.OrderPaidEvent
import com.ecommerce.shipping.service.ShippingService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ShippingEventConsumer(
    private val shippingService: ShippingService // 1. 비즈니스 로직을 처리할 서비스
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun onOrderPaid(): (OrderPaidEvent) -> Unit = { event ->
        log.info(">> Received OrderPaidEvent: {}", event)
        try {
            // 2. 서비스 레이어에 작업 위임
            shippingService.startShipping(event)
        } catch (e: Exception) {
            // 3. 에러 처리
            log.error("Failed to process shipping for orderId: ${event.orderId}", e)
            // 예외를 던지면 Spring Cloud Stream의 재시도/DLQ 메커니즘이 동작
            throw e
        }
    }
}