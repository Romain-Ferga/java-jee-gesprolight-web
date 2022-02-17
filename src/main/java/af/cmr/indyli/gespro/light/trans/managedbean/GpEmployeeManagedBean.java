package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpEmployeeServiceImpl;

@ManagedBean(name = "ctrEmployeeBean")
@RequestScoped
public class GpEmployeeManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GpEmployee empDataBean = new GpEmployee();

	private IGpEmployeeService<GpEmployee> empService = new GpEmployeeServiceImpl();

	private List<GpEmployee> empList = null;

	public GpEmployeeManagedBean() {
		this.empList = this.empService.findAll();
	}

	public String saveEmployee() throws GesproBusinessException {
		this.empService.create(this.empDataBean);
		this.empList = this.empService.findAll();
		return "success";
	}

	public String updateEmpById() {
		String editEmpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empId");
		this.empDataBean = this.empService.findById(Integer.valueOf(editEmpId));
		return "success";
	}

	public String updateEmployee() throws GesproBusinessException {
		this.empService.update(this.empDataBean);
		this.empList = this.empService.findAll();
		return "success";
	}

	public String deleteEmpById() {
		String delEmpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empId");
		this.empService.deleteById(Integer.valueOf(delEmpId));
		this.empList = this.empService.findAll();
		return "success";
	}

	public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
		String email = (String) value;
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
		if (!Pattern.compile(regexPattern).matcher(email).matches()) {
			FacesMessage message = new FacesMessage("Adresse Email invalide !");
			throw new ValidatorException(message);
		}
	}

	public void validateFileNumber(FacesContext context, UIComponent toValidate, Object value)
			throws ValidatorException {

		String fileNumber = (String) value;

		if (fileNumber == null) {

			FacesMessage message = new FacesMessage("Matricule obligatoire");

			throw new ValidatorException(message);

		}

	}

	public void validateLogin(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {

		String login = (String) value;

		if (login == null) {

			FacesMessage message = new FacesMessage("Login obligatoire");

			throw new ValidatorException(message);

		}

	}

	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {

		String nom = (String) value;

		if (nom == null) {

			FacesMessage message = new FacesMessage("Nom obligatoire");

			throw new ValidatorException(message);

		}

	}

	public GpEmployee getEmpDataBean() {
		return empDataBean;
	}

	public List<GpEmployee> getEmpList() {
		return empList;
	}

	public void setEmpDataBean(GpEmployee empDataBean) {
		this.empDataBean = empDataBean;
	}

	public void setEmpList(List<GpEmployee> empList) {
		this.empList = empList;
	}

}
