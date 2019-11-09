package email;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

/**
 *
 * @author Emine
 */
public class ReceiveWithAttachment {
    public static void main(String[] args){
        //Define Protocol
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        //Create Session
        Session session = Session.getInstance(props, null);
        try {
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "cse482atisik@gmail.com", "EmineEkin");
            System.out.println(store);

            Folder[] f = store.getDefaultFolder().list();
            System.out.println("Folder list");
            for(Folder fd: f)
                System.out.println("" +fd.getName());

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            int msgCount = inbox.getMessageCount();
            System.out.println("Message count "+msgCount);
            for(int i=0;i<10;i++){
                System.out.println("Message "+i);
                Message msg = inbox.getMessage(i+1);
                Address[] in = msg.getFrom();
                for (Address address : in) {
                    System.out.println("FROM " + address.toString());
                }
                Multipart mp = (Multipart) msg.getContent();
                int partCount = mp.getCount();

                for(int j=0;j<partCount;j++){
                    MimeBodyPart bp =(MimeBodyPart) mp.getBodyPart(j); //get the body part
                    if(Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())){
                        //so we have a file in this part
                        bp.saveFile(bp.getFileName());
                        System.out.println("dosyayi da sakladim");

                    }
                }

            }

//            BodyPart bp = mp.getBodyPart(0);
//            System.out.println("SENT DATE " + msg.getSentDate());
//            System.out.println("SUBJECT " + msg.getSubject());
//            System.out.println("CONTENT " + bp.getContent());

        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}