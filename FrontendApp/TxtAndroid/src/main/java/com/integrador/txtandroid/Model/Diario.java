package com.integrador.txtandroid.Model;

public class Diario {
	private long idDiario;
	private String textoDiario;
	private String dataDiario;
	private Usuario usuario;
	
	public Diario() {
		super();
	}
	
	public Diario(long idDiario, String textoDiario, String dataDiario,
			Usuario usuario) {
		super();
		this.idDiario = idDiario;
		this.textoDiario = textoDiario;
		this.dataDiario = dataDiario;
		this.usuario = usuario;
	}
	public long getIdDiario() {
		return idDiario;
	}
	public void setIdDiario(long idDiario) {
		this.idDiario = idDiario;
	}
	public String getTextoDiario() {
		return textoDiario;
	}
	public void setTextoDiario(String textoDiario) {
		this.textoDiario = textoDiario;
	}
	public String getDataDiario() {
		return dataDiario;
	}
	public void setDataDiario(String dataDiario) {
		this.dataDiario = dataDiario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

	
	
	