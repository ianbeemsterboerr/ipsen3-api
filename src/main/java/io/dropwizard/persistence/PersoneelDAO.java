package io.dropwizard.persistence;

import io.dropwizard.models.Personeel;

public class PersoneelDAO {
    Personeel user1;
    public PersoneelDAO() {
        user1 = new Personeel(1, "Pietje", "Potvis", "potvis@live.nl", "wachtwoord", "Admin", "Ja");
    }

    public Personeel getByEmailaddress(String username) {
        return user1;
    }
}
