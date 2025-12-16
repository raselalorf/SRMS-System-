import java.io.IOException;
// لاحظ أننا لم نعد نحتاج لإنشاء كائن StudentFileHandler

public class AutoSaveThread extends Thread {

    private final StudentManager manager; // يفضل استخدام final
    // حذف تعريف fileHandler

    public AutoSaveThread(StudentManager manager) {
        this.manager = manager;
        // حذف تهيئة fileHandler
        this.setName("AutoSaveThread-Worker"); 
    }

    @Override
    public void run() {
        System.out.println("AutoSave thread started. Saving every minute...");
        
        while (true) {
            try {
                Thread.sleep(60000); 

               
                StudentFileHandler.saveRecords(manager.getAllStudents()); 
                System.out.println("Records saved successfully at: " + new java.util.Date());

            } 
            catch (InterruptedException e) {
                System.out.println("AutoSave thread interrupted. Exiting clean.");
                break;
            } 
            catch (IOException e) { 
                System.err.println("Error saving records automatically: " + e.getMessage());
            }
        }
    }
}