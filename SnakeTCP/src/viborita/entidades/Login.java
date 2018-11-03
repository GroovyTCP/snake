package viborita.entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class Login extends JFrame{

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8622887703162718277L;
	
	public Login() {
		initialize();
	}

	/**
	 * Inicializa el contenido del frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Viborita");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 102, 255));
		frame.setBounds(100, 100, 383, 497);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder());
		panel.setBackground(new Color(204, 255, 255));
		panel.setBounds(12, 13, 341, 424);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setFont(new Font("Trajan Pro", Font.PLAIN, 13));
		btnIniciarSesion.setBackground(new Color(153, 255, 255));
		btnIniciarSesion.setBounds(163, 292, 151, 25);
		
		LoginActionListener lal = new LoginActionListener();
		btnIniciarSesion.addActionListener(lal);
		
		panel.add(btnIniciarSesion);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setToolTipText("Ingrese Su Usuario");
		txtUsuario.setBounds(163, 203, 151, 22);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setToolTipText("Ingrese su contrase\u00F1a");
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		txtPassword.setBounds(163, 238, 151, 22);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 17));
		lblUsuario.setBounds(163, 187, 74, 20);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 17));
		lblContrasea.setBounds(163, 220, 85, 20);
		panel.add(lblContrasea);
		
		JLabel lblLaVivorita = new JLabel("La Viborita");
		lblLaVivorita.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaVivorita.setFont(new Font("Wide Latin", Font.PLAIN, 17));
		lblLaVivorita.setBounds(157, 142, 172, 42);
		panel.add(lblLaVivorita);
		
//		Icon icon = new ImageIcon("C:/Users/thandley/git/snake/SnakeTCP/recursos/imagenes");
		Icon icon = new ImageIcon("../../recursos/imagenes/serpiente-login.gif");
		JLabel label = new JLabel(icon);
		label.setBounds(0, 91, 161, 226);
		panel.add(label);
//		label.setIcon(new ImageIcon("../../recursos/imagenes/serpiente-login.gif"));
//		label.setIcon(new ImageIcon("C:\\Users\\thandley\\git\\snake\\SnakeTCP\\recursos\\imagenes"));
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
			
			System.out.println("Validar usuario en servidor");
			
			Cliente cliente = new Cliente("localhost", 8080);
			
			if(cliente.loginCliente(txtUsuario.getText(), txtPassword.getPassword().toString())) {
				System.out.println("Loguea ok y va a la pantalla de salas");
			}else {
				System.out.println("Muestro mensaje error para validacion de inputs");
			}
			
		}

	}
	
}
