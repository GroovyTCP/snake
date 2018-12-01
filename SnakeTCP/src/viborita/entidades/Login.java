package viborita.entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

import viborita.cliente.Cliente;
import viborita.cliente.HiloCliente;
import viborita.cliente.ServerRequest;
import viborita.cliente.ServerResponse;
import viborita.enums.EstadoUsuarioEnum;
//import viborita.cliente.Cliente;
import viborita.interfaz.SalaInterfaz;
import viborita.servidor.ConfiguracionServidor;

public class Login extends JFrame {

	static private JFrame frame;
	private JTextField textFieldUsuario;
	private JPasswordField passField;
	private Musica musica;
	private boolean musicOn = true;
	private BaseDatos bd = new BaseDatos();
	private SalaInterfaz sala;
	private HiloCliente connectionThread;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8622887703162718277L;

	public Login(HiloCliente connectionThread)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		initialize();
		this.connectionThread = connectionThread;
	}

	protected void processLoginResponse(ServerResponse response) {
		if (response.getStatus() == 200) {
			frame.dispose();
//			clip.stop();
			musica.detener();
			//doLogin();
		} else {
			JOptionPane.showMessageDialog(null, "El usuario o la contraseņa son incorrectos", "Usuario no encontrado",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Inicializa el contenido del frame
	 * 
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 */
	private void initialize() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		frame = new JFrame();
		frame.setTitle("Viborita");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(23, 32, 42));
		frame.setSize(383, 497);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder());
		panel.setBackground(new Color(21, 67, 96));
		panel.setBounds(15, 15, 345, 440);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblLaVivorita = new JLabel("La Viborita");
		lblLaVivorita.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaVivorita.setFont(new Font("Wide Latin", Font.PLAIN, 17));
		lblLaVivorita.setBounds(157, 142, 172, 42);
		lblLaVivorita.setForeground(Color.lightGray);
		panel.add(lblLaVivorita);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 17));
		lblUsuario.setBounds(163, 187, 74, 20);
		lblUsuario.setForeground(Color.lightGray);
		panel.add(lblUsuario);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		textFieldUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsuario.setToolTipText("Ingrese Su Usuario");
		textFieldUsuario.setBorder(null);
		textFieldUsuario.setBounds(163, 210, 151, 22);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contraseņa");
		lblContrasea.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 17));
		lblContrasea.setBounds(163, 240, 95, 20);
		lblContrasea.setForeground(Color.lightGray);
		panel.add(lblContrasea);

		passField = new JPasswordField();
		passField.setToolTipText("Ingrese su contraseņa");
		passField.setHorizontalAlignment(SwingConstants.CENTER);
		passField.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		passField.setBorder(null);
		passField.setBounds(163, 260, 151, 22);
		panel.add(passField);
		passField.setColumns(10);

		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setFont(new Font("Trajan Pro", Font.PLAIN, 13));
		btnIniciarSesion.setBackground(new Color(153, 255, 255));
		btnIniciarSesion.setBounds(163, 292, 151, 25);

		panel.add(btnIniciarSesion);

		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Usuario usuario = new Usuario(textFieldUsuario.getText(), new String(passField.getPassword()));
				usuario.setAccionCliente(EstadoUsuarioEnum.LOGIN);

				ServerRequest request = new ServerRequest();
				request.setPath(EstadoUsuarioEnum.LOGIN.name());
				request.setBody(usuario.convertirDeObjAJSON());

				
				/**
				 * Hago la request y al volver, se ejecuta el metodo que procesa la response (processLoginResponse).
				 */
				connectionThread.doRequest(request, Login.this::processLoginResponse);
			}
		});

		JButton btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.setFont(new Font("Trajan Pro", Font.PLAIN, 13));
		btnCrearUsuario.setBackground(new Color(153, 255, 255));
		btnCrearUsuario.setBounds(163, 327, 151, 25);
		panel.add(btnCrearUsuario);

		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario = new Usuario(textFieldUsuario.getText(), new String(passField.getPassword()));
				
				ServerRequest request = new ServerRequest();
				request.setPath(EstadoUsuarioEnum.REGISTRO.name());
				request.setBody(usuario.convertirDeObjAJSON());
				
				connectionThread.doRequest(request, Login.this::processRegistrationCallback);

				// Hacer esto desde el sv
//				bd.crearUsuario(usuario);

			}
		});

		Icon icon = new ImageIcon("recursos\\imagenes\\serpiente-login.gif");
		JLabel label = new JLabel(icon);
		label.setBounds(0, 120, 161, 226);
		panel.add(label);

		// Musica
		musica = new Musica("musicaLogin.wav");
		musica.reproducir();

		JButton btnMusica = new JButton();
		btnMusica.setBackground(Color.LIGHT_GRAY);
		btnMusica.setIcon(new ImageIcon("recursos\\imagenes\\iconoMusica.png"));
		btnMusica.setBounds(290, 415, 55, 25);
		panel.add(btnMusica);
		btnMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (musicOn == false) {
					musica.reproducir();
					musicOn = true;
				} else {
					musica.detener();
					musicOn = false;
				
				}

			}
		});

	}
	
	public void processRegistrationCallback(ServerResponse response) {
		
	}

	public void run() {
		try {
			this.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo abrir pantalla login");
		}
	}

}
