package pranoy.uni.project.content;

import pranoy.uni.project.archive.ArchiveableContent;

import java.util.List;


/**
 * @author Pranoy 21587070
 */
public interface ContentManager {
    List<ArchiveableContent> getUnusedContents();

    List<ArchiveableContent> getContents();

    void addContents(final List<ArchiveableContent> contents);

    void addContent(final ArchiveableContent content);

    void removeContent(final ArchiveableContent content);

    void removeContent(String contentId);

    boolean hasContent(String contentId);

    ArchiveableContent getContent(String contentId);

    void archiveContent(final ArchiveableContent content);

    void saveToDrive(final String fileName);

    boolean loadFromDrive(final String fileName);

    void purgeContents();
}
