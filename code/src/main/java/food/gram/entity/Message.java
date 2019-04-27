package food.gram.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Profile senderProfile;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Profile receiverProfile;

    @Column(name = "description")
    private String description;

    @Column(name = "sendingTime")
    private Timestamp sendingTime;

    public Message(){
        super();
    }

    public Message(Profile senderProfile, Profile receiverProfile, String description, Timestamp sendingTime) {
        this.senderProfile = senderProfile;
        this.receiverProfile = receiverProfile;
        this.description = description;
        this.sendingTime = sendingTime;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Profile getSenderProfile() {
        return senderProfile;
    }

    public void setSenderProfile(Profile senderProfile) {
        this.senderProfile = senderProfile;
    }

    public Profile getReceiverProfile() {
        return receiverProfile;
    }

    public void setReceiverProfile(Profile receiverProfile) {
        this.receiverProfile = receiverProfile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(Timestamp sendingTime) {
        this.sendingTime = sendingTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderProfile=" + senderProfile +
                ", receiverProfile=" + receiverProfile +
                ", description='" + description + '\'' +
                ", sendingTime=" + sendingTime +
                '}';
    }
}
