package com.crud.simple.service;

import com.crud.simple.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAppDetailsService implements UserDetailsService {

    private final UserAppRepository userAppRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAppRepository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new UsernameNotFoundException("Email " + email + " Not Found")
        );
    }
}
