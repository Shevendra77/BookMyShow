package com.cfs.BMS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig {

    // @Bean se hum WebMvcConfigurer ka object
    // Spring container mein register kar rahe hain.
    //
    // WebMvcConfigurer ki help se hum
    // Spring MVC ki configuration customize kar sakte hain.
    @Bean
    public WebMvcConfigurer corsConfigMapping() {

        // Anonymous WebMvcConfigurer object return kar rahe hain.
        return new WebMvcConfigurer() {

            // Ye method CORS rules configure karne ke liye use hota hai.
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // /api/** ka matlab:
                // Ye CORS configuration un sabhi API endpoints
                // par apply hogi jo /api/ se start hote hain.
                //
                // Examples:
                // /api/movies
                // /api/bookings
                // /api/users
                // /api/payments
                registry.addMapping("/api/**")


                        // Sirf in frontend origins ko allow kar rahe hain.
                        //
                        // localhost:5500
                        // aur
                        // 127.0.0.1:5500
                        //
                        // dono localhost ko represent karte hain,
                        // lekin browser ke liye ye different origins ho sakte hain.
                        .allowedOrigins(
                                "http://localhost:5500",
                                "http://127.0.0.1:5500"
                        )


                        // Cross-origin requests mein ye HTTP methods
                        // allowed hain.
                        //
                        // GET    -> data read karna
                        // POST   -> new data create karna
                        // PUT    -> existing data update karna
                        // DELETE -> data delete karna
                        // OPTIONS -> CORS preflight request
                        .allowedMethods(
                                "GET",
                                "POST",
                                "PUT",
                                "DELETE",
                                "OPTIONS"
                        )


                        // "*" ka matlab kisi bhi request header ko
                        // CORS ke perspective se allow karna.
                        //
                        // Examples:
                        // Content-Type
                        // Authorization
                        // Accept
                        .allowedHeaders("*");
            }
        };
    }
}