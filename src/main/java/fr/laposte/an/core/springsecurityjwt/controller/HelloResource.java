package fr.laposte.an.core.springsecurityjwt.controller;

import fr.laposte.an.core.springsecurityjwt.model.AuthenficationRequest;
import fr.laposte.an.core.springsecurityjwt.model.AuthenticationResponse;
import fr.laposte.an.core.springsecurityjwt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping( "/hello" )
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenficationRequest authenficationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenficationRequest.getUserName(), authenficationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username and password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenficationRequest.getUserName());

        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
