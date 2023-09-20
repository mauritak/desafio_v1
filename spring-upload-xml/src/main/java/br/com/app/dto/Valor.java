package br.com.app.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Valor {

	private List<Double> valor;

	public List<Double> getValor() {
		return valor;
	}

	public void setValor(List<Double> valor) {
		this.valor = valor;
	}

}