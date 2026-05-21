package com.ecommerce.order.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val memberId: Long,

    @field:NotNull
    var productId: Long,

    @field:NotNull
    var shippingAddress: String,

    @field:NotNull
    var status: OrderStatus = OrderStatus.PENDING,
) : BaseEntity() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(Order::class.java)
    }

    /**
     * 배송지 변경 로직 (비즈니스 규칙 포함)
     */
    fun updateShippingInfo(newAddress: String) {
        // [규칙] 이미 배송 시작된 주문은 배송지를 변경할 수 없다.
        if (this.status != OrderStatus.PENDING) {
            throw IllegalStateException("배송이 시작된 주문은 주소를 변경할 수 없습니다.")
        }
        this.shippingAddress = newAddress
    }

    /**
     * 주문 확인 상태로 변경
     */
    fun confirm(){
        this.status = OrderStatus.CONFIRM
    }

    /**
     * 주문 취소 로직 (비즈니스 규칙 포함)
     */
    fun cancel(reason: String) {
        // [규칙] 이미 배송 시작된 주문은 취소할 수 없다.
        log.error(reason)
        if (this.status != OrderStatus.PENDING) {
            throw IllegalStateException("배송이 시작된 주문은 취소할 수 없습니다.",)
        }
        this.status = OrderStatus.CANCELLED
    }


}

enum class OrderStatus { PENDING, PAID, CONFIRM, SHIPPED, DELIVERED, CANCELLED }