package br.senac.rj.banco.teste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.banco.janelas.JanelaConta;

public class TesteSwing {

	public static void apresentarMenu() {
		// Define a janela
		JFrame janelaPrincipal = new JFrame("Jogadores de Basquete"); // Janela Normal
		janelaPrincipal.setTitle("Lista de Jogadores");
		janelaPrincipal.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 400); // Define tamanho da janela
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "Não");
		// Cria uma barra de menu para a janela principal
		JMenuBar menuBar = new JMenuBar();
		// Adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);
		// Define e adiciona menu na barra de menu
		JMenu menuAtualizar = new JMenu("Lista");
		menuBar.add(menuAtualizar);
		// Cria e adiciona um item simples para o menu
		JMenuItem menuConta = new JMenuItem("Jogadores");
		menuAtualizar.add(menuConta);
		// Criar a janela de atualiza��o da conta
		JFrame janelaConta = JanelaConta.criarJanelaConta();
		// Adiciona a��o para o item do menu
		menuConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaConta.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
	}

	public static void main(String[] args) {
		apresentarMenu();
	}
}
