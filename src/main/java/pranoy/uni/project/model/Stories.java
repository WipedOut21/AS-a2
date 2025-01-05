package pranoy.uni.project.model;

import pranoy.uni.project.archive.ArchiveableContent;

import java.io.Serializable;

/**
 * @author Pranoy 21587070
 */
public class Stories extends ArchiveableContent implements Serializable {
    private String title;
    private String content;

    public Stories(String title, String content) {
        this.content = content;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Stories{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
