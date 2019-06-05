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

    /**Create comment*/
    public void createComment(Post post, Profile profile, Commentary commentary){
        commentary.setProfile(profile);
        commentary.setPost(post);
        commentary.setCommentaryTime(Timestamp.valueOf(LocalDateTime.now()));

        commentaryRepository.save(commentary);
    }

    /**Delete comment*/
    public void deleteComment(Commentary commentary){
        commentaryRepository.delete(commentary);
    }

    /**View All comments*/
    public List<Commentary> viewAllComments(Post post){
        return commentaryRepository.findAllByPost(post);
    }

    /**View number of comments*/
    public long viewNumberOfComments(Post post){
        List<Commentary> comments = commentaryRepository.findAllByPost(post);
        if(comments == null) return 0;
        return comments.size();
    }

    /**View recent comments - comments made today*/
    public List<Commentary> viewRecentComments(Post post){
        Timestamp currentMoment = Timestamp.valueOf(LocalDateTime.now());
        int year = currentMoment.getYear();
        int month = currentMoment.getMonth();
        int day = currentMoment.getDay();

        Timestamp currentDatStart = Timestamp.valueOf(year + "-" + month + "-" + day + "00:00:00");
        return commentaryRepository.findAllByCommentaryTimeIsBetween(currentDatStart,currentMoment);
    }
}
