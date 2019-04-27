package food.gram.repository;

import food.gram.entity.Post;
import food.gram.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    public Post findByPostId(int postId);
    public List<Post> findAllByProfile(Profile profile);
    public List<Post> findAllByPostTimeIsBetween(Timestamp start,Timestamp end);
    public List<Post> findAllByLocation(String location);
}
