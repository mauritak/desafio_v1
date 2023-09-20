package br.com.app.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Regiao {

	private String sigla;

	private List<Valor> geracao;

	private List<Valor> compra;

	@XmlAttribute
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@XmlElement(name = "geracao")
	public List<Valor> getGeracao() {
		return geracao;
	}

	public void setGeracao(List<Valor> geracao) {
		this.geracao = geracao;
	}

	@XmlElement(name = "compra")
	public List<Valor> getCompra() {
		return compra;
	}

	public void setCompra(List<Valor> compra) {
		this.compra = compra;
	}

}
