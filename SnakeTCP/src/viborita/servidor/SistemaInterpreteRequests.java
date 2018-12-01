package viborita.servidor;

import java.io.IOException;

import viborita.conexion.ServerRequest;
import viborita.conexion.ServerResponse;
import viborita.servidor.interpretesrequests.InterpreteRequestLogin;
import viborita.servidor.interpretesrequests.InterpreteRequestRegistro;
import viborita.servidor.interpretesrequests.InterpreteRequests;
import viborita.servidor.interpretesrequests.InterpreterRequestLobby;

public class SistemaInterpreteRequests {

	private InterpreteRequests[] interpretes;

	public SistemaInterpreteRequests(InterpreteRequests... interpretes) {
		this.interpretes = interpretes;
	}

	public ServerResponse interpretar(ServerRequest request) throws IOException {
		for (InterpreteRequests interprete : interpretes) {

			// Revisar cada interprete registrado
			// Y utilizar el primero valido
			if (interprete.soporta(request)) {
				return interprete.procesar(request);
			}
		}

		// Ningun interprete pudo procesar mi request (NO deberia llegar nunca a este punto)
		// :(
		return new ServerResponse(404, null);
	}

	public static SistemaInterpreteRequests instanciar(Servidor servidor) {
		InterpreteRequests[] interpretes = new InterpreteRequests[] {
			new InterpreterRequestLobby(),
			new InterpreteRequestLogin(),
			new InterpreteRequestRegistro()
		};
		
		return new SistemaInterpreteRequests(interpretes);
	}

}
