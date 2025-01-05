package pranoy.uni.project.archive;

import pranoy.uni.project.serializable.SerializableContentList;

import java.util.List;

/**
 * @author Pranoy 21587070
 * <p></p>
 * <p>Singleton Implementation of the ArchiveRepository interface</p>
 */
public class ArchiveRepositoryImpl extends SerializableContentList<ArchiveableContent> implements ArchiveRepository {
    private static ArchiveRepository instance;

    protected ArchiveRepositoryImpl() {
    }

    public static void initialize() {
        if (instance != null) {
            throw new UnsupportedOperationException("ArchiveRepository is already initialized");
        }
        instance = new ArchiveRepositoryImpl();
    }

    public static ArchiveRepository getInstance() {
        if (instance == null) {
            throw new UnsupportedOperationException("ArchiveRepository is not initialized");
        }
        return instance;
    }

    /**
     * Saves content to archive, throws {@link UnsupportedOperationException} if content is already archived
     *
     * @param content content instance to be archived.
     */
    @Override
    public void saveToArchive(ArchiveableContent content) {
        if (contentList.contains(content)) {
            throw new UnsupportedOperationException("Content is already archived!");
        }

        contentList.add(content);
        content.setArchived(true);
    }

    /**
     * Retrieves content from archive using the provided contentId
     *
     * @param contentId unique identifier for the content
     * @return the Content based off the id if present in the repository, null otherwise
     */
    @Override
    public ArchiveableContent retrieveFromArchive(String contentId) {
        return contentList.stream()
                .filter(c -> c.getId().equals(contentId))
                .findFirst()
                .orElse(null);
    }

    /**
     * @param contentId unique identifier of the content
     * @return true if content is archived, false otherwise
     */
    @Override
    public boolean isInArchive(String contentId) {
        return retrieveFromArchive(contentId) != null;
    }

    /**
     * @return An immutable copy of the archivedItems list
     */
    @Override
    public List<ArchiveableContent> getArchivedContents() {
        return List.copyOf(contentList);
    }

    /**
     * Clears all archived contents
     */
    @Override
    public void purgeArchive() {
        contentList.forEach(content -> content.setArchived(false));
        contentList.clear();
    }

    /**
     * @return string containing the information of elements in the repo, useful for debugging purposes.
     */
    @Override
    public String getArchiveDetails() {
        return "[Content size:" + contentList.size() + "]";
    }

    /**
     * Removes the content from the archive, may throw {@link UnsupportedOperationException} if the content is not already archived.
     *
     * @param content to be unarchived.
     */
    @Override
    public void removeFromArchive(ArchiveableContent content) {
        if (!isInArchive(content.getId())) {
            throw new UnsupportedOperationException("Content is not archived!");
        }

        contentList.remove(content);
        content.setArchived(false);
    }

}
