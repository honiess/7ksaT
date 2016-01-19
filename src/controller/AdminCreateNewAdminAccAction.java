package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.Model;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;
import formbean.AdminCreateNewAdminAccForm;


public class AdminCreateNewAdminAccAction extends Action{

	private FormBeanFactory<AdminCreateNewAdminAccForm> createEmployeeAccountFormFactory = FormBeanFactory.getInstance(AdminCreateNewAdminAccForm.class);
	private AdminDAO adminDAO;
	
	public AdminCreateNewAdminAccAction(Model model){
		adminDAO = model.getAdminDAO();
	}
	
	@Override
	public String getName() {
		return "create-employee-account.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	AdminCreateNewAdminAccForm form = createEmployeeAccountFormFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "create-account-employee.jsp";
	        }
	
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "create-account-employee.jsp";
	        }
	
	        
	        synchronized (this) {
	        	EmployeeBean employee = adminDAO.lookup(form.getUserName());
		       	if (employee != null) {
		       		errors.add("Existing Username");
	                return "create-account-employee.jsp";
		       	}
		       	
		       	employee = new EmployeeBean();
		       	employee.setUserName(form.getUserName());
		       	employee.setPassword(form.getPassword());
		       	employee.setFirstName(form.getFirstName());
		       	employee.setLastName(form.getLastName());
		       	adminDAO.create(employee);
	        }
        
	        request.setAttribute("message","Account successfully created for " + "<b>" + form.getUserName() + "</b>");
	       	
			return "employee-confirmation.jsp";

        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}

}