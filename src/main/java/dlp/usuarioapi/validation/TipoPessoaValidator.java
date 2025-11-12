package dlp.usuarioapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoPessoaValidator implements ConstraintValidator<TipoPessoaValida, String> {

//    @Override
//    public void initialize(TipoPessoaValida constraintAnnotation) { }
//
//    @Override
//    public boolean isValid(String tipoPessoa, ConstraintValidatorContext context) {
//        if (tipoPessoa == null) return false;
//        String val = tipoPessoa.trim().toUpperCase();
//        return "PF".equals(val) || "PJ".equals(val);

    @Override
    public boolean isValid(String tipoPessoa, ConstraintValidatorContext context) {
        // Se for nulo ou vazio, deixa o @NotBlank tratar isso
        if (tipoPessoa == null || tipoPessoa.trim().isEmpty()) {
            return true;
        }

        String val = tipoPessoa.trim().toUpperCase();

        boolean valido = "PF".equals(val) || "PJ".equals(val);

        if (!valido) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Tipo de pessoa inválido. Use 'PF' para pessoa física ou 'PJ' para pessoa jurídica."
            ).addConstraintViolation();
        }

        return valido;

    }
}
