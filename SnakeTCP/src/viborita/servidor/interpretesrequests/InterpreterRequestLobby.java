/**
 * 
 */
package viborita.servidor.interpretesrequests;

import java.io.IOException;

import viborita.cliente.ServerRequest;
import viborita.cliente.ServerResponse;


/**
 * @author mcurrao
 *
 */
public class InterpreterRequestLobby extends InterpreteRequests {

	/* (non-Javadoc)
	 * @see viborita.servidor.interpretesrequests.InterpreteRequests#soporta(viborita.cliente.ServerRequest)
	 */
	@Override
	public boolean soporta(ServerRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see viborita.servidor.interpretesrequests.InterpreteRequests#procesar(viborita.cliente.ServerRequest)
	 */
	@Override
	public ServerResponse procesar(ServerRequest request) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
