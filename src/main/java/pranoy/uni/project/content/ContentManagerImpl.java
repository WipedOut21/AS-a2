package pranoy.uni.project.content;

import pranoy.uni.project.archive.ArchiveRepository;
import pranoy.uni.project.archive.ArchiveableContent;
import pranoy.uni.project.serializable.SerializableContentList;

import java.util.Calendar;
import java.util.List;

import static pranoy.uni.project.utils.DateUtils.monthsBetween;

/**
 * @author Pranoy 21587070
 * Singleton implementation of the ContentManager interface.
 */
public class ContentManagerImpl extends SerializableContentList<ArchiveableContent> implements ContentManager {
    private static ContentManager instance;

    public static void initialize(ArchiveRepository archiveRepository) {
        if (instance != null) {
            throw new UnsupportedOperationException("Content manager is already initialized");
        }
        instance = new ContentManagerImpl(archiveRepository);
    }

    public static ContentManager getInstance() {
        if (instance == null) {
            throw new UnsupportedOperationException("Content Manager not initialized");
        }
        return instance;
    }

    private final int archiveDurationMonths = 6; // in months
    private final ArchiveRepository archiveRepository;

    protected ContentManagerImpl(ArchiveRepository archiveRepository) {
        // dependency injection
        this.archiveRepository = archiveRepository;
    }

    /**
     * @return a list of contents that is archive-able
     */
    public List<ArchiveableContent> getUnusedContents() {
        return contentList.stream()
                .filter(this::isArchiveable)
                .toList();
    }

    /**
     *
     * @return An immutable copy of the current contentList.
     */
    @Override
    public List<ArchiveableContent> getContents() {
        return List.copyOf(contentList);
    }

    /**
     * Adds provided content List to the currentList with no duplicates
     */
    @Override
    public void addContents(List<ArchiveableContent> contents) {
        contentList.addAll(contents.stream().filter(c -> !contentList.contains(c)).toList());
    }

    /**
     * Checks if the content is archive-able by calculating the difference of current date and publication date to see the difference.
     * @param content the content instance to check its archive ability.
     * @return true if content is supposed to be archived, false otherwise.
     */
    protected boolean isArchiveable(ArchiveableContent content) {
        return monthsBetween(Calendar.getInstance().getTime(), content.getPublicationDate()) >= archiveDurationMonths;
    }

    /**
     * Adds given content to the ContentManager instance
     * @param content content to add
     */
    @Override
    public void addContent(final ArchiveableContent content) {
        if (contentList.contains(content)) {
            throw new UnsupportedOperationException("Content: " + content.getId() + " already exists in ContentManager!");
        }
        contentList.add(content);
    }

    /**
     * Removes content from the content list
     * @param content the content instance to be removed.
     */
    @Override
    public void removeContent(final ArchiveableContent content) {
        contentList.remove(content);
    }

    /**
     * Removes content from content list based off its id
     * @param contentId id to be removed
     */
    @Override
    public void removeContent(String contentId) {
        contentList.removeIf(c -> c.getId().equals(contentId));
    }

    /**
     * @param contentId the id of the content to query the list from
     * @return true if the ContentManager has the specified content with the provided contentId, false otherwise.
     */
    @Override
    public boolean hasContent(String contentId) {
        return contentList.stream().anyMatch(c -> c.getId().equals(contentId));
    }

    /**
     * @param contentId the id of the content to query the list from
     * @return an ArchiveableContent instance if content is present, null otherwise
     */
    @Override
    public ArchiveableContent getContent(String contentId) {
        return contentList.stream().filter(c -> c.getId().equals(contentId)).findFirst().orElse(null);
    }

    /**
     * Saves the provided content to archive.
     * @param content the content instance to be archived
     */
    @Override
    public void archiveContent(final ArchiveableContent content) {
        archiveRepository.saveToArchive(content);
    }

    /**
     * Clears the contents of the content manager.
     */
    @Override
    public void purgeContents() {
        contentList.clear();
    }
}