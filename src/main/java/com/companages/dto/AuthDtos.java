package com.companages.dto;
import jakarta.validation.constraints.*; import java.time.Instant;
public final class AuthDtos { private AuthDtos(){}
 public record RegisterRequest(@NotBlank @Size(max=100) String name,@NotBlank @Email String email,@NotBlank @Size(min=8,max=72) String password){}
 public record LoginRequest(@NotBlank @Email String email,@NotBlank String password){}
 public record UserResponse(Long id,String name,String email,String avatarUrl,String phone,String bio,Instant createdAt){}
 public record AuthResponse(String token,String accessToken,String refreshToken,long expiresIn,UserResponse user){}
 public record RefreshRequest(@NotBlank String refreshToken){}
 public record LogoutRequest(@NotBlank String refreshToken){}
 public record ForgotPasswordRequest(@NotBlank @Email String email){}
 public record ResetPasswordRequest(@NotBlank String token,@NotBlank @Size(min=8,max=72) String newPassword){}
 public record ChangePasswordRequest(@NotBlank String currentPassword,@NotBlank @Size(min=8,max=72) String newPassword){}
 public record UpdateProfileRequest(@NotBlank @Size(max=100) String name,@Size(max=500) String avatarUrl,@Size(max=40) String phone,@Size(max=1000) String bio){}
}
