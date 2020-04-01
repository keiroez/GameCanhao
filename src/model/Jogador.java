package model;

import java.io.IOException;

import view.TelaJogo;


public class Jogador{
	private int life;
	private Projetil pj;
	private int posX, posY;
	private Sprite imagem;
	private Sprite potencia;
	private int potenciaNumero=20;
	private int pontuacao=0;
	private int qtdBomba;
	
	public Jogador(int x, int y,int cena,int cenaPot, int ajusteX,int anguloInicial, String estado, double tmpVoo) {
		this.life = 100;
		this.posX =  x;
		this.posY =  y;
		this.pj = new Projetil(110,anguloInicial,this.posX+ajusteX,this.posY+40, estado, tmpVoo);
		
			try {
				imagem = new Sprite(cena, 60, 67, 6, 1, "imagens/spriteCanhao23.png");
				potencia = new Sprite(cenaPot, 240, 88, 2, 5, "imagens/barraPot.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public Jogador(int x, int y,int cena,int cenaPot, int ajusteX, int ajusteY,int anguloInicial, String estado, int qtdBomba, double tmpVoo) {
		this.life = 100;
		this.posX =  x;
		this.posY =  y;
		this.pj = new Projetil(110,anguloInicial,this.posX+ajusteX,this.posY+ajusteY, estado, tmpVoo);
		this.qtdBomba = qtdBomba;
		
			try {
				imagem = new Sprite(cena, 60, 67, 6, 1, "imagens/spriteCanhao23.png");
				potencia = new Sprite(cenaPot, 240, 88, 2, 5, "imagens/barraPot.png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void atirar(){
		if(this.pj.getEstado().equals("espera")){
			this.qtdBomba--;
			this.pj.setEstado("mov");
			this.pj.calcularMovimento(this.pj.getPosX(),this.pj.getPosY());
			}
	}
	
	public void anguloMouse(double angulo, int jg){
		if(jg == 1){
		if(this.pj.getEstado().equals("espera")){
			if(angulo*180/Math.PI<45){
				this.imagem.setCena(5);
				this.pj.setAnguloGraus(30);
			}
			if(angulo*180/Math.PI>=45 && angulo*180/Math.PI<60){
				this.imagem.setCena(4);
				this.pj.setAnguloGraus(45);
			}
			if(angulo*180/Math.PI>=60){
				this.imagem.setCena(3);
				this.pj.setAnguloGraus(60);;
			}
		}
		}
		if(jg == 2){
			if(this.pj.getEstado().equals("espera")){
				if(angulo*180/Math.PI<45){
					this.imagem.setCena(0);
					this.pj.setAnguloGraus(150);
				}
				if(angulo*180/Math.PI>=45 && angulo*180/Math.PI<60){
					this.imagem.setCena(1);
					this.pj.setAnguloGraus(135);
				}
				if(angulo*180/Math.PI>=60){
					this.imagem.setCena(2);
					this.pj.setAnguloGraus(120);;
				}
			}
			}
	}
	
	public void aumentarAngulo(int cenaMin, int angulo, int cenaSet, int nJ){
		if(nJ==1){
			if(this.imagem.getCena() > cenaMin){
				if(this.pj.getEstado().equals("espera")){
					this.pj.setAnguloGraus(this.pj.getAnguloGraus()+angulo);
					this.imagem.setCena(this.imagem.getCena()-cenaSet);
				}
			}
		}
		if(nJ==2){
			if(this.imagem.getCena() < cenaMin){
				if(this.pj.getEstado().equals("espera")){
					this.pj.setAnguloGraus(this.pj.getAnguloGraus()-angulo);
					this.imagem.setCena(this.imagem.getCena()+cenaSet);
				}
			}
		}
	}
	
	public void diminuirAngulo(int cenaMin, int angulo, int cenaSet, int nJ){
		if(nJ==1){
			if(this.imagem.getCena() < cenaMin){
				if(this.pj.getEstado().equals("espera")){
					this.pj.setAnguloGraus(this.pj.getAnguloGraus()-angulo);
					this.imagem.setCena(this.imagem.getCena()+cenaSet);
				}
			}
		}
		
		if(nJ==2){
			if(this.imagem.getCena() > cenaMin){
				if(this.pj.getEstado().equals("espera")){
					this.pj.setAnguloGraus(this.pj.getAnguloGraus()+angulo);
					this.imagem.setCena(this.imagem.getCena()-cenaSet);
				}
			}
		}
	}
	
	public void andarDireita(int max){
		if(this.pj.getEstado().equals("espera")  && this.posX<max){
			this.setPosX(this.getPosX()+5);
			this.pj.setPosX(this.pj.getPosX()+5);
			this.pj.setPosInicX(this.pj.getPosInicX()+5);
		}
	}
	
	public void andarEsquerda(int min){
		if(this.pj.getEstado().equals("espera")  && this.posX>min){
			this.setPosX(this.getPosX()-5);
			this.pj.setPosX(this.pj.getPosX()-5);
			this.pj.setPosInicX(this.pj.getPosInicX()-5);
		}
	}
	
	public void aumentarForcaTiro(){
		if(this.potenciaNumero<100){
			if(this.pj.getEstado().equals("espera")){
				this.setPotenciaNumero(this.potenciaNumero+20);
				this.pj.setVelocInicial(this.pj.getVelocInicial()+10);
				this.potencia.setCena(this.potencia.getCena()+1);
			}
		}
	}
	
	public void diminuirForcaTiro(){
		if(this.potenciaNumero>20){
			if(this.pj.getEstado().equals("espera")){
				this.setPotenciaNumero(this.potenciaNumero-20);
				this.pj.setVelocInicial(this.pj.getVelocInicial()-10);
				this.potencia.setCena(this.potencia.getCena()-1);
			}
		}
	}
	

	public int getLife() {
		return life;
	}


	public void setLife(int life) {
		this.life = life;
	}


	public Projetil getPj() {
		return pj;
	}


	public void setPj(Projetil pj) {
		this.pj = pj;
	}


	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}


	public Sprite getImagem() {
		return imagem;
	}


	public void setImagem(Sprite imagem) {
		this.imagem = imagem;
	}


	public Sprite getPotencia() {
		return potencia;
	}


	public void setPotencia(Sprite potencia) {
		this.potencia = potencia;
	}


	public int getPotenciaNumero() {
		return potenciaNumero;
	}


	public void setPotenciaNumero(int potenciaNumero) {
		this.potenciaNumero = potenciaNumero;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getQtdBomba() {
		return qtdBomba;
	}

	public void setQtdBomba(int qtdBomba) {
		this.qtdBomba = qtdBomba;
	}
	
}
