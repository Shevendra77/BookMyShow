
package com.cfs.BMS.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final int MAX_REQUESTS = 10;
    private static final long WINDOW_MS = 60_000;

    private final ConcurrentHashMap<String, Window> clients =
            new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {

        String clientIp = request.getRemoteAddr();
        String key = clientIp + ":" + request.getRequestURI();

        Window window = clients.computeIfAbsent(
                key, ignored -> new Window()
        );

        RequestResult result = window.checkRequest();

        response.setHeader(
                "X-RateLimit-Limit",
                String.valueOf(MAX_REQUESTS)
        );
        response.setHeader(
                "X-RateLimit-Remaining",
                String.valueOf(result.remaining())
        );

        if (!result.allowed()) {
            response.setStatus(429);
            response.setHeader(
                    "Retry-After",
                    String.valueOf(result.retryAfterSeconds())
            );
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            response.getWriter().write(
                    "{\"error\":\"Too many requests. Try again later.\"}"
            );

            return false;
        }

        return true;
    }

    private record RequestResult(
            boolean allowed,
            int remaining,
            long retryAfterSeconds
    ) {}

    private static class Window {

        private long startTime = System.currentTimeMillis();
        private int count = 0;

        synchronized RequestResult checkRequest() {
            long now = System.currentTimeMillis();

            if (now - startTime >= WINDOW_MS) {
                startTime = now;
                count = 0;
            }

            if (count >= MAX_REQUESTS) {
                long remainingMs =
                        WINDOW_MS - (now - startTime);

                long retryAfterSeconds =
                        Math.max(1, (remainingMs + 999) / 1000);

                return new RequestResult(
                        false, 0, retryAfterSeconds
                );
            }

            count++;

            return new RequestResult(
                    true, MAX_REQUESTS - count, 0
            );
        }
    }
}
