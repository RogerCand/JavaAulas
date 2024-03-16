package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class basquete {
	private int id;
	private String nome;
	private String clube;
	private String assistencia;
	private String pontos;
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClube() {
		return clube;
	}

	public void setClube(String clube) {
		this.clube = clube;
	}

	public String getAssistencia() {
		return assistencia;
	}

	public void setAssistencia(String assistencia) {
		this.assistencia = assistencia;
	}

	public String getPontos() {
		return pontos;
	}

	public void setPontos(String pontos) {
		this.pontos = pontos;
	}


	public static boolean cadastrarJogador(int id, String nome, String clube, String assistencia, String pontos) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into jogadores set id=?, nome=?, clube=?, assistencias=?, pontos=?;";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, id); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			ps.setString(2, nome); // Substitui o segundo par�metro da consulta pela conta informada
			ps.setString(3, clube); // Substitui o terceiro par�metro da consulta pelo titular informado
			ps.setString(4, assistencia); // Substitui o terceiro par�metro da consulta pelo titular informado
			ps.setString(5, pontos); // Substitui o terceiro par�metro da consulta pelo titular informado
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar o Jogador: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarJogador(int id) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from jogadores where id=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, id); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
				System.out.println("Jogador não cadastrado!");
				return false; // Conta n�o cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.nome = rs.getString("nome");
					this.clube = rs.getString("clube");
					this.assistencia = rs.getString("assistencias");
					this.pontos = rs.getString("pontos");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o Jogador: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarJogador(int id, String nome, String clube, String assistencia, String pontos) {
		if (!consultarJogador(id))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update jogadores set nome=?, clube=?, assistencias=?, pontos=? where id=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				ps.setString(1, nome);
				ps.setString(2, clube);
				ps.setString(3, assistencia);
				ps.setString(4, pontos);
				ps.setInt(5, id);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o Paciente: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	
	public boolean deletarJogador(int id) {
	    Connection conexao = null;
	    try {
	        conexao = Conexao.conectaBanco();
	        String sql = "DELETE FROM jogadores WHERE id=?";
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, id);
	        
	        int linhasAfetadas = ps.executeUpdate();
	        if (linhasAfetadas == 0) {
	            System.out.println("Jogador não encontrado para exclusão.");
	            return false; // 
	        } else {
	            System.out.println("Jogador excluído com sucesso!");
	            return true; // 
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao excluir o Jogador: " + erro.toString());
	        return false;
	    } finally {
	        Conexao.fechaConexao(conexao);
	    }
	
	}
	
}
