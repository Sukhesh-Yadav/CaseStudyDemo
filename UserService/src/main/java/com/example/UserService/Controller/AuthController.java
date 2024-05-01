package com.example.UserService.Controller;

import com.example.UserService.Model.AuthRequest;
import com.example.UserService.Model.User;
import com.example.UserService.Service.AuthService;
import com.example.UserService.Service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/register")
    public String addNewUser(@RequestBody User user){

        log.info("User trying to register");
        return authService.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        log.info("Generating a token");
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return authService.generateToken(authRequest.getUsername());
        }else {
            throw new RuntimeException("User do not exist");
        }
    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        log.info("Validating user");
        authService.validateToken(token);
        return "token is valid";
    }

    @PostMapping("/getusername")
    public ResponseEntity<String> getUsername(@RequestBody String token){
        log.info("Getting username");
        return jwtService.extractSubject(token);
    }

    @GetMapping("/getEmail")
    public String getEmail(@RequestBody String username){
        return authService.getEmail(username);
    }
}
