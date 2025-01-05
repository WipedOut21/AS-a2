package pranoy.uni.project.command;

import pranoy.uni.project.archive.ArchiveableContent;
import pranoy.uni.project.content.ContentManager;

/**
 * @author Pranoy 21587070
 * <p></p>
 * Command used to add contents into the ContentManager.
 */
public class AddContentCommand implements Command {
    private final ContentManager contentManager;
    private ArchiveableContent content;

    public AddContentCommand(ContentManager contentManager, ArchiveableContent content) {
        this.contentManager = contentManager;
        this.content = content;
    }

    public AddContentCommand setContent(ArchiveableContent content) {
        this.content = content;
        return this;
    }

    @Override
    public void execute() {
        contentManager.addContent(content);
    }
}
