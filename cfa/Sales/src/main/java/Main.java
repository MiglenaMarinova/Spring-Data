

import Entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory("test")
                .createEntityManager();



    }
}