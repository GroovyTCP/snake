package testEntidades;

import entidades.Punto;
import entidades.Cuerpo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CuerpoTest {

	@Test
	public void moverCuerpo() {
		///Inicialmente la cabeza esta en (4,1)
		///La cabeza ya se movio hacia arriba
		List<Punto> partes = new ArrayList<>();

		partes.add(new Punto(5,1));
		partes.add(new Punto(6,1));
		partes.add(new Punto(6,2));		
		
		List<Punto> resultado = new ArrayList<>();
		
		resultado.add(new Punto(4,1));
		resultado.add(new Punto(5,1));
		resultado.add(new Punto(6,1));
		
		Cuerpo cuerpo = new Cuerpo(partes);
		
		cuerpo.moverCuerpo(new Punto (4,1));
		
		
	}
}
