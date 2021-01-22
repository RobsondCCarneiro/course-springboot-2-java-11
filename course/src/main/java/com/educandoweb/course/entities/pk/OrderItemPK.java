/*
 * O item de pedido é uma associação muito-para-muitos com atributos extras
 * 
 * Quando tinha a relação Categoria~Produto, não havia nenhum dado na associação,
 * simplesmente um produto tinha uma coleção de categoria e vice-versa.
 * 
 * A relação entre o produto e o pedido, tem dados extras, como Produto em Pedido
 * tem uma quantidade. O preço do pedido tem que estar no item de pedido (OrderItem),
 * para fins históricos, como caso houver uma mudança de preço do produto, o pedido
 * mostra quanto o item custou na época do pedido.
 * 
 * No paradigma Orientado a Objetos não tem o conceito de chave primária composta,
 * o atributo identificador do objeto é um só, de forma que é necessário criar uma
 * classe auxilar como esta, para representar o par.
 */

package com.educandoweb.course.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;

//A anotação embeddable, para indicar que a classe é chave-primária composta.
@Embeddable
public class OrderItemPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
}
