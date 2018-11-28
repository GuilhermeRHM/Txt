package com.integrador.txtandroid.Model;

public class Curtida {
	private long idCurtida;
	private Usuario usuario;
	private Postagem postagem;
	
	public Curtida(){
		
	}
	
	public Curtida(long idCurtida, Usuario usuario,
			Postagem postagem) {
		super();
		this.idCurtida = idCurtida;
		this.usuario = usuario;
		this.postagem = postagem;
	}

	public long getIdCurtida() {
		return idCurtida;
	}
	public void setIdCurtida(long idCurtida) {
		this.idCurtida = idCurtida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}
}