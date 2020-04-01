package view;

import java.awt.Font;
import java.awt.ScrollPane;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import model.RankingXml;

public class TelaRanking extends JFrame{

	JPanel painel;
	JTextArea ranking;
	JScrollPane scrol;
	
	public TelaRanking(String xml) {
		
		ranking = new JTextArea();
		ranking.setEditable(false);
		ranking.setSize(100,100);
		ranking.setFont(new Font("", Font.BOLD, 25));
		
		
		scrol = new JScrollPane(ranking, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		setSize(200,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		RankingXml rk = new RankingXml(xml);
		int[] arr;
		arr=rk.ler();
		Arrays.sort(arr);
		for(int i=rk.ler().length-1; i>0;i--){
			ranking.setText(ranking.getText()+arr[i]+"\n");
		}
		add(scrol);
		setVisible(true);
		

			
		
	}

}
