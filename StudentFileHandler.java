import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

/**
 * This class handles all file input/output operations for student records.
 * It uses Java Serialization, which is good for saving complex Java objects.
 * This separation of concerns follows the OOP principle of Single Responsibility.
 */
public class StudentFileHandler {
    
    // The fixed name for our serialized data file
    private static final String FILE_NAME = "srms_data.ser"; 

    // --- SAVE METHOD ---
    /**
     * Saves the entire ArrayList of Student objects to a file using Serialization.
     * @param students The list of students to be saved.
     */
    public static void saveRecords(ArrayList<Student> students) {
        // We use try-with-resources: this automatically closes file streams, which is safer
        try (
            // FileOutputStream connects the program to the physical file
            FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
            // ObjectOutputStream prepares Java objects to be written as a stream of bytes
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)
        ) {
            objectOut.writeObject(students); // Save the entire list of objects
            System.out.println("Success: Student records saved to " + FILE_NAME);

        } catch (IOException e) {
            // [EXCEPTION HANDLING]: Catch errors during file writing (e.g., permissions, disk error)
            System.err.println("Error saving records: An I/O error occurred.");
            System.err.println("Details: " + e.getMessage());
        }
    }

    // --- LOAD METHOD ---
    /**
     * Loads the entire ArrayList of Student objects from the file using Deserialization.
     * @return The list of students loaded from the file, or an empty list if loading fails.
     */
    @SuppressWarnings("unchecked") // This suppresses a technical warning when casting the loaded object
    public static ArrayList<Student> loadRecords() {
        ArrayList<Student> students = new ArrayList<>();

        try (
            // FileInputStream reads bytes from the file
            FileInputStream fileIn = new FileInputStream(FILE_NAME);
            // ObjectInputStream converts bytes back into Java objects
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)
        ) {
            // Read the object and cast it back to the expected type
            students = (ArrayList<Student>) objectIn.readObject();
            System.out.println("Success: Student records loaded from " + FILE_NAME);

        } catch (FileNotFoundException e) {
            // [EXCEPTION HANDLING]: CRITICAL for first run. If file doesn't exist, we start empty gracefully.
            System.out.println("Data file not found. Starting with an empty record list (First Run).");
            return new ArrayList<>(); 

        } catch (ClassNotFoundException e) {
            // [EXCEPTION HANDLING]: Handles errors if the saved data structure doesn't match the current class structure.
            System.err.println("Error: Class definition mismatch. Cannot load old data format.");
            System.err.println("Details: " + e.getMessage());
            return new ArrayList<>();

        } catch (IOException e) {
            // [EXCEPTION HANDLING]: Handles general read/corruption errors.
            System.err.println("Error loading records due to file corruption or I/O issues.");
            System.err.println("Details: " + e.getMessage());
            return new ArrayList<>();
        }
        
        return students;
    }
}