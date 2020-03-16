package bton.ci536.fizzit.database;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.*;

@Named
@Entity()
@Table(name="Customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "fname")
	private String fname;
	@Column(name = "sname")
	private String sname;
	
	public String getEmail(){
		return email;
	}
	
	public String getFname() {
		return fname;
	}
	
	public String getSname() {
		return sname;
	}
	
	public boolean passwordMatch(String enteredPass) {
		if(this.password.equals(enteredPass)) {
			return true;
		}else {
			return false;
		}
	}
}
