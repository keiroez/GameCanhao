package control;

import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;

import model.Jogador;
import model.Multiplayer;
import model.PainelMultiJogo;
import model.PainelTelaJogo;
import model.SinglePlayer;
import view.TelaAjuda;
import view.TelaCreditos;
import view.TelaInicialMenu;
import view.TelaJogo;
import view.TelaRanking;

public class GameController implements KeyListener, ActionListener,MouseMotionListener,MouseListener, MouseWheelListener {
	private TelaJogo tela;
	private TelaInicialMenu menu;
	private Jogador jogador;
	private Jogador jogador2;
	private SinglePlayer level;
	private Multiplayer multi;
	private int potencia;
	private PainelTelaJogo fase;
	private PainelMultiJogo faseMulti;
	private double tmpVoo = 0.1;
	
	private String modoJogo;
	
	public GameController() {
		menu = new TelaInicialMenu();
		potencia = 20;
		menu.getSingleButton().addActionListener(this);
		menu.getMultiButton().addActionListener(this);
		menu.getAjudaButton().addActionListener(this);
		menu.getCreditosButton().addActionListener(this);
		menu.getSairButton().addActionListener(this);
		
		menu.getDiminuirTmp().addActionListener(this);
		menu.getAumentarTmp().addActionListener(this);
		
		menu.getRkgSingle().addActionListener(this);
		menu.getRkgMult().addActionListener(this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				if(this.modoJogo.equals("mult")){
					if(this.multi.getJogadorAtual()==1){
						this.faseMulti.setPosicaoTelaX(0);
						this.jogador.atirar();
					}
					if(this.multi.getJogadorAtual()==2){
						this.faseMulti.setPosicaoTelaX(-1344);
						this.jogador2.atirar();
					}
				}
				if(this.modoJogo.equals("single"))
					this.jogador.atirar();
			break;
			
			case KeyEvent.VK_ENTER:
				if(this.modoJogo.equals("mult")){
					if(this.multi.getJogadorAtual()==1){
						this.faseMulti.setPosicaoTelaX(0);
						this.jogador.atirar();
						this.jogador.getPj().superBomba();
					}
					if(this.multi.getJogadorAtual()==2){
						this.faseMulti.setPosicaoTelaX(-1344);
						this.jogador2.atirar();
						this.jogador2.getPj().superBomba();
					}
				}
				if(this.modoJogo.equals("single"))
					this.jogador.atirar();
			break;
			
			case KeyEvent.VK_SHIFT:
				this.level.mudarFase(this.level.getLevel()+1);
			break;
			
			case KeyEvent.VK_D:
					if(!this.modoJogo.equals("")){
						if(this.modoJogo.equals("single"))
							this.fase.movimentarCameraDir(this.jogador);
						if(this.modoJogo.equals("mult")){
							if(this.multi.getJogadorAtual()==1)
								this.faseMulti.movimentarCameraDir(this.jogador,1344);
							if(this.multi.getJogadorAtual()==2)
								this.faseMulti.movimentarCameraDir(this.jogador2,1344);
						}
					}
				
			break;
			
			case KeyEvent.VK_A:
				if(!this.modoJogo.equals("")){
					if(this.modoJogo.equals("single"))
						this.fase.movimentarCameraEsq(this.jogador);
					if(this.modoJogo.equals("mult")){
						if(this.multi.getJogadorAtual()==1)
							this.faseMulti.movimentarCameraEsq(this.jogador,0);
						if(this.multi.getJogadorAtual()==2)
							this.faseMulti.movimentarCameraEsq(this.jogador2,0);
					}
				}
			break;
			
			case KeyEvent.VK_UP:
				if(this.modoJogo.equals("mult")){
				if(this.multi.getJogadorAtual()==1)
					this.jogador.aumentarAngulo(3,15,1,1);
				if(this.multi.getJogadorAtual()==2)
					this.jogador2.aumentarAngulo(2,15,1,2);
				}
				
				if(this.modoJogo.equals("single"))
					this.jogador.aumentarAngulo(3,15,1,1);
				
			break;
			
			case KeyEvent.VK_DOWN:
				if(this.modoJogo.equals("mult")){
					if(this.multi.getJogadorAtual()==1)
						this.jogador.diminuirAngulo(5,15,1,1);
					if(this.multi.getJogadorAtual()==2)
						this.jogador2.diminuirAngulo(0,15,1,2);
				}
				
				if(this.modoJogo.equals("single"))
					this.jogador.diminuirAngulo(5,15,1,1);
			break;
			
			case KeyEvent.VK_RIGHT:
				if(this.modoJogo.equals("mult")){
					if(this.multi.getJogadorAtual()==1)
						this.jogador.andarDireita(100);
					if(this.multi.getJogadorAtual()==2)
						this.jogador2.andarDireita(2490);
				}
				
				if(this.modoJogo.equals("single"))
					this.jogador.andarDireita(100);
			break;
			
			case KeyEvent.VK_LEFT:
				if(this.modoJogo.equals("mult")){
					if(this.multi.getJogadorAtual()==1)
						this.jogador.andarEsquerda(20);
					if(this.multi.getJogadorAtual()==2)
						this.jogador2.andarEsquerda(2410);
				}
				
				if(this.modoJogo.equals("single"))
					this.jogador.andarEsquerda(20);
			break;
			
			case KeyEvent.VK_PAGE_UP:
				if(this.modoJogo.equals("mult")){
					if(this.multi.getJogadorAtual()==1)
						this.jogador.aumentarForcaTiro();
					if(this.multi.getJogadorAtual()==2)
						this.jogador2.aumentarForcaTiro();
				}
				
				if(this.modoJogo.equals("single"))
					this.jogador.aumentarForcaTiro();
					
			break;
			
			case KeyEvent.VK_PAGE_DOWN:
				if(this.modoJogo.equals("mult")){
					if(this.multi.getJogadorAtual()==1)
						this.jogador.diminuirForcaTiro();
					if(this.multi.getJogadorAtual()==2)
						this.jogador2.diminuirForcaTiro();
				}
				
				if(this.modoJogo.equals("single"))
					this.jogador2.diminuirForcaTiro();
			break;
			
			case KeyEvent.VK_ESCAPE:
				if(this.modoJogo.equals("single")){
					fase.setRunningFase(false);
					fase.setRunning(false);
					tela.dispose();
					menu.setVisible(true);
				}
				if(this.modoJogo.equals("mult")){
					faseMulti.setRunningFase(false);
					faseMulti.setRunning(false);
					tela.dispose();
					menu.setVisible(true);
				}
			break;
			
			default:
			break;
		}	
	}

	public PainelTelaJogo getFase() {
		return fase;
	}

	public void setFase(PainelTelaJogo fase) {
		this.fase = fase;
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
			
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menu.getSingleButton()){
			jogador=new Jogador(60,510,3,5,5,40,60,"espera",5, this.tmpVoo);
			this.modoJogo="single";
			level = new SinglePlayer(1,this);
			level.setSingle(true);
			menu.setVisible(false);
			this.tela = new TelaJogo(this, "Singleplayer");
			fase = new PainelTelaJogo(this);
			this.tela.add(fase);
			this.level.start();
		}
		
		if(e.getSource()==this.menu.getMultiButton()){
			this.modoJogo = "mult";
			jogador=new Jogador(60,510,3,5,5,40,60,"espera",2, this.tmpVoo);
			jogador2=new Jogador(2460, 510, 2,0,35,40,120,"espera",2, this.tmpVoo);
			multi = new Multiplayer(this);
			multi.setMulti(true);
			menu.setVisible(false);
			this.tela = new TelaJogo(this,"Multiplayer");
			faseMulti = new PainelMultiJogo(this);
			this.tela.add(faseMulti);
			this.multi.start();
		}
		
		if(e.getSource()==this.menu.getAjudaButton())
			new TelaAjuda();
		
		if(e.getSource()==this.menu.getCreditosButton())
			new TelaCreditos();
		
		if(e.getSource()==this.menu.getSairButton())
			System.exit(0);
		

		if(e.getSource()==menu.getAumentarTmp() && this.menu.getConfigVeloc().getText().equals(" 1")){
			menu.getConfigVeloc().setText(" 2");
			this.tmpVoo = 0.2;
		}
		else if(e.getSource()==menu.getAumentarTmp() && this.menu.getConfigVeloc().getText().equals(" 2")){
			menu.getConfigVeloc().setText(" 3");
			this.tmpVoo = 0.3;
		}
		if(e.getSource()==menu.getDiminuirTmp() && this.menu.getConfigVeloc().getText().equals(" 3")){
			menu.getConfigVeloc().setText(" 2");
			this.tmpVoo = 0.2;
		}
		else if(e.getSource()==menu.getDiminuirTmp() && this.menu.getConfigVeloc().getText().equals(" 2")){
			menu.getConfigVeloc().setText(" 1");
			this.tmpVoo = 0.1;
		}
		
		if(e.getSource()==menu.getRkgSingle()){
			new TelaRanking("ranking.xml");
		}
		if(e.getSource()==menu.getRkgMult()){
			new TelaRanking("rankingMult.xml");
		}
		
	}

	public PainelMultiJogo getFaseMulti() {
		return faseMulti;
	}

	public void setFaseMulti(PainelMultiJogo faseMulti) {
		this.faseMulti = faseMulti;
	}

	public SinglePlayer getLevel() {
		return level;
	}

	public void setLevel(SinglePlayer level) {
		this.level = level;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x, y;
		x=e.getX();
		y=e.getY();
		if(y>485)
			y=485;
		if(x>200)
			x=200;

		double deltaX = x-70;
		double deltaY = 485-y;
		double angulo = (Math.atan(deltaY/deltaX));
		
		if(this.modoJogo.equals("single"))
			jogador.anguloMouse(angulo,1);
		
		if(this.modoJogo.equals("mult")){
			if(this.multi.getJogadorAtual()==1)
				jogador.anguloMouse(angulo,1);
			if(this.multi.getJogadorAtual()==2)
				jogador2.anguloMouse(angulo,2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(this.modoJogo.equals("single"))
			this.jogador.atirar();
		
		if(this.modoJogo.equals("mult")){
			if(this.multi.getJogadorAtual()==1)
				this.jogador.atirar();
			if(this.multi.getJogadorAtual()==2)
				this.jogador2.atirar();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public TelaJogo getTela() {
		return tela;
	}

	public void setTela(TelaJogo tela) {
		this.tela = tela;
	}

	public TelaInicialMenu getMenu() {
		return menu;
	}

	public void setMenu(TelaInicialMenu menu) {
		this.menu = menu;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		int scroll = e.getWheelRotation();
			if(this.modoJogo.equals("single")){
				if(scroll>0)
					this.jogador.diminuirForcaTiro();
				if(scroll<0)
					this.jogador.aumentarForcaTiro();
			}
			
			if(this.modoJogo.equals("mult")){
				if(this.multi.getJogadorAtual()==1){
					if(scroll>0)
						this.jogador.diminuirForcaTiro();
					if(scroll<0)
						this.jogador.aumentarForcaTiro();
				}
				
				if(this.multi.getJogadorAtual()==2){
					if(scroll>0)
						this.jogador2.diminuirForcaTiro();
					if(scroll<0)
						this.jogador2.aumentarForcaTiro();
				}
			}
	}
	
	public void tmpVoo(double tmp){
		this.tmpVoo = tmp;
	}

	public Multiplayer getMulti() {
		return multi;
	}

	public void setMulti(Multiplayer multi) {
		this.multi = multi;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}			
}
