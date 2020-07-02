package io.github.wimdeblauwe.jpearl.user;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity<UserId> {
    private String name;

    protected User() {
    }

    public User(UserId id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
