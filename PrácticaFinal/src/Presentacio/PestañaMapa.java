package Presentacio;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JToolBar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class PestañaMapa extends JPanel {
	private JToolBar toolBar;
	private JButton btnCargarMapa;
	private JButton btnRectangulo;
	private JButton btnTexto;
	private JScrollPane scrollPane;
	private JLabel lblMapa;
	private AreaDibujo lblAreaDibujo;
	private JPanel panel;
	private BufferedImage imagen;
	private RenderedImage imagenGuardado;
	private JFrame frame;
	private Toolkit toolkit;
	private Image imagenRectangulo;
	private Image imagenTexto;
	private AreaDibujo areaDibujo;
	
	private int x;
	private int y;
	private JTextField txtTexto = new JTextField();
	
	int modo = -1;
	private final int RECTANGULO = 1;
	private final int TEXTO = 2;
	private Cursor cursorRectangulo;
	private Cursor cursorTexto;
	/**
	 * Create the panel.
	 */
	public PestañaMapa() {
		setLayout(new BorderLayout(0, 0));
		{
			toolBar = new JToolBar();
			add(toolBar, BorderLayout.NORTH);
			{
				btnCargarMapa = new JButton("");
				btnCargarMapa.addActionListener(new BtnCargarMapaActionListener());
				btnCargarMapa.setIcon(new ImageIcon (PestañaMapa.class.getResource("cargarMapa.png")));
				toolBar.add(btnCargarMapa);
			}
			{
				btnRectangulo = new JButton("");
				btnRectangulo.setIcon(new ImageIcon (PestañaMapa.class.getResource("rectangulo.png")));
				btnRectangulo.addActionListener(new BtnRectanguloActionListener());
				toolBar.add(btnRectangulo);
			}
			{
				btnTexto = new JButton("");
				btnTexto.setIcon(new ImageIcon (PestañaMapa.class.getResource("aniadirTexto.png")));
				btnTexto.addActionListener(new BtnTextoActionListener());
				toolBar.add(btnTexto);
			}
		}
		{
			scrollPane = new JScrollPane();
			add(scrollPane, BorderLayout.CENTER);
			{
				scrollPane.setViewportView(lblMapa);
			
			}
			{
				areaDibujo = new AreaDibujo();
				areaDibujo.addMouseMotionListener(new AreaDibujoMouseMotionListener());
				areaDibujo.addMouseListener(new AreaDibujoMouseListener());
				areaDibujo.setIcon(null);
				scrollPane.setViewportView(areaDibujo);
			}
			toolkit = Toolkit.getDefaultToolkit();
			imagenRectangulo =toolkit.getImage(PestañaMapa.class.getResource("rectangulo.png"));
			imagenTexto =	toolkit.getImage(PestañaMapa.class.getResource("aniadirTexto.png"));
		
			/**Montamos los cursores para saber que tenemos seleccionado en cada momento**/
			try{
				cursorRectangulo = toolkit.createCustomCursor(imagenRectangulo,new Point(0,0),"CURSOR_RECTANGULO");
				cursorTexto= toolkit.createCustomCursor(imagenTexto,new Point(0,0),"CURSOR_TEXTO");
			}catch(Exception e){}
			
		}

	}

	
	

	private class BtnCargarMapaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			/*JFileChooser fcAbrir = new JFileChooser();
			int valorDevuelto = fcAbrir.showOpenDialog(frame);
			if (valorDevuelto == JFileChooser.APPROVE_OPTION) {
				File file = fcAbrir.getSelectedFile();
				imagen = new ImageIcon(file.getAbsolutePath());
				areaDibujo.setIcon(imagen);
			}*/
			JFileChooser fc=new JFileChooser();
			int r=fc.showOpenDialog(null);
			if(r==JFileChooser.APPROVE_OPTION){
				try {
					imagen=ImageIO.read(fc.getSelectedFile().toURL());
					//extension=fc.getSelectedFile().toURL().toString().substring(fc.getSelectedFile().toURL().toString().length()-3, fc.getSelectedFile().toURL().toString().length()).toUpperCase();
					//System.out.println(extension);
					ImageIcon img=new ImageIcon(imagen);
					areaDibujo.setIcon(img);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private class BtnRectanguloActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			modo = RECTANGULO;
			//frame.setCursor(cursorRectangulo);

		}
	}
	private class BtnTextoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			modo = TEXTO;
			//frame.setCursor(cursorTexto);

		}
	}
	private class AreaDibujoMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			if (imagen != null){
				switch (modo){
				case RECTANGULO:
					areaDibujo.addObjetoGrafico(new PintarRectangulo(x,y,x,y,Color.BLUE));
					break;
				case TEXTO:
					txtTexto.setBounds(x, y, 200,30);
					txtTexto.setVisible(true);
					txtTexto.requestFocus();
					txtTexto.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							if(!txtTexto.getText().equals(""))
								areaDibujo.addObjetoGrafico(new PintarTexto(x, y+15, txtTexto.getText(),Color.BLACK));
							txtTexto.setText("");
							txtTexto.setVisible(false);
							areaDibujo.repaint();
						}
					});
					areaDibujo.add(txtTexto);
					break;
			}

		}
	}
}
	private class AreaDibujoMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			if (modo == RECTANGULO && imagen!=null) {
				((PintarRectangulo)areaDibujo.getUltimoObjetoGrafico()).setX1(e.getX());
				((PintarRectangulo)areaDibujo.getUltimoObjetoGrafico()).setY1(e.getY());
				areaDibujo.repaint();
			}

		}
		}
	
}
	

