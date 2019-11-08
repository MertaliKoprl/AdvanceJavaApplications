package Email;

import javax.mail.internet.MimeBodyPart;
import java.util.List;


public class Email {


    private String fromMailAdress;//ComesFrom
    private String toMailAdress;//Sends To
    private String SubjectTitle;
    private String MailBody;
    private List<MimeBodyPart> attachment;
    public Email(){


    }
    public Email(String fromMailAdress,String toMailAdress,String SubjectTitle,String MailBody){ //To send
        this.toMailAdress=toMailAdress;
        this.SubjectTitle=SubjectTitle;
        this.MailBody=MailBody;
        this.fromMailAdress=fromMailAdress;

    }

    public String getFromMailAdress() {
        return fromMailAdress;
    }
    public void addAttachment(MimeBodyPart attachment){
        this.attachment.add(attachment);


    }

    public String getMailBody() {
        return MailBody;
    }

    public String getSubjectTitle() {
        return SubjectTitle;
    }

    public String getToMailAdress() {
        return toMailAdress;
    }

    public void setMailBody(String mailBody) {
        MailBody = mailBody;
    }

    public void setFromMailAdress(String fromMailAdress) {
        this.fromMailAdress = fromMailAdress;
    }

    public void setToMailAdress(String toMailAdress) {
        this.toMailAdress = toMailAdress;
    }

    public void setSubjectTitle(String subjectTitle) {
        SubjectTitle = subjectTitle;
    }

    public List<MimeBodyPart> getAttachment() {
        return attachment;
    }

}
