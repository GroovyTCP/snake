package viborita.entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SnakeGrafico extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int sepacionEntreJugadores = 30;
	
	private Mapa mapa;
	
	public SnakeGrafico(Mapa mapa) {
		this.mapa = mapa;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for (Vibora snake : this.mapa.getViboras()) {
			if (!snake.isMuerta()) {
				g.setColor(snake.getColor().darker());
				g.fillRect(snake.getCabeza().getPosicion().getX(), snake.getCabeza().getPosicion().getY(), 10, 10);
				
				g.setColor(snake.getColor().brighter());
				
				for (int i = 0; i < snake.getCuerpo().getCantidad(); i++)
					g.fillRect(snake.getCuerpo().getCuerpo().get(i).getX(), snake.getCuerpo().getCuerpo().get(i).getY(), 10, 10);
				
			}
			
		}
		g.setColor(Color.GREEN);
		g.fillOval(this.mapa.getFruta().getPosicion().getX(), this.mapa.getFruta().getPosicion().getY(), 10, 10);
		
		/// Blanco = Jugador Vivo - Rojo = Jugador Muerto
		g.setColor(Color.GRAY);
		g.fillRect(mapa.getAncho() + 10,0, Mapa.getAnchotabladepuntos(), mapa.getAlto() + 10);
		int nroJugador = 0;
		g.setFont(new Font("ComicSans", Font.PLAIN, 15));
		g.setColor(Color.WHITE);
		g.drawString("Jugador", mapa.getAncho()+30, 20);
		g.drawString("Color", mapa.getAncho()+Mapa.getAnchotabladepuntos()/2, 20);
		g.drawString("Puntaje", mapa.getAncho()+Mapa.getAnchotabladepuntos()-Mapa.getAnchotabladepuntos()/4, 20);
		
		for (Vibora e: mapa.getViboras()) {
			g.setColor(e.isMuerta()?Color.RED:Color.WHITE);
			//g.drawString(e.isMuerta()?"�":"", mapa.getAncho()+1, 60+i*20);
			
			///g.drawString("Jugador " +(nroJugador+1), mapa.getAncho()+10, 60+nroJugador*sepacionEntreJugadores);
			g.drawString(e.getNombre(), mapa.getAncho()+30, 60+nroJugador*sepacionEntreJugadores);
			g.setColor(e.getColor());
			g.fillRect(mapa.getAncho()+Mapa.getAnchotabladepuntos()/2,50+nroJugador*sepacionEntreJugadores, 50, 10);
			g.setColor(Color.WHITE);
			g.drawString(e.getPuntaje()+"", mapa.getAncho()+Mapa.getAnchotabladepuntos()-Mapa.getAnchotabladepuntos()/4, 60+nroJugador*sepacionEntreJugadores);
			nroJugador++;
		}

	}
	
	public Vibora[] getVibora() {
		return this.mapa.getViboras();
	}
	
	public void setVibora(Mapa mapa) {
		this.mapa = mapa;
	}
}
