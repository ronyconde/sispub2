package br.com.projeto.control.mb;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projeto.model.bean.Usuario;

@SuppressWarnings("serial")
@SessionScoped
@Named
public class UsuarioSessionMB implements Serializable{
	
	@Inject
	private Conversation conversa;
	
	private Usuario usuario;
	public static final String SESSION_HOME_LABEL = "paginaInicial";
	
	public String exibirHome() {
		if (!conversa.isTransient()) {
			conversa.end();
		}
		return "index?faces-redirect=true";
	}

	public boolean isLogado() {
		return usuario != null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
