package com.ServiHouse.ApiGateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;

@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingGlobalFilter.class);

@Override
public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String clientIp = exchange.getRequest().getRemoteAddress() != null
            ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
            : "unknown";
    String requestUrl = exchange.getRequest().getURI().toString();

    return chain.filter(exchange).then(
        Mono.fromRunnable(() -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            String routeUri = (route != null) ? route.getUri().toString() : "unknown";
            logger.info("Request from {}, URL: {}, redirecting to: {}", clientIp, requestUrl, routeUri);
        })
    );
}

    @Override
    public int getOrder() {
        return -1;
    }
}