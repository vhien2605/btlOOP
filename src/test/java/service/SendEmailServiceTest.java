package service;

import app.service.subService.GMailer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SendEmailServiceTest {

    private GMailer gMailer;

    @BeforeEach
    public void initialize() throws Exception {
        String targetEmail = "hvu6582@gmail.com";
        gMailer = new GMailer(targetEmail);
    }

    @Test
    public void testSendEmail() throws Exception {
        gMailer.sendMail("testing", "this email for testing", null);
    }
}
