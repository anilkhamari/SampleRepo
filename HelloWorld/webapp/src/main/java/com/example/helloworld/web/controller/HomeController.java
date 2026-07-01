package com.example.helloworld.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.helloworld.web.service.NotificationService;
import java.util.List;

@Controller
    public class HomeController {

    private final NotificationService notificationService;

    public HomeController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Displays the home page. Accessible only to authenticated users.
     */
    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
            long unreadCount = notificationService.getUnreadCountForUser(username);
            model.addAttribute("unreadCount", unreadCount);
        }
        return "home";
    }

    /**
     * Redirects to home page.
     */
    @GetMapping("/home")
    public String homeRedirect() {
        return "redirect:/";
    }

    /**
     * Displays the user profile page. Accessible only to authenticated users.
     */
    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("email", authentication.getName() + "@example.com");
            model.addAttribute("lastLogin", "Today");
        }
        return "profile";
    }

    /**
     * Displays a simple notifications page populated with sample notifications.
     */
    @GetMapping("/notifications")
    public String notifications(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            model.addAttribute("username", username);
            List<com.example.helloworld.web.model.Notification> notifications = notificationService.getNotificationsForUser(username);
            model.addAttribute("notifications", notifications);
        }
        return "notifications";
    }

    @PostMapping("/notifications/markAllRead")
    public String markAllRead(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            notificationService.markAllRead(authentication.getName());
        }
        return "redirect:/notifications";
    }

    @PostMapping("/notifications/clear")
    public String clearNotifications(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            notificationService.clearNotifications(authentication.getName());
        }
        return "redirect:/notifications";
    }

    @PostMapping("/notifications/{id}/delete")
    public String deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return "redirect:/notifications";
    }

    @PostMapping("/notifications/{id}/markAsRead")
    public String markNotificationAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return "redirect:/notifications";
    }
}
