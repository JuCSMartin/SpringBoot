package br.com.generation.atividade01spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividade01")
public class Atvidade01Controller {

	@GetMapping
	public String atividade01() {
		
		return "Olá! Para realizar essa atividade precisei utilizar a habilidade de persistência e a mentalidade de atenção aos detalhes.";
	}
}
