package space.indietech.calculadora.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import space.indietech.calculadora.business.CalculadoraBo;
import space.indietech.calculadora.business.CalculadoraService;
import space.indietech.calculadora.conversor.CalculadoraConversor;

@RestController
@RequestMapping(value = "/calculadora")
public class CalculadoraRest {

	private CalculadoraService calculadoraService;

	@Autowired
	public CalculadoraRest(CalculadoraService calculadoraService) {
		this.calculadoraService = calculadoraService;
	}

	@PutMapping
	public ResponseEntity<CalculadoraDTODevolve> calcular(@RequestBody CalculadoraDTORecebe cDTOrecebe) {
		try {
			CalculadoraBo bo = CalculadoraConversor.convertDTORecebetoBo(cDTOrecebe);
			bo = calculadoraService.calcular(bo);
			CalculadoraDTODevolve produtoDTODevolve = CalculadoraConversor.converterBoToDTODevolve(bo);
			return ResponseEntity.ok(produtoDTODevolve);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

}
