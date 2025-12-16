import java.io.Serializable;
public class Student extends Person{
   private int  studentId; 
   private Major major;
   private double gpa;
   private String year;
// Constroctor
  public Student(String n,int a,int id,int sid,Major m,double g,String y ){
   super (n,a,id);
  setMajor(m);
  setGpa(g);
  setYear(y);
  setStudentId(sid);
  }
//Getters
   public Major getMajor(){
    return major;}

public int getStudentId(){
    return studentId;}

public double getGpa(){
    return gpa;}

public String getYear(){
    return year;}
//Setters
public void setStudentId(int sid){
    if(sid>=100000&& sid<=999999){
    studentId=sid;}else{
      throw new IllegalArgumentException("student id must be 6 digits!");}}

public void setMajor(Major m){
if (m ==null ){
major =new Major("UNK","unknown");}else {
    major=m;}}

public void setGpa(double g){ 
    if(g>=0 && g<=4){
        gpa=g;}else{
throw new IllegalArgumentException("GPA must be between 0 and 4");}}
 public void setYear(String y){
    if(y==null|| y.isEmpty()){year="unknown";}else{
    year =y;}}
//toString
 @Override
  public String toString(){
    return"Name:"+getName()+
    "\nAge:"+getAge()+
    "\nNational ID:"+getNationalId()+
    "\nStudent ID:"+ getStudentId()+
     "\nMajor:"+getMajor()+
     "\nGPA:"+getGpa()+
     "\nYear:"+getYear();}


public class Student extends Person implements Serializable {
    private int studentId;       // 6-digit student ID
    private Major major;         // Student major
    private double gpa;          // 0.0 - 4.0
    private String year;         // e.g., "Sophomore"

    // Constructor
    public Student(String name, int age, int nationalId, int studentId,
                   Major major, double gpa, String year) {
        super(name, age, nationalId);
        setStudentId(studentId);
        setMajor(major);
        setGpa(gpa);
        setYear(year);
    }

    // Getters
    public int getStudentId() { return studentId; }
    public Major getMajor() { return major; }
    public double getGpa() { return gpa; }
    public String getYear() { return year; }

    // Setters
    public void setStudentId(int studentId) {
        if (studentId >= 100000 && studentId <= 999999) {
            this.studentId = studentId;
        } else {
            throw new IllegalArgumentException("Student ID must be 6 digits!");
        }
    }

    public void setMajor(Major major) {
        if (major == null) {
            this.major = new Major("UNK", "Unknown");
        } else {
            this.major = major;
        }
    }

    public void setGpa(double gpa) {
        if (gpa >= 0 && gpa <= 4) {
            this.gpa = gpa;
        } else {
            throw new IllegalArgumentException("GPA must be between 0 and 4");
        }
    }

    public void setYear(String year) {
        if (year == null || year.isEmpty()) {
            this.year = "Unknown";
        } else {
            this.year = year;
        }
    }

    // toString
    @Override
    public String toString() {
        return "Name: " + getName() +
               "\nAge: " + getAge() +
               "\nNational ID: " + getNationalId() +
               "\nStudent ID: " + getStudentId() +
               "\nMajor: " + getMajor() +
               "\nGPA: " + getGpa() +
               "\nYear: " + getYear();
    }
}}