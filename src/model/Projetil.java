package model;


import java.awt.Rectangle;
import java.io.IOException;



public class Projetil extends Entidades{
	private double velocY, velocX, velocInicial;
	private double anguloGraus, anguloRad;
	private static double tempoDeVoo;
	private String estado;
	private int posInicX, posInicY;
	private Sprite imagemExplosao, imgPrincipal, imgSuperBomba;
	private boolean superBomba = false;
	private int qtdSuperBomba = 1;
	private double fatorVeloc;
	
	public Projetil(int velocidade, int angulo, int posX, int posY, String estado, double fatorVeloc){
		super(0,15, 15,1, 1,posX,posY,"imagens/bomba2.png");
		this.velocInicial = velocidade;
		this.anguloGraus = angulo;
		this.anguloRad = (this.anguloGraus*(Math.PI/180));
		this.velocY= Math.round(velocInicial*Math.sin(anguloRad));
		this.velocX= Math.round(velocInicial*Math.cos(anguloRad));
		this.tempoDeVoo = 0;
		this.posX = posX;
		this.posY = posY;
		this.posInicX=posX;
		this.posInicY=posY;
		this.estado = estado;
		this.imgPrincipal = this.imagem;
		this.fatorVeloc = fatorVeloc;
		try {
			this.imagemExplosao = new Sprite(0, 100, 103, 3, 2, "imagens/explod.png");
			this.imgSuperBomba = new Sprite(0, 37, 37, 1, 1, "imagens/bomba.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void explodir(){
		if(this.estado.equals("explodir")){
			this.imagem = this.imagemExplosao;
			if(this.imagem.getCena()<5){
				this.imagem.setCena(this.imagem.getCena()+1);
			}
			else{
				reiniciarPos();
			}
		}
	}
	
	public void superBomba(){
		if(this.qtdSuperBomba>0){
		this.imagem = imgSuperBomba;
		this.superBomba = true;
		this.qtdSuperBomba--;
		}
	}
	
	public void explodirMult(){
		if(this.estado.equals("explodir")){
			this.imagem = this.imagemExplosao;
			if(this.imagem.getCena()<5){
				this.imagem.setCena(this.imagem.getCena()+1);
			}
			else{
				reiniciarPosMult();
			}
		}
	}
	
	public void reiniciarPosMult(){
		this.imagem=imgPrincipal;
		this.imagemExplosao.setCena(0);
		this.estado="carregando";
		this.tempoDeVoo=0;
		this.setPosX(posInicX);
		this.setPosY(posInicY);
		this.areaImpacto.height = 15;
		this.areaImpacto.width = 15;
		this.superBomba = false;
	}
	
	public void tiro(){
		if(this.estado.equals("mov")){
			this.calcularMovimento(this.posInicX,this.posInicY);
		}
		else
			this.imagem=imgPrincipal;
	}
	
	public void reiniciarPos(){
		this.imagem=imgPrincipal;
		this.imagemExplosao.setCena(0);
		this.estado="carregando";
		this.tempoDeVoo=0;
		this.setPosX(posInicX);
		this.setPosY(posInicY);
	}
	
	public void posLevel(){
		this.imagem=imgPrincipal;
		this.imagemExplosao.setCena(0);
		this.estado="espera";
		this.tempoDeVoo=0;
		this.setPosX(posInicX);
		this.setPosY(posInicY);
	}
	
	public void calcularMovimento(int posXInicial, int posYInicial){
		if(this.tempoDeVoo==0){
		this.anguloRad = (this.anguloGraus*(Math.PI/180));
		this.velocY= Math.round(velocInicial*Math.sin(anguloRad));
		this.velocX= Math.round(velocInicial*Math.cos(anguloRad));
		}
		if((this.posY==posYInicial || (tempoDeVoo>=0)) && this.getPosY()<AtualizaTela.ALTURA-80){
			if(this.estado.equals("mov")){
						this.tempoDeVoo +=fatorVeloc;
						this.posX = posXInicial+(int)(this.velocX * this.tempoDeVoo);
						this.posY = posYInicial-(int)(this.velocY * this.tempoDeVoo - 0.5*10 * this.tempoDeVoo * this.tempoDeVoo);	
						this.setPosColisao(this.posX, this.posY);
			}
		}
		else{
			this.imagem=imgPrincipal;
			this.estado="carregando";
			this.posX=posXInicial;
			this.posY=posYInicial;
			this.tempoDeVoo=0;
		}
	}

	public Sprite getImgPrincipal() {
		return imgPrincipal;
	}

	public void setImgPrincipal(Sprite imgPrincipal) {
		this.imgPrincipal = imgPrincipal;
	}

	public boolean colisao(Rectangle b){
		if(this.areaImpacto.intersects(b)){
			return true;
		}
		else{
			return false;
		}
	}

	public double getVelocY() {
		return velocY;
	}
	


	public void setVelocY(double velocY) {
		this.velocY = velocY;
	}


	public double getVelocX() {
		return velocX;
	}


	public void setVelocX(double velocX) {
		this.velocX = velocX;
	}


	public double getVelocInicial() {
		return velocInicial;
	}


	public void setVelocInicial(double velocInicial) {
		this.velocInicial = velocInicial;
	}


	public double getAnguloGraus() {
		return anguloGraus;
	}


	public void setAnguloGraus(double anguloGraus) {
		this.anguloGraus = anguloGraus;
	}


	public double getAnguloRad() {
		return anguloRad;
	}


	public void setAnguloRad(double anguloRad) {
		this.anguloRad = anguloRad;
	}


	public double getTempoDeVoo() {
		return tempoDeVoo;
	}


	public void setTempoDeVoo(double tempoDeVoo) {
		this.tempoDeVoo = tempoDeVoo;
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


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getPosInicX() {
		return posInicX;
	}

	public void setPosInicX(int posInicX) {
		this.posInicX = posInicX;
	}

	public int getPosInicY() {
		return posInicY;
	}

	public void setPosInicY(int posInicY) {
		this.posInicY = posInicY;
	}

	public boolean isSuperBomba() {
		return superBomba;
	}

	public void setSuperBomba(boolean superBomba) {
		this.superBomba = superBomba;
	}

	public int getQtdSuperBomba() {
		return qtdSuperBomba;
	}

	public void setQtdSuperBomba(int qtdSuperBomba) {
		this.qtdSuperBomba = qtdSuperBomba;
	}

}
