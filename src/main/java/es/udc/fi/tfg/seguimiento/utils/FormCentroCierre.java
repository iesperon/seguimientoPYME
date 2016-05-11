package es.udc.fi.tfg.seguimiento.utils;

import es.udc.fi.tfg.seguimiento.model.CierreCaja;

public class FormCentroCierre {

	private Long idCentro;
	private CierreCaja cierre;

	public FormCentroCierre(){
		
	}

	public FormCentroCierre(Long idCentro, CierreCaja cierre) {
		super();
		this.idCentro = idCentro;
		this.cierre = cierre;
	}

	public Long getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(Long idCentro) {
		this.idCentro = idCentro;
	}

	public CierreCaja getCierre() {
		return cierre;
	}

	public void setCierre(CierreCaja cierre) {
		this.cierre = cierre;
	}


}
