package bton.ci536.fizzit.userLogin;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bton.ci536.fizzit.database.Customer;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class LoginControl implements Serializable {
    

    @PersistenceContext(name = "twoo")
    EntityManager em;

    private static final long serialVersionUID = 1L;
    private String email;
    private String password;

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

    

    public void submit(Customer customer) {
        
        
        Customer cust = em.createNamedQuery("userLogin", Customer.class)
                .setParameter("cEmail", email)
                .setParameter("cPass", password)
                .getSingleResult();
        
        if (cust == null) {
            email = null;
            password = null;
            FacesContext.getCurrentInstance()
                    .addMessage("form:user-login", 
                            new FacesMessage("Unfortunatly either the "
                                    + "password or email was incorrect, "
                                    + "please try again"));
        } else {
            //hack to update managed bean :(
            customer.setCustomerId(cust.getCustomerId());
            customer.setEmail(cust.getEmail());
            customer.setFname(cust.getFname());
            customer.setPassword(cust.getPassword());
            customer.setSname(cust.getSname());
            try {
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect("index.xhtml");
            } catch(IOException ex)
            {
                ex.printStackTrace(System.err);
            }
        }
    }
}
