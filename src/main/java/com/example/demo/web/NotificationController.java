package com.example.demo.web;

import com.example.demo.services.NotificationService;
import com.example.demo.services.UserService;
import com.example.demo.web.dto.NotificationDTO;
import com.example.demo.web.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final UserService userService;
    private final NotificationService notificationService;

    public NotificationController(UserService userService,
                                  NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        this.userService.createUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO dto) {
        this.notificationService.createNotification(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }
}
