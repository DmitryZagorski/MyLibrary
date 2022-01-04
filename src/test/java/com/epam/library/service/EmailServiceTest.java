package com.epam.library.service;

import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    @Test
    void getInstance() {
    }

    @Test
    void notifyCustomerRegistration() {
    }

    @Test
    void testMessageSending() throws MessagingException {
        EmailService.getInstance().notifyCustomerRegistration("TrainEpam2021@gmail.com", 1);
    }
}