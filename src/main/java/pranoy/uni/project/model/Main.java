package pranoy.uni.project.model;

import pranoy.uni.project.archive.ArchiveRepositoryImpl;
import pranoy.uni.project.archive.ArchiveableContent;
import pranoy.uni.project.content.ContentManagerImpl;

import java.util.List;
import java.util.Scanner;

/**
 * @author Pranoy 21587070
 * <p>
 *
 *     This is a simple program created just for interactivity, the test use-case is more
 *     useful. See test/java/TestAssignment2
 * </p>
 */
public class Main {
    public static void main(String[] args) {
        ArchiveRepositoryImpl.initialize();
        ContentManagerImpl.initialize(ArchiveRepositoryImpl.getInstance());

        int choice = 0;

        while (choice != 10) {
            System.out.println("====Menu Driven Archival Program===");
            System.out.println("1. Create New Content");
            System.out.println("2. Display All Content");
            System.out.println("3. Delete Content");
            System.out.println("4. Archive Content");
            System.out.println("5. Remove Content from archive");
            System.out.println("6. Show archive contents");
            System.out.println("7. Deserialize contents to ContentManager");
            System.out.println("8. Deserialize Contents to ArchiveRepository");
            System.out.println("9. Serialize all data");
            System.out.println("10. Exit");
            System.out.println("===================================");

            System.out.print("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            try {
                choice = Integer.parseInt(scanner.nextLine());;
                System.out.println();
            } catch (Exception e) {
                System.out.println("Invalid choice, try again!");
                continue;
            }

            switch (choice) {
                case 1:
                    createNewContent(scanner);
                    break;
                case 2:
                    displayContents();
                    break;
                case 3:
                    deleteContent(scanner);
                    break;
                case 4:
                    archiveContent(scanner);
                    break;
                case 5:
                    removeContentFromArchive(scanner);
                    break;
                case 6:
                    showArchiveContents();
                    break;
                case 7:
                    deserializeContentManager(scanner);
                    break;
                case 8:
                    deserializeArchiveRepository(scanner);
                    break;
                case 9:
                    serializeAllData(scanner);
                    break;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        }
    }

    private static void serializeAllData(Scanner scanner) {
        System.out.println("Enter archive file name: ");
        String archiveFileName = scanner.nextLine();

        System.out.println("Enter contents file name: ");
        String contentsFileName = scanner.nextLine();

        try {
            ArchiveRepositoryImpl.getInstance().saveToDrive(archiveFileName);
            ContentManagerImpl.getInstance().saveToDrive(contentsFileName);
        } catch (Exception ex) {
            System.out.println("Could not save contents to drive!");
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println("Serialized contents to drive!");
    }

    private static void deserializeContentManager(Scanner scanner) {
        System.out.print("Enter file name: ");
        String filename = scanner.nextLine();

        try {
            ContentManagerImpl.getInstance().loadFromDrive(filename);
        } catch (Exception ex) {
            System.out.println("could not deserialize contents from file: " + filename);
            System.out.println(ex.getMessage());
        }
    }

    private static void deserializeArchiveRepository(Scanner scanner) {
        System.out.print("Enter file name: ");
        String filename = scanner.nextLine();

        try {
            ArchiveRepositoryImpl.getInstance().loadFromDrive(filename);
        } catch (Exception ex) {
            System.out.println("could not deserialize contents from file: " + filename);
            System.out.println(ex.getMessage());
        }
    }

    private static void showArchiveContents() {
        List<ArchiveableContent> contents = ArchiveRepositoryImpl.getInstance().getArchivedContents();
        if (contents.isEmpty()) {
            System.out.println("There are no archive contents in the archive repository");
            return;
        }
        for (ArchiveableContent content : contents) {
            System.out.println(content.toString());
        }
    }

    private static void removeContentFromArchive(Scanner scanner) {
        System.out.print("Enter content id to be removed from archive: ");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            return;
        }

        ArchiveableContent content = ArchiveRepositoryImpl.getInstance().retrieveFromArchive(id);
        if (content == null) {
            System.out.println("Content with id " + id + " does not exist in archive!");
            return;
        }

        ArchiveRepositoryImpl.getInstance().removeFromArchive(content);
        System.out.println("Content with id " + id + " was removed from archive!");
    }

    private static void archiveContent(Scanner scanner) {
        System.out.print("Enter content id to be archived: ");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            return;
        }

        ArchiveableContent content = ContentManagerImpl.getInstance().getContent(id);
        if (content == null) {
            System.out.println("Content with id " + id + " does not exist!");
            return;
        }

        ArchiveRepositoryImpl.getInstance().saveToArchive(content);
        System.out.println("Content with id " + id + " has been archived!");
    }

    private static void displayContents() {
        List<ArchiveableContent> contents = ContentManagerImpl.getInstance().getContents();
        if (contents.isEmpty()) {
            System.out.println("There are no archive contents in the Content Manager");
            return;
        }
        for (ArchiveableContent content : contents) {
            System.out.println(content.toString());
        }
    }

    private static void deleteContent(Scanner scanner) {
        System.out.print("Enter content id to be deleted: ");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            return;
        }

        if (!ContentManagerImpl.getInstance().hasContent(id)) {
            System.out.println("Content with id " + id + " does not exist!");
            return;
        }
        ContentManagerImpl.getInstance().removeContent(id);
        System.out.println("Content with id " + id + " has been deleted!");
    }

    private static void createNewContent(Scanner scanner) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("====Content Types===");
            System.out.println("1: Photograph");
            System.out.println("2: Story");
            System.out.println("3: Magazine");
            System.out.println("4: Advertisement");
            System.out.println("5: Exit ");
            System.out.println("======================");

            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());;
                System.out.println();
            } catch (Exception e) {
                System.out.println("Invalid choice, try again!");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter photograph description: ");
                        String pDesc = scanner.nextLine();
                        System.out.print("Enter photograph file path: ");
                        String pFilePath = scanner.nextLine();
                        Photographs photograph = new Photographs(pDesc, pFilePath);
                        ContentManagerImpl.getInstance().addContent(photograph);
                        System.out.println("\nAdded photograph with id: " + photograph.getId());
                        return;
                    case 2:
                        System.out.print("Enter story content: ");
                        String sContent = scanner.nextLine();
                        System.out.print("Enter story title");
                        String sTitle = scanner.nextLine();
                        Stories story = new Stories(sTitle, sContent);
                        ContentManagerImpl.getInstance().addContent(story);
                        System.out.println("\nAdded story with id: " + story.getId());
                        return;
                    case 3:
                        System.out.print("Enter issue id: ");
                        int mIssueId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter magazine title");
                        String mTitle = scanner.nextLine();
                        Magazine magazine = new Magazine(mIssueId, mTitle);
                        ContentManagerImpl.getInstance().addContent(magazine);
                        System.out.println("\nAdded magazine with id: " + magazine.getId());
                        return;
                    case 4:
                        System.out.print("Enter advertisement title: ");
                        String aTitle = scanner.nextLine();
                        Advertisement ad = new Advertisement(aTitle);
                        ContentManagerImpl.getInstance().addContent(ad);
                        System.out.println("\nAdded advertisement with id: " + ad.getId());
                        return;
                }
            } catch (Exception ex) {
                System.out.println("Invalid choice, try again!");
                continue;
            }
        }
    }
}