package pranoy.uni.project.model;

import java.util.UUID;

/**
 * @author Pranoy 21587070
 */
public abstract class User {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
