//package com.cfs.BMS.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.io.IOException;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class RateLimitInterceptor implements HandlerInterceptor {
//
//    private static final int MAX_REQUESTS = 10;
//    private static final long WINDOW_MS = 60_000;
//
//    private final ConcurrentHashMap<String, Window> clients =
//            new ConcurrentHashMap<>();
//
//    @Override
//    public boolean preHandle(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            Object handler) throws IOException {
//
//        String clientIp = request.getRemoteAddr();
//        String key = clientIp + ":" + request.getRequestURI();
//
//        Window window = clients.computeIfAbsent(
//                key, ignored -> new Window());
//
//        boolean allowed = window.allowRequest();
//
//        if (!allowed) {
//            response.setStatus(429);
//            response.setContentType("application/json");
//            response.getWriter().write(
//                    "{\"error\":\"Too many requests. Try again later.\"}"
//            );
//            return false;
//        }
//
//        return true;
//    }
//
//    private static class Window {
//        private long startTime = System.currentTimeMillis();
//        private int count = 0;
//
//        synchronized boolean allowRequest() {
//            long now = System.currentTimeMillis();
//
//            if (now - startTime >= WINDOW_MS) {
//                startTime = now;
//                count = 0;
//            }
//
//            if (count >= MAX_REQUESTS) {
//                return false;
//            }
//
//            count++;
//            return true;
//        }
//    }
//}
