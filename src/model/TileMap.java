package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import control.GameController;

public class TileMap {
	private final int numeroColunas = 80;
	private final int numeroLinhas = 30;
	private final int colunasTileSet = 8;
	private final int tileSize = 32;
	
	private int camada[][];
	private BufferedImage tileSet;
	private BufferedImage mapa = new BufferedImage(numeroColunas* tileSize,numeroLinhas* tileSize, BufferedImage.TYPE_4BYTE_ABGR);
	
	private Graphics2D dbg=mapa.createGraphics();
	
	private boolean isEntidade;
	private Rectangle[][] alvos;
	
	private int levelGame;
	
	public TileMap(String nomeTileSet, String nomeMapaMatriz, boolean isEntidade,int levelGame) {
		
		try {
			tileSet=ImageIO.read(getClass().getClassLoader().getResourceAsStream(nomeTileSet));
		} catch (IOException e) {
			System.out.println("Impossivel ler tileSet");
			e.printStackTrace();
		}
		
		alvos = new Rectangle[numeroLinhas][numeroColunas];
		
		camada=carregarMatriz(nomeMapaMatriz);
		this.isEntidade=isEntidade;
		this.levelGame=levelGame;
	}
	
	public void montarMapa(){
	mapa.createGraphics();
		
		int tile;
		int tileRow;
		int tileCol;
		
		for (int i = 0; i < numeroLinhas; i++) {
			for (int j = 0; j < numeroColunas; j++) {
				tile = (camada[i][j] !=0) ? (camada[i][j] - 1) : 0;
				tileRow = (tile / colunasTileSet) | 0;
				tileCol = (tile % colunasTileSet) | 0;
				dbg.drawImage(tileSet,
						(j * tileSize),
						(i * tileSize), 
						(j * tileSize) + tileSize,
						(i * tileSize) + tileSize, (tileCol * tileSize),
						(tileRow * tileSize), (tileCol * tileSize) + tileSize,
						(tileRow * tileSize) + tileSize, null);
						
				if(isEntidade==true){
					alvos[i][j]=new Rectangle(j*tileSize, i*tileSize, tileSize, tileSize);
				}
			}
		}
	}
	
	
	public void montarMapa(GameController controle){
		mapa.createGraphics();
			
			if(this.isEntidade && controle.getLevel().getLevel()<4)
			controle.getLevel().montarFase(this);
			
			int tile;
			int tileRow;
			int tileCol;
			
			for (int i = 0; i < numeroLinhas; i++) {
				for (int j = 0; j < numeroColunas; j++) {
					tile = (camada[i][j] !=0) ? (camada[i][j] - 1) : 0;
					tileRow = (tile / colunasTileSet) | 0;
					tileCol = (tile % colunasTileSet) | 0;
					dbg.drawImage(tileSet,
							(j * tileSize),
							(i * tileSize), 
							(j * tileSize) + tileSize,
							(i * tileSize) + tileSize, (tileCol * tileSize),
							(tileRow * tileSize), (tileCol * tileSize) + tileSize,
							(tileRow * tileSize) + tileSize, null);
							
					if(isEntidade==true){
						alvos[i][j]=new Rectangle(j*tileSize, i*tileSize, tileSize, tileSize);
					}
				}
			}
		}
	
	
	public int[][] carregarMatriz(String nomeMapa){
		int[][] matz = new int[numeroLinhas][numeroColunas];
		
		InputStream input = getClass().getClassLoader().getResourceAsStream(nomeMapa);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		
		ArrayList<String> linhas = new ArrayList<>();
		String linha="";
		
		try {
			while((linha=br.readLine())!=null)
				linhas.add(linha);
				
			int coluna=0;
			
			for(int i = 0; i < linhas.size();i++){
				StringTokenizer token = new StringTokenizer(linhas.get(i), ",");
				
				while (token.hasMoreElements()) {
					matz[i][coluna] = Integer.parseInt(token.nextToken());
					coluna++;
				}
				coluna=0;
			}
		} catch (IOException e) {
			System.out.println("Não carregou matriz");
			e.printStackTrace();
		}
		
		return matz;
	}

	public BufferedImage getMapa() {
		return mapa;
	}

	public int getNumeroColunas() {
		return numeroColunas;
	}

	public int getNumeroLinhas() {
		return numeroLinhas;
	}

	public int[][] getCamada() {
		return camada;
	}

	public void setCamada(int i, int j, int valor) {
		this.camada[i][j] = valor;
	}
	
	public Rectangle[][] getAlvos() {
		return alvos;
	}

	public void setAlvos(Rectangle[][] alvos) {
		this.alvos = alvos;
	}

}
