package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaCreditos extends JFrame {
	private String Desen;
	private String nome;
	private String curso;
	private String universidade;
	private String cadeira;
	private Image imgUfrpe;
	private Image fundo;
	
	public TelaCreditos() {
		Desen = "Desenvolvido por:";
		nome = "Izaquiel de Queiroz Ferreira";
		curso = "Bacharelado em Sistemas de Informação - 2016.1";
		cadeira = "Modelagem e Programação Orientada a Objetos";
		universidade = "Universidade Federal Rural de Pernambuco - UAST";
		imgUfrpe = new ImageIcon("imagens/brasao.png").getImage();
		fundo = new ImageIcon("imagens/cenario12.gif").getImage();
		
		setSize(800,600);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		
		g.drawImage(fundo, 0, 0, 800, 600,null);
		g.setFont(new Font("Dialog", Font.BOLD,40));
		g.drawString("Créditos", 320, 70);
		
		g.setFont(new Font("Dialog", Font.BOLD, 24));
		g.drawString(nome, 50, 100);
		g.drawString(curso, 80, 150);
		g.drawString(cadeira, 110, 200);
		g.drawString(universidade, 140, 250);
		g.drawImage(imgUfrpe, 300, 260, 204, 325,null);
		
	}

}
