package dlp.usuarioapi.validation;

import dlp.usuarioapi.dto.UsuarioDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class DocumentoValidoValidator implements ConstraintValidator<DocumentoValido, UsuarioDto> {

    @Override
    public boolean isValid(UsuarioDto usuario, ConstraintValidatorContext context) {
        if (usuario == null)
            return true; // outro validator (como @NotNull) cuidará disso

        String tipo = usuario.getTipoPessoa();
        String documento = usuario.getNumeroDocumento(); //campo herdado de UsuarioDto

        if (tipo == null || documento == null) {
            return true;
        }

        tipo = tipo.trim().toUpperCase();
        boolean valido = false;

        if (tipo.equals("PF")) {
            valido = CpfCnpjUtils.isValidCPF(documento);
        } else if (tipo.equals("PJ")) {
            valido = CpfCnpjUtils.isValidCNPJ(documento);
        } else {
            addConstraintViolation(context, "Tipo de pessoa inválido: deve ser PF ou PJ");
            return false;
        }
        if (!valido) {
            addConstraintViolation(context, "Documento inválido para o tipo de pessoa informado");
            return false;
        }
        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String mensagem) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(mensagem)
                .addPropertyNode("numeroDocumento")
                .addConstraintViolation();
    }
}


