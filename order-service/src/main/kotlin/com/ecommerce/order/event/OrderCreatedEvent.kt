package com.ecommerce.order.event

data class OrderCreatedEvent(
    val orderId: Long,
    val memberId: Long,
//    val orderDate: LocalDateTime,
    val productId: Long,
//    val status: OrderStatus,
//    val quantity: Int,
//    val unitPrice: BigDecimal,
//    val memberId: Long
)