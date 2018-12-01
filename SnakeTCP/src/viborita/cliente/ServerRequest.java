package viborita.cliente;

import java.util.HashMap;
import java.util.Map;

public class ServerRequest {

	private String path;
	private Map<String, String> headers = new HashMap<>();
	private String body;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
