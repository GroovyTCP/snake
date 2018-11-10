package entidades;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import viborita.entidades.Mapa;
import viborita.entidades.Punto;
import viborita.entidades.Vibora;

// Serpiente roja, se mueve con las flechitas y la otra con WASD


public class Ventana extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int intervaloDeTiempo = 60;

	private JPanel contentPane;
	private Mapa mapa;

	public Ventana(Mapa mapa) {
		super("Snake");
		setResizable(false);
		this.mapa = mapa;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				int jPaneResult = JOptionPane.showConfirmDialog(windowEvent.getComponent(),
						"¿Estás seguro que deseas abandonar la partida?", "¿Abandonar partida?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jPaneResult == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				}
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				try {
					Thread.sleep(intervaloDeTiempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				setMovimiento(tecla);
			}
		});
		setBounds(0, 0, mapa.getAncho()+Mapa.getAnchotabladepuntos(), mapa.getAlto());

		contentPane = new SnakeGrafico(mapa);
		setBackground(Color.BLACK);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void setMovimiento(KeyEvent evento) {

		System.out.println("-------------------------------");

		int viboraNumero = 0;
		Punto direccionActual = mapa.getViboras()[viboraNumero].getDireccionActual();

		if (evento.getKeyCode() == KeyEvent.VK_LEFT && direccionActual != Direcciones.DERECHA) {
			mapa.getViboras()[viboraNumero].setDireccionActual(Direcciones.IZQUIERDA);
		}
		if (evento.getKeyCode() == KeyEvent.VK_RIGHT && direccionActual != Direcciones.IZQUIERDA) {
			mapa.getViboras()[viboraNumero].setDireccionActual(Direcciones.DERECHA);
		}
		if (evento.getKeyCode() == KeyEvent.VK_UP && direccionActual != Direcciones.ABAJO) {
			mapa.getViboras()[viboraNumero].setDireccionActual(Direcciones.ARRIBA);
		}
		if (evento.getKeyCode() == KeyEvent.VK_DOWN && direccionActual != Direcciones.ARRIBA) {
			mapa.getViboras()[viboraNumero].setDireccionActual(Direcciones.ABAJO);
		}

		// Vibora numero 2
		viboraNumero = 1;
		Punto direccionActual2 = mapa.getViboras()[viboraNumero].getDireccionActual();

		if (evento.getKeyCode() == KeyEvent.VK_A && direccionActual2 != Direcciones.DERECHA) {
			mapa.getViboras()[viboraNumero].setDireccionActual(Direcciones.IZQUIERDA);
		}
		if (evento.getKeyCode() == KeyEvent.VK_D && direccionActual2 != Direcciones.IZQUIERDA) {
			mapa.getViboras()[viboraNumero].setDireccionActual(Direcciones.DERECHA);
		}
		if (evento.getKeyCode() == KeyEvent.VK_W && direccionActual2 != Direcciones.ABAJO) {
			mapa.getViboras()[viboraNumero].setDireccionActual(Direcciones.ARRIBA);
		}
		if (evento.getKeyCode() == KeyEvent.VK_S && direccionActual2 != Direcciones.ARRIBA) {
			mapa.getViboras()[viboraNumero].setDireccionActual(Direcciones.ABAJO);
		}
//		repaint();
	}


	@Override
	public void run() {

		while (mapa.cantidadViborasVivas() > 0) {
			try {
				Thread.sleep(intervaloDeTiempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			mapa.evaluarMovimientoViborita();
			repaint();
		}
		mostrarGanador();
	}

	private void mostrarGanador() {
		ArrayList<Vibora> ganadoras = new ArrayList<>();
		int ganador = 0;
		for(int i = 0;i<mapa.getViboras().length;i++) {
			if(mapa.getViboras()[i].getPuntaje() > ganador) {
				ganador = mapa.getViboras()[i].getPuntaje();
			}
		}
		for(int i = 0;i<mapa.getViboras().length;i++) {
			if(mapa.getViboras()[i].getPuntaje() == ganador) {
				ganadoras.add(mapa.getViboras()[i]);
			}
		}
		
		mostrarMensajeCorrespondiente(ganador, ganadoras);
	}

	private void mostrarMensajeCorrespondiente(int ganador, ArrayList<Vibora> ganadoras) {
		if(ganador == 0) {
			int n = JOptionPane.showOptionDialog(new JFrame(), "Son horribles", 
			        "Fin del juego", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
			        null, new Object[] {"Aceptar"}, JOptionPane.YES_OPTION);
			        if (n == JOptionPane.YES_OPTION) {
			            this.dispose();
			        }
		}else {
			if(ganadoras.size() > 1) {
				int n = JOptionPane.showOptionDialog(new JFrame(), "EMPATE!!!!!!!", 
				        "Fin del juego", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				        null, new Object[] {"Aceptar"}, JOptionPane.YES_OPTION);

				        if (n == JOptionPane.YES_OPTION) {
				            this.dispose();
				        }
			} else {
				int n = JOptionPane.showOptionDialog(new JFrame(), "Ganador: " + ganadoras.get(0).getColor(), 
				        "Fin del juego", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				        null, new Object[] {"Aceptar"}, JOptionPane.YES_OPTION);

				        if (n == JOptionPane.YES_OPTION) {
				            this.dispose();
				        }
			}
		}
	}
	
	
}
