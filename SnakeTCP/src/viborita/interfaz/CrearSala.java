package viborita.interfaz;

import java.awt.EventQueue;
import java.awt.PointerInfo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import viborita.cliente.HiloCliente;
import viborita.entidades.PaqueteUsuario;
import viborita.entidades.Sala;
import viborita.entidades.Usuario;
import viborita.enums.EstadoUsuarioEnum;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearSala {

	private JFrame frame;
	private JTextField textFieldNomSala;
	private JTextPane textPaneDescrip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearSala window = new CrearSala(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param hilocliente 
	 */
	public CrearSala(HiloCliente hilocliente) {
		initialize(hilocliente);
		frame.setVisible(true);
	}
 
	/**
	 * Initialize the contents of the frame.
	 * @param hc 
	 */
	private void initialize(HiloCliente hc) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombreDeSala = new JLabel("    Nombre de sala:");
		lblNombreDeSala.setBounds(22, 23, 106, 25);
		frame.getContentPane().add(lblNombreDeSala);
		
		textFieldNomSala = new JTextField();
		textFieldNomSala.setBounds(138, 25, 246, 20);
		frame.getContentPane().add(textFieldNomSala);
		textFieldNomSala.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("      Descripcion:");
		lblDescripcion.setBounds(37, 68, 91, 25);
		frame.getContentPane().add(lblDescripcion);
		
		textPaneDescrip = new JTextPane();
		textPaneDescrip.setBounds(138, 68, 246, 96);
		frame.getContentPane().add(textPaneDescrip);
		
		JButton btnCrearSala = new JButton("Crear sala");
		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PaqueteUsuario room = new PaqueteUsuario();
				room.setDescSala(textPaneDescrip.getText());
				room.setNombreSala(textFieldNomSala.getText());
				room.setUsername(hc.getUsernameCliente());
				room.setAccionCliente(EstadoUsuarioEnum.CREAR_SALA);
				
				hc.crearSala(room.convertirDeObjAJSON());
				
				if (hc.estadoUser == EstadoUsuarioEnum.SALA_EXISTENTE) {
					return;
				}
				
				SalaV sala = new SalaV(hc);
				sala.setDescripcionSala(textPaneDescrip.getText());
				sala.setNomSala(textFieldNomSala.getText());
				sala.setDueño(hc.getUsernameCliente());
				sala.setIdSala(generarIdSala());
				
				frame.dispose();
				
			}
		});
		btnCrearSala.setBounds(189, 190, 140, 39);
		frame.getContentPane().add(btnCrearSala);
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
