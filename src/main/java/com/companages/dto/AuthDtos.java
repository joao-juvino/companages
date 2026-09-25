package com.companages.dto;
import jakarta.validation.constraints.*; import java.time.Instant;
public final class AuthDtos { private AuthDtos(){}
 public record RegisterRequest(@NotBlank @Size(max=100) String name,@NotBlank @Email String email,@NotBlank @Size(min=8,max=72) String password){}
 public record LoginRequest(@NotBlank @Email String email,@NotBlank String password){}
 public record UserResponse(Long id,String name,String email,Instant createdAt){}
 public record AuthResponse(String token,UserResponse user){}
}
