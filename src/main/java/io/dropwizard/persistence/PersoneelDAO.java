package io.dropwizard.persistence;

import io.dropwizard.models.Personeel;

public class PersoneelDAO {
    Personeel user1;
    public PersoneelDAO() {
        user1 = new Personeel(1, "Henk", "Potvis", "potvis@live.nl", "kk", "Admin", "Ja");
    }

    public Personeel getByEmailaddress(String username) {
        return user1;
    }
}
