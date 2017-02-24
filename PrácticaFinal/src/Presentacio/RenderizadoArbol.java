package Presentacio;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class RenderizadoArbol extends DefaultTreeCellRenderer{
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
		String c = (String)(nodo.getUserObject());
		switch (c){
		case "Informacion":
			setIcon(new ImageIcon(RenderizadoArbol.class.getResource("iconoinicio.gif")));
			break;
		case "Perros":
			setIcon(new ImageIcon(RenderizadoArbol.class.getResource("iconoperro.png")));
			break;
		case "Voluntarios":
			setIcon(new ImageIcon(RenderizadoArbol.class.getResource("iconovoluntario.jpg")));
			break;
		case "Mapa":
			setIcon(new ImageIcon(RenderizadoArbol.class.getResource("iconomapa.png")));
			break;
	}
	return this;
}
}
