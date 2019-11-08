package sendWithAttachment;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.util.Properties;

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
            for(int i=0;i<msgCount;i++){
                System.out.println("Message "+i);
                Message msg = inbox.getMessage(i+1);
                Address[] in = msg.getFrom();
                for (Address address : in) {
                    System.out.println("FROM " + address.toString());
                }
                if(msg.getContent() instanceof Multipart){
                Multipart mp = (Multipart) msg.getContent();
                int partCount = mp.getCount();
                    System.out.println("Part Count : "+partCount);
                boolean buldum  = false;
                for(int j=0;j<partCount;j++){
                    MimeBodyPart bp =(MimeBodyPart) mp.getBodyPart(j); //get the body part
                    if(Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())){
                        //so we have a file in this part
                        bp.saveFile(bp.getFileName());
                        System.out.println("dosyayi da sakladim");
                        buldum = true;
                    }//ON CLICK OLARAK EKLE !!!
                    else {
                        //body part has some content
                        System.out.println(bp.getContent());
                    }

                }
                if (buldum) break;

            }
                else{
                    //plain text Content
                    System.out.println(msg.getContent());

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
