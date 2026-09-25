package com.companages.controller;
import com.companages.dto.DashboardResponse; import com.companages.service.DashboardService; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/dashboard") public class DashboardController { private final DashboardService service; public DashboardController(DashboardService s){service=s;} @GetMapping DashboardResponse get(){return service.get();} }
