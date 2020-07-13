package com.mariopalmeira.cursojava.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable{ 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;	
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="endereco_entrega_id")
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> itemPedido = new HashSet<>();
	
	public Pedido() {}

	public Pedido(Integer id, Date instante, Endereco enderecoEntrega, Cliente cliente) {
		super();
		this.id = id;
		this.instante = instante;
		this.enderecoEntrega = enderecoEntrega;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(Set<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}
	
	public double getTotal() {
		double soma = 0.0;
		for(ItemPedido itemPedido : itemPedido) {
			soma += itemPedido.getSubTotal();
		}
		return soma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido No.: ");
		builder.append(id);
		builder.append(" - Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(" - Documento: ");
		builder.append(getCliente().getCpfOuCnpj());
		builder.append(" - Incluído em: ");
		builder.append(sdf.format(instante));
		builder.append("\n\n");
		builder.append("O pagamento encontra-se: ");
		builder.append(getPagamento().getEstadoPagamento());
		builder.append("\n\n");
		for(ItemPedido item : getItemPedido()) {
			builder.append("- ");
			builder.append(item.getProduto().getNome());
			builder.append(" | Quantidade: ");
			builder.append(item.getQuantidade());
			builder.append(" | Preço unit.: ");
			builder.append(nf.format(item.getPreco()));
			builder.append(" | Subtotal: ");
			builder.append(nf.format(item.getSubTotal()));		
			builder.append("\n");
		}
		builder.append("\n\n");
		builder.append("Total: ");
		builder.append(nf.format(getTotal()));
		return builder.toString();
	}
}
