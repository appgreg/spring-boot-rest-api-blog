package springboot.rest.api.blog.controller.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.rest.api.blog.payload.security.JWTAuthResponse;
import springboot.rest.api.blog.payload.security.LoginDto;
import springboot.rest.api.blog.payload.security.RegisterDto;
import springboot.rest.api.blog.service.security.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    final
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String registerMessage = authService.register(registerDto);
        return new ResponseEntity<>(registerMessage, HttpStatus.CREATED);
    }
}
