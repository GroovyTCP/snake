package viborita.interfaz;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import entidades.Direcciones;
import entidades.Ventana;
import viborita.cliente.HiloCliente;
import viborita.entidades.Cabeza;
import viborita.entidades.Cuerpo;
import viborita.entidades.Mapa;
import viborita.entidades.PaqueteUsuario;
import viborita.entidades.Punto;
import viborita.entidades.Sala;
import viborita.entidades.Vibora;
import viborita.enums.EstadoUsuarioEnum;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

public class SalaV {

	private int idSala;
	private JFrame frame;
	private JTextPane txtpnDescrip;
	private JLabel lblNomSala;
	private JLabel lblNomDueno;
	private DefaultListModel<String> modeloLista = new DefaultListModel<>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Sala window = new Sala();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @param hc 
	 */
	public SalaV(HiloCliente hc) {
		initialize(hc);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param hc 
	 */
	private void initialize(HiloCliente hc) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				int jPaneResult = JOptionPane.showConfirmDialog(windowEvent.getComponent(),
						"¿Estas seguro que deseas abandonar la sala?", "¿Abandonar sala?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jPaneResult == JOptionPane.YES_OPTION) {
					PaqueteUsuario mensaje = new PaqueteUsuario();
					mensaje.setNombreSala(lblNomSala.getText());
					mensaje.setUsername(hc.getUsernameCliente());
					
					if (mensaje.getUsername().equals(lblNomDueno.getText())) {
						mensaje.setAccionCliente(EstadoUsuarioEnum.ELIMINAR_SALA);
						hc.eliminar_sala(mensaje.convertirDeObjAJSON());
					}else {
						mensaje.setAccionCliente(EstadoUsuarioEnum.DESCONECTAR_SALA);
						hc.desconectarDeSala(mensaje.convertirDeObjAJSON());
					}
					
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				}
			}
		});
		
		frame.getContentPane().setLayout(null);
		
		JButton btnJugar = new JButton("Iniciar Juego");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				//hc.iniciarJuego();
				
				
				Vibora[] snake = new Vibora[2];
				snake[0] = new Vibora(new Cabeza(new Punto(60, 50)), "1", Color.RED, Direcciones.DERECHA);
				ArrayList<Punto> cuerpo1 = new ArrayList<Punto>();
				cuerpo1.add(new Punto(50, 50));
				cuerpo1.add(new Punto(40, 50));
				snake[0].setCuerpo(new Cuerpo(cuerpo1));
				
				snake[1] = new Vibora(new Cabeza(new Punto(540, 450)), "2", Color.CYAN, Direcciones.IZQUIERDA);
				ArrayList<Punto> cuerpo2 = new ArrayList<Punto>();
				cuerpo2.add(new Punto(550, 450));
				cuerpo2.add(new Punto(560, 450));
				snake[1].setCuerpo(new Cuerpo(cuerpo2));
				
				/*
				 * snake array viboras
				 * 600 ancho mapa
				 * 500 largo mapa
				 * 100 puntaje por comer una fruta
				 */
				Mapa game = new Mapa(snake, 600, 500, 100);
				
				Runnable r = new Ventana(game);
				Thread t = new Thread(r);
				t.start();
				
			}
		});
		btnJugar.setBounds(145, 212, 125, 38);
		frame.getContentPane().add(btnJugar);
		
		JButton btnSalir = new JButton("Salir de sala");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int jPaneResult = JOptionPane.showConfirmDialog(null,
						"¿Estas seguro que deseas abandonar la sala?", "¿Abandonar sala?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jPaneResult == JOptionPane.YES_OPTION) {
					PaqueteUsuario mensaje = new PaqueteUsuario();
					mensaje.setNombreSala(lblNomSala.getText());
					mensaje.setUsername(hc.getUsernameCliente());
					System.out.println(mensaje.getUsername() + " - " +lblNomDueno.getText());
					if (mensaje.getUsername().equals(lblNomDueno.getText())) {
						mensaje.setAccionCliente(EstadoUsuarioEnum.ELIMINAR_SALA);
						hc.eliminar_sala(mensaje.convertirDeObjAJSON());
					}else {
						mensaje.setAccionCliente(EstadoUsuarioEnum.DESCONECTAR_SALA);
						hc.desconectarDeSala(mensaje.convertirDeObjAJSON());
					}
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.dispose();
				}
				
			}
		});
		btnSalir.setBounds(10, 212, 125, 38);
		frame.getContentPane().add(btnSalir);
		
		//276, 11, 148, 239);
		JList<String>listUsuarios = new JList<>(new DefaultListModel<>());
		listUsuarios.setBounds(276, 11, 148, 239);
		//panelPrincipal.add(listSalas);
		listUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listUsuarios.setModel(modeloLista);
		frame.getContentPane().add(listUsuarios);
		
		JLabel lblSala = new JLabel("         Sala :");
		lblSala.setBounds(0, 0, 77, 23);
		frame.getContentPane().add(lblSala);
		
		JLabel lblDueno = new JLabel("         Due\u00F1o :");
		lblDueno.setBounds(0, 34, 77, 23);
		frame.getContentPane().add(lblDueno);
		
		txtpnDescrip = new JTextPane();
		txtpnDescrip.setEditable(false);
		txtpnDescrip.setText("Bienvenido!");
		txtpnDescrip.setBounds(10, 85, 260, 116);
		frame.getContentPane().add(txtpnDescrip);
		
		lblNomSala = new JLabel("");
		lblNomSala.setBounds(74, 2, 192, 21);
		frame.getContentPane().add(lblNomSala);
		
		lblNomDueno = new JLabel("");
		lblNomDueno.setBounds(87, 38, 183, 19);
		frame.getContentPane().add(lblNomDueno);
		
		JLabel lblNewLabel = new JLabel("Descripcion sala");
		lblNewLabel.setBounds(10, 68, 85, 14);
		frame.getContentPane().add(lblNewLabel);
		
		modeloLista.addElement(hc.getUsernameCliente());
	}
	
	public void setNomSala(String nomSala) {
		this.lblNomSala.setText(nomSala);
	}
	
	public void setDescripcionSala(String descripcion) {
		if(descripcion.length() != 0)
			this.txtpnDescrip.setText(descripcion);
	}
	
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	public String getNomSala() {
		return this.lblNomSala.getText();
	}
	
	public void setDueño(String dueño) {
		this.lblNomDueno.setText(dueño);
	}
	
	public void setSala(Sala sala) {
		this.lblNomDueno.setText(sala.getAdmin());
		this.lblNomSala.setText(sala.getNombreSala());
		this.txtpnDescrip.setText(sala.getDescripcion());
		this.modeloLista.clear();
		for (String jugador : sala.getUsuarios()) {
			this.modeloLista.addElement(jugador);
		}
	}
}
