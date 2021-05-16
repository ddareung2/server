package com.ddareung2.server.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtController {
	
	private final AdminService adminService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
    		HttpServletResponse response) throws IllegalArgumentException {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = adminService
            .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie","accessToken="+token+";Max-Age=30;");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }

    private void authenticate(String username, String password) throws IllegalArgumentException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new IllegalArgumentException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("INVALID_CREDENTIALS", e);
        }
    }
}
