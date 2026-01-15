package com.alumnexus.lms.Service;

import com.alumnexus.lms.Entity.ProfileEntity;
import com.alumnexus.lms.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ProfileEntity entity = profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No User found with email: " + email));
        return User.builder()
                .username(entity.getEmail())
                .password(entity.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_" + entity.getRole().name()))
                .build();
    }
}
