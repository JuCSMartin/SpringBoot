package br.com.generation.atividade02spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividade02")
public class Atividade02Controller {

	@GetMapping
	public String atividade02() {
		return "Olá! Meu principal objetivo de aprendizagem para a semana é compreender o funcionamento do framework SpringBoot para conseguir utilizá-lo de forma otimizada.";
	}
}
