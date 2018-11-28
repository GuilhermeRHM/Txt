package com.txt.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.txt.model.Comentario;
import com.txt.model.Postagem;
import com.txt.model.Usuario;



public class ComentarioDAO implements DAO<Object> {
	
	private ConexaoMySQL conexao;
	
	public ComentarioDAO() {
		super();
		this.conexao = new ConexaoMySQL("localhost", "root", "", "txt");
	}

	@Override
	public Object cadastrar(Object objeto) {
		Comentario comentario = (Comentario) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO Comentario VALUES(null, curdate(), ?, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, comentario.getTextoComentario());
			statement.setLong(2, comentario.getUsuario().getIdUsuario());
			statement.setLong(3, comentario.getPostagem().getIdPostagem());
		
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				comentario.setIdComentario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return comentario;
		
	}
	
	@Override
	public void editar(Object objeto) {
		Comentario comentario = (Comentario) objeto;
		String sqlEditar = "UPDATE Comentario SET data_comentario=curdate(), texto_comentario=? "
				+ "WHERE id_comentario=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, comentario.getTextoComentario());
			statement.setLong(2, comentario.getIdComentario());
		
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	@Override
	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM Comentario WHERE id_comentario=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
			public List<Comentario> buscarTodosPorIdPostagem(long idPostagem) { //busca todos por id da postagem
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT * FROM Comentario c "
						+ "INNER JOIN Usuario u ON c.id_usuario = u.id_usuario "
						+ "INNER JOIN Postagem p ON c.id_postagem = p.id_postagem WHERE c.id_postagem=? ORDER BY c.data_comentario, c.id_comentario DESC;";
				PreparedStatement statement;
				Comentario comentario = null;
				List<Comentario> listaComentarios = new ArrayList<Comentario>();
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idPostagem);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						comentario = new Comentario();
						comentario.setIdComentario(rs.getLong("id_comentario"));
						comentario.setDataComentario(rs.getString("data_comentario"));
						comentario.setTextoComentario(rs.getString("texto_comentario"));
						
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
						comentario.setUsuario(usuario);
						
						Postagem postagem = new Postagem();
						postagem.setIdPostagem(rs.getLong("id_postagem"));
						postagem.setNomePostagem(rs.getString("nome_postagem"));
						postagem.setCategoria(rs.getString("categoria"));
						postagem.setDataPostagem(rs.getString("data_postagem"));
						postagem.setTextoPostagem(rs.getString("texto_postagem"));

						comentario.setPostagem(postagem);
						listaComentarios.add(comentario);
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaComentarios;
			}
			
			public int buscarQntdComentariosPorIdPostagem(long idPostagem) {
				this.conexao.abrirConexao();
				String sqlSelect = "SELECT COUNT(id_comentario) FROM Comentario WHERE id_postagem=?;";
				PreparedStatement statement;
				int countComentarios = 0;
				try {
					statement = this.conexao.getConexao().prepareStatement(sqlSelect);
					statement.setLong(1, idPostagem);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						countComentarios = rs.getInt("COUNT(id_comentario)");
					} 
				} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						this.conexao.fecharConexao();
					}
					return countComentarios;
			}
		
			
			
			
}

