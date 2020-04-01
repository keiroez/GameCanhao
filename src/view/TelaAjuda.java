package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TelaAjuda extends JFrame {
	private String angulo;
	private String anguloMsg;
	private String forca;
	private String forcaMsg;
	private String forcaMsg2;
	private String mov;
	private String movMsg;
	private String atirar;
	private String atirarMsg;
	private String atirarMsg2;
	private String single;
	private String singleplayer;
	private String multiplayer;
	private String multi;
	private String multi2;
	private String multi3;
	private String ajuda;
	private Image fundo;
	
	
	public TelaAjuda(){
		fundo = new ImageIcon("imagens/cenario12.gif").getImage();
		
		ajuda = "Ajuda";
		
		angulo = "Angulo";
		anguloMsg = "Para mudar angulo do canhão, utilizar o mouse ou as setas pra cima e pra baixo do teclado.";
		
		forca = "Força";
		forcaMsg = "Para aumentar ou diminuir a força do canhão, utilizar os botõs PgUp ou PgDown do teclado";
		forcaMsg2 = " ou o rolo Scrol do mouse.";
		
		mov = "Movimentar";
		movMsg = "Para movimentar o canhão, utilizar as setas esquerda e direita do teclado.";
		
		atirar = "Atirar";
		atirarMsg = "Para atirar bomba normal, apertar espaço do teclado ou botão direito do mouse.";
		atirarMsg2 = "Para Super Bomba apertar Enter.";
		
		single = "Singleplayer"; 
		singleplayer = "Complete a pontuação para passar de fase.";
		
		multiplayer = "Multiplayer";
		multi = "Faça mais pontos que seu adversário destruindo sua base e pontuando na floresta.";
		multi2 = "NÃO ESQUEÇA, AS BOMBAS ACABAM.";
		multi3 = "Colete as superbombas na floresta para pontuar mais no bombardeio";
		
		setSize(800,600);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.drawImage(fundo, 0, 0, 800, 600,null);
		g.setFont(new Font("Dialog", Font.BOLD,40));
		g.drawString(ajuda, 350, 70);
		
		g.setFont(new Font("Dialog", Font.BOLD,25));
		g.drawString(angulo, 50, 140);
		g.setFont(new Font("Dialog", Font.BOLD,17));
		g.drawString(anguloMsg, 50, 160);
		
		g.setFont(new Font("Dialog", Font.BOLD,25));
		g.drawString(forca, 50, 190);
		g.setFont(new Font("Dialog", Font.BOLD,17));
		g.drawString(forcaMsg, 50, 210);
		g.drawString(forcaMsg2, 50, 225);
		
		g.setFont(new Font("Dialog", Font.BOLD,25));
		g.drawString(mov, 50, 255);
		g.setFont(new Font("Dialog", Font.BOLD,17));
		g.drawString(movMsg, 50, 275);
		
		g.setFont(new Font("Dialog", Font.BOLD,25));
		g.drawString(atirar, 50, 305);
		g.setFont(new Font("Dialog", Font.BOLD,17));
		g.drawString(atirarMsg, 50, 325);
		g.drawString(atirarMsg2, 50, 340);
		
		g.setFont(new Font("Dialog", Font.BOLD,25));
		g.drawString(single, 50, 385);
		g.setFont(new Font("Dialog", Font.BOLD,17));
		g.drawString(singleplayer, 50, 405);
		
		g.setFont(new Font("Dialog", Font.BOLD,25));
		g.drawString(multiplayer, 50, 450);
		g.setFont(new Font("Dialog", Font.BOLD,17));
		g.drawString(multi, 50, 470);
		g.drawString(multi2, 50, 485);
		g.drawString(multi3, 50, 500);
		
	}

}
