package com.company.demo.util;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Integer> {


    public Optional<Integer> getCurrentAuditor() {
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Integer> userId = Optional.of(100);
        return userId;
    }

}