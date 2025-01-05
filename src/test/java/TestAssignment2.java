import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pranoy.uni.project.archive.ArchiveRepository;
import pranoy.uni.project.archive.ArchiveRepositoryImpl;
import pranoy.uni.project.command.*;
import pranoy.uni.project.content.ContentManagerImpl;
import pranoy.uni.project.archive.ArchiveableContent;
import pranoy.uni.project.content.ContentManager;
import pranoy.uni.project.model.Advertisement;
import pranoy.uni.project.model.Magazine;
import pranoy.uni.project.model.Photographs;
import pranoy.uni.project.model.Stories;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Pranoy - 21587070
 * <p></p>
 * Class used for Unit testing ArchiveRepository and ContentManager.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAssignment2 {
    // seed the test data
    public static final List<ArchiveableContent> testData = List.of(
            TestData.story1,
            TestData.story2,
            TestData.magazine1,
            TestData.magazine2,
            TestData.advertisement1,
            TestData.advertisement2,
            TestData.photograph1,
            TestData.photograph2
    );


    @Test
    @Order(1)
    void testContentManagerSeeding() {
        // initialize archive manager for dependency injection and standalone reasons.
        ArchiveRepositoryImpl.initialize();
        // initialize content manager.
        ContentManagerImpl.initialize(ArchiveRepositoryImpl.getInstance());

        ContentManager contentManager = ContentManagerImpl.getInstance();

        // add contents
        contentManager.addContents(testData);
        // check if content manager added the contents properly.
        assertTrue(contentManager.getContents().containsAll(testData));
    }

    @Test
    @Order(2)
    void testArchiveManager() {
        ArchiveRepository archiveRepository = ArchiveRepositoryImpl.getInstance();

        archiveRepository.saveToArchive(TestData.story1);

        // check if archive functionality works
        assertTrue(archiveRepository.isInArchive(TestData.story1.getId()));

        archiveRepository.saveToArchive(TestData.story2);
        System.out.println(archiveRepository.getArchiveDetails());

        // check if size of archived contents is 2. Since we only archived two items
        assertEquals(2, archiveRepository.getArchivedContents().size());

    }

    @Test
    @Order(3)
    void testCommands() {
        ArchiveRepository archiveRepository = ArchiveRepositoryImpl.getInstance();
        ContentManager contentManager = ContentManagerImpl.getInstance();

        // make sure previous contents are cleared.
        contentManager.purgeContents();
        archiveRepository.purgeArchive();

        // first we initialize our command invoker.
        CommandInvoker commandInvoker = new CommandInvoker();

        // create commands for adding content to our content manager
        AddContentCommand addStory1 = new AddContentCommand(contentManager, TestData.story1);
        AddContentCommand addStory2 = new AddContentCommand(contentManager, TestData.story2);

        // add commands to command invoker
        commandInvoker.addCommand(addStory1);
        commandInvoker.addCommand(addStory2);

        // invoke command.
        commandInvoker.invoke();

        // make sure content manager has 2 elements present.
        assertEquals(2, contentManager.getContents().size());

        // now lets add some magazines.
        AddContentCommand addMagazine1 = new AddContentCommand(contentManager, TestData.magazine1);
        AddContentCommand addMagazine2 = new AddContentCommand(contentManager, TestData.magazine2);

        commandInvoker.addCommand(addMagazine1);
        commandInvoker.addCommand(addMagazine2);

        // invoke commands again
        commandInvoker.invoke();

        // make sure content manager has 4 items now.
        assertEquals(4, contentManager.getContents().size());
        assertTrue(contentManager.getContents().containsAll(List.of(TestData.magazine1, TestData.magazine2, TestData.story1, TestData.story2)));


        // add more contents.
        AddContentCommand addPhotograph1 = new AddContentCommand(contentManager, TestData.photograph1);
        AddContentCommand addPhotograph2 = new AddContentCommand(contentManager, TestData.photograph2);
        AddContentCommand addAdvertisement1 = new AddContentCommand(contentManager, TestData.advertisement1);
        AddContentCommand addAdvertisement2 = new AddContentCommand(contentManager, TestData.advertisement2);

        commandInvoker.addCommand(addPhotograph1);
        commandInvoker.addCommand(addPhotograph2);
        commandInvoker.addCommand(addAdvertisement1);
        commandInvoker.addCommand(addAdvertisement2);
        commandInvoker.invoke();

        assertEquals(8, contentManager.getContents().size());

        // archive 3 items only
        ArchiveContentCommand archiveStory1 = new ArchiveContentCommand(archiveRepository, TestData.story1);
        ArchiveContentCommand archiveStory2 = new ArchiveContentCommand(archiveRepository, TestData.story2);
        ArchiveContentCommand archiveMagazine1 = new ArchiveContentCommand(archiveRepository, TestData.magazine1);

        commandInvoker.addCommand(archiveStory1);
        commandInvoker.addCommand(archiveStory2);
        commandInvoker.addCommand(archiveMagazine1);

        commandInvoker.invoke();

        // make sure archive repository has 3 items.
        assertEquals(3, archiveRepository.getArchivedContents().size());

        // check if the models are archived
        assertTrue(TestData.story1.isArchived());
        assertTrue(TestData.story2.isArchived());
        assertTrue(TestData.magazine1.isArchived());

        // let's try removing items from ContentManager and ArchiveRepository now.
        RemoveContentCommand removeStory2 = new RemoveContentCommand(contentManager, TestData.story2);
        UnarchiveContentCommand unarchiveMagazine1 = new UnarchiveContentCommand(archiveRepository, TestData.magazine1);

        commandInvoker.addCommand(removeStory2);
        commandInvoker.addCommand(unarchiveMagazine1);

        commandInvoker.invoke();

        // new expected values
        assertEquals(2, archiveRepository.getArchivedContents().size());
        assertEquals(7, contentManager.getContents().size());
        assertFalse(TestData.magazine1.isArchived());

        // data persistence.
        contentManager.saveToDrive("cm.bin");
        archiveRepository.saveToDrive("ar.bin");
    }

    @Test
    @Order(4)
    void testDataLoading() {
        ArchiveRepository archiveRepository = ArchiveRepositoryImpl.getInstance();
        ContentManager contentManager = ContentManagerImpl.getInstance();

        // first we remove all contents from archiveRepository and content manager.
        archiveRepository.purgeArchive();
        contentManager.purgeContents();

        // make sure its empty.
        assertTrue(contentManager.getContents().isEmpty());
        assertTrue(archiveRepository.getArchivedContents().isEmpty());

        // load content manager and archive repository from local storage
        assertTrue(contentManager.loadFromDrive("cm.bin"));
        assertTrue(archiveRepository.loadFromDrive("ar.bin"));

        // Expected values
        assertEquals(7, contentManager.getContents().size());
        assertEquals(2, archiveRepository.getArchivedContents().size());
    }

    public static class TestData {
        public static final Stories story1 = new Stories("Harry Potter", "Test data");
        public static final Stories story2 = new Stories("Harry Potter Pt.2", "test data");

        public static final Magazine magazine1 = new Magazine(1, "issued now.");
        public static final Magazine magazine2 = new Magazine(2, "issued now.");

        public static final Photographs photograph1 = new Photographs("test photograph", "test.png");
        public static final Photographs photograph2 = new Photographs("test photograph", "test.png");

        public static final Advertisement advertisement1 = new Advertisement("test Advert");
        public static final Advertisement advertisement2 = new Advertisement("test Advert");
    }
}
