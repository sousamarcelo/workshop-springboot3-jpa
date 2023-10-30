package com.educandoweb.course.entities.enums;

public enum OrderStatus {
	
	/* importante para evitar quebrar a sequencia numerica de cada atributo caso algum programador insira um enum no meio dos outrosm foi atribuido os valores para cada enum */
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	/*devido a necessidade de atribuir manualmente os valores para os enums, foram necessario criar um atributo
	 *cod e a contrutor, só que contrutor enum tem uma parcitularidade ele é privado
	 * foi criado um metodo
	 */
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	/* criado um metodo statico para eventualmente converter um numero  
	 * 
	 *ou seja será recebido um code e esse metodo devera retornar o orderStatus correspondente sem precisar ficar estanciando objetos
	 */
	
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
