package testEntidades;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import entidades.Cabeza;
import entidades.Cuerpo;
import entidades.Mapa;
import entidades.Punto;
import entidades.Vibora;


public class ChoqueTest {
	@Test
	public void choqueDosViboras () {

		
		///Creo dos viboras yendo a la izquierda paralelas juntas
		ArrayList<Punto> partes1 = new ArrayList<>();
		partes1.add(new Punto (3,0));
		partes1.add(new Punto (4,0));
		Cuerpo cuerpo1 = new Cuerpo(partes1);
		
		ArrayList<Punto> partes2 = new ArrayList<>();
		partes2.add(new Punto (3,1));
		partes2.add(new Punto (4,1));
		Cuerpo cuerpo2 = new Cuerpo(partes2);
		
		Vibora v1 = new Vibora(0,new Cabeza(new Punto(2,0)),cuerpo1,"rojo",new Punto(-1,0));
		Vibora v2 = new Vibora(0,new Cabeza(new Punto(2,1)),cuerpo2,"azul",new Punto(-1,0));
		
		Vibora[] viboritas = new Vibora[2];
		viboritas[0] = v1;
		viboritas[1] = v2;
		
		
		Mapa mapa = new Mapa(viboritas,10,10);
		
		///Una se mueve pa arriba, otra pa abajo
		Punto p1 = new Punto(0,-1);
		Punto p2 = new Punto(0,1);
		Punto[] movimientos = new Punto[2];
		movimientos[0] = p1;
		movimientos[1] = p2;
		
		///Antes vivas
		Assert.assertEquals(false,v1.isMuerta()); ///v1 muere
		Assert.assertEquals(false,v2.isMuerta()); ///v2 muere
		
		mapa.evaluarMovimientoViborita(movimientos);
		
		///Ahora mueren ambas
		Assert.assertEquals(true,v1.isMuerta()); ///v1 muere
		Assert.assertEquals(true,v2.isMuerta()); ///v2 muere
	}
		
		
	
	
}
