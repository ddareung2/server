package com.ddareung2.server.jwtToekn;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ddareung2.server.admin.Admin;
import com.ddareung2.server.admin.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
	
	private final AdminRepository adminRepository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin adminItem = adminRepository.findAccountByUsername(username);
        if (adminItem == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(adminItem.getUsername())
                .password(adminItem.getPassword())
                .roles(adminItem.getRole().getName())
                .build();
    }
}
