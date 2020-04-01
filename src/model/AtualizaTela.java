package model;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AtualizaTela extends JPanel implements Runnable {
	

	private static final long serialVersionUID = 1L;

	public static int  LARGURA= 2560;
	public static int  ALTURA = 960;
	private Thread thread;
	private boolean running;
	private boolean runningFase=true;
	private BufferedImage image;
	protected Graphics2D g;
	private int FPS = 30;
	private int posicaoTelaX=0;
	private int posicaoTelaY=-290;

	@SuppressWarnings("unused")
	private Double averageFPS;

	public AtualizaTela() {
		super();
		setPreferredSize(new Dimension(LARGURA,ALTURA)) ;
		setSize(LARGURA,ALTURA);
		init();
	}
	public abstract void init();


	public void addNotify() {
		super.addNotify();

		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public void run() {
		running = true;
		image = new BufferedImage(LARGURA,ALTURA, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;

		int frameCount = 0;
		int maxFrameCount = 30;

		long tragetTime = 1000 / FPS;
		
		while(runningFase){
		while (running) {
			startTime = System.nanoTime();
			
			gameUpdate();
			gameRender();
			gameDraw();
			

			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = tragetTime - URDTimeMillis;
			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
			}

			totalTime += System.nanoTime() - startTime;
			frameCount++;

			if (frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}

		gameDraw();
		}
	}

	public abstract void gameUpdate();
	
	public abstract void gameRender();

	private void gameDraw() {
		if(this.runningFase){
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(image, posicaoTelaX, posicaoTelaY, null);
		g2.dispose();
		}
	}
	
	public int getPosicaoTelaX() {
		return posicaoTelaX;
	}
	public void setPosicaoTelaX(int posicaoTela) {
		this.posicaoTelaX = posicaoTela;
	}
	public int getPosicaoTelaY() {
		return posicaoTelaY;
	}
	public void setPosicaoTelaY(int posicaoTelaY) {
		this.posicaoTelaY = posicaoTelaY;
	}
	public boolean isRunningFase() {
		return runningFase;
	}
	public void setRunningFase(boolean runningFase) {
		this.runningFase = runningFase;
	}
}
