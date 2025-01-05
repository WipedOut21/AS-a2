package pranoy.uni.project.archive;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author Pranoy - 21587070
 * <p> </p>
 * Abstract class that can be implemented for functionality with ContentManager.
 * This class helps in Archiving contents and providing an abstraction layer.
 */
public abstract class ArchiveableContent {
    private final String id = UUID.randomUUID().toString(); // assign random unique id on object instantiation
    private Date publicationDate = Calendar.getInstance().getTime(); // initial publication date is dependent on object instantiation
    private boolean archived = false;

    public ArchiveableContent() {}

    public ArchiveableContent(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
