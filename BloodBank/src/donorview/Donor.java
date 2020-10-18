/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donorview;

/**
 *
 * @author asus
 */
public class Donor {
    private int id;
    private String lastName;
    private String firstName;
    private int Age;
    private String Gender;
    private String bloodGroup;
    private String phoneNumber;
    private String City;
    
    public Donor(int id, String lastName, String firstName, int Age, String Gender, String bloodGroup, String phoneNumber, String City)
    {
        this.id=id;
        this.lastName=lastName;
        this.firstName=firstName;
        this.Age=Age;
        this.Gender=Gender;
        this.bloodGroup=bloodGroup;
        this.phoneNumber=phoneNumber;
        this.City=City;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
    
    
}
