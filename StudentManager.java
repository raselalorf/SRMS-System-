import java.util.ArrayList;

public class StudentManager {

    // قائمة الطلاب
    private ArrayList<Student> students;

    // Constructor لتحميل البيانات عند إنشاء الكلاس
    public StudentManager() {
        // تحميل السجلات من الملف، إذا الملف غير موجود يتم إنشاء قائمة فارغة
        this.students = StudentFileHandler.loadRecords();
    }

    // إضافة طالب
    public void addStudent(Student s) {
        if (validateStudent(s)) {
            if (isStudentExists(s.getStudentId())) {
                System.out.println("Student already exists!");
            } else {
                students.add(s);
                // حفظ التغييرات مباشرة بعد الإضافة
                StudentFileHandler.saveRecords(students);
                System.out.println("Student added successfully.");
            }
        } else {
            System.out.println("Invalid student data!");
        }
    }

    // إزالة طالب حسب ID
    public void removeStudent(int sid) {
        Student s = findById(sid);
        if (s != null) {
            students.remove(s);
            StudentFileHandler.saveRecords(students);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // البحث عن طالب حسب ID
    public Student findById(int sid) {
        for (Student s : students) {
            if (s.getStudentId() == sid) {
                return s;
            }
        }
        return null;
    }

    // تحديث معلومات طالب
    public void updateStudent(int sid, String newName, int newAge, int newNationalId,
                              Major newMajor, double newGpa, String newYear) {
        Student s = findById(sid);
        if (s != null) {
            s.setName(newName);
            s.setAge(newAge);
            s.setNationalId(newNationalId);
            s.setMajor(newMajor);
            s.setGpa(newGpa);
            s.setYear(newYear);

            StudentFileHandler.saveRecords(students);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // الحصول على الطلاب حسب السنة
    public ArrayList<Student> getByYear(String y) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getYear().equalsIgnoreCase(y)) {
                result.add(s);
            }
        }
        return result;
    }

    // الحصول على الطلاب حسب التخصص
    public ArrayList<Student> getByMajor(Major m) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().getMajorName().equalsIgnoreCase(m.getMajorName())) {
                result.add(s);
            }
        }
        return result;
    }

    // التحقق من وجود الطالب
    public boolean isStudentExists(int sid) {
        return findById(sid) != null;
    }

    // عدد الطلاب
    public int countStudent() {
        return students.size();
    }

    // طباعة جميع الطلاب
    public void printAllStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
            System.out.println("----------");
        }
    }

    // التحقق من صحة بيانات الطالب
    private boolean validateStudent(Student s) {
        if (s.getStudentId() < 100000 || s.getStudentId() > 999999) return false;
        if (s.getName() == null || s.getName().isEmpty()) return false;
        if (s.getGpa() < 0 || s.getGpa() > 4) return false;
        if (s.getMajor() == null || s.getMajor().getMajorName().isEmpty()) return false;
        if (s.getYear() == null || s.getYear().isEmpty()) return false;
        return true;
    }

    
    // Main للتجربة مع Threads
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
       
        AutoSaveThread autoSave = new AutoSaveThread(manager);
        autoSave.start();

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
    public java.util.List<Student> getAllStudents() {
    return students;
}

}