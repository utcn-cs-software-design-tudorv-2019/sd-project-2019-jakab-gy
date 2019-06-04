package food.gram.business;

import food.gram.persistence.entity.*;
import food.gram.persistence.repository.CommentaryRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Inject
    CommentaryRepository commentaryRepository;

    public void createComment(Post post, Profile profile, String text){
        Commentary comment = new Commentary(post,profile,text, Timestamp.valueOf(LocalDateTime.now()));
        commentaryRepository.save(comment);
    }

    public void deleteComment(Commentary comment){
        commentaryRepository.delete(comment);
    }

    public List<Commentary> listAllComments(Post post){
        return commentaryRepository.findAllByPost(post);
    }
}
