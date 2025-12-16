import java.util.ArrayList;

public class StudentManager {
// Changed initialization. The list will be populated by the file handler now.
    private ArrayList<Student> students; 
    // Modified the constructor to load data on startup.
    public StudentManager() {
        // Load existing records when the manager is created. If the file is not found, an empty list is returned.


    
        this.students = StudentFileHandler.loadRecords() ;}


    // Add student
    public void addStudent(Student s) {
        if (validateStudent(s)) {
            if (isStudentExists(s.getStudentId())) {
                System.out.println("Student already exists!");
            } else {
                students.add(s);
                // Save the changes to the file immediately after adding
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
            // Save the changes to the file immediately after removing
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

// Save the changes to the file immediately after updating
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

    // Check if student exists
    public boolean isStudentExists(int sid) {
        return findById(sid) != null;
    }

    // Count students
    public int countStudent() {
        return students.size();
    }

    // Print all students
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

        if (s.getStudentId() < 100000 ||  s.getStudentId() > 999999) return false;
        if (s.getName() == null || s.getName().isEmpty()) return false;
        if (s.getStudentId() < 100000 || s.getStudentId() > 999999) return false;
        if (s.getName() == null||  s.getName().isEmpty()) return false;
        if (s.getGpa() < 0  ||s.getGpa() > 4) return false;
        if (s.getMajor() == null || s.getMajor().getMajorName().isEmpty()) return false;
        if (s.getYear() == null || s.getYear().isEmpty()) return false;

        return true;
    }
}