package com.gymbuddy.backgymbuddy.admin.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();
        return Optional.of(loginId);
    }

    /**
     * User user = (User) authentication.getPrincipal();
     *         return Optional.of(user.getUserId());
     */

    /*@Bean
    public AuditorAware<String> auditorProvider() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String loginId = userDetails.getUsername();
        return () -> Optional.of(loginId);
    }*/
}
