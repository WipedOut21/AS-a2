package pranoy.uni.project.model;

import pranoy.uni.project.archive.ArchiveableContent;

import java.io.Serializable;


/**
 * @author Pranoy 21587070
 */
public class Magazine extends ArchiveableContent implements Serializable {
    private int issueId;
    private String title;

    public Magazine(int issueId, String title) {
        super();
        this.issueId = issueId;
        this.title = title;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "issueId=" + issueId +
                ", title='" + title + '\'' +
                '}';
    }
}
