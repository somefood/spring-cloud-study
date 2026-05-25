package com.ecommerce.order.service

import com.ecommerce.order.client.ProductServiceClient
import com.ecommerce.order.command.CreateOrderCommand
import com.ecommerce.order.domain.Order
import com.ecommerce.order.domain.OrderRepository
import com.ecommerce.order.domain.OrderStatus
import com.ecommerce.order.dto.CreateOrderRequest
import com.ecommerce.order.dto.OrderResponse
import com.ecommerce.order.event.OrderPaidEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productServiceClient: ProductServiceClient,
    private val streamBridge: StreamBridge, // 2. StreamBridge 주입
) {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun createOrder(command: CreateOrderCommand): Long {
        // 1. 주문을 'PENDING' 상태로 생성하고 DB에 저장
        val order = Order(
            memberId = command.memberId,
            productId = command.productId,
            shippingAddress = command.shippingAddress,
            status = OrderStatus.PENDING,
        )
        val savedOrder = orderRepository.save(order)
        val orderId = savedOrder.id!!

        log.info("OrderCreatedEvent sent for orderId: {}", orderId)
        return orderId
    }

    @Transactional
    fun createAndPayOrder(memberId: Long, request: CreateOrderRequest): OrderResponse {
        // ...
        // (06장에서 구현한 상품 재고 확인 및 주문 생성 로직)
        val savedOrder = orderRepository.save(newOrder)
        // ...
        // (결제 서비스 연동 로직)
        // paymentService.processPayment(...)
        // 결제가 성공했다고 가정
        savedOrder.markAsPaid()

        // 3. 이벤트 생성
        val event = OrderPaidEvent(
            orderId = savedOrder.id!!,
            memberId = savedOrder.memberId,
            totalAmount = savedOrder.totalAmount.toBigDecimal(),
            orderLines = savedOrder.orderLines.map {
                OrderPaidEvent.OrderLineItem(it.productId, it.quantity)
            }
        )

        // --- 4. StreamBridge로 이벤트 발행 ---
        // 첫 번째 인자: application.yml에 정의한 바인딩 이름
        // 두 번째 인자: 보낼 이벤트 객체
        val isSent = streamBridge.send("orderPaidEventProducer-out-0", event)
        log.info("OrderPaidEvent sent for orderId {}: {}", savedOrder.id, isSent)

        return savedOrder.toResponse()
    }
}