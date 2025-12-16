import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private ArrayList<Student> students; 

    // الدالة البانية (Constructor)
    public StudentManager() {
        // تحميل السجلات الموجودة عند إنشاء Manager
        // نفترض أن loadRecords ترجع ArrayList<Student>
        this.students = StudentFileHandler.loadRecords();
    } 

    // دالة ضرورية لـ AutoSaveThread للقراءة
    public synchronized List<Student> getAllStudents() {
        return students;
    }

    // إضافة طالب
    public synchronized void addStudent(Student s) {
        if (validateStudent(s)) {
            if (isStudentExists(s.getStudentId())) {
                System.out.println("Student already exists!");
            } else {
                students.add(s);
                // هنا نستخدم الدالة الثابتة (Static) من StudentFileHandler
                // يجب التأكد من أنها لا تلقي بـ IOException، أو نضعها في try-catch هنا
                // بما أننا نستخدم AutoSaveThread للحفظ، يمكننا إزالة هذا السطر أو وضعه في try-catch
                // StudentFileHandler.saveRecords(students); 
                System.out.println("Student added successfully.");
            }
        } else {
            System.out.println("Invalid student data!");
        }
    }

    // إزالة طالب
    public synchronized void removeStudent(int sid) {
        Student s = findById(sid);
        if (s != null) {
            students.remove(s);
            // StudentFileHandler.saveRecords(students); 
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // البحث عن طالب بالرقم التعريفي
    public synchronized Student findById(int sid) {
        for (Student s : students) {
            if (s.getStudentId() == sid) {
                return s;
            }
        }
        return null;
    }

    // تحديث بيانات الطالب
    public synchronized void updateStudent(int sid, String newName, int newAge, int newNationalId,
                              Major newMajor, double newGpa, String newYear) {

        Student s = findById(sid);
        if (s != null) {
            s.setName(newName);
            s.setAge(newAge);
            s.setNationalId(newNationalId);
            s.setMajor(newMajor);
            s.setGpa(newGpa);
            s.setYear(newYear);

            // StudentFileHandler.saveRecords(students); 
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // الحصول على الطلاب حسب السنة
    public synchronized ArrayList<Student> getByYear(String y) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getYear().equalsIgnoreCase(y)) {
                result.add(s);
            }
        }
        return result;
    }

    // الحصول على الطلاب حسب التخصص
    public synchronized ArrayList<Student> getByMajor(Major m) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().getMajorName().equalsIgnoreCase(m.getMajorName())) {
                result.add(s);
            }
        }
        return result;
    }

    // التحقق مما إذا كان الطالب موجوداً
    public synchronized boolean isStudentExists(int sid) {
        return findById(sid) != null;
    }

    // عد الطلاب
    public synchronized int countStudent() {
        return students.size();
    }

    // طباعة كل الطلاب
    public synchronized void printAllStudent() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
            System.out.println("----------");
        }
    }

    // التحقق من صحة بيانات الطالب (تم تصحيح العوامل المنطقية)
    private boolean validateStudent(Student s) {
        if (s.getStudentId() < 100000 || s.getStudentId() > 999999) return false;
        if (s.getName() == null || s.getName().isEmpty()) return false;
        if (s.getGpa() < 0 || s.getGpa() > 4) return false;
        if (s.getMajor() == null || s.getMajor().getMajorName().isEmpty()) return false;
        if (s.getYear() == null || s.getYear().isEmpty()) return false;

        return true;
    }
}