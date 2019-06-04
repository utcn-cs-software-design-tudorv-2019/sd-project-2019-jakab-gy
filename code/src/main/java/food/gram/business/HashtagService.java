package food.gram.business;

import food.gram.persistence.entity.Hashtag;
import food.gram.persistence.repository.HashtagRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class HashtagService {
    @Inject
    HashtagRepository hashtagRepository;

    public Hashtag viewHashtag(String hashtagName){
        return hashtagRepository.findByHashtagName(hashtagName);
    }

    /**Hashtag names are separated with spaces*/
    public void analiseHashtags(String hashtags){
        String[] splitResult = hashtags.split(" ");

        for(String itr : splitResult){
            Hashtag hashtag = hashtagRepository.findByHashtagName(itr);
            if(hashtag != null) {
                hashtag.setAppearance(hashtag.getAppearance() + 1);
                hashtagRepository.save(hashtag);
            }
        }
    }
}
