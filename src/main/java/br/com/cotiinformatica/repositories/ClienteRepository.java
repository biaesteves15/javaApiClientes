package br.com.cotiinformatica.repositories;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ClienteRepository {

	public void create(Cliente cliente) throws Exception {

		var connection = ConnectionFactory.getConnection();

		var statement = connection
				.prepareStatement("INSERT INTO cliente(id, nome, email, telefone, dataHoraCadastro) VALUES(?,?,?,?,?)");
		statement.setString(1, cliente.getId().toString());
		statement.setString(2, cliente.getNome());
		statement.setString(3, cliente.getEmail());
		statement.setString(4, cliente.getTelefone());
		statement.setTimestamp(5, Timestamp.valueOf(cliente.getDataHoraCadastro()));
		statement.execute();

		connection.close();
	}

	public void update(Cliente cliente) throws Exception {

		var connection = ConnectionFactory.getConnection();

		var statement = connection.prepareStatement("UPDATE cliente SET nome=?, email=?, telefone=?, dataHoraCadastro=? WHERE id=?");
		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getTelefone());
		statement.setTimestamp(4, Timestamp.valueOf(cliente.getDataHoraCadastro()));
		statement.setString(5, cliente.getId().toString());
		statement.execute();

		connection.close();
	}

	public void delete(UUID id) throws Exception {

		var connection = ConnectionFactory.getConnection();

		var statement = connection.prepareStatement("DELETE FROM cliente WHERE id=?");
		statement.setString(1, id.toString());
		statement.execute();

		connection.close();
	}

	public List<Cliente> getAll() throws Exception {

		var connection = ConnectionFactory.getConnection();

		var statement = connection.prepareStatement("SELECT id, nome, email, telefone, dataHoraCadastro FROM cliente ORDER BY nome");
		var resultSet = statement.executeQuery();

		var lista = new ArrayList<Cliente>();

		while (resultSet.next()) {

			var cliente = new Cliente();

			cliente.setId(UUID.fromString(resultSet.getString("id")));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setTelefone(resultSet.getString("telefone"));
			cliente.setDataHoraCadastro(resultSet.getTimestamp("dataHoraCadastro").toLocalDateTime());

			lista.add(cliente);
		}
		connection.close();

		return lista;

	}

	public Cliente getById(UUID id) throws Exception {

		var connection = ConnectionFactory.getConnection();

		var statement = connection.prepareStatement("SELECT id, nome, email, telefone, dataHoraCadastro FROM cliente WHERE id=?");

		statement.setString(1, id.toString());

		var resultSet = statement.executeQuery();
		Cliente cliente = null;

		if (resultSet.next()) {
			cliente = new Cliente();
			cliente.setId(UUID.fromString(resultSet.getString("id")));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setTelefone(resultSet.getString("telefone"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setDataHoraCadastro(resultSet.getTimestamp("dataHoraCadastro").toLocalDateTime());
		}
		connection.close();

		return cliente;
	}
}
