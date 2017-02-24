package Presentacio;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AreaDibujo extends JLabel{
	private ArrayList<PintarArea> objetosGraficos = new ArrayList<PintarArea>();
	public AreaDibujo(){}
	
	void addObjetoGrafico(PintarArea objg) {
		objetosGraficos.add(objg);
	}
	
	public PintarArea getUltimoObjetoGrafico(){
		return objetosGraficos.get(objetosGraficos.size()-1);
	}
	
public void paint(Graphics g){
		super.paint(g);
		for (int i = 0; i < objetosGraficos.size(); i++) {
			PintarArea objg = objetosGraficos.get(i);
			if (objg instanceof ImagenGrafico){
				g.drawImage(((ImagenGrafico)objg).getImagen(), objg.getX(),objg.getY(), null);
			}else if (objg instanceof PintarRectangulo){
				g.setColor(((PintarRectangulo)objg).getColor());
				int w = ((PintarRectangulo)objg).getX1() - objg.getX();
				int h = ((PintarRectangulo)objg).getY1() - objg.getY();
				g.drawRect(objg.getX(),objg.getY(),w,h);
			}else { //Es un objeto de tipo TextoGrafico
				g.setColor(((PintarTexto)objg).getColor());
				g.drawString(((PintarTexto)objg).getTexto(),objg.getX(),objg.getY());
			}


		} 
	}
}