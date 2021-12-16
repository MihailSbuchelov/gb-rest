package ru.gb.gbrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditAwareBean")
@ComponentScan("ru.gb")
@EnableTransactionManagement
@EnableJpaRepositories("ru.gb.gbrest.dao")
public class ShopConfig {

    @Bean
    public AuditorAware<String> auditAwareBean() {
        return () -> Optional.of("User");
    }
}