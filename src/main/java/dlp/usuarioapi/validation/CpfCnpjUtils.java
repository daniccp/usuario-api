package dlp.usuarioapi.validation;

public class CpfCnpjUtils {
    private CpfCnpjUtils() {
    }

    public static boolean isValidCPF(String cpf) {
        if (cpf == null) return false;

        String digits = cpf.replaceAll("\\D", "");
        if (digits.length() != 11) return false;

        // Rejeita sequências repetidas (000... 111... etc)
        if (digits.chars().distinct().count() == 1) return false;

        try {
            int soma1 = 0;
            for (int i = 0; i < 9; i++) {
                soma1 += Character.getNumericValue(digits.charAt(i)) * (10 - i);
            }
            int resto1 = soma1 % 11;
            int digito1 = (resto1 < 2) ? 0 : 11 - resto1;

            int soma2 = 0;
            for (int i = 0; i < 10; i++) {
                soma2 += Character.getNumericValue(digits.charAt(i)) * (11 - i);
            }
            int resto2 = soma2 % 11;
            int digito2 = (resto2 < 2) ? 0 : 11 - resto2;

            return digito1 == Character.getNumericValue(digits.charAt(9)) &&
                    digito2 == Character.getNumericValue(digits.charAt(10));

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida um CNPJ (somente números).
     *
     * @param cnpj número do CNPJ (com ou sem pontuação)
     * @return true se o CNPJ for válido
     */
    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null) return false;

        String digits = cnpj.replaceAll("\\D", "");
        if (digits.length() != 14) return false;

        // Rejeita sequências repetidas como 00000000000000
        if (digits.chars().distinct().count() == 1) return false;

        try {
            int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(digits.charAt(i)) * peso1[i];
            }
            int resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : 11 - resto;

            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(digits.charAt(i)) * peso2[i];
            }
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : 11 - resto;

            return digito1 == Character.getNumericValue(digits.charAt(12)) &&
                    digito2 == Character.getNumericValue(digits.charAt(13));

        } catch (NumberFormatException e) {
            return false;
        }
    }
}

