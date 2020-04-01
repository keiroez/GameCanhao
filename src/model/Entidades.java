package model;

import java.awt.Rectangle;
import java.io.IOException;

public abstract class Entidades {
	protected Rectangle areaImpacto;
	protected int imagemLargura;
	protected int imagemAltura;
	protected int posX, posY;
	protected Sprite imagem;
	
	
	public Entidades(int cena, int largura, int altura,int linha, int coluna, int x, int y, String endereco) {
		this.areaImpacto = new Rectangle(x,y,largura, altura);
		this.imagemLargura = largura;
		this.imagemAltura = altura;
		this.posX=x;
		this.posY=y;
		
		try {
			imagem = new Sprite(cena, largura, altura, coluna, linha, endereco);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPosColisao(int x, int y){
		this.areaImpacto.x=x;
		this.areaImpacto.y=y;
	}
	
	public Rectangle getAreaImpacto() {
		return areaImpacto;
	}
	public void setAreaImpacto(Rectangle areaImpacto) {
		this.areaImpacto = areaImpacto;
	}
	public int getImagemLargura() {
		return imagemLargura;
	}
	public void setImagemLargura(int imagemLargura) {
		this.imagemLargura = imagemLargura;
	}
	public int getImagemAltura() {
		return imagemAltura;
	}
	public void setImagemAltura(int imagemAltura) {
		this.imagemAltura = imagemAltura;
	}
}
