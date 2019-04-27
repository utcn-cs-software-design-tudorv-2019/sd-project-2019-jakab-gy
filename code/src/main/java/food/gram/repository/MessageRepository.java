package food.gram.repository;

import food.gram.entity.Message;
import food.gram.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    public Message findByMessageId(int messageId);
    public List<Message> findAllBySenderProfile(Profile senderProfile);
    public List<Message> findAllByReceiverProfile(Profile receiverProfile);

}
