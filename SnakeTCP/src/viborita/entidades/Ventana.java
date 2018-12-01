package viborita.entidades;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// Serpiente roja, se mueve con las flechitas y la otra con WASD


public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int intervaloDeTiempo = 60;
	
	private static boolean finDelJuego = false;

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
						"�Est�s seguro que deseas abandonar la partida?", "�Abandonar partida?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (jPaneResult == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					finDelJuego = true;
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
//		setBounds(0, 0, mapa.getAncho()+Mapa.getAnchotabladepuntos(), mapa.getAlto());
		
		contentPane = new SnakeGrafico(mapa);
		
		contentPane.setPreferredSize(new Dimension(mapa.getAncho()+Mapa.getAnchotabladepuntos() + 10, mapa.getAlto() + 10));
		
		setBackground(Color.BLACK);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	private void setMovimiento(KeyEvent evento) {

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


	//@Override
	public void run() {
		
//		mapa.ponerNombreViboras();

		while (mapa.cantidadViborasVivas() > 0) {
			try {
				Thread.sleep(intervaloDeTiempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			mapa.evaluarMovimientoViborita();
			repaint();
		}
		if(!finDelJuego)
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
				int n = JOptionPane.showOptionDialog(new JFrame(), "Ganador: " + ganadoras.get(0).getNombre(), ///ganadoras.get(0).getColor(), 
				        "Fin del juego", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
				        null, new Object[] {"Aceptar"}, JOptionPane.YES_OPTION);

				        if (n == JOptionPane.YES_OPTION) {
				            this.dispose();
				        }
			}
		}
	}
	
	public static void main(String[] args) {
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
		
		new Ventana(game).run();
	}
}
