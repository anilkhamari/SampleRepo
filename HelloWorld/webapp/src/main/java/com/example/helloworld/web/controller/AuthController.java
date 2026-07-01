package com.example.helloworld.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    /**
     * Displays the login page.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Logout is handled automatically by Spring Security
     * This mapping is here for reference only (redirects to login after logout).
     */
    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "redirect:/login?logout=true";
    }
}
