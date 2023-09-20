package br.com.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.dto.Agentes;
import br.com.app.exception.RegistroNaoEncontradoException;
import br.com.app.persistence.entities.AgenteEntity;
import br.com.app.persistence.entities.RegiaoEntity;
import br.com.app.persistence.repository.AgenteRepository;
import br.com.app.persistence.repository.RegiaoRepository;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@Service
public class AgenteService {

	private AgenteRepository agenteRepository;

	private RegiaoRepository regiaoRepository;

	private final MapperFacade mapper;

	@Autowired
	public AgenteService(AgenteRepository agenteRepository, RegiaoRepository regiaoRepository, MapperFacade mapper) {
		this.agenteRepository = agenteRepository;
		this.regiaoRepository = regiaoRepository;
		this.mapper = mapper;
	}

	@SuppressWarnings("unchecked")
	public void salvarAgentes(MultipartFile file) throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Agentes.class);

		Unmarshaller unmarshaller = context.createUnmarshaller();

		Agentes agentesXml = new Agentes();

		try (InputStream inputStream = file.getInputStream()) {
			agentesXml = (Agentes) unmarshaller.unmarshal(inputStream);

		} catch (IOException e) {
			log.warn(e.getMessage());
		}

		this.imprimirCodigoAgenteNoConsole(agentesXml, file.getOriginalFilename());

		List<AgenteEntity> agentesNoXml = new ArrayList<>();
		agentesXml.getAgentes().forEach(a -> {
			AgenteEntity map = mapper.map(a, AgenteEntity.class);
			agentesNoXml.add(map);
		});
		
		List<AgenteEntity> agentesJaCadastrados = agenteRepository.findAllByCodigoIn(agentesNoXml.stream()
		        .map(AgenteEntity::getCodigo)
		        .collect(Collectors.toList()));		
		
		List<AgenteEntity> agentesNovos = agentesNoXml.stream()
		        .filter(novoAgente -> !agentesJaCadastrados.stream()
		        		.anyMatch(agenteExistente -> agenteExistente.getCodigo() == novoAgente.getCodigo()))
		        .collect(Collectors.toList());

		agenteRepository.saveAll(agentesNovos);
	}

	private void imprimirCodigoAgenteNoConsole(Agentes xml, String xmlName) throws JAXBException {
		log.info("### agentes recebidos no xml {}", xmlName);
		xml.getAgentes().forEach(a -> System.out.println(String.format("### codigo do agente: %s", a.getCodigo())));
	}

	public List<RegiaoEntity> pesquisarAgentesPorRegiao(String sigla) {
		List<RegiaoEntity> regiaoPorSigla = regiaoRepository.findBySigla(sigla);
		
		if(Objects.isNull(regiaoPorSigla) || regiaoPorSigla.isEmpty()) {
			throw new RegistroNaoEncontradoException(
					String.format("Nao foram encontrados registros para sigla %s", sigla));
		}		
		return regiaoPorSigla;		
	}
}
