package com.educandoweb.course.entities.enums;

public enum OrderStatus {

	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	/*
	 * Para manter a numeração fixa e explicita ao programador qual é
	 * os numeros de cada pedido, então é necessário fazer essa implementação
	 * abaixo. 
	 */
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	/*
	 * Metodo que funciona sem instanciar, feito para percorrer todos os enumerados
	 * e compará-lo com o parâmetro
	 */
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		//Caso o número não existir, então lance esta exceção
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
