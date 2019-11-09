package Email;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public User u1 = new User("", "");
    FilteredList<Email> flEmail;
    private ObservableList<Email> mailBox = FXCollections.observableArrayList();
    private String FileAttached="";

    @FXML
    TableView<Email> mailList = new TableView<Email>();
    @FXML
    TextField searchBoxField;
    @FXML
    TextField fromField;
    @FXML
    TextField toField;
    @FXML
    TextField subjectField;
    @FXML
    Button replyBtn;
    @FXML
    Button forwardBtn;
    @FXML
    Button discardBtn;
    @FXML
    Button sendMailBtn;
    @FXML
    Button loginBtn;
    @FXML
    TextField userMailAddress;
    @FXML
    PasswordField passwordField;
    @FXML
    Label numberOfMessages;
    @FXML
    TextArea mailBodyArea;
    @FXML
    TableColumn headMail;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headMail.setCellValueFactory(
                new PropertyValueFactory<Email, String>("fromMailAdress")
        );

    }

    public void sendMail(ActionEvent actionEvent) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.from", u1.getUsersMailAdress());
        props.put("mail.smtp.auth", "true");
        Authenticator a = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(u1.getUsersMailAdress(), u1.getUsersPassword());
            }
        };

        Session session = Session.getDefaultInstance(props, a);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            fromField.setText(u1.getUsersMailAdress());
            fromField.setEditable(false);
            msg.setRecipients(Message.RecipientType.TO, toField.getText());
            msg.setSubject(subjectField.getText()); //Puts Subject to the Subject Field
            msg.setSentDate(new Date());
            Multipart mp = new MimeMultipart();
            BodyPart bp = new MimeBodyPart();
            bp.setText(mailBodyArea.getText());//Puts Context to body part
            mp.addBodyPart(bp);

            BodyPart partForAtt = new MimeBodyPart();
           if(!FileAttached.equals("")) {
               String filename = FileAttached;//If There Are Any
               DataSource source = new FileDataSource(filename);
               partForAtt.setDataHandler(new DataHandler(source));
               partForAtt.setFileName(filename);

            mp.addBodyPart(partForAtt);
           }
            msg.setContent(mp);
            Transport.send(msg);
            System.out.println("Message Sent !");
            JOptionPane.showMessageDialog(null,"Message sent!","Mail Information",JOptionPane.INFORMATION_MESSAGE);
            fromField.setEditable(true);
            initialClear();
            refreshMails();

        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }


    }

    public void initialClear(){
        this.fromField.setText("");
        fromField.setStyle("-fx-background-color: white");
        this.toField.setText("");
        this.subjectField.setText("");
        this.mailBodyArea.setText("");
    }


    @FXML
    public void addFile(ActionEvent actionEvent) {

        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(((javafx.scene.control.Button) actionEvent.getSource()).getScene().getWindow());
        FileAttached=f.toString();
    }




    public void getMails() {

        //Define Protocol
        mailList.setEditable(true);
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        //Create Session
        Session session = Session.getInstance(props, null);
        try {
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", u1.getUsersMailAdress(), u1.getUsersPassword());//"cse482atisik@gmail.com" ,"EmineEkin"
            System.out.println("Ben Storeum " + store);

            Folder[] f = store.getDefaultFolder().list();
            System.out.println("Folder list");
            for (Folder fd : f)
                System.out.println("" + fd.getName());

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            int msgCount = inbox.getMessageCount();
            numberOfMessages.setText(String.valueOf(msgCount));
            System.out.println("Message count " + msgCount);
            for (int i = 0; i < msgCount; i++) {
                Email mail = new Email();
                System.out.println("Message " + i);
                Message msg = inbox.getMessage(i + 1);
                Address[] in = msg.getFrom();
                for (Address address : in) {

                    mail.setFromMailAdress(address.toString());
                    mail.setToMailAdress(this.userMailAddress.getText());
                    System.out.println("FROM " + address.toString());
                }
                if (msg.getContent() instanceof Multipart) {
                    Multipart mp = (Multipart) msg.getContent();
                    int partCount = mp.getCount();
                    System.out.println("Part Count : " + partCount);
                    boolean buldum = false;
                    for (int j = 0; j < partCount; j++) {
                        MimeBodyPart bp = (MimeBodyPart) mp.getBodyPart(j); //get the body part
                        if (Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())) {
                            //so we have a file in this part
                            System.out.println(bp.getFileName());
                            mail.setMailBody(bp.getContent().toString());
                            bp.saveFile(bp.getFileName());
                            System.out.println("dosyayi da sakladim");
                            buldum = true;
                        }//ON CLICK OLARAK EKLE !!!
                        else if (!buldum) {
                            //body part Contendi var demektir
                            mail.setMailBody(bp.getContent().toString());
                            System.out.println(bp.getContent());
                        }
                    }
                    mailBox.add(mail);

                    if (buldum) break;
                } else {
                    //plain text Content
                    System.out.println(msg.getContent());
                }
            }

//            BodyPart bp = mp.getBodyPart(0);
//            System.out.println("SENT DATE " + msg.getSentDate());
//            System.out.println("SUBJECT " + msg.getSubject());
//            System.out.println("CONTENT " + bp.getContent());
            flEmail = new FilteredList<Email>(mailBox, p -> true);

            mailList.setItems(flEmail);
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }

    public void loginAction(ActionEvent actionEvent) {
        if (!userMailAddress.equals("") && !passwordField.equals("")) {
            u1.setUsersMailAdress(userMailAddress.getText());
            u1.setUsersPassword(passwordField.getText());
            initialClear();
            getMails();
        }


    }



    public void searchMail(KeyEvent keyEvent) {
        if (!keyEvent.equals(""))
            flEmail.setPredicate(p -> p.getFromMailAdress().toLowerCase().contains(searchBoxField.getText().toLowerCase().trim()));
    }

    public void newMail(ActionEvent actionEvent) {
        this.fromField.setText(u1.getUsersMailAdress());
        fromField.setEditable(false);
        fromField.setStyle("-fx-background-color: Gray");
        this.toField.setText("");
        this.subjectField.setText("");
        this.mailBodyArea.setText("");
    }

    public void refreshMails() {
        mailList.setItems(null);
        getMails();
    }
}

