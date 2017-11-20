package space.indietech.estacionamento;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vagas")
public class EstacionamentoRest {

	private EstacionamentoService estacionamentoService = new EstacionamentoService();

	@GetMapping
	public ResponseEntity<Vaga[][]> getAll() {
		return ResponseEntity.ok(estacionamentoService.getVagas());
	}

	@GetMapping("/{andar}")
	public ResponseEntity<Vaga[]> getByAndar(@PathVariable("andar") int andar) {
		try {
			return ResponseEntity.ok(estacionamentoService.getVagasDoAndar(andar));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/livres")
	public ResponseEntity<List<Vaga>> getByStatusLivre() {
		return ResponseEntity.ok(estacionamentoService.getVagasLivres());

	}

	@GetMapping("/ocupadas")
	public ResponseEntity<List<Vaga>> getByStatusOcupada() {
		return ResponseEntity.ok(estacionamentoService.getVagasOcupadas());

	}

	@PutMapping("/{andar}/{numero}/{status}")
	public ResponseEntity<Vaga> alteraStatus(@PathVariable("andar") int andar, @PathVariable("numero") int numero,
			@PathVariable("status") String status) {
		try {
			estacionamentoService.setStatus(andar, numero, status);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
