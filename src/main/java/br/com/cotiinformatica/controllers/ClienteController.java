package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ClienteRequestDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")

public class ClienteController {

	@PostMapping
	public String post(@RequestBody ClienteRequestDto request) throws Exception {

		var cliente = new Cliente();

		cliente.setId(UUID.randomUUID());
		cliente.setNome(request.getNome());
		cliente.setEmail(request.getEmail());
		cliente.setTelefone(request.getTelefone());
		cliente.setDataHoraCadastro(request.getDataHoraCadastro());
		
		var clienteRepository = new ClienteRepository();
		clienteRepository.create(cliente);

		return "Cliente cadastrado com sucesso.";
	}

	@PutMapping("{id}")

	public String put(@PathVariable UUID id, @RequestBody ClienteRequestDto request) throws Exception {

		var clienteRepository = new ClienteRepository();
		var cliente = clienteRepository.getById(id);

		if (cliente != null) {

			cliente.setNome(request.getNome());
			cliente.setEmail(request.getEmail());
			cliente.setTelefone(request.getTelefone());
			cliente.setDataHoraCadastro(request.getDataHoraCadastro());

			clienteRepository.update(cliente);

			return "Cliente atualizado com sucesso.";
		} else {

			return "Cliente não encontrado. Verifique o ID informado.";
		}

	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) throws Exception {

		var clienteRepository = new ClienteRepository();
		var cliente = clienteRepository.getById(id);

		if (cliente != null) {

			clienteRepository.delete(id);

			return "Cliente excluído com sucesso.";

		} else {
			
			return "Cliente não encontrado. Verifique o ID informado.";
		}

	}

	@GetMapping
	public List<Cliente> get() throws Exception {

		var clienteRepository = new ClienteRepository();

		return clienteRepository.getAll();
	}
}
