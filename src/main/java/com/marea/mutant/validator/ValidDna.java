package com.marea.mutant.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DnaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDna {
    String message() default "La secuencia de ADN es inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}