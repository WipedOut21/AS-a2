package pranoy.uni.project.model;

import pranoy.uni.project.archive.ArchiveableContent;

import java.io.Serializable;

/**
 * @author Pranoy 21587070
 */
public class Advertisement extends ArchiveableContent implements Serializable {
    private String title;

    public Advertisement(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "title='" + title + '\'' +
                '}';
    }
}
