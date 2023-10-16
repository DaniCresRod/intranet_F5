package com.intranet_F5.Config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/variables.properties")
@Getter
public class EnvVariablesConfig {
    @Value("${SECRET_KEY}")
    private String S_KEY;
}
