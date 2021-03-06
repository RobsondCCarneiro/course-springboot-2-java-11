package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
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
import javax.persistence.Table;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

//Por causa do conflito foi necessário , foi necessário colocar o nome de "tb_order"
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	//***************ATRIBUTOS BÁSICOS**************************
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	//private OrderStatus orderStatus;
	private Integer orderStatus;

	
	//********************ASSOCIAÇÕES****************************
	/*
	 * Para transformar client em chave estrangeira, isso porque um Usuário (User)
	 * pode ter várias pedidos, enquanto o Pedido (Order) pode ter apenas um
	 * usuário.
	 */
	@ManyToOne
	// nome da chave estrangeira chamada no banco de dados
	@JoinColumn(name = "client_id")
	private User client;
	
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	/*
	 * A classe Order é a classe independente, pois não a associação com o pagamento é
	 * de 0..1; O cascade é para mapear que ambas as entidades (pedido e pagamento) tenham o mesmo Id.
	 * Isso é para mapeamento um para um.
	 */
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	//*******************CONSTRUTORES********************************
	public Order() {

	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		//this.orderStatus = orderStatus;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	
	//***************GETTERS E SETTERS*****************************
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	
	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItem> getItems(){
		return items;
	}

	
//	public OrderStatus getOrderStatus() {
//		return orderStatus;
//	}
	
	public OrderStatus getOrderStatus() {
		/*
		 * Como o orderStatus é inteiro (Integer) internamente na classe,
		 * pois ele precisa de ser retornado como o tipo OrderStatus, então
		 * ele foi convertido pelo método valoeOf(). 
		 */
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		/*
		 * Agora é o processo inverso, ou seja, recebe uma variável
		 * do tipo orderStatus e a seta como Integer para o processamento
		 * interno nesta classe. 
		 */
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x : items) {
			sum += x.getSubTotal(); 
		}
		return sum;
	}

	//**************************HASH CODES E EQUALS*************************
	
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
