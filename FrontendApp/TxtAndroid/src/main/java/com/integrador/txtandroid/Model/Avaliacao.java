package com.integrador.txtandroid.Model;

public class Avaliacao {
	
	private long idAvaliacao;
	private Usuario usuario;
	private Postagem postagem;
	private double nota;
	
	public Avaliacao() {
		super();
	}
	
	public Avaliacao(long idAvaliacao, Usuario usuario,
			Postagem postagem, double nota) {
		super();
		this.idAvaliacao = idAvaliacao;
		this.usuario = usuario;
		this.postagem = postagem;
		this.nota = nota;
	}

	public long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
	
}


	
	