public class AutoSaveThread extends Thread {

    private StudentManager manager;

    public AutoSaveThread(StudentManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60000); // كل دقيقة
                StudentFileHandler.saveRecords(manager.getAllStudents());
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
