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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;


public class Controller implements Initializable {


    public User u1 = new User("", "");
    @FXML
    TableView<Email> mailList = new TableView<Email>();
    @FXML
    TextField searchBoxField;
    @FXML
    TextField fromField;
    @FXML
    TextField toField;
    @FXML
    Label sendDateLabel;
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
    WebView webViewContext;
    @FXML
    TableColumn headMail;
    @FXML
    Text discardBtnLabel;
    @FXML
    Text attachmentBtnLabel;
    @FXML
    Button attachMentBtn;
    @FXML
    Button saveAttachedFileBtn;
    FilteredList<Email> flEmail;
    Email selectedMail;
    private ObservableList<Email> mailBox = FXCollections.observableArrayList();
    private String FileAttached = "";
    private boolean isLogedIn;
    private boolean isNewMail;
    private boolean isMailSelected;
    private boolean isReply;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headMail.setCellValueFactory(
                new PropertyValueFactory<Email, String>("fromMailAdress")
        );
        isMailSelected = false;
        isNewMail = false;
        saveAttachedFileBtn.setVisible(false);
        attachmentBtnLabel.setVisible(false);
        discardBtnLabel.setVisible(false);
        discardBtn.setVisible(false);
        attachMentBtn.setVisible(false);
        isLogedIn = false;
        mailBodyArea.setVisible(false);
        webViewContext.setVisible(true);
        selectedMail = new Email();
    }

    public void sendMail(ActionEvent actionEvent) {
        if (isLogedIn) {
            if (!isMailSelected) {
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
                    if(isReply)
                    bp.setText(mailBodyArea.getText()+"\n this is a Reply");//Puts Context to body part
                    else
                        bp.setText(mailBodyArea.getText());//Puts Context to body part
                    mp.addBodyPart(bp);

                    BodyPart partForAtt = new MimeBodyPart();
                    if (!FileAttached.equals("")) {
                        String filename = FileAttached;//If There Are Any
                        DataSource source = new FileDataSource(filename);
                        partForAtt.setDataHandler(new DataHandler(source));
                        partForAtt.setFileName(filename);

                        mp.addBodyPart(partForAtt);
                    }
                    msg.setContent(mp);
                    Transport.send(msg);
                    System.out.println("Message Sent !");
                    JOptionPane.showMessageDialog(null, "Message sent!", "Mail Information", JOptionPane.INFORMATION_MESSAGE);
                    fromField.setEditable(true);
                    initialClear();

                } catch (MessagingException mex) {
                    System.out.println("send failed, exception: " + mex);
                }


            } else {
                JOptionPane.showMessageDialog(null, "You can not send received Mail Try Forward!", "Info", JOptionPane.INFORMATION_MESSAGE);

            }
        } else
            JOptionPane.showMessageDialog(null, "Login First!", "Request", JOptionPane.ERROR_MESSAGE);
    }

    public void initialClear() {
        isNewMail = true;
        isReply=false;
        this.fromField.setText("");
        this.toField.setText("");
        this.subjectField.setText("");
        this.mailBodyArea.setText("");
        mailBodyArea.setVisible(true);
        fromField.setStyle("-fx-background-color: white");
        subjectField.setEditable(true);
        fromField.setEditable(true);
        toField.setEditable(true);


    }


    @FXML
    public void addFile(ActionEvent actionEvent) {
        if (isLogedIn) {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(((javafx.scene.control.Button) actionEvent.getSource()).getScene().getWindow());
            if (f != null) {
                FileAttached = f.toString();
                JOptionPane.showMessageDialog(null, "File Attached!", "Mail Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else
            JOptionPane.showMessageDialog(null, "Login First!", "Request", JOptionPane.ERROR_MESSAGE);
    }


    public void getMails() {

        mailBox.clear();
        //Define Protocol
        mailList.setEditable(true);
        Properties props = new Properties();
        props.setProperty("mail.imaps.partialfetch", "false");//props.setProperty("mail.store.protocol", "imaps"); Old Protocol
        //Create Session
        Session session = Session.getInstance(props, null);
        try {
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", u1.getUsersMailAdress(), u1.getUsersPassword());//"cse482atisik@gmail.com" ,"EmineEkin"
            isLogedIn = true;
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
                mail.setSendDate(msg.getSentDate().toString());

                Address[] in = msg.getFrom();
                mail.setSubjectTitle(msg.getSubject());
                System.out.println("ben mesajım" + i);
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

                            System.out.println("Ben Fileım" + bp.getFileName());
                            System.out.println("Ben Contendim : " + bp.getContent());
                            mail.setAttachment(bp.getFileName());
                            File sourceAttach = new File(bp.getFileName());
                          //  bp.saveFile(sourceAttach);//Saves File  AUTOMATHICALLY
                            System.out.println("Auto Saved to Root File");
                            mail.bp = bp;
                            System.out.println("dosyayi da sakladim");

                        }//ON CLICK OLARAK EKLENEBİLİR !!!
                        else {
                            //body part Contendi var demektir
                            mail.setMailBody(bp.getContent().toString());
                            System.out.println("Ben Contendim : " + bp.getContent());
                        }
                    }

                    mailBox.add(mail);
                } else {
                    //plain text Content
                    System.out.println("Ben Contendim" + msg.getContent());
                }
            }

            flEmail = new FilteredList<Email>(mailBox, p -> true);
            mailList.setItems(flEmail);

        } catch (Exception mex) {

            mex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wrong id or password!", "Warning", JOptionPane.ERROR_MESSAGE);
        }
        if(!isLogedIn){
            JOptionPane.showMessageDialog(null, "Login First!", "Request", JOptionPane.ERROR_MESSAGE);
        }
    }




    public void loginAction(ActionEvent actionEvent) {
        if (!userMailAddress.equals("") && !passwordField.equals("")) {
            this.u1 = new User("", "");
            u1.setUsersMailAdress(userMailAddress.getText());
            u1.setUsersPassword(passwordField.getText());
            initialClear();
            getMails();
        }


    }


    public void searchMail(KeyEvent keyEvent) {
        if (isLogedIn) {
            if (!keyEvent.equals(""))
                flEmail.setPredicate(p -> p.getFromMailAdress().toLowerCase().contains(searchBoxField.getText().toLowerCase().trim()));
        } else
            JOptionPane.showMessageDialog(null, "Login First!", "Request", JOptionPane.ERROR_MESSAGE);
    }

    public void newMail(ActionEvent actionEvent) {
        if (isLogedIn) {
            saveAttachedFileBtn.setVisible(false);
            initialClear();
            mailBodyArea.setVisible(true);
            isNewMail = true;
            attachmentBtnLabel.setVisible(true);
            discardBtnLabel.setVisible(true);
            discardBtn.setVisible(true);
            attachMentBtn.setVisible(true);
            webViewContext.setVisible(true);
            this.fromField.setText(u1.getUsersMailAdress());
            fromField.setEditable(false);
            toField.setEditable(true);
            mailBodyArea.setEditable(true);
            fromField.setStyle("-fx-background-color: Gray");

        } else
            JOptionPane.showMessageDialog(null, "Login First!", "Request", JOptionPane.ERROR_MESSAGE);
    }

    public void refreshMails() {
        if (isLogedIn) {
            mailList.setItems(null);
            getMails();
        } else
            JOptionPane.showMessageDialog(null, "Login First!", "Request", JOptionPane.ERROR_MESSAGE);
    }

    public void mailDisplay() {
        if (isLogedIn) {
            initialClear();
            mailBodyArea.setVisible(false);
            saveAttachedFileBtn.setVisible(true);
            webViewContext.setVisible(true);
            if (mailList.getSelectionModel().getSelectedItem() != null) {
                isMailSelected = true;
                this.selectedMail = mailList.getSelectionModel().getSelectedItem();
                sendDateLabel.setText(selectedMail.getSendDate());
                fromField.setEditable(false);
                toField.setEditable(false);
                subjectField.setEditable(false);
                mailBodyArea.setEditable(false);
                fromField.setText(selectedMail.getFromMailAdress());
                toField.setText(selectedMail.getToMailAdress());
                subjectField.setText(selectedMail.getSubjectTitle());
                mailBodyArea.setText(selectedMail.getMailBody());
                webViewContext.getEngine().loadContent(selectedMail.getMailBody());
            }
        } else
            JOptionPane.showMessageDialog(null, "Login First!", "Request", JOptionPane.ERROR_MESSAGE);
    }

    public void replyMail(ActionEvent actionEvent) {
        if (isMailSelected || !isNewMail) {
            discardBtnLabel.setVisible(true);
            discardBtn.setVisible(true);
            initialClear();
            isReply=true;
            fromField.setStyle("-fx-background-color: Gray");
            fromField.setText(u1.getUsersMailAdress());
            fromField.setEditable(false);
            toField.setText(selectedMail.getFromMailAdress());
            mailBodyArea.setEditable(true);
            mailBodyArea.setVisible(true);
            webViewContext.setVisible(false);

        } else {
            JOptionPane.showMessageDialog(null, "You Should select Mail First!", "Warning", JOptionPane.WARNING_MESSAGE);


        }
    }

    public void forwardMail(ActionEvent actionEvent) {
        if (isMailSelected || !isNewMail) {
            discardBtnLabel.setVisible(true);
            discardBtn.setVisible(true);
            mailBodyArea.setVisible(false);
            webViewContext.setVisible(true);
            isReply=true;
            toField.setEditable(true);
            fromField.setStyle("-fx-background-color: Gray");
            fromField.setText(u1.getUsersMailAdress());
            toField.setText("");
            subjectField.setText(selectedMail.getSubjectTitle());
            mailBodyArea.setText(selectedMail.getMailBody());


        } else {
            JOptionPane.showMessageDialog(null, "You Should select Mail First!", "Warning", JOptionPane.WARNING_MESSAGE);


        }
    }

    public void rowDownItem(ActionEvent actionEvent) {
        if (isMailSelected) {
            int newIndex = mailList.getSelectionModel().getSelectedIndex();
            if (newIndex < Integer.valueOf(numberOfMessages.getText()))
                newIndex++;
            mailList.getSelectionModel().clearAndSelect(newIndex);
            mailDisplay();
        } else {
            JOptionPane.showMessageDialog(null, "You Should select Mail First!", "Indexing", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void rowUpItem(ActionEvent actionEvent) {
        if (isMailSelected) {
            int newIndex = mailList.getSelectionModel().getSelectedIndex();
            if (newIndex >= 0)
                newIndex--;
            mailList.getSelectionModel().clearAndSelect(newIndex);
            mailDisplay();
        } else {
            JOptionPane.showMessageDialog(null, "You Should select Mail First!", "Indexing", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void selectedRemover(ActionEvent actionEvent) {
        isMailSelected = false;
        mailList.getSelectionModel().clearSelection();
        searchBoxField.setText("");
        webViewContext.getEngine().loadContent("");
        webViewContext.setVisible(false);
        mailBodyArea.setVisible(true);
        initialClear();

    }

    public void discardBtn() {
        if (isMailSelected || isNewMail) {
            webViewContext.setVisible(false);
            initialClear();
        } else {
            JOptionPane.showMessageDialog(null, "You Should select Mail First!", "Indexing", JOptionPane.INFORMATION_MESSAGE);


        }

    }

    public void saveFileaction(ActionEvent actionEvent)  {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(new JFrame());
            File f =  chooser.getSelectedFile();
            try {

                if(saveFile(selectedMail.getAttachment(),selectedMail.bp,f.getAbsolutePath())){

                    JOptionPane.showMessageDialog(null, "File is saved to Directory!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "There is no File to Save!", "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (MessagingException e) {
                JOptionPane.showMessageDialog(null, "There is no such Directory to Save!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }



    public boolean saveFile(String filename,Part part,String dest) throws IOException, MessagingException {
        System.out.println("I AM FILE"+filename);
        System.out.println("I am Dest"+dest);
        boolean ren = true;
        FileOutputStream fos = null;
        BufferedInputStream fin = null;
        InputStream input = part.getInputStream();

        File pdffile = new File(dest+"\\"+filename);
        try{
            if(!pdffile.exists()){
                fos = new FileOutputStream(pdffile);
                fin = new BufferedInputStream(input);
                int size = 512;
                byte[] buf = new byte[size];
                int len;

                while ( (len = fin.read(buf)) != -1 ) {
                    fos.write(buf, 0, len);
                }

                input.close();
                fos.close();
            }else{
                System.out.println("File already exists");
            }
        }catch(Exception e ){
            ren = false;
        }
        return ren;
    }


    public void opacity(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(0.6);
    }

    public void opacityOut(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(1);
    }


}

