package email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class SendWithAttachment {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.from", "cse482atisik@gmail.com");
        props.put("mail.smtp.auth", "true");
        Authenticator a = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cse482atisik@gmail.com", "EmineEkin");
            }
        };

        Session session = Session.getDefaultInstance(props, a);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO, "emine.ekin@gmail.com");
            msg.setSubject("JavaMail hello world example");
            msg.setSentDate(new Date());
            Multipart mp = new MimeMultipart();
            BodyPart bp = new MimeBodyPart();
            bp.setText("Hellö");
            mp.addBodyPart(bp);

            BodyPart partForAtt = new MimeBodyPart();
            String filename = "getFile.jpg";
            DataSource source = new FileDataSource(filename);
            partForAtt.setDataHandler(new DataHandler(source));
            partForAtt.setFileName(filename);
            mp.addBodyPart(partForAtt);
            msg.setContent(mp);
            Transport.send(msg);
            System.out.println("gonderdim mesaji");
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }
}