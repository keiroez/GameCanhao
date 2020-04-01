package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import control.GameController;
import model.PainelTelaJogo;


public class TelaJogo extends JFrame {
	private static int largura = 1216;
	private static int comprimento = 695;
	private JButton ajuda;
	
	public TelaJogo(GameController controle, String modo){
		super(modo);		
		setSize(largura,comprimento);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		this.addKeyListener(controle);
		this.addMouseMotionListener(controle);
		this.addMouseListener(controle);
		this.addMouseWheelListener(controle);
	}


	public static int getLargura() {
		return largura;
	}

	public static void setLargura(int largura) {
		TelaJogo.largura = largura;
	}

	public static int getComprimento() {
		return comprimento;
	}

	public static void setComprimento(int comprimento) {
		TelaJogo.comprimento = comprimento;
	}


	public JButton getAjuda() {
		return ajuda;
	}


	public void setAjuda(JButton ajuda) {
		this.ajuda = ajuda;
	}
}
