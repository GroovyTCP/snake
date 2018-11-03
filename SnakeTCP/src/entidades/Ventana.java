package entidades;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import viborita.entidades.Cabeza;
import viborita.entidades.Cuerpo;
import viborita.entidades.Mapa;
import viborita.entidades.Punto;
import viborita.entidades.Vibora;


// Serpiente roja, se mueve con las flechitas y la otra con WASD

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Mapa juego;
	
	public Ventana(Mapa mapa) {
		super("Snake");
		setResizable(false);
		juego = mapa;
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent tecla) {
				setMovimiento(tecla);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, mapa.getAncho(), mapa.getLargo());
		
		contentPane = new SnakeGrafico(mapa);
		setBackground(Color.BLACK);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
		
		int temporizadorFruta = 0;
		
		while(mapa.cantidadViborasVivas() > 0) {
			try {
				Thread.sleep(200);
				temporizadorFruta++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (temporizadorFruta % 3 == 0) {
				juego.mandarFruta();
				temporizadorFruta = 0;
			}
			juego.evaluarMovimientoViborita();
			repaint();
		}
		
		//dispose();
		
	}
	
	public void setMovimiento(KeyEvent evento){

		System.out.println("-------------------------------");
		
		int viboraNumero = 0;
		Punto direccionActual = juego.getViboras()[viboraNumero].getDireccionActual();
		
		if(evento.getKeyCode() == KeyEvent.VK_LEFT && direccionActual != Direcciones.DERECHA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.IZQUIERDA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_RIGHT && direccionActual != Direcciones.IZQUIERDA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.DERECHA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_UP && direccionActual != Direcciones.ABAJO) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.ARRIBA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_DOWN && direccionActual != Direcciones.ARRIBA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.ABAJO);
		}
		
		// Vibora numero 2
		viboraNumero = 1;
		Punto direccionActual2 = juego.getViboras()[viboraNumero].getDireccionActual();
		
		if(evento.getKeyCode() == KeyEvent.VK_A && direccionActual2 != Direcciones.DERECHA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.IZQUIERDA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_D && direccionActual2 != Direcciones.IZQUIERDA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.DERECHA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_W && direccionActual2 != Direcciones.ABAJO) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.ARRIBA);
		}
		if(evento.getKeyCode() == KeyEvent.VK_S && direccionActual2 != Direcciones.ARRIBA) {
			juego.getViboras()[viboraNumero].setDireccionActual(Direcciones.ABAJO);
		}
		repaint();
	}
	
	public static void main(String[] args) {
		Vibora[] snake = new Vibora[2];
		snake[0] = new Vibora(new Cabeza(new Punto(100, 100)), Color.RED, Direcciones.DERECHA);
		ArrayList<Punto> cuerpo = new ArrayList<Punto>();
		cuerpo.add(new Punto(90, 100));
		cuerpo.add(new Punto(80, 100));
		cuerpo.add(new Punto(70, 100));
		cuerpo.add(new Punto(60, 100));
		snake[0].setCuerpo(new Cuerpo(cuerpo));
		
		snake[1] = new Vibora(new Cabeza(new Punto(160, 160)), Color.CYAN, Direcciones.DERECHA);
		
		Mapa game = new Mapa(snake, 300, 300);
		new Ventana(game);
	}
}
