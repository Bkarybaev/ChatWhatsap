package java16.chatwhatsap.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Бул жерде сиздин колдонуучу маалыматтарыңызды алуу керек
        return User.withUsername("username")
                .password("password")
                .authorities("ROLE_USER")
                .build();
    }
}
