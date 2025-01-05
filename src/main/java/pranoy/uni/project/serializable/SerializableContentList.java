package pranoy.uni.project.serializable;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that implements basic serialization and deserialization of ArrayList data.
 * @param <T> type of the elements in the list to serialize
 */
public abstract class SerializableContentList<T> {
    protected final List<T> contentList = new ArrayList<>();

    /**
     * Saves the current ContentManager list to local storage.
     * @param fileName the name of the file to be saved
     */
    public void saveToDrive(String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(file);

            outputStream.writeObject(contentList);

            outputStream.close();
            file.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the data from the provided fileName and checks it's validity.
     * @param fileName name of the file to load from
     * @return true if load was successful, false otherwise
     */
    public boolean loadFromDrive(String fileName) {
        boolean loaded = false;

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            Object data = in.readObject();

            if (data instanceof List) {
                List<T> newContentList = (List<T>) data;

                if (!newContentList.isEmpty()) {
                    contentList.clear();
                    contentList.addAll(newContentList);
                    loaded = true;
                }
            }

            in.close();
            file.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return loaded;
    }
}
