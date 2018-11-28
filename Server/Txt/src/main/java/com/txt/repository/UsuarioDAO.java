package com.txt.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.txt.model.Usuario;



public class UsuarioDAO implements DAO<Object>{
	
	private ConexaoMySQL conexao;
	
	public UsuarioDAO() {
		super();
		this.conexao = new ConexaoMySQL("localhost", "root", "", "txt");
	}
	
	@Override
	public Object cadastrar(Object objeto) {
		Usuario usuario = (Usuario) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO Usuario VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getNomeUsuario());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getLogin());
			statement.setString(4, usuario.getSenha());
			statement.setString(5, usuario.getDataNascimento());
			statement.setString(6, usuario.getUrlFoto());
			statement.setString(7, usuario.getBio());
			statement.setString(8, usuario.getGenero());
			statement.setString(9, usuario.getTelefone());
			statement.setString(10, usuario.getUrlSite());
			
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				usuario.setIdUsuario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return usuario;
		
	}
	
	@Override
	public void editar(Object objeto) {
		Usuario usuario = (Usuario) objeto;
		String sqlEditar = "UPDATE Usuario SET nome_usuario=?, email=?, login=?, senha=?, data_nascimento=?, url_foto=?, "
				+ "bio=?, genero=?, telefone=?, url_site=?  WHERE id_usuario=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, usuario.getNomeUsuario());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getLogin());
			statement.setString(4, usuario.getSenha());
			statement.setString(5, usuario.getDataNascimento());
			statement.setString(6, usuario.getUrlFoto());
			statement.setString(7, usuario.getBio());
			statement.setString(8, usuario.getGenero());
			statement.setString(9, usuario.getTelefone());
			statement.setString(10, usuario.getUrlSite());
			
			statement.setLong(11, usuario.getIdUsuario());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	@Override
	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM Comentario WHERE id_usuario=?; DELETE FROM Avaliacao WHERE id_usuario=?; DELETE FROM Curtida WHERE id_usuario=?; DELETE FROM Postagem WHERE id_usuario=?; DELETE FROM Diario WHERE id_usuario=?; DELETE FROM Seguidores WHERE id_usuario_seguidor=?; DELETE FROM Seguidores WHERE id_usuario_seguido=?; DELETE FROM Usuario WHERE id_usuario=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, id); 
			statement.setLong(2, id); 
			statement.setLong(3, id); 
			statement.setLong(4, id); 
			statement.setLong(5, id); 
			statement.setLong(6, id); 
			statement.setLong(7, id); 
			statement.setLong(8, id); 
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
			public List<Usuario> buscarTodos() {
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT * FROM Usuario;";
				PreparedStatement statement;
				Usuario usuario = null;
				List<Usuario> listaUsuarios = new ArrayList<Usuario>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setLogin(rs.getString("login"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setDataNascimento(rs.getString("data_nascimento"));
						usuario.setUrlFoto(rs.getString("url_foto"));
						usuario.setBio(rs.getString("bio"));
						usuario.setGenero(rs.getString("genero"));
						usuario.setTelefone(rs.getString("telefone"));
						usuario.setUrlSite(rs.getString("url_site"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
						listaUsuarios.add(usuario);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaUsuarios;
			}
		
			public Usuario buscarPorId(long id) {
				this.conexao.abrirConexao();
				String sqlInsert = "SELECT * FROM Usuario WHERE id_usuario=?;";
				PreparedStatement statement;
				Usuario usuario = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setLogin(rs.getString("login"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setDataNascimento(rs.getString("data_nascimento"));
						usuario.setUrlFoto(rs.getString("url_foto"));
						usuario.setBio(rs.getString("bio"));
						usuario.setGenero(rs.getString("genero"));
						usuario.setTelefone(rs.getString("telefone"));
						usuario.setUrlSite(rs.getString("url_site"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return usuario;
			}	

			public Usuario buscarPorLoginESenha(String login, String senha) {
				this.conexao.abrirConexao();
				String sqlInsert = "SELECT * FROM Usuario WHERE login=? AND senha=?;";
				PreparedStatement statement;
				Usuario usuario = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setString(1, login);
					statement.setString(2, senha);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setLogin(rs.getString("login"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setDataNascimento(rs.getString("data_nascimento"));
						usuario.setUrlFoto(rs.getString("url_foto"));
						usuario.setBio(rs.getString("bio"));
						usuario.setGenero(rs.getString("genero"));
						usuario.setTelefone(rs.getString("telefone"));
						usuario.setUrlSite(rs.getString("url_site"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return usuario;
			}
			
			public Usuario buscarPorLogin(String login) {
				this.conexao.abrirConexao();
				String sqlInsert = "SELECT * FROM Usuario WHERE login=?;";
				PreparedStatement statement;
				Usuario usuario = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setString(1, login);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						usuario = new Usuario();
						usuario.setIdUsuario(rs.getLong("id_usuario"));
						usuario.setEmail(rs.getString("email"));
						usuario.setLogin(rs.getString("login"));
						usuario.setSenha(rs.getString("senha"));
						usuario.setDataNascimento(rs.getString("data_nascimento"));
						usuario.setUrlFoto(rs.getString("url_foto"));
						usuario.setBio(rs.getString("bio"));
						usuario.setGenero(rs.getString("genero"));
						usuario.setTelefone(rs.getString("telefone"));
						usuario.setUrlSite(rs.getString("url_site"));
						usuario.setNomeUsuario(rs.getString("nome_usuario"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return usuario;
			}

}

