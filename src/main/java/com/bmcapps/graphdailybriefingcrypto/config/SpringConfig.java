package com.bmcapps.graphdailybriefingcrypto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:secrets.properties")
public class SpringConfig {
}
