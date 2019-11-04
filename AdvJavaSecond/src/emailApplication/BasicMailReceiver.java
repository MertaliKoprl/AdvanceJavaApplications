package emailApplication;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class BasicMailReceiver {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        //Create Session
        Session session = Session.getInstance(props, null);
        try {
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "cse482atisik@gmail.com", "EmineEkin");
            System.out.println(store);

            Folder[] f = store.getDefaultFolder().list();
            for (Folder fd : f)
                System.out.println("" + fd.getName());
            // Folder tryMe = store.getFolder("try me");
            // MimeMessage mm =(MimeMessage) tryMe.getMessage(0);
            //REFRESHTE BUNU KULLAN inbox.hasNewMessages()
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            System.out.println(inbox.getMessageCount());
            Message msg = inbox.getMessage(inbox.getMessageCount() - 1);

            Address[] in = msg.getFrom();

            for (Address address : in) {
                System.out.println("FROM " + address.toString());
            }


            Multipart mp = (Multipart) msg.getContent();

            BodyPart bp = mp.getBodyPart(0);
            System.out.println("SENT DATE " + msg.getSentDate());
            System.out.println("SUBJECT " + msg.getSubject());
            System.out.println("CONTENT " + bp.getContent());

        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }

}


