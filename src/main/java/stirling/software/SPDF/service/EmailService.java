package stirling.software.SPDF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired private JavaMailSender emailSender;

    @Autowired private TemplateEngine templateEngine;

    public void sendSignMail(String to, String subject, String url) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom("noreply@Stirlingpdf.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        Context context = new Context();

        context.setVariable("url", url);
        String processedString = templateEngine.process("template", context);

        mimeMessageHelper.setText(processedString, true);

        emailSender.send(mimeMessage);
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
