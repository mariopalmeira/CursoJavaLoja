package com.mariopalmeira.cursojava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mariopalmeira.cursojava.dao.ClienteDAO;
import com.mariopalmeira.cursojava.dao.EnderecoDAO;
import com.mariopalmeira.cursojava.domain.Cidade;
import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.domain.Endereco;
import com.mariopalmeira.cursojava.domain.enums.TipoCliente;
import com.mariopalmeira.cursojava.dto.ClienteDTO;
import com.mariopalmeira.cursojava.dto.ClienteInsereDTO;
import com.mariopalmeira.cursojava.services.exception.DataIntegrityException;
import com.mariopalmeira.cursojava.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;
	
	@Autowired
	private EnderecoDAO enderecoDAO;
	
	public Optional<Cliente> buscaPorId(Integer id){
		Optional<Cliente> cliente = clienteDAO.findById(id);
		return Optional.of(cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado.")));
	}
	
	public List<Cliente> buscarTodos(){
		List<Cliente> clientes = clienteDAO.findAll();
		return clientes;
	}
	
	@Transactional
	public Cliente incluir(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteDAO.save(cliente);
		enderecoDAO.saveAll(cliente.getEndereco());
		return cliente;
	}
	
	public Cliente editar(ClienteDTO clienteDto) {
		Optional<Cliente> clienteOptional = buscaPorId(clienteDto.getId());
		Cliente cliente = clienteDtoEmCliente(clienteDto, clienteOptional);
		return clienteDAO.save(cliente);
	}
	
	public void excluir(Integer id) {
		buscaPorId(id);
		try {
			clienteDAO.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Esse cliente tem pedidos associados.");
		}
	}
	
	public Page<Cliente> paginar(Integer pagina, Integer porPagina, String ordem, String direcao){
		PageRequest paginas = PageRequest.of(pagina, porPagina, Direction.valueOf(direcao), ordem);
		return clienteDAO.findAll(paginas);
	}
	
	public Cliente clienteDtoEmCliente(ClienteDTO clienteDto, Optional<Cliente> clienteOptional) {
		Cliente cliente = null;
		//Pegando um objeto do optional 
		if(clienteOptional.isPresent()) {
			cliente = clienteOptional.get();
			cliente.setNome(clienteDto.getNome());
			cliente.setEmail(clienteDto.getEmail());
		}
		return cliente;
	}
	
	public Cliente clienteInsereDTOEmCliente(ClienteInsereDTO clienteInsereDTO) {
		Cliente cliente = new Cliente(null, clienteInsereDTO.getNome(), clienteInsereDTO.getEmail(), clienteInsereDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteInsereDTO.getTipo()));
		Cidade cidade = new Cidade(clienteInsereDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, clienteInsereDTO.getLogradouro(), clienteInsereDTO.getNumero(), clienteInsereDTO.getComplemento(), clienteInsereDTO.getBairro(), clienteInsereDTO.getCep(), cliente, cidade);
		cliente.getEndereco().add(endereco);
		cliente.getTelefone().add(clienteInsereDTO.getTelefoneUm());
		if(clienteInsereDTO.getTelefoneDois() != null) {
			cliente.getTelefone().add(clienteInsereDTO.getTelefoneDois());	
		}
		if(clienteInsereDTO.getTelefoneTres() != null) {
			cliente.getTelefone().add(clienteInsereDTO.getTelefoneTres());
		}
		return cliente;
	}
	
}
