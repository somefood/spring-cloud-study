// shipping-service/src/main/kotlin/com/ecommerce/shipping/consumer/ShippingEventConsumer.kt
package com.ecommerce.shipping.consumer

import com.ecommerce.shipping.event.OrderPaidEvent
import com.ecommerce.shipping.service.ShippingService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class ShippingEventConsumer(
    private val shippingService: ShippingService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * @KafkaListener 어노테이션을 통해 특정 토픽과 그룹의 메시지를 구독
     */
    @KafkaListener(
        topics = ["ecommerce.orders.paid"],
        groupId = "shipping-service-group"
    )
    fun handleOrderPaidEvent(
        @Payload event: OrderPaidEvent, // 1. 메시지 페이로드를 OrderPaidEvent 객체로 자동 역직렬화
        @Header(KafkaHeaders.RECEIVED_KEY) key: String, // 2. 메시지 키(orderId)
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.OFFSET) offset: Long
    ) {
        log.info("Received event: {}, key={}, partition={}, offset={}",
            event, key, partition, offset)

        try {
            // 3. 비즈니스 로직 처리
            shippingService.startShipping(event)
        } catch (e: Exception) {
            log.error("Error processing event for orderId ${event.orderId}", e)
            // 에러 발생 시, Spring Kafka의 기본 에러 핸들러가 동작하여 재시도 또는 DLQ로 보냄
            throw e
        }
    }
}