
public class Person  {

import java.io.Serializable; // Necessary import for the Serializable interface

public class Person implements Serializable { // Parent class must implement Serializable for Student to inherit file saving ability
protected String name;
protected int age;
protected int nationalId;
 // Constroctor
public Person (){}

public Person (String n,int a,int id){
    setName(n);
    setAge(a);
    setNationalId(id);}
//Getters
public String getName(){
    return name;}

public int getAge(){
    return age;}

public int getNationalId(){
    return nationalId;}
// Setters
public void setName(String n){
if (n ==null|| n.equals("")){
name ="unknown";}else {
    name=n;}}

public void setAge(int a){
if(a>0){
    age=a;}else{
throw new IllegalArgumentException("Age must be positive!") ;}}

public void setNationalId(int id){
if(id>=10000 &&id<=99999){
    nationalId=id;}else{
      throw new IllegalArgumentException("id must be 5 digits!");
        }}
// toString
   public String toString(){
    return"Name :"+getName()+
    "\nAge:"+getAge()+
    "\nNational ID:"+getNationalId();}
}