package emailApplication;


import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Date;
import java.util.Properties;

public class Email {

    //emine_ekin@yahoo.com   VlkVlkW12naW
    //cse482atisik@gmail.com   , "EmineEkin"



    public static void main(String[] args) {
        Properties props= new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp/port","465");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.from","cse482atisik@gmail.com");
        props.put("mail.smtp.auth","true");

        Authenticator a = new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("cse482atisik@gmail.com","EmineEkin");
            }
        };


        Session session = Session.getDefaultInstance(props,a);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO,"emine.ekin@gmail.com");
            msg.setSubject("JavaX Mail Dikkat Hocam");
            msg.setSentDate(new Date());
            msg.setText("Bismillahirrahmanirrahim , ilk mail uygulamam hayirli ve ugurlu olsun iyi gunler... Mertali Koprulu");
            Transport.send(msg);
            System.out.println("Message sent!");
            JOptionPane.showMessageDialog(null,"message sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
