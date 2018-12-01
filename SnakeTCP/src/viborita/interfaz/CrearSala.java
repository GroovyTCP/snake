package viborita.interfaz;

import java.awt.EventQueue;
import java.awt.PointerInfo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import viborita.cliente.HiloCliente;
import viborita.conexion.ServerRequest;
import viborita.conexion.ServerResponse;
import viborita.entidades.Login;
import viborita.entidades.Sala;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNomSala;
	private JTextPane textPaneDescrip;
	private HiloCliente connectionThread;
	private Usuario user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearSala window = new CrearSala();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrearSala() {
		initialize();
		setVisible(true);
	}
 
	public CrearSala(HiloCliente connectionThread, Usuario user) {
		initialize();
		setVisible(true);
		this.connectionThread = connectionThread;
		this.user = user;
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Crear sala");
		
		getContentPane().setLayout(null);
		
		JLabel lblNombreDeSala = new JLabel("    Nombre de sala:");
		lblNombreDeSala.setBounds(22, 23, 106, 25);
		getContentPane().add(lblNombreDeSala);
		
		textFieldNomSala = new JTextField();
		textFieldNomSala.setBounds(138, 25, 246, 20);
		getContentPane().add(textFieldNomSala);
		textFieldNomSala.setColumns(10);
		
//		JLabel lblDescripcion = new JLabel("      Descripcion:");
//		lblDescripcion.setBounds(37, 68, 91, 25);
//		getContentPane().add(lblDescripcion);
		
		textPaneDescrip = new JTextPane();
		textPaneDescrip.setBounds(138, 68, 246, 96);
		getContentPane().add(textPaneDescrip);
		
		JButton btnCrearSala = new JButton("Crear sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				Sala sala = new Sala(textFieldNomSala.getText(), CrearSala.this.user);
				
				ServerRequest request = new ServerRequest();
				request.setPath(EstadoUsuarioEnum.CREAR_LOBBY.name());
				request.setBody(sala.convertirDeObjAJSON());

				/**
				 * Hago la request y al volver, se ejecuta el metodo que procesa la response (processLoginResponse).
				 */
				connectionThread.doRequest(request, CrearSala.this::processCreacionLobbyResponse);
				
//				SalaV sala = new SalaV(connectionThread);
//				sala.setVisible(true);
//				sala.setDescripcionSala(textPaneDescrip.getText());
//				sala.setNomSala(textFieldNomSala.getText());
//				sala.setIdSala(generarIdSala());
			}
		});
		btnCrearSala.setBounds(189, 190, 140, 39);
		getContentPane().add(btnCrearSala);
	}

	protected void processCreacionLobbyResponse(ServerResponse response) {
		if (response.getStatus() == 200) {
			dispose();
		}
	}
	
	public String getDescripcion() {
		return textPaneDescrip.getText();
	}
	
	public String getNomSala() {
		return textFieldNomSala.getText();
	}
	
	public int generarIdSala() {
		return (int)(Math.random() * 1000) +1;
	}
}
