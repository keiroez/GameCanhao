package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class RankingXml {
	
	private Element root = new Element("ranking");
	private SAXBuilder builder = new SAXBuilder();
	private File arquivo;
	private String caminhoXml;
	
	public RankingXml(String xml) {
		caminhoXml = xml;
		this.arquivo = new File(xml);
	}

	
	public void escrever(long pontuacao, String nome){
		Element jogador = new Element("jogador");
		jogador.setAttribute("nome", nome);
		jogador.setText(Long.toString(pontuacao));
		
		XMLOutputter saida = new XMLOutputter();
		
		Document doc;
		
		try {
			doc = (Document) builder.build(arquivo);
			
			doc.getRootElement().addContent(jogador);
			
			saida.output(doc, new FileWriter(this.caminhoXml));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	
	public void ordenar(int[] ar){
		int[] numeros = ar;
	}
	
	public int[] ler(){
		int[] array = new int[0];
		String records = "";
		StringBuilder sb =new StringBuilder();
		
		Document doc;
		
		try {
			doc = (Document) builder.build(arquivo);
			root = doc.getRootElement();
			List list = root.getChildren("jogador");
			
			array = new int [list.size()];
			
			for(int i=0;i<list.size();i++){
				Element no = (Element) list.get(i);
				array[i]=Integer.parseInt(no.getText());
				
				//sb.append(no.getAttributeValue("nome")+" "+no.getText()+" "+no.getAttributeValue("modo")+"\n");
			}
			
		} catch (JDOMException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		records = sb.toString();
		return array;
	}
}
