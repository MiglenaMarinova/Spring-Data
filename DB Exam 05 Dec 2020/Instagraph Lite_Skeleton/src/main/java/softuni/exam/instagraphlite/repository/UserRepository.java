package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.dto.ExportUserWithTheirPostDto;
import softuni.exam.instagraphlite.models.entity.User;

import java.util.List;
import java.util.Optional;

//ToDo
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findAllByUsername(String username);

    @Query(value = "SELECT u.id, (SELECT COUNT(p.id) " +
            "FROM instagraph_lite.posts " +
            "JOIN instagraph_lite.pictures p3 on p3.id = posts.picture_id " +
            "ORDER BY p3.size ) " +
            "FROM instagraph_lite.users u " +
            "JOIN instagraph_lite.posts p on u.id = p.user_id " +
            "GROUP BY u.id " +
            "ORDER BY COUNT(p.id) DESC, u.id", nativeQuery = true)
    List<User> findAllByPosts();
}
