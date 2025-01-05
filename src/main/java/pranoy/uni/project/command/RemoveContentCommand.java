package pranoy.uni.project.command;

import pranoy.uni.project.archive.ArchiveableContent;
import pranoy.uni.project.content.ContentManager;

/**
 * @author Pranoy 21587070
 * <p></p>
 * Command used to remove content from CommandManager.
 */
public class RemoveContentCommand implements Command {
    private final ContentManager contentManager;
    private ArchiveableContent content;

    public RemoveContentCommand(ContentManager contentManager, ArchiveableContent content) {
        this.contentManager = contentManager;
        this.content = content;
    }

    public RemoveContentCommand setContent(ArchiveableContent content) {
        this.content = content;
        return this;
    }

    @Override
    public void execute() {
        contentManager.removeContent(content);
    }
}
