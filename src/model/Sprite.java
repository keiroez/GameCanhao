package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage img;
	private int cena;
	private BufferedImage[] cenas;
	private int largura, altura;
	private int linha, colunas;
	private int velocidade = 5;
	private String endereco;
	
	public Sprite(int aparencia, int largura, int altura, int colunas, int linhas, String endereco) throws IOException{
		
		try {
			this.img = ImageIO.read(new File(endereco));
			this.cena = aparencia;
			this.largura = largura;
			this.altura = altura;
			
			cenas = new BufferedImage[linhas * colunas];
				for(int i = 0;i < colunas;i++){
					for(int j = 0; j < linhas; j++){
						cenas[(i*linhas)+j] = img.getSubimage(i*largura, j*altura, largura, altura);
					}
				}
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
	
	public void animar(){
		
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public BufferedImage[] getCenas() {
		return cenas;
	}

	public void setCenas(BufferedImage[] cenas) {
		this.cenas = cenas;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
