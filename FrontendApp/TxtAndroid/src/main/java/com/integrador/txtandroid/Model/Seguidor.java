package com.integrador.txtandroid.Model;

public class Seguidor {
	
	private Usuario seguidor;
	private Usuario seguido;
	private long id;
	private boolean status;

	public Seguidor() {
		super();
	}
	
	public Seguidor(Usuario seguidor, Usuario seguido, long id, boolean status) {
		super();
		this.seguidor = seguidor;
		this.seguido = seguido;
		this.id = id;
		this.status = status;
	}
	
	public Usuario getSeguidor() {
		return seguidor;
	}
	
	public void setSeguidor(Usuario seguidor) {
		this.seguidor = seguidor;
	}
	
	public Usuario getSeguido() {
		return seguido;
	}
	
	public void setSeguido(Usuario seguido) {
		this.seguido = seguido;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
