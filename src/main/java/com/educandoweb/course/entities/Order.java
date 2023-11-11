package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_order")  // alterando o nome do da tabela no banco de Order para XXX para evitar conflito no banco pois a palavra "Order" é uma paravra resevada do sql
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")   //informando ao JPA/jackson o padrão da data em GMT/timeZone
	private Instant moment;
	
	private Integer orderStatus;  /* tipo alterado de OrderStatus para Integer para ficar explicito que no banco estamos guardando um valor numerico, esse tratamento é interno da classe order, para o restante do codigo não devera afetar em nada esse tratamento */
	
	@ManyToOne  				//indicando para o JPA que é um relacionamento de muitos para um, ou seja, um pedido só pode ter um cliente, mas o cliente pode ter varios pedidos
	@JoinColumn(name = "client_id")  				//informando nome da chave estragenria no banco de dados
	private User client;
	
	//criado essa associação somente depois de ter criados as classe OrderItemPk e OrderItem e feito todas a associações necessarias inclusive as associações com o produto
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // cascade no caso de um para um pagamento x pedido está sendo mapeado o mesmo Id
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;		
	}

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
		
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);  /* utilizando o metodo de conversão criado lá no OrderStatus para devolver o status ao inves do codigo*/
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();  /* convertendo para numerico para poder guardar no atributo o valor passado no parametro */
		}
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
	
	public Double getTotal() {
		double sum = 0.0;
		
		for(OrderItem x : items) {
			sum += x.getSubTotal();
		}
		return sum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
