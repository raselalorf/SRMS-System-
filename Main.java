public class Main {
      public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();

        // Thread لطباعة كل الطلاب
        Thread printThread = new Thread(() -> {
            studentManager.printAllStudent();
        });

        // Thread لإضافة طالب جديد
        Thread addThread = new Thread(() -> {
            Student s = new Student("Ali", 20, 12345, 123456, new Major("CS101", "CS"), 3.5, "Sophomore");
            studentManager.addStudent(s);
        });

        // تشغيل الThreads
        printThread.start();
        addThread.start();
    }

}

