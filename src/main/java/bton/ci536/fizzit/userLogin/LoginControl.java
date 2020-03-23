package bton.ci536.fizzit.userLogin;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bton.ci536.fizzit.database.Customer;
import bton.ci536.fizzit.trade.LocalTradeList;


@Named("LoginControl")
@SessionScoped
public class LoginControl implements Serializable{
	
	
	
	@PersistenceContext(name="twoo")
	EntityManager em;
	
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	
	private String message;
	
	private Customer customer;
	
	@ManagedProperty(value = "#{LocalTradeList}")
	LocalTradeList tradeControl;
	
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
			customer = q.getSingleResult();
			message = customer.getFname();
		}catch(Exception c) {
			message = "either your email or password was incorrect";
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
