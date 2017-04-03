package sample.web.ui.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sample.web.ui.domain.Favourites;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Favourites favourites;

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
        this.favourites = builder.favourites;
    }

    public boolean getAdmin(){
        return this.admin;
    }

    public static class UserBuilder {

        private final String firstName;
        private final String lastName;
        private final String address;
        private final int age;
        private int telephoneNumber;
        private final String userName;
        private final String password;
        private final String emailAddress;
        private final boolean admin;
        private Favourites favourites;

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

        public UserBuilder favourites(Favourites favourites){
            this.favourites = favourites;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
