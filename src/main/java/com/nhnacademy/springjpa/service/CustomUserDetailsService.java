package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import java.util.Collections;
import java.util.Objects;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final ResidentRepository residentRepository;

    public CustomUserDetailsService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Resident resident = residentRepository.findById(username);

        if (Objects.isNull(resident)) {
            throw new UsernameNotFoundException(username + " NOT FOUND");
        }

        return new User(
            username,
            resident.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(resident.getAuthority().getAuthority()))
        );
    }
}
