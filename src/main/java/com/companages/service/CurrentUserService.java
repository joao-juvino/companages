package com.companages.service;
import com.companages.entity.User; import com.companages.repository.UserRepository; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.stereotype.Service;
@Service public class CurrentUserService { private final UserRepository users; public CurrentUserService(UserRepository u){users=u;} public User get(){return users.findByEmailIgnoreCase(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();} }
