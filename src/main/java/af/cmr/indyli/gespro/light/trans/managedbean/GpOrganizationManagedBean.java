package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;

@ManagedBean(name = "ctrOrganizationBean")
@RequestScoped
public class GpOrganizationManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GpOrganization orgDataBean = new GpOrganization();

	private IGpOrganizationService orgService = new GpOrganizationServiceImpl();

	private List<GpOrganization> orgList = null;

	public GpOrganizationManagedBean() {

		this.setOrgList(this.orgService.findAll());

	}

	public String saveOrganization() throws GesproBusinessException {

		this.getOrgService().create(this.getOrgDataBean());

		this.setOrgList(this.getOrgService().findAll());

		return "success";

	}

	public String findOrganizationById() {

		String editOrgId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("orgId");

		this.setOrgDataBean(this.getOrgService().findById(Integer.valueOf(editOrgId)));

		return "success";

	}

	public String updateOrganization() throws GesproBusinessException {

		this.getOrgService().update(this.getOrgDataBean());

		this.setOrgList(this.getOrgService().findAll());

		return "success";

	}

	public String deleteOrganizationById() {

		String delOrgId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("orgId");

		this.getOrgService().deleteById(Integer.valueOf(delOrgId));

		this.setOrgList(this.orgService.findAll());

		return "success";

	}

	public void validatePhoneNumber(FacesContext context, UIComponent toValidate, Object value)
			throws ValidatorException {

		String phoneNumber = (String) value;

		if (phoneNumber.indexOf("06") != 0) {

			FacesMessage message = new FacesMessage("Numéro de téléphone invalide !");

			throw new ValidatorException(message);

		}

	}

	public GpOrganization getOrgDataBean() {
		return orgDataBean;
	}

	public void setOrgDataBean(GpOrganization orgDataBean) {
		this.orgDataBean = orgDataBean;
	}

	public IGpOrganizationService getOrgService() {
		return orgService;
	}

	public void setOrgService(IGpOrganizationService orgService) {
		this.orgService = orgService;
	}

	public List<GpOrganization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<GpOrganization> orgList) {
		this.orgList = orgList;
	}

}
