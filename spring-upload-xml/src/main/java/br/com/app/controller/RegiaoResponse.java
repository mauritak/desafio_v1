package br.com.app.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.app.persistence.entities.CompraEntity;
import br.com.app.persistence.entities.GeracaoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "codigoRegiao", "siglaRegiao", "geracao", "compra" })
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegiaoResponse {

	@JsonProperty("codigo_regiao")
	private Long codigoRegiao;

	@JsonProperty("sigla_regiao")
	private String siglaRegiao;

	private List<GeracaoEntity> geracao;

	private List<CompraEntity> compra;
}
