package softuni.exam.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Agent;

import java.util.Optional;

// TODO:
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findAllByFirstName (String firstName);

}
