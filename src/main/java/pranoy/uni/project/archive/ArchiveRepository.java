package pranoy.uni.project.archive;

import java.util.List;

/**
 * @author Pranoy - 21587070
 * <p></p>
 */
public interface ArchiveRepository {
    void saveToArchive(ArchiveableContent content);

    ArchiveableContent retrieveFromArchive(String contentId);

    boolean isInArchive(String contentId);

    List<ArchiveableContent> getArchivedContents();

    void purgeArchive();

    String getArchiveDetails();

    void removeFromArchive(ArchiveableContent content);

    void saveToDrive(final String fileName);

    boolean loadFromDrive(final String fileName);
}
