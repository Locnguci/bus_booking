package org.app.bookingbus.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String userInput) throws UsernameNotFoundException {
//        Optional<org.app.bookingbus.model.entities.User> userOtp = accountRepository.findAccountByUserInput(userInput);
//        if (accountOpt.isEmpty()) {
//            throw new UsernameNotFoundException(userInput);
//        }
//        Account account = accountOpt.get();
//        return new User(
//                userInput,
//                account.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + account.getRole()))
//        );
        return null;
    }
}
