package com.ecommerce.order.service

import com.ecommerce.order.client.ProductServiceClient
import com.ecommerce.order.command.CreateOrderCommand
import com.ecommerce.order.domain.Order
import com.ecommerce.order.domain.OrderRepository
import com.ecommerce.order.domain.OrderStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productServiceClient: ProductServiceClient,
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
}