package model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import control.GameController;


public class SinglePlayer extends Thread {
	private int[][] fase;
	private int linhaMatriz = 8, colunaMatriz = 17;
	private static int level;
	private GameController controle;
	private int pontuacao, pontuacaoLevel, pontuacaoTotal;
	private boolean isSingle = false;

	public SinglePlayer(int level, GameController controle){
		fase = carregarMatriz("fase1.txt");
		this.level = level;
		this.controle = controle;
		this.pontuacao = 0;
		this.pontuacaoLevel = 4;
		pontuacaoTotal = 0;
	}
	

	


	public void mudarFase(int level){
		this.level = level;
		if(this.level==2){
			this.pontuacaoTotal+=pontuacao;
			this.pontuacaoLevel=this.pontuacaoLevel*2;
			this.pontuacao=0;
			this.controle.getJogador().setQtdBomba((this.controle.getJogador().getQtdBomba()+2)*4);
			controle.getFase().setRunning(false);
			this.linhaMatriz = 8;
			this.colunaMatriz = 11;
			this.fase = carregarMatriz("fase2.txt");
			this.controle.getJogador().getPj().posLevel();
			this.controle.getFase().init();
			this.controle.getFase().setPosicaoTelaX(0);
			controle.getFase().setRunning(true);
		}
		if(this.level == 3){
			this.pontuacaoTotal+=pontuacao;
			this.pontuacaoLevel=this.pontuacaoLevel*2;
			this.pontuacao=0;
			this.controle.getJogador().setQtdBomba((this.controle.getJogador().getQtdBomba()+2)*4);
			controle.getFase().setRunning(false);
			this.linhaMatriz = 8;
			this.colunaMatriz = 6;
			this.pontuacaoLevel=this.pontuacaoLevel*2;
			this.pontuacao=0;
			this.fase = carregarMatriz("fase3.txt");
			this.controle.getJogador().getPj().posLevel();
			this.controle.getFase().init();
			this.controle.getFase().setPosicaoTelaX(0);
			controle.getFase().setRunning(true);
		}
		if(this.level == 4){
			this.pontuacaoTotal+=pontuacao;
			this.pontuacaoLevel=this.pontuacaoLevel*2;
			this.pontuacao=0;
			this.controle.getJogador().setQtdBomba((this.controle.getJogador().getQtdBomba()+2)*4);
			controle.getFase().setRunning(false);
			this.pontuacaoLevel=this.pontuacaoLevel*2;
			this.pontuacao=0;
			this.controle.getJogador().getPj().posLevel();
			this.controle.getFase().init();
			this.controle.getFase().setPosicaoTelaX(0);
			controle.getFase().setRunning(true);
		}
		
		if(this.level>4){ 
			RankingXml rk = new RankingXml("ranking.xml");
			if(this.controle.getJogador().getQtdBomba()>0)
				rk.escrever(this.pontuacaoTotal*this.controle.getJogador().getQtdBomba(), "J1");
			else
				rk.escrever(this.pontuacaoTotal, "J1");
			this.controle.getJogador().getPj().setEstado("fimdejogo");
		}
	}
	
	public void montarFase(TileMap mapa){
		for(int i=0; i<mapa.getNumeroLinhas();i++){
			for(int j=0;j<mapa.getNumeroColunas();j++){
				for(int a =0;a<linhaMatriz;a++){
					for(int b=0; b<colunaMatriz; b++){
						if(mapa.getCamada()[i][j]==fase[a][b] && fase[a][b]!=0){
							mapa.setCamada(i, j, 0);
						}
					}
				}
			}
		}
	}
	
	
	public int[][] carregarMatriz(String nomeMapa){
		int[][] matz = new int[linhaMatriz][colunaMatriz];
		
		InputStream input = getClass().getClassLoader().getResourceAsStream(nomeMapa);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		
		ArrayList<String> linhas = new ArrayList<>();
		String linha="";
		
		try {
			while((linha=br.readLine())!=null){
				linhas.add(linha);	
			}
				
			int coluna=0 ;
			
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

	@Override
	public void run() {
		while(true){
			if(this.pontuacao>=this.pontuacaoLevel && this.level<5){
				mudarFase(this.level+1);
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		SinglePlayer.level = level;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getPontuacaoLevel() {
		return pontuacaoLevel;
	}

	public void setPontuacaoLevel(int pontuacaoLevel) {
		this.pontuacaoLevel = pontuacaoLevel;
	}


	public boolean isSingle() {
		return isSingle;
	}

	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

}
