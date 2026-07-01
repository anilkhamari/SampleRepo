package com.example.helloworld.web.service;

import com.example.helloworld.web.model.Notification;
import com.example.helloworld.web.model.User;
import com.example.helloworld.web.repository.NotificationRepository;
import com.example.helloworld.web.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Get or create a user, and initialize with sample notifications if new.
     */
    private User getOrCreateUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            user = new User(username);
            user = userRepository.save(user);
            // Seed with sample notifications
            addNotification(username, "Welcome back! Your profile was viewed 3 times today.");
            addNotification(username, "Security alert: New login from unknown device.");
            addNotification(username, "Your subscription renews in 5 days.");
            user = userRepository.findByUsername(username).orElse(user);
        }
        return user;
    }

    public List<Notification> getNotificationsForUser(String username) {
        User user = getOrCreateUser(username);
        return notificationRepository.findByUserOrderByIdDesc(user);
    }

    public long getUnreadCountForUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return 0;
        }
        return notificationRepository.countByUserAndReadFalse(user);
    }

    public void addNotification(String username, String text) {
        User user = getOrCreateUser(username);
        Notification notification = new Notification(text);
        notification.setUser(user);
        notificationRepository.save(notification);
    }

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    public void markAllRead(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            List<Notification> notifications = notificationRepository.findByUserOrderByIdDesc(user);
            for (Notification n : notifications) {
                n.setRead(true);
            }
            notificationRepository.saveAll(notifications);
        }
    }

    public void clearNotifications(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            List<Notification> notifications = notificationRepository.findByUserOrderByIdDesc(user);
            notificationRepository.deleteAll(notifications);
        }
    }
}
