package control;

import com.password4j.Hash;
import com.password4j.Password;

public class PasswordProtezione {

     //Genera un hash della password (Argon2id)
    public String hash(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "La password non può essere vuota"
            );
        }

        Hash hash = Password.hash(password)
                .withArgon2();

        return hash.getResult();
    }

     //Verifica la password inserita confrontandola con l'hash memorizzato
    public boolean verify(String password, String passwordHash) {
        if (password == null || passwordHash == null) {
            return false;
        }

        return Password.check(password, passwordHash)
                .withArgon2();
    }
}