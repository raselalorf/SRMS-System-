import java.util.ArrayList;
import java.util.Comparator;

public class ReportGenerator {

    // عرض الطلاب من الأعلى GPA إلى الأقل
    public static void topGPA(ArrayList<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getGpa).reversed());

        for (Student s : students) {
            System.out.println(s);
            System.out.println("------------");
        }
    }

    // عرض الطلاب حسب التخصص
    public static void byMajor(ArrayList<Student> students, String major) {
        for (Student s : students) {
            if (s.getMajor().getMajorName().equalsIgnoreCase(major)) {
                System.out.println(s);
            }
        }
    }
}
