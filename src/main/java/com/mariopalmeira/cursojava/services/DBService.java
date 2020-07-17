package com.mariopalmeira.cursojava.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.CategoriaDAO;
import com.mariopalmeira.cursojava.dao.CidadeDAO;
import com.mariopalmeira.cursojava.dao.ClienteDAO;
import com.mariopalmeira.cursojava.dao.EnderecoDAO;
import com.mariopalmeira.cursojava.dao.EstadoDAO;
import com.mariopalmeira.cursojava.dao.ItemPedidoDAO;
import com.mariopalmeira.cursojava.dao.PagamentoDAO;
import com.mariopalmeira.cursojava.dao.PedidoDAO;
import com.mariopalmeira.cursojava.dao.ProdutoDAO;
import com.mariopalmeira.cursojava.domain.Categoria;
import com.mariopalmeira.cursojava.domain.Cidade;
import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.domain.Endereco;
import com.mariopalmeira.cursojava.domain.Estado;
import com.mariopalmeira.cursojava.domain.ItemPedido;
import com.mariopalmeira.cursojava.domain.Pagamento;
import com.mariopalmeira.cursojava.domain.PagamentoBoleto;
import com.mariopalmeira.cursojava.domain.PagamentoCartao;
import com.mariopalmeira.cursojava.domain.Pedido;
import com.mariopalmeira.cursojava.domain.Produto;
import com.mariopalmeira.cursojava.domain.enums.EstadoPagamento;
import com.mariopalmeira.cursojava.domain.enums.Perfil;
import com.mariopalmeira.cursojava.domain.enums.TipoCliente;

@Service
public class DBService {

	@Autowired
	private CategoriaDAO categoriaDAO;
	@Autowired
	private ProdutoDAO produtoDAO;
	@Autowired
	private EstadoDAO estadoDAO;
	@Autowired
	private CidadeDAO cidadeDAO;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired 
	private EnderecoDAO enderecoDAO;
	@Autowired
	private PedidoDAO pedidoDAO;
	@Autowired
	private PagamentoDAO pagamentoDAO;
	@Autowired
	private ItemPedidoDAO itemPedidoDAO;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void instantiateTestDatabase() throws ParseException {
		Categoria categoriaUm = new Categoria(null, "OTTO");
		Categoria categoriaDois = new Categoria(null, "DIESEL");
		Categoria categoriaTres = new Categoria(null, "FUNILARIA");
		Categoria categoriaQuatro = new Categoria(null, "ELÉTRICA");
		Categoria categoriaCinco = new Categoria(null, "PNEUS");
		Categoria categoriaSeis = new Categoria(null, "RODAS");
		Categoria categoriaSete = new Categoria(null, "ACESSÓRIOS");
		Categoria categoriaOito = new Categoria(null, "PEÇAS ORIGINAIS");
		Categoria categoriaNove = new Categoria(null, "PEÇAS PARALELAS");
		Categoria categoriaDez = new Categoria(null, "PEÇAS USADAS");
		Categoria categoriaOnze = new Categoria(null, "FLUÍDOS");
		Categoria categoriaDoze = new Categoria(null, "SUSPENSÃO");
		
		Produto produtoUm = new Produto(null, "Jogo Vela de ignição" , 350.00);//1
		Produto produtoDois = new Produto(null, "Rolamento de roda" , 150.00);//1,2,9
		Produto produtoTres = new Produto(null, "Jogo Cabo de vela" , 230.00);//1
		Produto produtoQuatro = new Produto(null, "Jogo Parafuso de roda" , 150.00);//1,6,9
		Produto produtoCinco = new Produto(null, "Filtro de Combustível BMW" , 500.00);//1,8,11
		Produto produtoSeis = new Produto(null, "Kit Limpador de Parabrisa" , 235.00);//1,2,9
		Produto produtoSete = new Produto(null, "Parafuso Especial de Roda" , 154.00);//1,2,9,6
		Produto produtoOito = new Produto(null, "Sensor de Rotação VHC" , 362.00);//1,8
		Produto produtoNove = new Produto(null, "Kit Reparo Freio Traseiro" , 402.00);//1,9
		Produto produtoDez = new Produto(null, "Litro Liquido de Arrefecimento" , 115.00);//1,2,11
		Produto produtoOnze = new Produto(null, "Cubo de Roda Audi" , 980.00);//1,8
		Produto produtoDoze = new Produto(null, "Bomba Ar Condicionado" , 2115.00);//1,7,10
		Produto produtoTreze = new Produto(null, "Kit Mangueiras Audi" , 1441.00);//1,8,11
		
		//Relacionamento de produtos com a categoria
		categoriaUm.getProduto().addAll(Arrays.asList(produtoUm,produtoDois,produtoTres, produtoQuatro, produtoCinco, produtoSeis, produtoSete, produtoOito, produtoNove, produtoDez, produtoOnze, produtoDoze, produtoTreze));
		categoriaDois.getProduto().addAll(Arrays.asList(produtoDois, produtoSeis, produtoSete, produtoDez));
		categoriaTres.getProduto().addAll(Arrays.asList());
		categoriaQuatro.getProduto().addAll(Arrays.asList());
		categoriaCinco.getProduto().addAll(Arrays.asList());
		categoriaSeis.getProduto().addAll(Arrays.asList(produtoQuatro, produtoSete));
		categoriaSete.getProduto().addAll(Arrays.asList(produtoDoze));
		categoriaOito.getProduto().addAll(Arrays.asList(produtoCinco, produtoOito, produtoOnze, produtoTreze));
		categoriaNove.getProduto().addAll(Arrays.asList(produtoDois, produtoQuatro, produtoSeis, produtoSete, produtoNove));
		categoriaDez.getProduto().addAll(Arrays.asList(produtoDoze));
		categoriaOnze.getProduto().addAll(Arrays.asList(produtoCinco, produtoDez, produtoTreze));
		categoriaDoze.getProduto().addAll(Arrays.asList());
		
		//Relacionamento de categoria com o produto
		produtoUm.getCategoria().addAll(Arrays.asList(categoriaUm));
		produtoDois.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaDois, categoriaNove));
		produtoTres.getCategoria().addAll(Arrays.asList(categoriaUm));
		produtoQuatro.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaSeis, categoriaNove));
		produtoCinco.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaOito, categoriaOnze));
		produtoSeis.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaDois, categoriaNove));
		produtoSete.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaDois, categoriaSeis, categoriaNove));
		produtoOito.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaOito));
		produtoNove.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaNove));
		produtoDez.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaDois, categoriaOnze));
		produtoOnze.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaOito));
		produtoDoze.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaSete, categoriaDez));
		produtoTreze.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaOito, categoriaOnze));
		
		Estado estadoUm = new Estado(null, "São Paulo", "SP");
		Estado estadoDois = new Estado(null, "Rio Grande do Sul", "RS");
		
		Cidade cidadeUm = new Cidade(null, "São Paulo", estadoUm);
		Cidade cidadeDois = new Cidade(null, "Salto", estadoUm);
		Cidade cidadeTres = new Cidade(null, "Porto Alegre", estadoDois);
		Cidade cidadeQuatro = new Cidade(null, "Caxias do Sul", estadoDois);
		
		Cliente clienteUm = new Cliente(null, "Cliente UM", "clienteum@mail.com", "11122233344", TipoCliente.PESSOAFISICA, passwordEncoder.encode("senhauum"));
		Cliente clienteDois = new Cliente(null, "Cliente DOIS", "clientedois@mail.com", "22233344455", TipoCliente.PESSOAFISICA, passwordEncoder.encode("senhaudois"));
		Cliente clienteTres = new Cliente(null, "Cliente TRES", "clientetres@mail.com", "33444555000166", TipoCliente.PESSOAJURIDICA, passwordEncoder.encode("senhautres"));
		Cliente clienteQuatro = new Cliente(null, "Cliente QUATRO", "clientequatro@mail.com", "44555666000144", TipoCliente.PESSOAJURIDICA, passwordEncoder.encode("senhauquatro"));
		
		clienteUm.getTelefone().addAll(Arrays.asList("911111111", "912121212"));
		clienteDois.getTelefone().addAll(Arrays.asList("922222222", "923232323"));
		clienteTres.getTelefone().addAll(Arrays.asList("933333333", "934343434"));
		clienteQuatro.getTelefone().addAll(Arrays.asList("944444444", "945454545"));
		
		clienteDois.addPerfil(Perfil.ADMIN);
		
		Endereco enderecoUm = new Endereco(null, "Rua Um", "111", null, "Bairro Um", "01111001", clienteUm, cidadeUm);
		Endereco enderecoDois = new Endereco(null, "Rua Dois", "222", null, "Bairro Dois", "02221002", clienteDois, cidadeDois);
		Endereco enderecoTres = new Endereco(null, "Rua Tres", "333", null, "Bairro Tres", "03333003", clienteTres, cidadeTres);
		Endereco enderecoQuatro = new Endereco(null, "Rua Quatro", "333", null, "Bairro Quatro", "04444004", clienteQuatro, cidadeTres);
		
		SimpleDateFormat simpledf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido pedidoUm = new Pedido(null, simpledf.parse("01/07/2020 08:22"), enderecoUm, clienteUm);
		Pedido pedidoDois = new Pedido(null, simpledf.parse("01/07/2020 17:35"), enderecoTres, clienteTres);
		
		ItemPedido itemPedidoUm = new ItemPedido(pedidoUm, produtoDois, 0.00, 1, 150.00);
		ItemPedido itemPedidoDois = new ItemPedido(pedidoUm, produtoUm, 0.00, 1, 350.00);
		ItemPedido itemPedidoTres = new ItemPedido(pedidoDois, produtoTres, 0.00, 1, 230.00);
		
		//Relacionamento de pedido com itemPedido
		pedidoUm.getItemPedido().addAll(Arrays.asList(itemPedidoUm, itemPedidoDois));
		pedidoDois.getItemPedido().addAll(Arrays.asList(itemPedidoTres));
		
		//Relacionamento de produto com itemPedido
		produtoUm.getItemPedido().addAll(Arrays.asList(itemPedidoDois));
		produtoDois.getItemPedido().addAll(Arrays.asList(itemPedidoUm));
		produtoTres.getItemPedido().addAll(Arrays.asList(itemPedidoTres));
		
		//Instância das classes filhas no pagamento
		Pagamento pagamentoUm = new PagamentoCartao(null, EstadoPagamento.QUITADO, pedidoUm, 2);
		Pagamento pagamentoDois = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, pedidoDois, simpledf.parse("06/07/2020 00:00"), null);
		
		//Setando o pagamento dos pedidos
		pedidoUm.setPagamento(pagamentoUm);
		pedidoDois.setPagamento(pagamentoDois);
		
		//Relacionamento de cliente com os pedidos
		clienteUm.setPedidos(Arrays.asList(pedidoUm));
		clienteTres.setPedidos(Arrays.asList(pedidoDois));
		
		//Relacionamento de produtos com a categoria
		categoriaUm.getProduto().addAll(Arrays.asList(produtoUm,produtoDois,produtoTres));
		categoriaDois.getProduto().addAll(Arrays.asList(produtoDois));
		
		//Relacionamento de categoria com o produto
		produtoUm.getCategoria().addAll(Arrays.asList(categoriaUm));
		produtoDois.getCategoria().addAll(Arrays.asList(categoriaUm, categoriaDois));
		produtoTres.getCategoria().addAll(Arrays.asList(categoriaUm));
		
		//Relacionamento de estado com cidade
		estadoUm.getCidade().addAll(Arrays.asList(cidadeUm, cidadeDois));
		estadoDois.getCidade().addAll(Arrays.asList(cidadeTres, cidadeQuatro));
		
		//Relacionamento de cliente e telefone
		clienteUm.getTelefone().addAll(Arrays.asList("911111111", "912121212"));
		clienteDois.getTelefone().addAll(Arrays.asList("922222222", "923232323"));
		clienteTres.getTelefone().addAll(Arrays.asList("933333333", "934343434"));
		clienteQuatro.getTelefone().addAll(Arrays.asList("944444444", "945454545"));
		
		//Relacionamento de cliente e endereco
		clienteUm.getEndereco().addAll(Arrays.asList(enderecoUm));
		clienteDois.getEndereco().addAll(Arrays.asList(enderecoDois));
		clienteTres.getEndereco().addAll(Arrays.asList(enderecoTres));
		clienteQuatro.getEndereco().addAll(Arrays.asList(enderecoQuatro));
		
		categoriaDAO.saveAll(Arrays.asList(categoriaUm, categoriaDois, categoriaTres, categoriaQuatro, categoriaCinco, categoriaSeis, categoriaSete, categoriaOito, categoriaNove, categoriaDez, categoriaOnze, categoriaDoze));
		produtoDAO.saveAll(Arrays.asList(produtoUm, produtoDois, produtoTres, produtoQuatro, produtoCinco, produtoSeis, produtoSete, produtoOito, produtoNove, produtoDez, produtoOnze, produtoDoze, produtoTreze));
		estadoDAO.saveAll(Arrays.asList(estadoUm, estadoDois));
		cidadeDAO.saveAll(Arrays.asList(cidadeUm, cidadeDois, cidadeTres, cidadeQuatro));
		clienteDAO.saveAll(Arrays.asList(clienteUm, clienteDois, clienteTres, clienteQuatro));
		enderecoDAO.saveAll(Arrays.asList(enderecoUm, enderecoDois, enderecoTres, enderecoQuatro));
		pedidoDAO.saveAll(Arrays.asList(pedidoUm, pedidoDois));
		pagamentoDAO.saveAll(Arrays.asList(pagamentoUm, pagamentoDois));
		itemPedidoDAO.saveAll(Arrays.asList(itemPedidoUm, itemPedidoDois, itemPedidoTres));
	}
}
