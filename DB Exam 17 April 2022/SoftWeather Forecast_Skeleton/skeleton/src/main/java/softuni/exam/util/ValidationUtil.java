package softuni.exam.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {

    <E> boolean isValid(E entity);

//    <E> boolean isEmpty(E entity);
//    <E> Set<ConstraintViolation<E>> violations (E entity);

}
