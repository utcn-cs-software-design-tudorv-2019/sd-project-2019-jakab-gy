package food.gram.persistence.repository;

import food.gram.persistence.entity.Post;
import food.gram.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    public Post findByPostId(int postId);
    public List<Post> findAllByProfile(Profile profile);
    public List<Post> findAllByPostTimeIsBetween(Timestamp start,Timestamp end);
    public List<Post> findAllByProfileAndPostTimeIsBetween(Profile profile,Timestamp start,Timestamp end);
    public List<Post> findAllByLocation(String location);
}
