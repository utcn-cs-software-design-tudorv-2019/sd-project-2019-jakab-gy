package food.gram.business;

import food.gram.persistence.entity.Message;
import food.gram.persistence.entity.Profile;
import food.gram.persistence.repository.MessageRepository;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageService {
    @Inject
    MessageRepository messageRepository;

    public void createMessage(Profile sender, Profile receiver, String text){
        Message message = new Message(sender,receiver,text,Timestamp.valueOf(LocalDateTime.now()));
        messageRepository.save(message);
    }

    public void deleteMessage(Message message){
        messageRepository.delete(message);
    }

    /**List all messages within a conversation with a profile*/
    public List<Message> listAllMessages(Profile profile1,Profile profile2){
        List<Message> sentMessages = messageRepository.findAllBySenderProfileAndReceiverProfile(profile1,profile2);
        List<Message> incommingMessages = messageRepository.findAllBySenderProfileAndReceiverProfile(profile2,profile1);

        List<Message> messages = new ArrayList<>();
        messages.addAll(sentMessages);
        messages.addAll(incommingMessages);
        return messages;
    }
    /**List all profiles which whom current profile had/has conversation - ordered by last message time*/
    public List<Profile> listAllConversations(Profile profile){
        List<Message> sentMessage = messageRepository.findAllBySenderProfile(profile);
        List<Message> incommingMessage = messageRepository.findAllByReceiverProfile(profile);

        Collections.sort(sentMessage);
        Collections.sort(incommingMessage);

        List<Profile> profiles = new ArrayList<>();

        sentMessage.forEach(m->{
            Profile receiver = m.getReceiverProfile();
            if(!profiles.contains(receiver))
                profiles.add(receiver);
        });

        incommingMessage.forEach(m->{
            Profile sender = m.getSenderProfile();
            if(!profiles.contains(sender))
                profiles.add(sender);
        });

        return profiles;
    }
}
