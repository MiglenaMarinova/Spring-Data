package exam.util;

public interface ValidationUtil {

    <E> boolean isValid(E entity);

//    <E> boolean isEmpty(E entity);
//    <E> Set<ConstraintViolation<E>> violations (E entity);
}
