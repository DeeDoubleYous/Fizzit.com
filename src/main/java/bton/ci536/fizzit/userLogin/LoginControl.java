package bton.ci536.fizzit.userLogin;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("LoginControl")
@SessionScoped
public class LoginControl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void submit() {
		
	}

}
