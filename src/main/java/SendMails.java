import javax.mail.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.mail.smtp.SMTPTransport;

public class SendMails {
    private static final String SMTP_SERVER = "smtp.partner.outlook.cn";
    private static final String USERNAME = "jason@majun.fun";
    private static final String PASSWORD = "youpasswordHere";

    private static final String EMAIL_FROM = "jason@majun.fun";
    private static final String EMAIL_TO = "test01@majun.fun";
    private static final String EMAIL_TO_CC = "";

    static String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    public static void main(String[] args) {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587"); // default port 25
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");//add TLS version for lower javax.mail.JAR

        Session sendMailSession = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message msg = new MimeMessage(sendMailSession);
        //定时任务
        Timer timer = new Timer();
        //每5秒来一次
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    // send 5 times
                    for (int i = 0; i < 5; i++) {
                        EMAIL_SUBJECT = EMAIL_SUBJECT+ i;

                        // from
                        msg.setFrom(new InternetAddress(EMAIL_FROM));

                        // to
                        msg.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(EMAIL_TO, false));

                        // cc
                        msg.setRecipients(Message.RecipientType.CC,
                                InternetAddress.parse(EMAIL_TO_CC, false));

                        // subject
                        msg.setSubject(EMAIL_SUBJECT);

                        // content
                        msg.setText(EMAIL_TEXT);

                        msg.setSentDate(new Date());
                        // Get SMTPTransport
                        SMTPTransport t = (SMTPTransport) sendMailSession.getTransport("smtp");
                        // connect
                        t.connect(SMTP_SERVER, USERNAME, PASSWORD);
                        t.sendMessage(msg, msg.getAllRecipients());
                        System.out.println("Response: " + t.getLastServerResponse());
                        t.close();
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                System.out.println("每5s来一次");
            }
        },2000,3000);
    }
}
