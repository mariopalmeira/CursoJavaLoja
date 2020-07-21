package com.mariopalmeira.cursojava.resources;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mariopalmeira.cursojava.domain.Pedido;
import com.mariopalmeira.cursojava.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscaPedidoPorId(@PathVariable Integer id){
		 Optional<Pedido> pedido = pedidoService.buscaPedidoPorId(id);
		 return ResponseEntity.ok().body(pedido);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> incluir(@Valid @RequestBody Pedido pedido){
		pedido = pedidoService.incluir(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/cliente", method=RequestMethod.GET)
	public ResponseEntity<?> pedidosPorCliente(@RequestParam(name="pagina", defaultValue="0") Integer pagina,
			@RequestParam(name="porPagina", defaultValue="10") Integer porPagina,
			@RequestParam(name="ordem", defaultValue="id") String ordem,
			@RequestParam(name="direcao", defaultValue="ASC") String direcao){
		Page<Pedido> pedidos = pedidoService.paginarPorCliente(pagina, porPagina, ordem, direcao);
		return ResponseEntity.ok().body(pedidos);
	}
}
