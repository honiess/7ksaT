package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.EmployeeDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;

import formbean.LoginForm;

public class EmployeeLoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);

	private EmployeeDAO employeeDAO;

	public EmployeeLoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String getName() {
		return "employee-login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// If employee is already logged in, redirect to employee-mainpanel.jsp
		if (session.getAttribute("employeeUserName") != null) {
			return "employee-mainpanel.jsp";
		}

		// If customer is already logged in, redirect to customer-mainpanel.jsp
		if (session.getAttribute("customerId") != null) {
			return "customer-mainpanel.jsp";
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			LoginForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "index.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "index.jsp";
			}

			EmployeeBean employee = employeeDAO.read(form.getUserName());
			
			if (employee == null) {
	            errors.add("Incorrect/Invalid Employee Username");
	            return "index.jsp";
	        }
	        
	        if (!employee.checkPassword(form.getPassword())) {
	            errors.add("Incorrect/Invalid Password");
	            return "index.jsp";
	        }

			// Attach (this copy of) the user bean to the session
			session.setAttribute("employeeUserName", employee.getUserName());
			session.setAttribute("firstname", employee.getFirstName());
			session.setAttribute("lastname", employee.getLastName());

			// If redirectTo is null, redirect to the "todolist" action
			return "employee-mainpanel.jsp";

		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
