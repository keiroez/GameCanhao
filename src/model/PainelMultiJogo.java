package model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import control.GameController;
import view.TelaJogo;

public class PainelMultiJogo extends AtualizaTela {
	
	private TileMap fundo;
	private TileMap base;
	private TileMap arvore_casa;
	private TileMap pontos;
	private GameController controle;
	
	public PainelMultiJogo(GameController controle) {
		// TODO Auto-generated constructor stub
		this.controle = controle;
		this.setPosicaoTelaX(0);
	}

	@Override
	public void init() {
		fundo = new TileMap("tileset.png", "fundo.txt",false,1);
		base = new TileMap("tileset.png", "base.txt",false,1);
		arvore_casa = new TileMap("tileset.png", "arvores_casas.txt",true,1);
		pontos = new TileMap("tileset.png", "pontos.txt", false,1);
	}

	@Override
	public void gameUpdate() {
		fundo.montarMapa();
		base.montarMapa();
		arvore_casa.montarMapa();
		pontos.montarMapa();
		
		if(this.controle.getJogador().getPj().getEstado().equals("mov")){
		controle.getJogador().getPj().tiro();
		}
		if(this.controle.getJogador2().getPj().getEstado().equals("mov")){
			controle.getJogador2().getPj().tiro();
		}
		
		if(this.controle.getMulti().getJogadorAtual()==1){
			camera1();
			colisao1();
			this.controle.getJogador().getPj().explodirMult();
		}
		if(this.controle.getMulti().getJogadorAtual()==2){
			camera2();
			colisao2();
			this.controle.getJogador2().getPj().explodirMult();
		}
	}
	
	

	@Override
	public void gameRender() {
		g.setColor(Color.black);
		g.setFont(new Font("Dialog", Font.BOLD, 18));
		g.drawImage(fundo.getMapa(), 0, 0, null);
		g.drawImage(base.getMapa(), 0, 0, null);
		g.drawImage(arvore_casa.getMapa(), 0, 0, null);
		
		g.drawImage(this.controle.getJogador().getPj().getImagem().getCenas()[this.controle.getJogador().getPj().getImagem().getCena()], this.controle.getJogador().getPj().getPosX(), this.controle.getJogador().getPj().getPosY(), null);
		g.drawImage(this.controle.getJogador().getImagem().getCenas()[this.controle.getJogador().getImagem().getCena()],this.controle.getJogador().getPosX(),this.controle.getJogador().getPosY(),null);
		
		if(this.controle.getMulti().getJogadorAtual()==1){
			g.drawImage(this.controle.getJogador().getPotencia().getCenas()[this.controle.getJogador().getPotencia().getCena()], 10, 780,null);
			g.drawString(Integer.toString(this.controle.getJogador().getPotenciaNumero()), 60, 850);
			g.drawString("POTÊNCIA       ", 120, 850);
		}
		
		g.drawImage(this.controle.getJogador2().getPj().getImagem().getCenas()[this.controle.getJogador2().getPj().getImagem().getCena()], this.controle.getJogador2().getPj().getPosX(), this.controle.getJogador2().getPj().getPosY(), null);
		g.drawImage(this.controle.getJogador2().getImagem().getCenas()[this.controle.getJogador2().getImagem().getCena()], this.controle.getJogador2().getPosX(),this.controle.getJogador2().getPosY(),null);
		
		if(this.controle.getMulti().getJogadorAtual()==2){
			g.drawImage(this.controle.getJogador2().getPotencia().getCenas()[this.controle.getJogador2().getPotencia().getCena()], 2290, 780,null);
			g.drawString(Integer.toString(this.controle.getJogador2().getPotenciaNumero()), 2460, 850);
			g.drawString("POTÊNCIA       ", 2330, 850);
		}
		
		g.drawImage(pontos.getMapa(), 0, -this.getPosicaoTelaY()-290, null);
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.BOLD, 50));
		if(this.controle.getMulti().getJogadorAtual()==1){
			g.drawString("Pontos "+Integer.toString(this.controle.getJogador().getPontuacao()), -this.getPosicaoTelaX()+120, -this.getPosicaoTelaY()+650);
			g.setFont(new Font("Dialog", Font.BOLD, 15));
			g.drawString("Pontos adversário: "+Integer.toString(this.controle.getJogador2().getPontuacao()), -this.getPosicaoTelaX()+400, -this.getPosicaoTelaY()+650);
			
			for(int i = 0; i<this.controle.getJogador().getQtdBomba();i++ )
				g.drawImage(new ImageIcon("imagens/bomba2.png").getImage(), -this.getPosicaoTelaX()+800+(20*i), -this.getPosicaoTelaY()+620, null);
			
			for(int i = 0; i<this.controle.getJogador().getPj().getQtdSuperBomba();i++){
				g.drawImage(new ImageIcon("imagens/bomba.png").getImage(), -this.getPosicaoTelaX()+600+(40*i), -this.getPosicaoTelaY()+620, null);
			}
			
			g.setFont(new Font("Dialog", Font.BOLD, 15));
			g.drawString("Bombas Jogador 1", -this.getPosicaoTelaX()+800, -this.getPosicaoTelaY()+650);
		}
		
		g.setFont(new Font("Dialog", Font.BOLD, 50));
		if(this.controle.getMulti().getJogadorAtual()==2){
			g.drawString("Pontos "+Integer.toString(this.controle.getJogador2().getPontuacao()), -this.getPosicaoTelaX()+120, -this.getPosicaoTelaY()+650);
			g.setFont(new Font("Dialog", Font.BOLD, 15));
			g.drawString("Pontos adversário: "+Integer.toString(this.controle.getJogador().getPontuacao()), -this.getPosicaoTelaX()+400, -this.getPosicaoTelaY()+650);
			
			for(int i = 0; i<this.controle.getJogador2().getQtdBomba();i++ )
				g.drawImage(new ImageIcon("imagens/bomba2.png").getImage(), -this.getPosicaoTelaX()+800+(20*i), -this.getPosicaoTelaY()+620, null);
			
			for(int i = 0; i<this.controle.getJogador2().getPj().getQtdSuperBomba();i++){
				g.drawImage(new ImageIcon("imagens/bomba.png").getImage(), -this.getPosicaoTelaX()+600+(40*i), -this.getPosicaoTelaY()+620, null);
			}
			
			g.setFont(new Font("Dialog", Font.BOLD, 15));
			g.drawString("Bombas Jogador 2", -this.getPosicaoTelaX()+800, -this.getPosicaoTelaY()+650);
		}
		
		if(this.controle.getMulti().getJogadorAtual()==0){
			g.setColor(Color.white);
			g.setFont(new Font("Dialog", Font.BOLD, 50));
			g.drawString("Fim de jogo", -this.getPosicaoTelaX()+400, -this.getPosicaoTelaY()+200);
			g.drawString("Jogador1   "+Integer.toString(this.controle.getJogador().getPontuacao())+" x "+Integer.toString(this.controle.getJogador2().getPontuacao())+"   Jogador2"
					, -this.getPosicaoTelaX()+250, -this.getPosicaoTelaY()+300);
			g.setFont(new Font("Dialog", Font.BOLD, 15));
			g.drawString("Pressione Esc para voltar ao menu", -this.getPosicaoTelaX()+400, -this.getPosicaoTelaY()+220);
		}
	}
	
	public void movimentarCameraDir(Jogador jogador, int max){
		if(jogador.getPj().getEstado().equals("espera")){
			if(this.getPosicaoTelaX()>(-max))
				this.setPosicaoTelaX(this.getPosicaoTelaX()-32);
		}
	}
	public void movimentarCameraEsq(Jogador jogador, int min){
		if(jogador.getPj().getEstado().equals("espera")){
			if(this.getPosicaoTelaX()<min)
				this.setPosicaoTelaX(this.getPosicaoTelaX()+32);
		}
	}
	
	public void camera1(){
		if(this.controle.getJogador().getPj().getEstado().equals("mov")){
				
			if(this.controle.getJogador().getPj().getPosX()>TelaJogo.getLargura()/2 && this.getPosicaoTelaX()>(-1360))
				if(this.controle.getJogador().getPj().getPosX()<this.LARGURA-TelaJogo.getLargura()/2)
				this.setPosicaoTelaX(-(this.controle.getJogador().getPj().getPosX()-(TelaJogo.getLargura()/2))); 
			
			if(this.controle.getJogador().getPj().getPosY()<480 && this.getPosicaoTelaY()<0)
				this.setPosicaoTelaY(this.getPosicaoTelaY()+10);
			if(this.controle.getJogador().getPj().getPosY()>300 && this.getPosicaoTelaY()>(-290))
				this.setPosicaoTelaY(this.getPosicaoTelaY()-10);
		}
		
		if(this.getPosicaoTelaX()<0 && this.controle.getJogador().getPj().getEstado().equals("carregando")){
			this.setPosicaoTelaX(-1340);
			this.setPosicaoTelaY(-290);
		}
	}
	
	public void camera2(){
		if(this.controle.getJogador2().getPj().getEstado().equals("mov")){
				
			if(this.controle.getJogador2().getPj().getPosX()>TelaJogo.getLargura()/2 && this.getPosicaoTelaX()>(-1360))
				if(this.controle.getJogador2().getPj().getPosX()<this.LARGURA-TelaJogo.getLargura()/2)
				this.setPosicaoTelaX(-(this.controle.getJogador2().getPj().getPosX()-(TelaJogo.getLargura()/2))); 
			
			if(this.controle.getJogador2().getPj().getPosY()<480 && this.getPosicaoTelaY()<0)
				this.setPosicaoTelaY(this.getPosicaoTelaY()+10);
			if(this.controle.getJogador2().getPj().getPosY()>300 && this.getPosicaoTelaY()>(-290))
				this.setPosicaoTelaY(this.getPosicaoTelaY()-10);
		}
		
		if(this.getPosicaoTelaX()<0 && this.controle.getJogador2().getPj().getEstado().equals("carregando")){
			this.setPosicaoTelaX(0);
			this.setPosicaoTelaY(-290);
		}
	}
	
	public boolean colisao1(){
		int acrescimoSB=1;
		
		if(this.controle.getMulti().getJogadorAtual()==1){
			if(this.controle.getJogador().getPj().getPosX()>this.controle.getJogador().getPj().getPosInicX()+1700){
				for(int i=0; i<arvore_casa.getNumeroLinhas();i++){
					for(int j=0;j<arvore_casa.getNumeroColunas();j++){
						if(arvore_casa.getCamada()[i][j]>3 && this.controle.getJogador().getPj().colisao(this.arvore_casa.getAlvos()[i][j])){
							this.controle.getJogador().getPj().setEstado("explodir");
							if(!this.controle.getJogador().getPj().isSuperBomba())
							this.arvore_casa.setCamada(i,j,3);
							if(this.controle.getJogador().getPj().isSuperBomba()){
								this.arvore_casa.setCamada(i,j,3);
								this.arvore_casa.setCamada(i+1,j,3);
								this.arvore_casa.setCamada(i+1,j+1,3);
								this.arvore_casa.setCamada(i,j+1,3);
								this.arvore_casa.setCamada(i,j+2,3);
								this.arvore_casa.setCamada(i+2,j,3);
								this.arvore_casa.setCamada(i+2,j+2,3);
								this.arvore_casa.setCamada(i,j+3,3);
								this.arvore_casa.setCamada(i+3,j+3,3);
								this.arvore_casa.setCamada(i+3,j,3);
								acrescimoSB = 20;
							}
							if(this.controle.getJogador().getPj().getPosX()>=1888){
								if(this.controle.getJogador().getPj().getPosY()<520)
									this.controle.getJogador().setPontuacao(this.controle.getJogador().getPontuacao()+2*acrescimoSB);
								if(this.controle.getJogador().getPj().getPosY()>=520 && this.controle.getJogador().getPj().getPosY()<584)
									this.controle.getJogador().setPontuacao(this.controle.getJogador().getPontuacao()+4*acrescimoSB);
								if(this.controle.getJogador().getPj().getPosY()>=584 && this.controle.getJogador().getPj().getPosY()<628)
									this.controle.getJogador().setPontuacao(this.controle.getJogador().getPontuacao()+8*acrescimoSB);
								if(this.controle.getJogador().getPj().getPosY()>=628 && this.controle.getJogador().getPj().getPosY()<700)
									this.controle.getJogador().setPontuacao(this.controle.getJogador().getPontuacao()+10*acrescimoSB);
							}
							else
								this.controle.getJogador().setPontuacao(this.controle.getJogador().getPontuacao()+1*acrescimoSB);
							return true;
						}
					}
				}
			}
			
			if(this.controle.getJogador().getPj().getPosX()<this.controle.getJogador().getPj().getPosInicX()+1700 && this.controle.getJogador().getPj().getPosX()>800){
				for(int i=0; i<arvore_casa.getNumeroLinhas();i++){
					for(int j=0;j<arvore_casa.getNumeroColunas();j++){
						if(arvore_casa.getCamada()[i][j]>3 && this.controle.getJogador().getPj().colisao(this.arvore_casa.getAlvos()[i][j])){
							this.controle.getJogador().getPj().setEstado("explodir");
							
							if(!this.controle.getJogador().getPj().isSuperBomba()){
								this.arvore_casa.setCamada(i,j,3);
								}
								
								if(this.controle.getJogador().getPj().isSuperBomba()){
									this.arvore_casa.setCamada(i,j,3);
									this.arvore_casa.setCamada(i+1,j,3);
									this.arvore_casa.setCamada(i+1,j+1,3);
									this.arvore_casa.setCamada(i,j+1,3);
									this.arvore_casa.setCamada(i,j+2,3);
									this.arvore_casa.setCamada(i+2,j,3);
									this.arvore_casa.setCamada(i+2,j+2,3);
									this.arvore_casa.setCamada(i,j+3,3);
									this.arvore_casa.setCamada(i+3,j+3,3);
									this.arvore_casa.setCamada(i+3,j,3);
									acrescimoSB=10;
								}
							
							this.controle.getJogador().setPontuacao(this.controle.getJogador().getPontuacao()+1*acrescimoSB);
							
							return true;
						}
					
					}
				}
			}
		}
		return false;
	}
	
	public boolean colisao2(){
		int acrescimoSB=1;
		
		if(this.controle.getMulti().getJogadorAtual()==2){
			if(this.controle.getJogador2().getPj().getPosX()<=736){
				for(int i=0; i<arvore_casa.getNumeroLinhas();i++){
					for(int j=0;j<arvore_casa.getNumeroColunas();j++){
						if(arvore_casa.getCamada()[i][j]>3 && this.controle.getJogador2().getPj().colisao(this.arvore_casa.getAlvos()[i][j])){
							this.controle.getJogador2().getPj().setEstado("explodir");
							if(!this.controle.getJogador2().getPj().isSuperBomba()){
								this.arvore_casa.setCamada(i,j,3);
							}
								if(this.controle.getJogador2().getPj().isSuperBomba()){
									this.arvore_casa.setCamada(i,j,3);
									this.arvore_casa.setCamada(i+1,j,3);
									this.arvore_casa.setCamada(i+1,j+1,3);
									this.arvore_casa.setCamada(i,j+1,3);
									this.arvore_casa.setCamada(i,j+2,3);
									this.arvore_casa.setCamada(i+2,j,3);
									this.arvore_casa.setCamada(i+2,j+2,3);
									this.arvore_casa.setCamada(i,j+3,3);
									this.arvore_casa.setCamada(i+3,j+3,3);
									this.arvore_casa.setCamada(i+3,j,3);
									acrescimoSB = 20;
								}
							if(this.controle.getJogador2().getPj().getPosX()<=672){
								if(this.controle.getJogador2().getPj().getPosY()<520)
									this.controle.getJogador2().setPontuacao(this.controle.getJogador2().getPontuacao()+2*acrescimoSB);
								if(this.controle.getJogador2().getPj().getPosY()>=520 && this.controle.getJogador2().getPj().getPosY()<584)
									this.controle.getJogador2().setPontuacao(this.controle.getJogador2().getPontuacao()+4*acrescimoSB);
								if(this.controle.getJogador2().getPj().getPosY()>=584 && this.controle.getJogador2().getPj().getPosY()<628)
									this.controle.getJogador2().setPontuacao(this.controle.getJogador2().getPontuacao()+8*acrescimoSB);
								if(this.controle.getJogador2().getPj().getPosY()>=628 && this.controle.getJogador2().getPj().getPosY()<700)
									this.controle.getJogador2().setPontuacao(this.controle.getJogador2().getPontuacao()+10*acrescimoSB);
							}
							
							else
								this.controle.getJogador2().setPontuacao(this.controle.getJogador2().getPontuacao()+1*acrescimoSB);
							return true;
						}
					}
				}
			}
		}
		
		if(this.controle.getJogador2().getPj().getPosX()>800 && this.controle.getJogador2().getPj().getPosX()<1700){
			for(int i=0; i<arvore_casa.getNumeroLinhas();i++){
				for(int j=0;j<arvore_casa.getNumeroColunas();j++){
					if(arvore_casa.getCamada()[i][j]>3 && this.controle.getJogador2().getPj().colisao(this.arvore_casa.getAlvos()[i][j])){
						this.controle.getJogador2().getPj().setEstado("explodir");
						
						if(!this.controle.getJogador2().getPj().isSuperBomba()){
						this.arvore_casa.setCamada(i,j,3);
						}
						
						if(this.controle.getJogador2().getPj().isSuperBomba()){
							this.arvore_casa.setCamada(i,j,3);
							this.arvore_casa.setCamada(i+1,j,3);
							this.arvore_casa.setCamada(i+1,j+1,3);
							this.arvore_casa.setCamada(i,j+1,3);
							this.arvore_casa.setCamada(i,j+2,3);
							this.arvore_casa.setCamada(i+2,j,3);
							this.arvore_casa.setCamada(i+2,j+2,3);
							this.arvore_casa.setCamada(i,j+3,3);
							this.arvore_casa.setCamada(i+3,j+3,3);
							this.arvore_casa.setCamada(i+3,j,3);
							acrescimoSB = 10;
						}
						
						this.controle.getJogador2().setPontuacao(this.controle.getJogador2().getPontuacao()+1*acrescimoSB);
						
						
						return true;
					}
				
				}
			}
		}
		return false;
	}
}
