package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

@ManagedBean(name = "ctrPhaseBean")
@RequestScoped
public class GpPhaseManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GpPhase phsDataBean = new GpPhase();

	private IGpPhaseService phsService = new GpPhaseServiceImpl();

	private IGpProjectService prjService = new GpProjectServiceImpl();

	private List<GpPhase> phsList = null;

	public GpPhaseManagedBean() {

		this.setPhsList(this.getPhsService().findAll());

	}

	public String savePhase() throws GesproBusinessException {

		this.getPhsService().create(this.getPhsDataBean());

		this.setPhsList(this.getPhsService().findAll());

		return "success";

	}

	public String updatePhase() throws GesproBusinessException {

		this.getPhsService().update(this.getPhsDataBean());

		this.setPhsList(this.getPhsService().findAll());

		return "success";

	}

	public String findPhaseById(Integer phsId) throws GesproBusinessException {

		String editPhsId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("phsId");

		this.setPhsDataBean(this.getPhsService().findById(Integer.valueOf(editPhsId)));

		return "success";

	}

	public String deletePhaseById(Integer phsId) throws GesproBusinessException {

		String editPhsId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("phsId");

		this.getPhsService().deleteById(Integer.valueOf(editPhsId));

		return "success";

	}

	public GpPhase getPhsDataBean() {
		return phsDataBean;
	}

	public List<GpPhase> getPhsList() {
		return phsList;
	}

	public IGpPhaseService getPhsService() {
		return phsService;
	}

	public IGpProjectService getPrjService() {
		return prjService;
	}

	public void setPhsDataBean(GpPhase phsDataBean) {
		this.phsDataBean = phsDataBean;
	}

	public void setPhsList(List<GpPhase> phsList) {
		this.phsList = phsList;
	}

	public void setPhsService(IGpPhaseService phsService) {
		this.phsService = phsService;
	}

	public void setPrjService(IGpProjectService prjService) {
		this.prjService = prjService;
	}

}
