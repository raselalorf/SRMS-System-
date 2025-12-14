public class Major {
    private String majorCode; 
    private String majorName; 
    // Constructor
    public Major(String code, String name) {
        setMajorCode(code);
        setMajorName(name);
    }

    // Getters
    public String getMajorCode() {
        return majorCode;
    }

    public String getMajorName() {
        return majorName;
    }

    // Setters 
    public void setMajorCode(String code) {
        if (code == null  code.isEmpty()) {
            this.majorCode = "Unknown";
        } else {
            this.majorCode = code.toUpperCase();
        }
    }

    public void setMajorName(String name) {
        if (name == null  name.isEmpty()) {
            majorName = "Unknown Major";
        } else {
            majorName = name;
        }
    }

    @Override
    public String toString() {
        return " Major Name:"+getMajorName()+
         "\nMajor code :" + getMajorCode() ;
    }
}