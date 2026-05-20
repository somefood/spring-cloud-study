package com.ecommerce.gateway.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GlobalLoggingFilter : GlobalFilter, Ordered {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void?> {
        val request = exchange.request

        // --- Pre-Filter: 요청이 마이크로서비스로 가기 전 ---
        log.info(">>>>> [Global Filter] Request: ID={}, URI={}, Path={}",
            request.id, request.uri, request.path)

        // --- Post-Filter: 마이크로서비스에서 응답이 돌아온 후 ---
        // chain.filter(exchange)가 실행되어야 다음 필터 또는 목적지로 요청이 전달된다.
        // .then() 블록은 모든 로직이 끝난 후 실행된다.
        return chain.filter(exchange).then(Mono.fromRunnable {
            val response = exchange.response
            log.info("<<<<< [Global Filter] Response: Status={}, latency={}ms",
                response.statusCode, 0) // TODO (정확한 latency 측정 로직은 추가 구현 필요)
        })
    }

    // 필터의 실행 순서를 지정한다. 낮은 값일수록 먼저 실행된다.
    override fun getOrder(): Int {
        return 0 // (또는 Ordered.HIGHEST_PRECEDENCE)
    }
}