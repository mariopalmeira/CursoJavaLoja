package com.mariopalmeira.cursojava.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.dto.ClienteDTO;
import com.mariopalmeira.cursojava.dto.ClienteInsereDTO;
import com.mariopalmeira.cursojava.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Optional<Cliente>> buscaPorId(@PathVariable Integer id){
		Optional<Cliente> cliente = clienteService.buscaPorId(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> buscarTodos(){
		List<Cliente> clientes = clienteService.buscarTodos();
		List<ClienteDTO> lista = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody ClienteInsereDTO clienteInsereDTO){
		Cliente clienteObjeto = clienteService.clienteInsereDTOEmCliente(clienteInsereDTO);
		Cliente cliente = clienteService.incluir(clienteObjeto);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> editar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDto){
		clienteDto.setId(id);
		clienteService.editar(clienteDto);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		clienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/pagina", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> paginar(
			@RequestParam(name="pagina", defaultValue="0")Integer pagina, 
			@RequestParam(name="porPagina", defaultValue="10")Integer porPagina, 
			@RequestParam(name="ordem", defaultValue="id")String ordem, 
			@RequestParam(name="direcao", defaultValue="ASC")String direcao){
		Page<Cliente> clientes = clienteService.paginar(pagina, porPagina, ordem, direcao);
		Page<ClienteDTO> paginas = clientes.map(cliente -> new ClienteDTO(cliente));
		return ResponseEntity.ok().body(paginas);
	}
	
	@RequestMapping(value="imagem", method=RequestMethod.POST)
	public ResponseEntity<Void> enviarImagem(@RequestParam(name="arquivo") MultipartFile arquivo) throws IOException{
		URI uri = clienteService.enviarImagem(arquivo);
		return ResponseEntity.created(uri).build();
	}
}
