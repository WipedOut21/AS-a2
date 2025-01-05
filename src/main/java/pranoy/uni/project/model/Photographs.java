package pranoy.uni.project.model;

import pranoy.uni.project.archive.ArchiveableContent;

import java.io.Serializable;

/**
 * @author Pranoy 21587070
 */
public class Photographs extends ArchiveableContent implements Serializable {
    private String description;
    private String filePath;

    public Photographs(String description, String filePath) {
        this.description = description;
        this.filePath = filePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Photographs{" +
                "description='" + description + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
