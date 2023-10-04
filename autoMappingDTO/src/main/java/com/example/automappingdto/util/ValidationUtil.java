package com.example.automappingdto.util;



import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {

   <E> boolean isEmpty(E entity);
   <E> Set<ConstraintViolation<E>> violations (E entity);
}
