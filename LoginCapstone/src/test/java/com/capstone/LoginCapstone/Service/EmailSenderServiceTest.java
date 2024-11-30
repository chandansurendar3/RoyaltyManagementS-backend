package com.capstone.LoginCapstone.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.capstone.LoginCapstone.Services.EmailSenderService;

@ExtendWith(MockitoExtension.class)
public class EmailSenderServiceTest {

    @InjectMocks
    private EmailSenderService emailSenderService;

    @Mock
    private JavaMailSender javaMailSender;

    // No need for @BeforeEach setUp method as MockitoExtension takes care of it

    @Test
    public void testSendEmail() {
        emailSenderService.sendEmail("test@example.com", "Test Subject", "Test Body");

        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
