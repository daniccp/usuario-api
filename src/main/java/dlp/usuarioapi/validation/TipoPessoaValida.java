package dlp.usuarioapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

    @Documented
    @Constraint(validatedBy = TipoPessoaValidator.class)
    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TipoPessoaValida {
        String message() default "Tipo de pessoa inválida. Use 'PF' ou 'PJ'.";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }


