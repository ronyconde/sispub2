package br.com.projeto.interceptor;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.projeto.control.mb.UsuarioSessionMB;

@SuppressWarnings("serial")
public class AuthenticationPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		// Variavel com acesso a arvore de componentes
		FacesContext context = event.getFacesContext();
		// Verificacao de acesso a pagina de login da aplicacao
		if (context.getViewRoot().getViewId().equals("/login.xhtml")) {
			return;
		}

		// Carregando o objeto UsuarioLogado da sessao
		UsuarioSessionMB usuarioLogado = context.getApplication()
				.evaluateExpressionGet(context, "#{usuarioSessionMB}",
						UsuarioSessionMB.class);
		// Validacao de autenticacao
		if (!usuarioLogado.isLogado()) {
			// Configuracao para redirect apos login
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(false);
			session.setAttribute(UsuarioSessionMB.SESSION_HOME_LABEL, context
					.getViewRoot().getViewId());
			redirect(context, "login?faces-redirect=true");
		} else {
			// TODO: realizar testes de autorizacao

		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	private void redirect(FacesContext context, String pagina) {
		// Carga do objeto de navegacao
		NavigationHandler handler = context.getApplication()
				.getNavigationHandler();
		// Redirecionamento para a tela de login
		handler.handleNavigation(context, null, pagina);
		context.renderResponse();
	}

}
