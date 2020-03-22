package bton.ci536.fizzit.userLogin;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bton.ci536.fizzit.database.Customer;


@Named("LoginControl")
@SessionScoped
public class LoginControl implements Serializable{
	
	
	
	@PersistenceContext(name="twoo")
	EntityManager em;
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	
	private String message;
	
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
		TypedQuery<Customer> q = em.createQuery("select c from Customer c where c.email = '" + email + "' and c.password = '" + password + "'", Customer.class);
		try {
			Customer c = q.getSingleResult();
			message = c.getFname();
		}catch(Exception c) {
			message = "either you email or password was incorrect";
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
