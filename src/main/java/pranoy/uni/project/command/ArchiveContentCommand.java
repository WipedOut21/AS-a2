package pranoy.uni.project.command;

import pranoy.uni.project.archive.ArchiveRepository;
import pranoy.uni.project.archive.ArchiveableContent;

/**
 * @author Pranoy 21587070
 * Command used to Archive contents into ArchiveRepository.
 */
public class ArchiveContentCommand implements Command {
    private final ArchiveRepository archiveRepository;
    private ArchiveableContent content;

    public ArchiveContentCommand(ArchiveRepository archiveRepository, ArchiveableContent content) {
        this.archiveRepository = archiveRepository;
        this.content = content;
    }

    public ArchiveContentCommand setContent(ArchiveableContent content) {
        this.content = content;
        return this;
    }

    @Override
    public void execute() {
        archiveRepository.saveToArchive(content);
    }
}
