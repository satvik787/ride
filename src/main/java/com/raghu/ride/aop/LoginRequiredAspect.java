package com.raghu.ride.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.raghu.ride.models.ResponseDTO;
import com.raghu.ride.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginRequiredAspect {
    private final AuthService authService;

    @Autowired
    public LoginRequiredAspect(AuthService authService) {
        this.authService = authService;
    }

    @Pointcut("@annotation(com.raghu.ride.aop.LoginRequired)")
    public void LoginRequiredPointcut() {}

    @Around("LoginRequiredPointcut()")
    public Object LoginRequired(ProceedingJoinPoint  joinPoint) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = null;
        for(Object arg: args) {
            if(arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                break;
            }
        }
        if(request == null) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().errors(List.of("Request not found")).success(false).build());
        }
        String token = request.getHeader("Authorization");
        if(token == null) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().errors(List.of("Token not found")).success(false).build());
        }
        Long userId = this.authService.parseToken(token);
        System.out.println("userId: " + userId);
        if(userId == null) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().errors(List.of("Invalid token")).success(false).build());
        }
        try {
            request.setAttribute("userId", userId);
            return joinPoint.proceed();
        } catch (Throwable e) {
            return ResponseEntity.badRequest().body(ResponseDTO.builder().errors(List.of(e.getMessage())).success(false).build());
        }
    }
}
