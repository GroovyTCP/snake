package viborita.entidades;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

public class Login {

	private JFrame frame;
	private JTextField txtUsuario;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
		panel.add(btnIniciarSesion);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setToolTipText("Ingrese Su Usuario");
		txtUsuario.setBounds(163, 203, 151, 22);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setToolTipText("Ingrese Su contrase\u00F1a");
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
		
		JLabel label = new JLabel("");
		label.setBounds(0, 91, 161, 226);
		panel.add(label);
		label.setIcon(new ImageIcon("C:\\Users\\lucki\\workspace\\Formularios\\serpiente-y-culebra-imagen-animada-0144.gif"));
	}
}
