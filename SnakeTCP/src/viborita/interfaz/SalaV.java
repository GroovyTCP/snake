package viborita.interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import viborita.cliente.HiloCliente;
import viborita.conexion.ServerRequest;
import viborita.conexion.ServerResponse;
import viborita.entidades.Cabeza;
import viborita.entidades.Direcciones;
import viborita.entidades.Login;
import viborita.entidades.Mapa;
import viborita.entidades.Punto;
import viborita.entidades.Sala;
import viborita.entidades.Usuario;
import viborita.entidades.Ventana;
import viborita.entidades.Vibora;
import viborita.enums.EstadoUsuarioEnum;

public class SalaV extends JFrame {

	private int idSala;
	private JTextPane txtpnDescrip;
	private JLabel lblNomSala;
	private JLabel lblNomDueno;
	private HiloCliente connectionThread;
	private Sala sala;
	private Set<Usuario> usuarios = new HashSet<>();
	private List<Color> colores = new ArrayList<Color>();
	private List<Punto> direcciones = new ArrayList<Punto>();

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Sala window = new Sala();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public SalaV() {
	}

	public SalaV(HiloCliente connectionThread, Sala sala) {
		this.usuarios = sala.getUsuarios();
		this.connectionThread = connectionThread;
		this.sala = sala;
		initialize();
	}

	protected void processIniciarPartidaResponse(ServerResponse response) {
		if(response.getStatus() == 200) {
			ObjectMapper om = new ObjectMapper();
			try {
				Ventana ventana = new Ventana(om.readValue(response.getBody(), Mapa.class));
				ventana.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				int jPaneResult = JOptionPane.showConfirmDialog(windowEvent.getComponent(),
						"¿Estas seguro que deseas abandonar la sala?", "¿Abandonar sala?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jPaneResult == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				}
			}
		});
		
		getContentPane().setLayout(null);
		
		JButton btnJugar = new JButton("Iniciar Juego");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(usuarios.size() >= 1) {
					
					ServerRequest request = new ServerRequest();
					request.setPath(EstadoUsuarioEnum.INICIAR_PARTIDA.name());
					ObjectMapper om = new ObjectMapper();
					try {
						request.setBody(om.writeValueAsString(usuarios));
					} catch (JsonProcessingException e1) {
						e1.printStackTrace();
					}

					/**
					 * Hago la request y al volver, se ejecuta el metodo que procesa la response (processLoginResponse).
					 */
					connectionThread.doRequest(request, SalaV.this::processIniciarPartidaResponse);
				}
//				Vibora[] snake = new Vibora[2];
//				snake[0] = new Vibora(new Cabeza(new Punto(60, 50)), "1", Color.RED, Direcciones.DERECHA);
//				ArrayList<Punto> cuerpo1 = new ArrayList<Punto>();
//				cuerpo1.add(new Punto(50, 50));
//				cuerpo1.add(new Punto(40, 50));
//				snake[0].setCuerpo(new Cuerpo(cuerpo1));
//				
//				snake[1] = new Vibora(new Cabeza(new Punto(540, 450)), "2", Color.CYAN, Direcciones.IZQUIERDA);
//				ArrayList<Punto> cuerpo2 = new ArrayList<Punto>();
//				cuerpo2.add(new Punto(550, 450));
//				cuerpo2.add(new Punto(560, 450));
//				snake[1].setCuerpo(new Cuerpo(cuerpo2));
				
				/*
				 * snake array viboras
				 * 600 ancho mapa
				 * 500 largo mapa
				 * 100 puntaje por comer una fruta
				 */
//				Mapa game = new Mapa(snake, 600, 500, 100);
//				
//				Runnable r = new Ventana(game);
//				Thread t = new Thread(r);
//				t.start();
				
			}
		});
		btnJugar.setBounds(145, 212, 125, 38);
		getContentPane().add(btnJugar);
		
		JButton btnSalir = new JButton("Salir de sala");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int jPaneResult = JOptionPane.showConfirmDialog(null,
						"Estas seguro que deseas abandonar la sala?", "Abandonar sala?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jPaneResult == JOptionPane.YES_OPTION) {	
					dispose();
				}
			}
		});
		btnSalir.setBounds(10, 212, 125, 38);
		getContentPane().add(btnSalir);
		
		JList listUsuarios = new JList();
		listUsuarios.setBounds(276, 11, 148, 239);
		getContentPane().add(listUsuarios);
		
		JLabel lblSala = new JLabel("   Sala : " + this.sala.getNombre());
		lblSala.setBounds(0, 0, 77, 23);
		getContentPane().add(lblSala);
		
		JLabel lblDueno = new JLabel("   Duenio : " + this.sala.getAdmin().getUsuario());
		lblDueno.setBounds(0, 34, 77, 23);
		getContentPane().add(lblDueno);
		
		txtpnDescrip = new JTextPane();
		txtpnDescrip.setEditable(false);
		txtpnDescrip.setText("Bienvenido!");
		txtpnDescrip.setBounds(10, 85, 260, 116);
		getContentPane().add(txtpnDescrip);
		
		lblNomSala = new JLabel("");
		lblNomSala.setBounds(74, 2, 192, 21);
		getContentPane().add(lblNomSala);
		
		lblNomDueno = new JLabel("");
		lblNomDueno.setBounds(87, 38, 183, 19);
		getContentPane().add(lblNomDueno);
		
		JLabel lblNewLabel = new JLabel("Descripcion sala");
		lblNewLabel.setBounds(10, 68, 85, 14);
		getContentPane().add(lblNewLabel);
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
	
	public void setDueno(String dueno) {
		this.lblNomDueno.setText(dueno);
	}
}
