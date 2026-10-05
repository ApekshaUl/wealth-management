package com.wealthgame.backend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class CorrelationIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    )
    {
        String correlationId = request.getHeader("X-Correlation-ID");
        if(correlationId == null || correlationId.isBlank())
        {
            correlationId = UUID.randomUUID().toString();
        }

        response.setHeader("X-Correlation-ID",correlationId);
        System.out.println("Correlation ID: " +correlationId + " | " +request.getMethod() + " " +request.getRequestURI());
        return true;
    }
}
