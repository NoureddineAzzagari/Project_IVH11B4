package sample.web.ui.domain.User;

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
    private String firstName; //required
    private String lastName; //required
    private String address; //required
    private String emailAddress; //required
    private String userName; //required
    private String password; //required
    private boolean admin; //required
    private int age; //required
    private int telephoneNumber; //optional

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.age = builder.age;
        this.telephoneNumber = builder.telephoneNumber;
        this.userName = builder.userName;
        this.password = builder.password;
        this.emailAddress = builder.emailAddress;
        this.admin = builder.admin;
    }

    public boolean getAdmin(){
        return this.admin;
    }

    public static class UserBuilder {

        private final String firstName;
        private final String lastName;
        private final String address;
        private int age;
        private int telephoneNumber;
        private String userName;
        private String password;
        private String emailAddress;
        private boolean admin;

        public UserBuilder(String firstName, String lastName, String address, int age, String userName, String password, String emailAddress, boolean admin) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.age = age;
            this.userName = userName;
            this.password = password;
            this.emailAddress = emailAddress;
            this.admin = admin;
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
