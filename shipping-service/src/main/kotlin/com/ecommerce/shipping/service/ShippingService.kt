package com.ecommerce.shipping.service

import com.ecommerce.shipping.event.OrderPaidEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ShippingService {
    fun startShipping(event: OrderPaidEvent) {
        TODO("Not yet implemented")
    }
}