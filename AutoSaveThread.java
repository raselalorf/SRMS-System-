import java.io.IOException;
public class AutoSaveThread extends Thread {

    private StudentManager manager;

    public AutoSaveThread(StudentManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000); // كل 5 ثواني

                StudentFileHandler.saveRecords(manager.getStudents());
                System.out.println("[AutoSave] Data saved automatically.");

            } catch (InterruptedException e) {
                System.out.println("[AutoSave] Thread stopped.");
                break;
            }
            catch (IOException e) {
                System.err.println("[AutoSave] Error during auto-save: " + e.getMessage());
        }
    }
 
}
       
}