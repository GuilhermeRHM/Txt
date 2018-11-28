package com.txt.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.txt.model.Curtida;
import com.txt.model.Postagem;
import com.txt.model.Seguidor;
import com.txt.model.Usuario;



public class PostagemDAO implements DAO<Object> {
	
	private ConexaoMySQL conexao;
	
	public PostagemDAO() {
		super();
		this.conexao = new ConexaoMySQL("localhost", "root", "", "txt");
	}

	@Override
	public Object cadastrar(Object objeto) {
		Postagem postagem = (Postagem) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO Postagem VALUES(null, ?, ?, ?, ?, curdate());";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, postagem.getNomePostagem());
			statement.setString(2, postagem.getCategoria());
			statement.setString(3, postagem.getTextoPostagem());
			statement.setLong(4, postagem.getUsuario().getIdUsuario());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				postagem.setIdPostagem(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return postagem;
		
	}
	
	@Override
	public void editar(Object objeto) {
		Postagem postagem = (Postagem) objeto;
		String sqlEditar = "UPDATE Postagem SET nome_postagem=?, categoria=?, texto_postagem=?, data_postagem=curdate() WHERE id_postagem=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, postagem.getNomePostagem());
			statement.setString(2, postagem.getCategoria());
			statement.setString(3, postagem.getTextoPostagem());
			statement.setLong(4, postagem.getIdPostagem());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	@Override
	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM Comentario WHERE id_postagem =?; DELETE FROM Avaliacao WHERE id_postagem=?; " + 
				"DELETE FROM Curtida WHERE id_postagem=?; DELETE FROM Postagem WHERE id_postagem=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, id);
			statement.setLong(2, id);
			statement.setLong(3, id);
			statement.setLong(4, id);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
			public List<Postagem> buscarTodos() {
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT * FROM Postagem p "
						+ "INNER JOIN Usuario u ON p.id_usuario = u.id_usuario ORDER BY data_postagem, id_postagem DESC;";
				PreparedStatement statement;
				Postagem postagem = null;
				List<Postagem> listaPostagens = new ArrayList<Postagem>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						postagem = new Postagem();
						postagem.setIdPostagem(rs.getLong("id_postagem"));
						postagem.setNomePostagem(rs.getString("nome_postagem"));
						postagem.setCategoria(rs.getString("categoria"));
						postagem.setDataPostagem(rs.getString("data_postagem"));
						postagem.setTextoPostagem(rs.getString("texto_postagem"));
						
						Usuario usuario = new Usuario();
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
						postagem.setUsuario(usuario);
						
						listaPostagens.add(postagem);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaPostagens;
			}
		
			
			public List<Postagem> buscarPorIdUsuario(long idUsuario) {
				this.conexao.abrirConexao();
				String sqlInsert = "SELECT * FROM Postagem p" + 
				" INNER JOIN Usuario u ON p.id_usuario = u.id_usuario WHERE p.id_usuario=? ORDER BY data_postagem, id_postagem DESC;";
				PreparedStatement statement;
				Postagem postagem = null;
				List<Postagem> listaPostagens = new ArrayList<Postagem>();

				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, idUsuario);
					ResultSet rs = statement.executeQuery();
					while(rs.next()) {
						postagem = new Postagem();
						postagem.setIdPostagem(rs.getLong("id_postagem"));
						postagem.setNomePostagem(rs.getString("nome_postagem"));
						postagem.setCategoria(rs.getString("categoria"));
						postagem.setDataPostagem(rs.getString("data_postagem"));
						postagem.setTextoPostagem(rs.getString("texto_postagem"));

						
						
						Usuario usuario = new Usuario();
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
						postagem.setUsuario(usuario);
						
						listaPostagens.add(postagem);

						
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaPostagens;
			}
			
			public List<Postagem> buscarPorSeguidos(long idUsuario) {
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT * FROM Postagem p " + 
						"INNER JOIN Usuario u ON p.id_usuario = u.id_usuario " + 
						"INNER JOIN Seguidores s ON p.id_usuario = s.id_usuario_seguido "
						+ "WHERE s.id_usuario_seguidor=? AND s.status_seguidores=0;";
				PreparedStatement statement;
				Postagem postagem = null;
				List<Postagem> listaPostagens = new ArrayList<Postagem>();

				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idUsuario);
					ResultSet rs = statement.executeQuery();
					while(rs.next()) {
						postagem = new Postagem();
						postagem.setIdPostagem(rs.getLong("id_postagem"));
						postagem.setNomePostagem(rs.getString("nome_postagem"));
						postagem.setCategoria(rs.getString("categoria"));
						postagem.setDataPostagem(rs.getString("data_postagem"));
						postagem.setTextoPostagem(rs.getString("texto_postagem"));

						
						Usuario usuario = new Usuario();
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
						postagem.setUsuario(usuario);
						
						Seguidor seguidor = new Seguidor();
						seguidor.setId(rs.getLong("id"));
						seguidor.setStatus(rs.getBoolean("status_seguidores"));
					
						
						
						listaPostagens.add(postagem);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaPostagens; 
					
				
						
			}

			public List<Postagem> buscarPorCategoria(String categoria) {
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT * FROM Postagem p INNER JOIN Usuario u ON p.id_usuario = u.id_usuario WHERE categoria=? ORDER BY p.data_postagem DESC;";
				PreparedStatement statement;
				Postagem postagem = null;
				List<Postagem> listaPostagens = new ArrayList<Postagem>();

				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setString(1, categoria);
					ResultSet rs = statement.executeQuery();
					while(rs.next()) {
						postagem = new Postagem();
						postagem.setIdPostagem(rs.getLong("id_postagem"));
						postagem.setNomePostagem(rs.getString("nome_postagem"));
						postagem.setCategoria(rs.getString("categoria"));
						postagem.setDataPostagem(rs.getString("data_postagem"));
						postagem.setTextoPostagem(rs.getString("texto_postagem"));

						
						Usuario usuario = new Usuario();
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
						postagem.setUsuario(usuario);
						
						listaPostagens.add(postagem);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaPostagens; 
				
			}

			public List<Postagem> buscarFavoritosPorIdUsuario(long idUsuario){
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT * FROM Postagem p"
						+ " INNER JOIN Usuario u ON p.id_usuario = u.id_usuario "
						+ "INNER JOIN Curtida c ON p.id_postagem = c.id_postagem WHERE c.id_usuario=? ORDER BY data_postagem DESC;";
				PreparedStatement statement;
				Postagem postagem = null;
				List<Postagem> listaPostagens = new ArrayList<Postagem>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idUsuario);
					ResultSet rs = statement.executeQuery();
					while(rs.next()) {
						postagem = new Postagem();
						postagem.setIdPostagem(rs.getLong("id_postagem"));
						postagem.setNomePostagem(rs.getString("nome_postagem"));
						postagem.setCategoria(rs.getString("categoria"));
						postagem.setDataPostagem(rs.getString("data_postagem"));
						postagem.setTextoPostagem(rs.getString("texto_postagem"));

						
						Usuario usuario = new Usuario();
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
						postagem.setUsuario(usuario);
						
						Curtida curtida = new Curtida();
						curtida.setIdCurtida(rs.getLong("id_curtida"));
						
						
						listaPostagens.add(postagem);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaPostagens; 
			}
			
			public Postagem buscarPorId(long id) {
				this.conexao.abrirConexao();
				String sqlInsert = "SELECT * FROM Postagem p INNER JOIN Usuario u ON p.id_usuario = u.id_usuario WHERE id_postagem=?;";
				PreparedStatement statement;
				Postagem postagem = null;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						postagem = new Postagem();
						postagem.setIdPostagem(rs.getLong("id_postagem"));
						postagem.setNomePostagem(rs.getString("nome_postagem"));
						postagem.setTextoPostagem(rs.getString("texto_postagem"));
						postagem.setCategoria(rs.getString("categoria"));
						postagem.setDataPostagem(rs.getString("data_postagem"));
						
						Usuario usuario = new Usuario();
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
						postagem.setUsuario(usuario);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return postagem;
			}
			

			
}

