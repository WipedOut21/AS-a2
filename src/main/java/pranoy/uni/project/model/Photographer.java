package pranoy.uni.project.model;

/**
 * @author Pranoy 21587070
 */
public class Photographer extends User {

    public Photographer(String name, String email) {
        super(name, email);
    }

    public void savePhoto() {
        System.out.println("Saving photo");
    }
}
