package Email;

import javax.mail.internet.MimeBodyPart;
import java.util.List;


public class Email {


    private String fromMailAdress;//ComesFrom
    private String toMailAdress;//Sends To
    private String SubjectTitle;
    private String MailBody;
    private String SendDate;
    private String attachment;
    public MimeBodyPart bp;
    public Email(){


    }
    public Email(String fromMailAdress,String toMailAdress,String SubjectTitle,String MailBody){ //To send
        this.toMailAdress=toMailAdress;
        this.SubjectTitle=SubjectTitle;
        this.MailBody=MailBody;
        this.fromMailAdress=fromMailAdress;

    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getFromMailAdress() {
        return fromMailAdress;
    }


    public void setSendDate(String sendDate) {
        SendDate = sendDate;
    }

    public String getSendDate() {
        return SendDate;
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

}
