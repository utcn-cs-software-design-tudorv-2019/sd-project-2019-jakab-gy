package food.gram.persistence.repository;

import food.gram.persistence.entity.Message;
import food.gram.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    public Message findByMessageId(int messageId);
    public List<Message> findAllBySenderProfile(Profile senderProfile);
    public List<Message> findAllByReceiverProfile(Profile receiverProfile);
    public List<Message> findAllBySenderProfileAndReceiverProfile(Profile senderProfile, Profile receiverProfile);
    public List<Message> findAllBySendingTimeIsBetween(Timestamp start,Timestamp end);

}
