package view;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.GameController;

public class TelaInicialMenu extends JFrame{
	private JButton singleButton;
	private JButton multiButton;
	private JButton creditosButton;
	private JButton ajudaButton;
	private JButton sairButton;
	private ImageIcon imagem;
	private JLabel fundo;
	private JButton aumentarTmp, diminuirTmp;
	private JLabel velocTmp;
	private JTextField configVeloc;
	private JButton rkgSingle, rkgMult;


	public TelaInicialMenu(){
		super("Menu");
		
		
		this.singleButton = new JButton("SinglePlayer");
		this.multiButton = new JButton("Multiplayer");
		this.imagem = new ImageIcon("imagens/cenario12.gif");
		this.fundo = new JLabel(imagem);
		
		singleButton.setFont(new Font("Dialog", Font.BOLD, 18));
		multiButton.setFont(new Font("Dialog", Font.BOLD, 18));
		
		
		this.creditosButton = new JButton("Créditos");
		this.ajudaButton = new JButton("Ajuda");
		this.sairButton = new JButton("Sair");
		this.rkgMult = new JButton("Ranking Multi");
		this.rkgSingle = new JButton("Ranking Single");
		
		creditosButton.setFont(new Font("Dialog", Font.BOLD, 18));
		ajudaButton.setFont(new Font("Dialog", Font.BOLD, 18));
		sairButton.setFont(new Font("Dialog", Font.BOLD, 18));
		
		rkgMult.setFont(new Font("Dialog", Font.BOLD, 18));
		rkgSingle.setFont(new Font("Dialog", Font.BOLD, 18));
		
		this.aumentarTmp = new JButton("+");
		this.diminuirTmp = new JButton("-");
		this.configVeloc = new JTextField();
		this.velocTmp = new JLabel("Configuração de velocidade");
		
		aumentarTmp.setFont(new Font("Dialog", Font.BOLD, 18));
		diminuirTmp.setFont(new Font("Dialog", Font.BOLD, 18));
		configVeloc.setFont(new Font("Dialog", Font.BOLD, 18));
		
		singleButton.setBounds(200, 30, 400, 100);
		add(singleButton);
		multiButton.setBounds(200, 130, 400, 100);
		add(multiButton);
		ajudaButton.setBounds(200, 230, 400, 100);
		add(ajudaButton);
		creditosButton.setBounds(200, 330, 400, 100);
		add(creditosButton);
		sairButton.setBounds(200, 430, 400, 100);
		add(sairButton);
		
		this.velocTmp.setBounds(10, 10, 200, 20);
		add(velocTmp);
		this.diminuirTmp.setBounds(20, 30, 50, 50);
		add(diminuirTmp);
		this.aumentarTmp.setBounds(95, 30, 50, 50);
		add(aumentarTmp);
		this.configVeloc.setBounds(70, 40, 25, 25);
		add(configVeloc);
		
		rkgSingle.setBounds(0, 130, 200, 400);
		add(rkgSingle);
		rkgMult.setBounds(600, 130, 200, 400);
		add(rkgMult);
		
		
		configVeloc.setEditable(false);
		configVeloc.setText(" 1");
		
		this.fundo.setBounds(0, 0, 800, 600);
		add(fundo);
		
		setSize(800,600);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


	public JButton getSingleButton() {
		return singleButton;
	}


	public void setSingleButton(JButton singleButton) {
		singleButton = singleButton;
	}


	public JButton getMultiButton() {
		return multiButton;
	}


	public void setMultiButton(JButton multiButton) {
		multiButton = multiButton;
	}


	public JButton getSairButton() {
		return sairButton;
	}


	public void setSairButton(JButton sairButton) {
		this.sairButton = sairButton;
	}


	public ImageIcon getImagem() {
		return imagem;
	}


	public void setImagem(ImageIcon imagem) {
		this.imagem = imagem;
	}


	public JLabel getFundo() {
		return fundo;
	}


	public void setFundo(JLabel fundo) {
		this.fundo = fundo;
	}


	public JButton getCreditosButton() {
		return creditosButton;
	}


	public void setCreditosButton(JButton creditosButton) {
		this.creditosButton = creditosButton;
	}


	public JButton getAjudaButton() {
		return ajudaButton;
	}


	public void setAjudaButton(JButton ajudaButton) {
		this.ajudaButton = ajudaButton;
	}


	public JButton getAumentarTmp() {
		return aumentarTmp;
	}


	public void setAumentarTmp(JButton aumentarTmp) {
		this.aumentarTmp = aumentarTmp;
	}


	public JButton getDiminuirTmp() {
		return diminuirTmp;
	}


	public void setDiminuirTmp(JButton diminuirTmp) {
		this.diminuirTmp = diminuirTmp;
	}


	public JLabel getVelocTmp() {
		return velocTmp;
	}


	public void setVelocTmp(JLabel velocTmp) {
		this.velocTmp = velocTmp;
	}


	public JTextField getConfigVeloc() {
		return configVeloc;
	}


	public void setConfigVeloc(JTextField configVeloc) {
		this.configVeloc = configVeloc;
	}


	public JButton getRkgSingle() {
		return rkgSingle;
	}


	public void setRkgSingle(JButton rkgSingle) {
		this.rkgSingle = rkgSingle;
	}


	public JButton getRkgMult() {
		return rkgMult;
	}


	public void setRkgMult(JButton rkgMult) {
		this.rkgMult = rkgMult;
	}

	
}
