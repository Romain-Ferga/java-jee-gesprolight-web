package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
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

	private List<GpProject> prjList = null;

	public GpProjectManagedBean() {

		this.setPrjList(this.getPrjService().findAll());

	}

	public String saveProject() throws GesproBusinessException {

		this.getPrjService().create(this.getPrjDataBean());

		this.setPrjList(this.getPrjService().findAll());

		return "success";

	}

	public String updateProject() throws GesproBusinessException {

		this.getPrjService().update(this.getPrjDataBean());

		this.setPrjList(this.getPrjService().findAll());

		return "success";

	}

	public String findProjectById(Integer prjId) throws GesproBusinessException {

		String editPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");

		this.setPrjDataBean(this.getPrjService().findById(Integer.valueOf(editPrjId)));

		return "success";

	}

	public String deleteProjectById(Integer prjId) throws GesproBusinessException {

		String editPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");

		this.getPrjService().deleteById(Integer.valueOf(editPrjId));

		return "success";

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

	public GpProject getPrjDataBean() {
		return prjDataBean;
	}

	public List<GpProject> getPrjList() {
		return prjList;
	}

	public IGpProjectService getPrjService() {
		return prjService;
	}

}
