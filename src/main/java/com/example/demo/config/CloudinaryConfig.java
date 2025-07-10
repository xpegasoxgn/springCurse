package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dlz01zss5",
            "api_key", "147269221379532",
            "api_secret", "ycQkfVZkPRgbtAFGUHYzaO9lY64",
            "secure", true
        ));
    }
}
