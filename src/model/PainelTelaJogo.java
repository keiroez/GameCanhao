package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import control.GameController;
import view.TelaJogo;

public class PainelTelaJogo extends AtualizaTela {
	private TileMap fundo;
	private TileMap base;
	private TileMap arvore_casa;
	private TileMap pontos;
	private int modoJogo = 1;
	
	GameController controle;
	
	public PainelTelaJogo(GameController controle) {
		super();
		this.controle=controle;
	}

	@Override
	public void init() {
		fundo = new TileMap("tileset.png", "fundo.txt",false,modoJogo);
		base = new TileMap("tileset.png", "base.txt",false,modoJogo);
		arvore_casa = new TileMap("tileset.png", "arvores_casas.txt",true,modoJogo);
		pontos = new TileMap("tileset.png", "pontos.txt", false, modoJogo);
	}
	

	@Override
	public void gameUpdate() {

			fundo.montarMapa();
			base.montarMapa();
			arvore_casa.montarMapa(this.controle);
			pontos.montarMapa();

		controle.getJogador().getPj().tiro();
		camera();
		colisao();
		this.controle.getJogador().getPj().explodir();
	}
	
	public void camera(){
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
			this.setPosicaoTelaX(0);
			this.setPosicaoTelaY(-290);
			this.controle.getJogador().getPj().setEstado("espera");
		}
	}
	
	public void movimentarCameraDir(Jogador jogador){
		if(jogador.getPj().getEstado().equals("espera")){
			if(this.getPosicaoTelaX()>(-1344))
				this.setPosicaoTelaX(this.getPosicaoTelaX()-32);
		}
	}
	public void movimentarCameraEsq(Jogador jogador){
		if(jogador.getPj().getEstado().equals("espera")){
			if(this.getPosicaoTelaX()<0)
				this.setPosicaoTelaX(this.getPosicaoTelaX()+32);
		}
	}
	
	public boolean colisao(){
		if(this.controle.getJogador().getPj().getPosX()>this.controle.getJogador().getPj().getPosInicX()+840)
		for(int i=0; i<arvore_casa.getNumeroLinhas();i++){
			for(int j=0;j<arvore_casa.getNumeroColunas();j++){
				if(arvore_casa.getCamada()[i][j]>3 && this.controle.getJogador().getPj().colisao(this.arvore_casa.getAlvos()[i][j])){
					this.controle.getJogador().getPj().setEstado("explodir");
					this.arvore_casa.setCamada(i,j,3);
					if(this.controle.getJogador().getPj().getPosX()>1760)
					this.controle.getLevel().setPontuacao(this.controle.getLevel().getPontuacao()+5);
					else
					this.controle.getLevel().setPontuacao(this.controle.getLevel().getPontuacao()+1);
					return true;
				}
			}
		}
		return false;
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
		g.drawImage(this.controle.getJogador().getPotencia().getCenas()[this.controle.getJogador().getPotencia().getCena()], 10, 780,null);
		g.drawString(Integer.toString(this.controle.getJogador().getPotenciaNumero()), 60, 850);
		g.drawString("POTÊNCIA       ", 120, 850);
		
		g.drawImage(pontos.getMapa(), 0, -this.getPosicaoTelaY()-290, null);
		
		for(int i = 0; i<this.controle.getJogador().getQtdBomba();i++ )
			g.drawImage(new ImageIcon("imagens/bomba2.png").getImage(), -this.getPosicaoTelaX()+30+(20*i), -this.getPosicaoTelaY()+580, null);
		
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.BOLD, 50));
		g.drawString("Pontos "+Integer.toString(this.controle.getJogador().getPontuacao()), -this.getPosicaoTelaX()+340, -this.getPosicaoTelaY()+650);
		
		if(this.controle.getLevel().isSingle()){
			if(this.controle.getLevel().getLevel()<=4){
				g.setColor(Color.white);
				g.setFont(new Font("Dialog", Font.BOLD, 50));
				g.drawString("Level "+Integer.toString(SinglePlayer.getLevel()), -this.getPosicaoTelaX()+120, -this.getPosicaoTelaY()+650);
				g.drawString(Integer.toString((this.controle.getLevel().getPontuacao()*100)/this.controle.getLevel().getPontuacaoLevel())+"% concluido", -this.getPosicaoTelaX()+720, -this.getPosicaoTelaY()+650);
			}
		
			if(this.controle.getLevel().getLevel()>4){
				g.setColor(Color.white);
				g.setFont(new Font("Dialog", Font.BOLD, 50));
				g.drawString("Fim de jogo", -this.getPosicaoTelaX()+400, -this.getPosicaoTelaY()+200);
				g.setFont(new Font("Dialog", Font.BOLD, 15));
				g.drawString("Pressione Esc para voltar ao menu", -this.getPosicaoTelaX()+400, -this.getPosicaoTelaY()+220);
			}
		}
	}

	public TileMap getArvore_casa() {
		return arvore_casa;
	}

	public void setArvore_casa(TileMap arvore_casa) {
		this.arvore_casa = arvore_casa;
	}
}
