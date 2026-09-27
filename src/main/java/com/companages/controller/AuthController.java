package com.companages.controller;
import com.companages.dto.AuthDtos.*; import com.companages.service.AuthService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api") public class AuthController { private final AuthService service; public AuthController(AuthService s){service=s;}
 @PostMapping("/auth/register") ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.register(r));}
 @PostMapping("/auth/login") AuthResponse login(@Valid @RequestBody LoginRequest r){return service.login(r);}
 @PostMapping("/auth/refresh") AuthResponse refresh(@Valid @RequestBody RefreshRequest r){return service.refresh(r);}
 @PostMapping("/auth/logout") @ResponseStatus(HttpStatus.NO_CONTENT) void logout(@Valid @RequestBody LogoutRequest r){service.logout(r);}
 @PostMapping("/auth/forgot-password") @ResponseStatus(HttpStatus.NO_CONTENT) void forgot(@Valid @RequestBody ForgotPasswordRequest r){service.forgotPassword(r);}
 @PostMapping("/auth/reset-password") @ResponseStatus(HttpStatus.NO_CONTENT) void reset(@Valid @RequestBody ResetPasswordRequest r){service.resetPassword(r);}
 @PostMapping("/auth/change-password") @ResponseStatus(HttpStatus.NO_CONTENT) void change(@Valid @RequestBody ChangePasswordRequest r){service.changePassword(r);}
 @GetMapping("/auth/me") UserResponse me(){return service.me();}
 @PutMapping("/users/me") UserResponse profile(@Valid @RequestBody UpdateProfileRequest r){return service.updateProfile(r);}
}
