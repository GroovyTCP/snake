package entidades;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SnakeGrafico extends JPanel {

	private static final long serialVersionUID = 1L;
	private Vibora snake;
	
	public SnakeGrafico(Vibora snake) {
		this.snake = snake;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(this.snake.getCabeza().getCabeza().getX(), this.snake.getCabeza().getCabeza().getY(), 10, 10);
		
		g.setColor(Color.GREEN);
		for (int i = 0; i < snake.getCuerpo().getCantidad(); i++)
			g.fillRect(this.snake.getCuerpo().getCuerpo().get(i).getX(), this.snake.getCuerpo().getCuerpo().get(i).getY(), 10, 10);

	}
	
	public Vibora getVibora() {
		return this.snake;
	}
	
	public void setVibora(Vibora snake) {
		this.snake = snake;
	}
}
