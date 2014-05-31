package br.com.projeto.model.enums;

public enum ChaveMensagemEnum {
	ALERTA_OPERACAO_RESULTADO("alerta_operacao_resultado"), 
	ALERTA_OPERACAO_SUCESSO("alerta_operacao_sucesso"), 
	ALERTA_OPERACAO_FALHA("alerta_operacao_falha"), 
	LABEL_NAO_INFORMADO("label_nao_informado"),
	ALERTA_FALHA_LOGIN("alerta_operacao_falha_login");

	private String chave;

	private ChaveMensagemEnum(String chave) {
		this.chave = chave;
	}

	public String getChave() {
		return chave;
	}
}
