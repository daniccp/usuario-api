package dlp.usuarioapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

    @Documented
    @Constraint(validatedBy = DocumentoValidoValidator.class)
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface DocumentoValido {
        String message() default "documento inválido para o tipo de pessoa";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

