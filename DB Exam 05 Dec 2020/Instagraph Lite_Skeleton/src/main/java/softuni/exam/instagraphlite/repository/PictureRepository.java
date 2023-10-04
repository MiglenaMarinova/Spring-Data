package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.Picture;

import java.util.Optional;

//ToDo
@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<Picture> findAllByPath(String path);


}
