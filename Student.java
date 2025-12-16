import java.io.Serializable; // Necessary import for the Serializable interface

// Student now extends Person and implements Serializable 
 public class Student extends Person implements Serializable { // Implementing Serializable allows Student objects to be saved to file
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

}