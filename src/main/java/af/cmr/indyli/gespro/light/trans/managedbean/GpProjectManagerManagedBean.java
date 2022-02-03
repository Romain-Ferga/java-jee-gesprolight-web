package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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

		this.setPmList(this.pmService.findAll());

	}

	public String saveProjectManager() throws GesproBusinessException {

		this.pmService.create(this.pmDataBean);

		this.setPmList(this.pmService.findAll());

		return "success";

	}

	public String updateProjectManager() throws GesproBusinessException {

		this.pmService.update(pmDataBean);

		this.setPmList(this.pmService.findAll());

		return "success";

	}

	public String findProjectManagerById(Integer pmId) throws GesproBusinessException {

		String editPmId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pmId");

		this.pmDataBean = this.pmService.findById(Integer.valueOf(editPmId));

		return "success";

	}

	public String deleteProjectManagerById() throws GesproBusinessException {

		String delPmId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pmId");

		this.pmService.deleteById(Integer.valueOf(delPmId));

		this.setPmList(this.pmService.findAll());

		return "success";

	}

//	public void validateEmail() {
//
//	}

	public List<GpProjectManager> getPmList() {
		return pmList;
	}

	public void setPmList(List<GpProjectManager> pmList) {
		this.pmList = pmList;
	}

	public GpProjectManager getPmDataBean() {
		return pmDataBean;
	}

	public void setPmDataBean(GpProjectManager pmDataBean) {
		this.pmDataBean = pmDataBean;
	}

}
