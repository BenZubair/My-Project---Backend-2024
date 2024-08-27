package swahili.cafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    public static final String REQUEST_ABOUT_KUFANYA_KIPINDI = "Request About Kufanya Kipindi.";

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async
    public void sendEMailMessage(String firstName, String to) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(REQUEST_ABOUT_KUFANYA_KIPINDI);
            message.setFrom(fromEmail);
            message.setTo(to);
//            message.setText("Hey, It worked!!! Haha!");
            message.setText(getEmailMessage(firstName));
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    private String getEmailMessage(String firstName) {
        return "Hello " + firstName + ",\n\nYour account has been created.\n\nThe SWAHILI CAFE support team.\n\nThanks.";
    }


}
