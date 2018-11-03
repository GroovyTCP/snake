package entidades;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import viborita.entidades.Fruta;
import viborita.entidades.Mapa;
import viborita.entidades.Vibora;

public class SnakeGrafico extends JPanel {

	private static final long serialVersionUID = 1L;
	//private Vibora snake;
	private Mapa mapa;
	
	public SnakeGrafico(Mapa mapa) {
		this.mapa = mapa;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for (Vibora snake : this.mapa.getViboras()) {
			if (!snake.isMuerta()) {
				g.setColor(snake.getColor().darker());
				g.fillRect(snake.getCabeza().getCabeza().getX(), snake.getCabeza().getCabeza().getY(), 10, 10);
				
				g.setColor(snake.getColor().brighter());
				
				for (int i = 0; i < snake.getCuerpo().getCantidad(); i++)
					g.fillRect(snake.getCuerpo().getCuerpo().get(i).getX(), snake.getCuerpo().getCuerpo().get(i).getY(), 10, 10);
				
			}
			
		}
		for (Fruta fruit : this.mapa.getFrutas()) {
			g.setColor(Color.GREEN);
			g.fillOval(fruit.getPosicion().getX(), fruit.getPosicion().getY(), 10, 10);
		}
	}
	
	public Vibora[] getVibora() {
		return this.mapa.getViboras();
	}
	
	public void setVibora(Mapa mapa) {
		this.mapa = mapa;
	}
}
