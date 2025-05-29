package com.example.demo.services;

import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.web.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserService userService;
    private final MailSender sender;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository,
                               UserService userService,
                               MailSender sender) {
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.sender = sender;
    }

    public void createNotification(NotificationDTO dto) {
        User user = this.userService.getUserById(dto.getUserId());

        Notification notification = new Notification();
        notification.setUserEmail(user.getEmail());
        notification.setTitle(dto.getTitle());
        notification.setBody(dto.getBody());
        notification.setCreatedOn(LocalDateTime.now());

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(notification.getUserEmail());
        message.setText(notification.getBody());
        message.setSubject(notification.getTitle());

        try {
            this.sender.send(message);
            this.notificationRepository.save(notification);
        } catch (Exception e) {

            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
