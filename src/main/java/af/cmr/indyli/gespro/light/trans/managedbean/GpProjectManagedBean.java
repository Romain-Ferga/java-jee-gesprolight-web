package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

@ManagedBean(name = "ctrProjectBean")
@RequestScoped
public class GpProjectManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GpProject prjDataBean = new GpProject();

	private IGpProjectService prjService = new GpProjectServiceImpl();

	private IGpOrganizationService orgService = new GpOrganizationServiceImpl();

	private IGpEmployeeService<GpProjectManager> pmService = new GpProjectManagerServiceImpl();

	private List<GpProject> prjList = null;

	private Integer orgId;

	private Integer pmId;

	private Date startDate;

	public GpProjectManagedBean() {

		this.setPrjList(this.getPrjService().findAll());

	}

	public String deleteProjectById(Integer prjId) throws GesproBusinessException {

		String editPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");

		this.getPrjService().deleteById(Integer.valueOf(editPrjId));

		return "success";

	}

	public String findProjectById(Integer prjId) throws GesproBusinessException {

		String editPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");

		this.setPrjDataBean(this.getPrjService().findById(Integer.valueOf(editPrjId)));

		return "success";

	}

	public Integer getOrgId() {
		return orgId;
	}

	public IGpOrganizationService getOrgService() {
		return orgService;
	}

	public Integer getPmId() {
		return pmId;
	}

	public IGpEmployeeService<GpProjectManager> getPmService() {
		return pmService;
	}

	public GpProject getPrjDataBean() {
		return prjDataBean;
	}

	public List<GpProject> getPrjList() {
		return prjList;
	}

	public IGpProjectService getPrjService() {
		return prjService;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String saveProject() throws GesproBusinessException {

		this.getPrjDataBean().setGpOrganization(this.getOrgService().findById(this.getOrgId()));

		this.getPrjDataBean().setGpChefProjet(this.getPmService().findById(this.getPmId()));

		this.getPrjService().create(this.getPrjDataBean());

		this.setPrjList(this.getPrjService().findAll());

		return "success";

	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public void setOrgService(IGpOrganizationService orgService) {
		this.orgService = orgService;
	}

	public void setPmId(Integer pmId) {
		this.pmId = pmId;
	}

	public void setPmService(IGpEmployeeService<GpProjectManager> pmService) {
		this.pmService = pmService;
	}

	public void setPrjDataBean(GpProject prjDataBean) {
		this.prjDataBean = prjDataBean;
	}

	public void setPrjList(List<GpProject> prjList) {
		this.prjList = prjList;
	}

	public void setPrjService(IGpProjectService prjService) {
		this.prjService = prjService;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String updateProject() throws GesproBusinessException {

		this.getPrjService().update(this.getPrjDataBean());

		this.setPrjList(this.getPrjService().findAll());

		return "success";

	}

	public void validateCode(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {

		String code = (String) value;

		if (code == null)

			throw new ValidatorException(new FacesMessage("Code obligatoire"));

		for (GpProject prj : this.getPrjList()) {

			if (code == prj.getProjectCode())

				throw new ValidatorException(new FacesMessage("Code déjà existant.."));

		}

	}

	public void validateEndDate(FacesContext context, UIComponent toValidate, Object value)
			throws ValidatorException, ParseException {

		String endDateString = (String) value;

		Date endDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(endDateString);

		if (this.getStartDate() == null)

			throw new ValidatorException(new FacesMessage("Veuillez d'abord entrer une date de début.."));

		if (endDate.compareTo(this.getStartDate()) <= 0)

			throw new ValidatorException(new FacesMessage("La date de fin ne peut être antérieure à celle du début.."));

	}

	public void validateOrganization(FacesContext context, UIComponent toValidate, Object value)
			throws ValidatorException {

		String orgId = (String) value;

		if (orgId == null)

			throw new ValidatorException(new FacesMessage("Organisation obligatoire"));

	}

	public void validateProjectManager(FacesContext context, UIComponent toValidate, Object value)
			throws ValidatorException {

		Integer pmId = (Integer) value;

		if (pmId == null)

			throw new ValidatorException(new FacesMessage("Chef de projet obligatoire"));

	}

	public void validateStartDate(FacesContext context, UIComponent toValidate, Object value)
			throws ValidatorException, ParseException {

		String startDateString = (String) value;

		Date startDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(startDateString);

		this.setStartDate(startDate);

		if (startDate == null)

			throw new ValidatorException(new FacesMessage("Date de début obligatoire"));

	}

}
