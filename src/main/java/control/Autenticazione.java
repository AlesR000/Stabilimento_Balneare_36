package control;


public class Autenticazione {

    private PasswordProtezione passwordHasher;

    public Autenticazione() {
        passwordHasher = new PasswordProtezione();
    }

    public String generaPasswordHash(String password) {

        return passwordHasher.hash(password);
    }

}
