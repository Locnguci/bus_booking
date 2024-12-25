package org.app.bookingbus.config;

import org.app.bookingbus.security.SecurityUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class CustomerAuditorWare implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<String> username = SecurityUtil.getUserName();
        return username.isPresent() ? username : Optional.of("System");
    }
}
