package br.com.projeto.model.enums;

public enum StatusVendaEnum {
	ABERTA("venda aberta"), FECHADA("venda fechada"), CANCELADA("venda cancelada");

	private String descricao;

	private StatusVendaEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
