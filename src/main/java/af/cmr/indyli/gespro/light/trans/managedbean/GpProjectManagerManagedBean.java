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

import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;

@ManagedBean(name = "ctrProjectManagerBean")
@RequestScoped
public class GpProjectManagerManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GpProjectManager pmDataBean = new GpProjectManager();

	private IGpEmployeeService<GpProjectManager> pmService = new GpProjectManagerServiceImpl();

	private List<GpProjectManager> pmList = null;

	public GpProjectManagerManagedBean() {

		this.setPmList(this.getPmService().findAll());

	}

	public String saveProjectManager() throws GesproBusinessException {

		this.getPmService().create(this.getPmDataBean());

		this.setPmList(this.getPmService().findAll());

		return "success";

	}

	public String updateProjectManager() throws GesproBusinessException {

		this.getPmService().update(this.getPmDataBean());

		this.setPmList(this.getPmService().findAll());

		return "success";

	}

	public String findProjectManagerById(Integer pmId) throws GesproBusinessException {

		String editPmId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pmId");

		this.setPmDataBean(this.getPmService().findById(Integer.valueOf(editPmId)));

		return "success";

	}

	public String deleteProjectManagerById() throws GesproBusinessException {

		String delPmId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pmId");

		this.getPmService().deleteById(Integer.valueOf(delPmId));

		this.setPmList(this.getPmService().findAll());

		return "success";

	}

	public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {

		String email = (String) value;

		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

		if (!Pattern.compile(regexPattern).matcher(email).matches()) {

			FacesMessage message = new FacesMessage("Email invalide !");

			throw new ValidatorException(message);

		}

	}

	public GpProjectManager getPmDataBean() {
		return pmDataBean;
	}

	public List<GpProjectManager> getPmList() {
		return pmList;
	}

	public IGpEmployeeService<GpProjectManager> getPmService() {
		return pmService;
	}

	public void setPmDataBean(GpProjectManager pmDataBean) {
		this.pmDataBean = pmDataBean;
	}

	public void setPmList(List<GpProjectManager> pmList) {
		this.pmList = pmList;
	}

	public void setPmService(IGpEmployeeService<GpProjectManager> pmService) {
		this.pmService = pmService;
	}

}
