package br.com.app.persistence.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agente")
public class AgenteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo_agente", unique = true)
	private int codigo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agente")
//    private List<RegiaoEntity> regiao;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	    name = "agente_regiao", // Nome da tabela intermediária
	    joinColumns = @JoinColumn(name = "agente_id"),
	    inverseJoinColumns = @JoinColumn(name = "regiao_id")
	)
	private List<RegiaoEntity> regiao = new ArrayList<>();
}