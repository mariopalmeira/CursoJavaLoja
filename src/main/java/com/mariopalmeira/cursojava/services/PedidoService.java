package com.mariopalmeira.cursojava.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.ItemPedidoDAO;
import com.mariopalmeira.cursojava.dao.PagamentoDAO;
import com.mariopalmeira.cursojava.dao.PedidoDAO;
import com.mariopalmeira.cursojava.dao.ProdutoDAO;
import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.domain.ItemPedido;
import com.mariopalmeira.cursojava.domain.PagamentoBoleto;
import com.mariopalmeira.cursojava.domain.Pedido;
import com.mariopalmeira.cursojava.domain.Produto;
import com.mariopalmeira.cursojava.domain.enums.EstadoPagamento;
import com.mariopalmeira.cursojava.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;
	
	@Autowired
	private PagamentoDAO pagamentoDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private ItemPedidoDAO itemPedidoDAO;
	
	@Autowired
	private ClienteService clienteService;
	
	public Optional<Pedido> buscaPedidoPorId(Integer id){
		Optional<Pedido> pedido = pedidoDAO.findById(id);
		return Optional.of(pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado")));
	}
	
	public Pedido incluir(Pedido pedido) {
		//Garantir que realmente vai incluir um novo pedido
		pedido.setId(null);
		Calendar calendario = Calendar.getInstance();
		Date agora = calendario.getTime();
		pedido.setInstante(agora);

		Optional<Cliente> clienteOptional = clienteService.buscaPorId(pedido.getCliente().getId());
		if(clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			pedido.setCliente(cliente);
		}

		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagamentoBoleto = (PagamentoBoleto) pedido.getPagamento();
			Calendar data = Calendar.getInstance();
			//Inicia a data como sendo o momento que o pedido é feito
			data.setTime(pedido.getInstante());
			//Sabendo que deve contar em dias da semana, adiciona 7 dias a data inicial
			data.add(Calendar.DAY_OF_MONTH, 7);
			//Termina por setar a data de vencimento pegando um objeto tipo Date do data
			pagamentoBoleto.setDataVencimento(data.getTime());
		}
		pedido = pedidoDAO.save(pedido);
		pagamentoDAO.save(pedido.getPagamento());
		
		for(ItemPedido itemPedido : pedido.getItemPedido()) {
			itemPedido.setDesconto(0.0);
			
			Optional<Produto> produtoOptional = produtoDAO.findById(itemPedido.getProduto().getId());
			if(produtoOptional.isPresent()) {
				Produto produto = produtoOptional.get();
				itemPedido.setProduto(produto);
				itemPedido.setPreco(produto.getPreco());
			}
			itemPedido.setPedido(pedido);
		}
		itemPedidoDAO.saveAll(pedido.getItemPedido());
		System.out.println(pedido);
		return pedido;
	}
}
