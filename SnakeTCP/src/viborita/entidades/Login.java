package viborita.entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

import viborita.interfaz.SalaInterfaz;
import viborita.repositorio.impl.UsuarioServiceImpl;

public class Login extends JFrame{

	private JFrame frame;
	private JTextField textFieldUsuario;
	private JPasswordField passField;
	private File cancionLogin;
	private AudioInputStream audio;
	private Clip clip;
	private boolean musicOn = true;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8622887703162718277L;
	
	public Login() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		initialize();
	}

	/**
	 * Inicializa el contenido del frame
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
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 17));
		lblContrasea.setBounds(163, 240, 95, 20);
		lblContrasea.setForeground(Color.lightGray);
		panel.add(lblContrasea);
		
		passField = new JPasswordField();
		passField.setToolTipText("Ingrese su contraseña");
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
		
		LoginActionListener lal = new LoginActionListener();
		btnIniciarSesion.addActionListener(lal);
		
		panel.add(btnIniciarSesion);
		
		Icon icon = new ImageIcon("recursos\\imagenes\\serpiente-login.gif");
		JLabel label = new JLabel(icon);
		label.setBounds(0, 120, 161, 226);
		panel.add(label);
		
		//Musica
		cancionLogin = new File("recursos\\soundtrack\\musicaLogin.wav");
		audio = AudioSystem.getAudioInputStream(cancionLogin);
		clip = AudioSystem.getClip();
		clip.open(audio);
		clip.start();
		
		JButton btnMusica = new JButton();
		btnMusica.setBackground(Color.LIGHT_GRAY);
		btnMusica.setIcon(new ImageIcon("recursos\\imagenes\\iconoMusica.png"));
		btnMusica.setBounds(290, 415, 55, 25);
		panel.add(btnMusica);
		btnMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(musicOn == false) {
					clip.start();
					musicOn=true;
				}
				else {
					clip.stop();
					musicOn=false;
				}
					
			}
		});
	}

	public void run() {
		try {
			this.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo abrir pantalla login");
		}
	}
	
	private class LoginActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
//			System.out.println("Validar usuario en servidor");
			
//			Cliente cliente = new Cliente("localhost", 8080);
			
//			if(cliente.loginCliente(txtUsuario.getText(), txtPassword.getPassword().toString())) {
//				System.out.println("Loguea ok y va a la pantalla de salas");
//			}else {
//				System.out.println("Muestro mensaje error para validacion de inputs");
//			}
			
			System.out.println("Validar usuario");
			
			UsuarioServiceImpl us = new UsuarioServiceImpl();
			Usuario user = us.get(textFieldUsuario.getText());
			
			if(user != null && user.getContrasenia().equals(new String((passField.getPassword())))) {
				//Usuario validado. Muestro salas (las tiene el sv)
				System.out.println("Pass validada. Muestro salas");
				SalaInterfaz salas;
				try {
					salas = new SalaInterfaz();
					salas.setVisible(true);
					frame.dispose();
					clip.stop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println("Validacion de inputs");
			}
			
		}

	}
	
}
