package bton.ci536.fizzit.userLogin;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bton.ci536.fizzit.database.Customer;

@Named("LoginControl")
@SessionScoped
public class LoginControl implements Serializable{
	
	@PersistenceContext(name="twoo")
	EntityManager em;
	
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
		Customer c = em.find(Customer.class, email);
		if(c == null) {
			c = null;
			email = null;
			password = null;
			FacesContext.getCurrentInstance().addMessage("form:user-login", new FacesMessage("Unfortunatly one of either you password or email was incorrect, please try again"));
		}else if(!c.passwordMatch(password)){
			c = null;
			email = null;
			password = null;
			FacesContext.getCurrentInstance().addMessage("form:user-login", new FacesMessage("Unfortunatly one of either you password or email was incorrect, please try again"));
		}
	}

}
