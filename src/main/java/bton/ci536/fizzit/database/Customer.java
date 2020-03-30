package bton.ci536.fizzit.database;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;


@NamedQueries(
        @NamedQuery(
                name = "userLogin",
                query = "select c from Customer c where "
                + "c.email like :cEmail and c.password like :cPass"
        )
)
@Named
@Entity
@Table(name = "Customer")
@SessionScoped
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customerId")
    private String customerId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "fname")
    private String fname;
    @Column(name = "sname")
    private String sname;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }


    public boolean passwordMatch(String enteredPass) {
        return password.equals(enteredPass);
	}
}
