import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        students = StudentFileHandler.loadRecords();
        if (students == null) {
            students = new ArrayList<>();
        }
    }

    // Add student
    public void addStudent(Student s) {
        if (validateStudent(s)) {
            if (isStudentExists(s.getStudentId())) {
                System.out.println("Student already exists!");
            } else {
                students.add(s);
                StudentFileHandler.saveRecords(students);
                System.out.println("Student added successfully.");
            }
        } else {
            System.out.println("Invalid student data!");
        }
    }

    // Remove student by ID
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

    // Find student by ID
    public Student findById(int sid) {
        for (Student s : students) {
            if (s.getStudentId() == sid) {
                return s;
            }
        }
        return null;
    }

    // Update student information
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

    // Get students by year
    public ArrayList<Student> getByYear(String y) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getYear().equalsIgnoreCase(y)) {
                result.add(s);
            }
        }
        return result;
    }

    // Get students by major
    public ArrayList<Student> getByMajor(Major m) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getMajor().getMajorName().equalsIgnoreCase(m.getMajorName())) {
                result.add(s);
            }
        }
        return result;
    }

    public boolean isStudentExists(int sid) {
        return findById(sid) != null;
    }

    public int countStudent() {
        return students.size();
    }

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

    // Validate student data
    private boolean validateStudent(Student s) {
        if (s.getStudentId() < 100000 || s.getStudentId() > 999999) return false;
        if (s.getName() == null || s.getName().isEmpty()) return false;
        if (s.getGpa() < 0 || s.getGpa() > 4) return false;
        if (s.getMajor() == null || s.getMajor().getMajorName().isEmpty()) return false;
        if (s.getYear() == null || s.getYear().isEmpty()) return false;
        return true;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}