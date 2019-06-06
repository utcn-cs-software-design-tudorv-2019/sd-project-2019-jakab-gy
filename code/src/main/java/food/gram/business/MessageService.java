package food.gram.business;

import food.gram.persistence.entity.Message;
import food.gram.persistence.entity.Profile;
import food.gram.persistence.repository.MessageRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageService {
    @Inject
    MessageRepository messageRepository;

    public void createMessage(Profile sender, Profile receiver, Message message){
        message.setSenderProfile(sender);
        message.setReceiverProfile(receiver);
        message.setSendingTime(Timestamp.valueOf(LocalDateTime.now()));
        messageRepository.save(message);
    }

    public void deleteMessage(Message message){
        messageRepository.delete(message);
    }

    /**List all messages within a conversation with a profile*/
    public List<Message> listAllMessages(Profile profile1,Profile profile2){
        List<Message> foundMessages = messageRepository.findAllBySenderProfileOrReceiverProfile(profile1,profile1);
        if(foundMessages == null) return new ArrayList<>();
        List<Message> messages = new ArrayList<>();

        foundMessages.forEach(m -> {
            if(m.getSenderProfile().getProfileId() == profile2.getProfileId() || m.getReceiverProfile().getProfileId() == profile2.getProfileId())
                messages.add(m);
        });
        if(messages.isEmpty()) return messages;
        List<Message> orderedMessages = new ArrayList<>();
        for(int i =(messages.size() - 1) ; i >= 0 ; i--)
            orderedMessages.add(messages.get(i));
        return orderedMessages;
    }

    /**List all profiles which whom current profile had/has conversation - ordered by last message time*/
    public List<Profile> listAllConversations(Profile profile){
        List<Message> foundMessages = messageRepository.findAllBySenderProfileOrReceiverProfile(profile,profile);

        List<Profile> profiles = new ArrayList<>();

        foundMessages.forEach( m -> {
            Profile p1 = m.getReceiverProfile();
            Profile p2 = m.getSenderProfile();

            if(p1.getProfileId() == profile.getProfileId()) {
                if (!profiles.contains(p2))
                    profiles.add(p2);
            }
            if(p2.getProfileId() == profile.getProfileId()){
                if(!profiles.contains(p1))
                    profiles.add(p1);
            }
        });

        return profiles;
    }
}
