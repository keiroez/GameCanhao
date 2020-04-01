package model;

import java.awt.Rectangle;
import java.util.Random;

import control.GameController;

public class Multiplayer extends Thread {
	private boolean isMulti = false;
	private GameController controle;
	private int jogadorAtual;
	private Rectangle[] bombasBonus = new Rectangle[3];
	
	public Multiplayer(GameController controle) {
		this.controle = controle;
		this.jogadorAtual = 1;
		
		
		Random radom = new Random();
		int numeroAletorioX, numeroAletorioY;
		for(int i =0 ; i<3 ; i++){
			numeroAletorioX = radom.nextInt(864)+864;
			numeroAletorioY = radom.nextInt(32)+608;
			this.bombasBonus[i] = new Rectangle(numeroAletorioX, numeroAletorioY, 32, 32);
			System.out.println(numeroAletorioX+"  "+numeroAletorioY);
		}
	}

	public boolean isMulti() {
		return isMulti;
	}

	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}
	
	public void run(){
		while(true){
			if(this.jogadorAtual==1){
				for(int i=0; i<3; i++){
					if(this.controle.getJogador().getPj().areaImpacto.intersects(this.bombasBonus[i])){
						this.bombasBonus[i].y=1000;
						this.controle.getJogador().getPj().setQtdSuperBomba(this.controle.getJogador().getPj().getQtdSuperBomba()+1);
					}
				}
			}
			
			if(this.jogadorAtual==2){
				for(int i=0; i<3; i++){
					if(this.controle.getJogador2().getPj().areaImpacto.intersects(this.bombasBonus[i])){
						this.bombasBonus[i].y=1000;
						this.controle.getJogador2().getPj().setQtdSuperBomba(this.controle.getJogador2().getPj().getQtdSuperBomba()+1);
					}
				}
			}
			
			if(this.jogadorAtual==1 && this.controle.getJogador2().getQtdBomba()>0){
				if(this.controle.getJogador().getPj().getEstado().equals("carregando")){
					this.controle.getJogador2().getPj().setEstado("espera");
					jogadorAtual = 2;
					this.controle.getFaseMulti().setPosicaoTelaX(-1340);
					this.controle.getFaseMulti().setPosicaoTelaY(-290);
				}
			}
			if(this.jogadorAtual==2  && this.controle.getJogador().getQtdBomba()>0){
				if(this.controle.getJogador2().getPj().getEstado().equals("carregando")){
					this.controle.getJogador().getPj().setEstado("espera");
					jogadorAtual = 1;
					this.controle.getFaseMulti().setPosicaoTelaX(0);
					this.controle.getFaseMulti().setPosicaoTelaY(-290);
				}
			}
			if(this.controle.getJogador().getQtdBomba()==0 && this.controle.getJogador2().getQtdBomba()==0 && this.controle.getJogador().getPj().getEstado().equals("carregando") && this.controle.getJogador2().getPj().getEstado().equals("carregando")) {
				if(this.controle.getJogador().getPontuacao()>=this.controle.getJogador2().getPontuacao()){
					RankingXml rk = new RankingXml("rankingMult.xml");
					rk.escrever(this.controle.getJogador().getPontuacao(), "J1");
				}
				else if(this.controle.getJogador().getPontuacao()<this.controle.getJogador2().getPontuacao()){
					RankingXml rk = new RankingXml("rankingMult.xml");
					rk.escrever(this.controle.getJogador2().getPontuacao(), "J2");
				}
				this.jogadorAtual=0;
			}
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getJogadorAtual() {
		return jogadorAtual;
	}

	public void setJogadorAtual(int jogadorAtual) {
		this.jogadorAtual = jogadorAtual;
	}

	
}
