package com.integrador.txtandroid.Model;

public class Postagem {
	private long idPostagem;
	private String nomePostagem;
	private String categoria;
	private String textoPostagem;
	private String dataPostagem;
	private Usuario usuario;
	
	public Postagem() {
		super();
	}

	public Postagem(long idPostagem, String categoria, String textoPostagem, String dataPostagem, String nomePostagem,
			Usuario usuario) {
		super();
		this.idPostagem = idPostagem;
		this.categoria = categoria;
		this.textoPostagem = textoPostagem;
		this.dataPostagem = dataPostagem;
		this.nomePostagem = nomePostagem;
		this.usuario = usuario;
	}

	public long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTextoPostagem() {
		return textoPostagem;
	}

	public void setTextoPostagem(String textoPostagem) {
		this.textoPostagem = textoPostagem;
	}

	public String getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(String dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public String getNomePostagem() {
		return nomePostagem;
	}

	public void setNomePostagem(String nomePostagem) {
		this.nomePostagem = nomePostagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	

}
	