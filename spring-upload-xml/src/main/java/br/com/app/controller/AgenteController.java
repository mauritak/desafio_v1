package br.com.app.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.app.persistence.entities.RegiaoEntity;
import br.com.app.service.AgenteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/agente")
public class AgenteController {

	@Autowired
	private AgenteService service;

	@CrossOrigin
	@PostMapping
	@ResponseBody
	public ResponseEntity<AgenteResponse> uploadXmlFile(@RequestParam("file") MultipartFile file) {
		
		try {
			if (Objects.isNull(file) || file.isEmpty()) {
				return ResponseEntity.badRequest()
						.body(AgenteResponse.builder().message("O arquivo xml esta vazio").build());
			}		
			
			log.info("### arquivo {} recebido ", file.getOriginalFilename());

			if (!file.getContentType().equals("application/xml")) {
				return ResponseEntity.badRequest()
						.body(AgenteResponse.builder().message("O arquivo recebido por parametro nao e xml").build());
			}

			service.salvarAgentes(file);

			return ResponseEntity.status(HttpStatus.OK).body(AgenteResponse.builder()
					.message(String.format("Arquivo XML %s processado com sucesso!", file.getOriginalFilename())).build());

		} catch (Exception e) {
			log.error(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(AgenteResponse.builder()
							.message(String.format("Ocorreu um erro durante o processamento do arquivo %s. \n Verifique se o arquivo informado esta correto e tente novamente", file.getOriginalFilename()))
							.build());
		}
	}

}
