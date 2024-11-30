package com.capstone.AdminCapstone.Service;

import com.capstone.AdminCapstone.Entities.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSenderServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailSenderService emailSenderService;

    @Test
    void testSendEmail() {
        // Given
        String toEmail = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        // When
        emailSenderService.sendEmail(toEmail, subject, body);

        // Then
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}

