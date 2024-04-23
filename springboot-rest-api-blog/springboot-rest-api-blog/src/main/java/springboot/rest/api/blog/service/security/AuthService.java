package springboot.rest.api.blog.service.security;

import springboot.rest.api.blog.payload.security.LoginDto;
import springboot.rest.api.blog.payload.security.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
