package com.ecommerce.order.dto

import com.ecommerce.order.domain.OrderStatus
import jakarta.validation.Valid
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.LocalDateTime

data class CreateOrderRequest(
    @field:NotNull(message = "Member ID is required")
    @field:Min(1, message = "Member ID must be greater than 0")
    val memberId: Long,

    @field:NotBlank(message = "Shipping address is required")
    @field:Size(max = 500, message = "Shipping address must not exceed 500 characters")
    val shippingAddress: String,

    val notes: String? = null,

    @field:Valid
    @field:NotEmpty(message = "Order items cannot be empty")
    val items: List<OrderItemCreateRequest>,

    @field:Valid
    @field:NotEmpty(message = "Product items cannot be empty")
    val productId: Long,

    @field:Valid
    @field:Min(0, message = "Quantity must be at least 1")
    val quantity: Int
)

data class OrderItemCreateRequest(
    @field:NotNull(message = "Product ID is required")
    @field:Min(1, message = "Product ID must be greater than 0")
    val productId: Long,

    @field:NotBlank(message = "Product name is required")
    val productName: String,

    @field:NotNull(message = "Quantity is required")
    @field:Min(1, message = "Quantity must be at least 1")
    val quantity: Int,

    @field:NotNull(message = "Unit price is required")
    @field:DecimalMin(value = "0.00", message = "Unit price must be non-negative")
    val unitPrice: BigDecimal
)

data class OrderUpdateRequest(
    val shippingAddress: String? = null,
    val notes: String? = null,
    val status: OrderStatus? = null
)

data class OrderResponse(
    val id: Long,
    val memberId: Long,
    val orderNumber: String,
    val status: OrderStatus,
    val totalAmount: BigDecimal,
    val shippingAddress: String,
    val notes: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val items: List<OrderItemResponse>
)

data class OrderItemResponse(
    val id: Long,
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val unitPrice: BigDecimal,
    val totalPrice: BigDecimal
)

data class OrderSummaryResponse(
    val id: Long,
    val memberId: Long,
    val orderNumber: String,
    val status: OrderStatus,
    val totalAmount: BigDecimal,
    val createdAt: LocalDateTime,
    val itemCount: Int
)

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)

data class ErrorResponse(
    val success: Boolean = false,
    val message: String,
    val errors: List<String>? = null
)

// Member service communication DTOs
data class MemberResponse(
    val id: Long,
    val username: String,
    val email: String,
    val fullName: String,
    val status: String
)