
import java.io.Serializable;

public class Major implements Serializable {

    private String majorCode;
    private String majorName;

    public Major(String code, String name) {
        setMajorCode(code);
        setMajorName(name);
    }

    public String getMajorCode() {
        return majorCode;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorCode(String code) {
        if (code == null || code.isEmpty()) {
            majorCode = "UNK";
        } else {
            majorCode = code.toUpperCase();
        }
    }
    public void setMajorName(String name) {
        if (name == null || name.isEmpty()) {
            majorName = "Unknown Major";
        } else {
            majorName = name;
        }
    }
    
    @Override
    public String toString() {
        return majorName + " (" + majorCode + ")";
    }
}
