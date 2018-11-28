package com.integrador.txtandroid.Model;

public class Comentario {
	private long idComentario;
	private String dataComentario;
	private String textoComentario;
	private Usuario usuario;
	private Postagem postagem;
	
	public Comentario() {
		super();
	}

	public Comentario(long idComentario, String dataComentario,
			String textoComentario, Usuario usuario, Postagem postagem) {
		super();
		this.idComentario = idComentario;
		this.dataComentario = dataComentario;
		this.textoComentario = textoComentario;
		this.usuario = usuario;
		this.postagem = postagem;
	}

	public long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(long idComentario) {
		this.idComentario = idComentario;
	}

	public String getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(String dataComentario) {
		this.dataComentario = dataComentario;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
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
	