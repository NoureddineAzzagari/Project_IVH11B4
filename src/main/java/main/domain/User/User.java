package main.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String firstname; //required
    private String lastname; //required
    private String address; //required
    private String emailAddress; //required
    private String userName; //required
    private String password; //required
    private int age; //required
    private int telephoneNumber; //optional

    private User(UserBuilder builder) {
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.address = builder.address;
        this.age = builder.age;
        this.telephoneNumber = builder.telephoneNumber;
        this.userName = builder.userName;
        this.password = builder.password;
        this.emailAddress = builder.emailAddress;

    }

    public static class UserBuilder {

        private final String firstname;
        private final String lastname;
        private final String address;
        private int age;
        private int telephoneNumber;
        private String userName;
        private String password;
        private String emailAddress;

        public UserBuilder(String firstname, String lastname, String address, int age, String userName, String password, String emailAddress) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.address = address;
            this.age = age;
            this.userName = userName;
            this.password = password;
            this.emailAddress = emailAddress;
        }

        public UserBuilder telephoneNumber(int telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }



}
