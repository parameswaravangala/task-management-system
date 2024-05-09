package com.bank.auth.config;

import com.bank.auth.entity.Permission;
import com.bank.auth.entity.Role;
import com.bank.auth.entity.User;
import com.bank.auth.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Optional;

@Configuration
public class CustomClaimsConfiguration {
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer(UserDetailRepository userDetailRepository, @Value("${authorizationserver.issuer}") String issuer) {
        return (context) -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {


                context.getClaims().claims((claims) -> {
                    Optional<User> userOptional = userDetailRepository.findByUsername(context.getPrincipal().getName());
                    if (userOptional.isPresent()) {
						User user = userOptional.get();
                        claims.replace("iss",issuer);
                        claims.put("roles", user.getRoles().stream().map(Role::getName).toArray(String[]::new));
                        claims.put("authorities", user.getRoles().stream().flatMap(role -> role.getPermissions().stream().map(Permission::getName).toList().stream()).toArray(String[]::new));
                    }
                });
            }
        };
    }
}