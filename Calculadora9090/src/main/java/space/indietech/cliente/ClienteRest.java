package space.indietech.cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteRest {

	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteRest(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> clientes = new ArrayList<>();
		for(Cliente cliente : clienteRepository.findAll()){
			clientes.add(cliente);
		}
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{cpfCnpj}")
	public ResponseEntity<Cliente> get(@PathVariable("cpfCnpj") String cpfCnpj) {
		Cliente cliente = clienteRepository.findOne(cpfCnpj);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<Object> savePF(@PathVariable("cpf") String cpf, @RequestBody Cliente cliente) {
		cliente.setCpfCnpj(cpf);

		boolean exists = clienteRepository.exists(cpf);

		cliente = clienteRepository.save(cliente);

		if (!exists) {
			return new ResponseEntity<>(cliente, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@DeleteMapping("/{cpfCnpj}")
	public ResponseEntity<Object> delete(@PathVariable("cpfCnpj") String cpfCnpj) {
		boolean exists = clienteRepository.exists(cpfCnpj);
		if (exists) {
			clienteRepository.delete(cpfCnpj);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
