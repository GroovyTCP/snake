package testEntidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entidades.Vibora;
import entidades.Cabeza;
import entidades.Cuerpo;
import entidades.Fruta;
import entidades.Punto;

public class ComerCrecerTest {

	@Test
	public void ComeDosFrutasYCrece () {
		ArrayList<Punto> partes = new ArrayList<>();
		partes.add(new Punto (3,1));
		partes.add(new Punto (4,1));
		Cuerpo cuerpo = new Cuerpo(partes);
		
		Vibora v1 = new Vibora(0,new Cabeza(new Punto(2,1)),cuerpo,"rojo",new Punto(1,0));
	
		Fruta frutin1 = new Fruta(new Punto(1,1),100);
		Fruta frutin2 = new Fruta(new Punto(2,0),100);
		
		v1.comerFruta(frutin1, new Punto(1,1));
		v1.comerFruta(frutin2, new Punto(2,-1));
		

		Assert.assertEquals(200,v1.getPuntaje()); ///200 PUNTOS
		Assert.assertEquals(4, v1.getCuerpo().getCuerpo().size()); ///AHORA TIENE 4 PARTES
		
		
	}
}
