package licenta.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationid;
    @Column(name = "useremail")
    private String useremail;

    @Column(name = "message", length = 30000)
    @Lob
    private String message;
    @Column(name = "sentdate")
    private Date sentdate;
    @Column(name = "issent")
    private boolean issent;

    public Notifications(String useremail, String message, Date sentdate, boolean issent) {
        this.useremail = useremail;
        this.message = message;
        this.sentdate = sentdate;
        this.issent = issent;
    }

    public long getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(long notificationid) {
        this.notificationid = notificationid;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentdate() {
        return sentdate;
    }

    public void setSentdate(Date sentdate) {
        this.sentdate = sentdate;
    }

    public boolean isIssent() {
        return issent;
    }

    public void setIssent(boolean issent) {
        this.issent = issent;
    }
}
