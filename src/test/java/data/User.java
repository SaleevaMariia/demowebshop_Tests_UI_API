package data;

import com.github.javafaker.Faker;
import dictionaries.Gender;

public class User {

    private Gender gender;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String authCookie;

    public String getAuthCookie() {
        return authCookie;
    }

    public void setAuthCookie(String authCookie) {
        this.authCookie = authCookie;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
        Faker faker = new Faker();
        this.gender = Gender.MALE;
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().emailAddress();
        this.password = faker.internet().password();
        this.confirmPassword = this.password;
    }

    public User(Gender gender, String firstName, String lastName, String email, String password, String confirmPassword) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String toRegisterPostRequest(){
        return String.format("Gender=%s&FirstName=%s&LastName=%s&Email=%s&Password=%s&ConfirmPassword=%s",
                this.gender, this.firstName, this.lastName, this.email, this.password, this.confirmPassword) ;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "gender=" + gender +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
