package com.mangastech.config;

import java.util.Optional;
import com.mangastech.security.UsuarioPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Braian
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditor() {
        return () -> {
            String atualAuditor;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
                atualAuditor = authentication.getPrincipal().toString();
            } else {
                atualAuditor = ((UsuarioPrincipal) authentication.getPrincipal()).getUsername();
            }
            return Optional.of(atualAuditor);
        };
    }
}