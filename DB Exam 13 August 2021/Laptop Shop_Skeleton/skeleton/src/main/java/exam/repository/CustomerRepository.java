package exam.repository;

import exam.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//ToDo:
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findAllByEmail(String email);
}
