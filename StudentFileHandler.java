import java.util.ArrayList;
import java.util.List;
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
     * @throws IOException إذا حدث خطأ أثناء الكتابة على الملف.
     */
    public static void saveRecords(List<Student> students) throws IOException { // 👈 تم إضافة 'throws IOException'
        
        try (
            FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)
        ) {
            objectOut.writeObject(students); 
            System.out.println("Success: Student records saved to " + FILE_NAME);

        } catch (IOException e) {
            // [EXCEPTION HANDLING]: طباعة الخطأ قبل إلقائه يسمح بتسجيله هنا
            System.err.println("Error saving records: An I/O error occurred during serialization.");
            System.err.println("Details: " + e.getMessage());
            // إعادة إلقاء الاستثناء ليتم التقاطه بواسطة الكلاس الذي يستدعيه (مثل AutoSaveThread)
            throw e; 
        }
    }

    // --- LOAD METHOD ---
    // تم إبقاء loadRecords كما هي لأنها تتعامل مع الأخطاء داخلياً وترجع قائمة فارغة عند الفشل
    /**
     * Loads the entire ArrayList of Student objects from the file using Deserialization.
     * @return The list of students loaded from the file, or an empty list if loading fails.
     */
    @SuppressWarnings("unchecked") 
    public static ArrayList<Student> loadRecords() {
        ArrayList<Student> students = new ArrayList<>();

        try (
            FileInputStream fileIn = new FileInputStream(FILE_NAME);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)
        ) {
            students = (ArrayList<Student>) objectIn.readObject();
            System.out.println("Success: Student records loaded from " + FILE_NAME);

        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty record list (First Run).");
            return new ArrayList<>(); 

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class definition mismatch. Cannot load old data format.");
            System.err.println("Details: " + e.getMessage());
            return new ArrayList<>();

        } catch (IOException e) {
            System.err.println("Error loading records due to file corruption or I/O issues.");
            System.err.println("Details: " + e.getMessage());
            return new ArrayList<>();
        }
        
        return students;
    }
}