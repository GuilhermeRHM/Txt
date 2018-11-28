package com.integrador.txtandroid.Model;

public class Usuario{
	private long idUsuario;
	 private String nomeUsuario;
    private String email;
    private String login;
    private String senha;
    private String dataNascimento;
    private String urlFoto;
    private String bio;
    private String genero;
    private String telefone;
    private String urlSite;
   
	
    
    public Usuario() {
		super();
	}



	public Usuario(long idUsuario, String nomeUsuario, String email, String login, String senha, String dataNascimento,
			String urlFoto, String bio, String genero, String telefone, String urlSite) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.urlFoto = urlFoto;
		this.bio = bio;
		this.genero = genero;
		this.telefone = telefone;
		this.urlSite = urlSite;
	}



	public long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNomeUsuario() {
		return nomeUsuario;
	}



	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public String getUrlFoto() {
		return urlFoto;
	}



	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}



	public String getBio() {
		return bio;
	}



	public void setBio(String bio) {
		this.bio = bio;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getUrlSite() {
		return urlSite;
	}



	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}
    
    
    

    

	
}
