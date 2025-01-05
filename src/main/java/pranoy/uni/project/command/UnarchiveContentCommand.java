package pranoy.uni.project.command;

import pranoy.uni.project.archive.ArchiveRepository;
import pranoy.uni.project.archive.ArchiveableContent;

/**
 * @author Pranoy 21587070
 * <p></p>
 * Command used to remove content from ArchiveRepository.
 */
public class UnarchiveContentCommand implements Command {
    private final ArchiveRepository archiveRepository;
    private ArchiveableContent content;

    public UnarchiveContentCommand(ArchiveRepository archiveRepository, ArchiveableContent content) {
        this.archiveRepository = archiveRepository;
        this.content = content;
    }

    public UnarchiveContentCommand setContent(ArchiveableContent content) {
        this.content = content;
        return this;
    }

    @Override
    public void execute() {
        archiveRepository.removeFromArchive(content);
    }
}
