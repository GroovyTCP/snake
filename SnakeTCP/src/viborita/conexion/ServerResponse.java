package viborita.conexion;

public class ServerResponse {

	private int status;
	private String body;

	public ServerResponse() {
		this(200, null);
	}

	public ServerResponse(int status, String body) {
		this.status = status;
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
