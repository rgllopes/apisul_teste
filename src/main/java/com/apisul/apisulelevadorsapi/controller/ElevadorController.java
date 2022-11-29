package com.apisul.apisulelevadorsapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apisul.apisulelevadorsapi.service.Interfaces.IElevadorService;

@RestController
@RequestMapping(value = "/api-v1")
public class ElevadorController {

	@Autowired
	private IElevadorService iElevadorService;

	@GetMapping("/andar-menos-utilizado")
	public ResponseEntity<List<Integer>> andarMenosUtilizado() throws Exception {
		try {
			List<Integer> result = iElevadorService.andarMenosUtilizado();

			return ResponseEntity.ok(result) ;	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	//############################################################################################
	@GetMapping(value = "/elevador-mais-utilizado")
	public ResponseEntity<List<Character>> elevadorMaisFrequentado() {
		try {
			List<Character> result = iElevadorService.elevadorMaisFrequentado();

			return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}		
	}
	//############################################################################################
	@GetMapping("/periodo-maior-fluxo-elevador-mais-frequentado")
	public ResponseEntity<List<Character>> periodoMaiorFluxoElevadorMaisFrequentado() {
		try {
			List<Character> result = iElevadorService.periodoMaiorFluxoElevadorMaisFrequentado();

			return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}		
	}
	//############################################################################################
	@GetMapping("/elevador-menos-frequentado")
	public ResponseEntity<List<Character>> elevadorMenosFrequentado() {
		try {
			List<Character> result = iElevadorService.elevadorMenosFrequentado();

			return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	} 
	//############################################################################################
	@GetMapping("/periodo-menor-fluxo-elevador-menos-frequentado")
	public ResponseEntity<List<Character>> periodoMenorFluxoElevadorMenosFrequentado() {
		try {
			List<Character> result = iElevadorService.periodoMenorFluxoElevadorMenosFrequentado();

		return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	} 
	//############################################################################################
	@GetMapping("/periodo-maior-utilizacao-conjunto-elevadores")
	public ResponseEntity<List<Character>> periodoMaiorUtilizacaoConjuntoElevadores() {
		try {
			List<Character> result = iElevadorService.periodoMaiorUtilizacaoConjuntoElevadores();

		return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	//############################################################################################
	@GetMapping("/percentual-de-uso-elevador-a")
	public ResponseEntity<Float> percentualDeUsoElevador() {
		try {
			Float result = iElevadorService.percentualDeUsoElevadorA();

		return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	//############################################################################################
	@GetMapping("/percentual-de-uso-elevador-b")
	public ResponseEntity<Float> percentualDeUsoElevadorB() {
		try {
			Float result = iElevadorService.percentualDeUsoElevadorB();

		return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	//############################################################################################
	@GetMapping("/percentual-de-uso-elevador-c")
	public ResponseEntity<Float> percentualDeUsoElevadorC() {
		try {
			Float result = iElevadorService.percentualDeUsoElevadorC();

		return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	//############################################################################################
	@GetMapping("/percentual-de-uso-elevador-d")
	public ResponseEntity<Float> percentualDeUsoElevadorD() {
		try {
			Float result = iElevadorService.percentualDeUsoElevadorD();

		return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	//############################################################################################
	@GetMapping("/percentual-de-uso-elevador-e")
	public ResponseEntity<Float> percentualDeUsoElevadorE() {
		try {
			Float result = iElevadorService.percentualDeUsoElevadorE();

		return ResponseEntity.ok(result);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	//############################################################################################
}
