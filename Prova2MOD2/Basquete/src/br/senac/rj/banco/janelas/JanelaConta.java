package br.senac.rj.banco.janelas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import br.senac.rj.banco.modelo.basquete;

public class JanelaConta {
	public static JFrame criarJanelaConta() {
		// Define a janela
		JFrame janelaConta = new JFrame("Lista de Jogadores"); // Janela Normal
		janelaConta.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaConta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaConta.setSize(400, 400); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaConta.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelClube = new JLabel("Clube: ");
		JLabel labelAssistencia = new JLabel("Assistências: ");
		JLabel labelPontos = new JLabel("Pontos: ");
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelClube.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		labelAssistencia.setBounds(50, 160, 100, 20); // coluna, linha, largura, tamanho
		labelPontos.setBounds(50, 200, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextClube = new JTextField();
		JTextField jTextAssistencia = new JTextField();
		JTextField jTextPontos = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextClube.setEnabled(false);
		jTextAssistencia.setEnabled(false);
		jTextPontos.setEnabled(false);
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextClube.setBounds(180, 120, 150, 20);
		jTextAssistencia.setBounds(180, 160, 150, 20);
		jTextPontos.setBounds(180, 200, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaConta.add(labelId);
		janelaConta.add(labelNome);
		janelaConta.add(labelClube);
		janelaConta.add(labelAssistencia);
		janelaConta.add(labelPontos);
		janelaConta.add(jTextId);
		janelaConta.add(jTextNome);
		janelaConta.add(jTextClube);
		janelaConta.add(jTextAssistencia);
		janelaConta.add(jTextPontos);
		// Define bot�es e a localiza��o deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 90, 20);
		janelaConta.add(botaoConsultar);
		
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 250, 80, 20);
		botaoGravar.setEnabled(false);
		janelaConta.add(botaoGravar);
		
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(50, 280, 80, 20);
		botaoDeletar.setEnabled(false);		
		janelaConta.add(botaoDeletar);
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(50, 310, 80, 20);
		janelaConta.add(botaoLimpar);
       
        
		// Define objeto conta para pesquisar no banco de dados
		basquete jogador = new basquete();
	
		// Define a��es dos bot�es
		botaoConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int id = Integer.parseInt(jTextId.getText());		            
		            String nome = "";
		            String clube = "";
		            String assistencia = "";
		            String pontos = "";

		            if (jogador.consultarJogador(id)) {
		            	nome = jogador.getNome();
		            	clube = jogador.getClube();
		            	assistencia = jogador.getAssistencia();
		            	pontos = jogador.getPontos();
		                
		                botaoGravar.setEnabled(true);
		                botaoDeletar.setEnabled(true); // Se a conta for encontrada, habilita o botão Deletar
		            } else {
		            	botaoGravar.setEnabled(true);
		                botaoDeletar.setEnabled(false); // Se a conta não for encontrada, desabilita o botão Deletar
		            }

		            jTextNome.setText(nome);
		            jTextClube.setText(clube);
		            jTextAssistencia.setText(assistencia);
		            jTextPontos.setText(pontos);
		            jTextId.setEnabled(false);
		            jTextNome.setEnabled(true);
		            jTextClube.setEnabled(true);
		            jTextAssistencia.setEnabled(true);
		            jTextPontos.setEnabled(true);
		            botaoConsultar.setEnabled(false);
		         
		        } catch (Exception erro) {
		            JOptionPane.showMessageDialog(janelaConta, "Preencha o campo ID do Jogador corretamente!!");
		        }
		    }
		});
		
		botaoGravar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int resposta = JOptionPane.showConfirmDialog(janelaConta, "Deseja atualizar?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		            int id = Integer.parseInt(jTextId.getText());
		            String nome = jTextNome.getText();
		            String clube = jTextClube.getText();
		            String assistencia = jTextAssistencia.getText();
		            String pontos = jTextPontos.getText();

		            // Validar os campos, se necessário
		            // Exemplo:
		            if (nome.length() == 0 || clube.length() == 0 || assistencia.length() == 0 || pontos.length() == 0) {
		                JOptionPane.showMessageDialog(janelaConta, "Preencha todos os campos!");
		                // Aqui você pode focar no primeiro campo em branco para facilitar a correção
		                jTextNome.requestFocus();
		            } else {
		                if (!jogador.consultarJogador(id)) {
		                    if (!basquete.cadastrarJogador(id, nome, clube, assistencia, pontos))
		                        JOptionPane.showMessageDialog(janelaConta, "Erro na inclusão do Jogador!");
		                    else
		                        JOptionPane.showMessageDialog(janelaConta, "Jogador incluído com sucesso!");
		                    jTextId.setText("");
			                jTextNome.setText("");
			                jTextClube.setText("");
			                jTextAssistencia.setText("");
			                jTextPontos.setText("");
			                botaoConsultar.setEnabled(true);
			                botaoGravar.setEnabled(false);			                
			                jTextId.setEnabled(true);
		                } else {
		                    if (!jogador.atualizarJogador(id, nome, clube, assistencia, pontos))
		                        JOptionPane.showMessageDialog(janelaConta, "Erro na atualização do Jogador!");
		                    else
		                        JOptionPane.showMessageDialog(janelaConta, "Jogador atualizado com sucesso!");
		                    jTextId.setText("");
		                    jTextNome.setText("");
			                jTextClube.setText("");
			                jTextAssistencia.setText("");
			                jTextPontos.setText("");
			                botaoConsultar.setEnabled(true);
			                botaoGravar.setEnabled(false);
			                botaoDeletar.setEnabled(false);
			                jTextId.setEnabled(true);
		                }
		            }
		        }
		    }
		});

		
		 botaoDeletar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int resposta = JOptionPane.showConfirmDialog(janelaConta, "Deseja deletar o Jogador?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		        	int id = Integer.parseInt(jTextId.getText());

		            // Chama o método para deletar a conta
		            if (jogador.deletarJogador(id)) {
		                JOptionPane.showMessageDialog(janelaConta, "Jogador deletado com sucesso!");
		                // Limpa os campos após a exclusão, se necessário
		                jTextId.setText("");
		                jTextNome.setText("");
		                jTextClube.setText("");
		                jTextAssistencia.setText("");
		                jTextPontos.setText("");
		                botaoConsultar.setEnabled(true);
		                botaoGravar.setEnabled(false);
		                botaoDeletar.setEnabled(false);
		                jTextId.setEnabled(true);
		            } else {
		                JOptionPane.showMessageDialog(janelaConta, "Erro ao deletar o Jogador!");
		            }
		        }
		    }
		}); 
		 
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextId.setText(""); // Limpar campo
				jTextNome.setText("");
                jTextClube.setText("");
                jTextAssistencia.setText("");
                jTextPontos.setText("");
				jTextId.setEnabled(true);
				jTextNome.setEnabled(true);
				jTextClube.setEnabled(true);
				jTextAssistencia.setEnabled(true);
				jTextPontos.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				botaoDeletar.setEnabled(false);				
				jTextId.requestFocus(); // Colocar o foco em um campo
			}
		}); 
		return janelaConta;
	}
}
