package Presentacio;
import Presentacio.Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JTree tree;
	private JPanel panelCard;
	private JPanel pnlVoluntarios;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenu mnAyuda;
	private JMenuItem mntmAcercaDe;
	private JPanel pnlPerros;
	private JPanel pnlMapa;
	private JPanel pnlInformacionRegistro;
	private static Registrados Registradoaux = new Registrados();
	private String fechaActual= "";
	private static Hashtable <String, Registrados> tabla = new Hashtable();
	private JMenuItem mntmSalir;
	private JMenuItem mntmAyuda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventana = new VentanaPrincipal();
					ventana.setVisible(true);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	//public VentanaPrincipal (){
		/*Obtenemos la fecha actual, modificamos el registro y guardamos la nueva informaci�n.
		 * Al mismo tiempo, utilizamos una variable auxiliar de tipo Registrados para almacenar 
		 * los datos del usuario que hizo el registro
		 * */
		
		

	//}
	public VentanaPrincipal() throws FileNotFoundException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900,650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			splitPane = new JSplitPane();
			contentPane.add(splitPane, BorderLayout.CENTER);
			{
				scrollPane = new JScrollPane();
				splitPane.setLeftComponent(scrollPane);
				{
					tree = new JTree();
					tree.addTreeSelectionListener(new TreeTreeSelectionListener());
					tree.setModel(new DefaultTreeModel(
						new DefaultMutableTreeNode("Informacion") {
							{
								DefaultMutableTreeNode node_1;
							node_1 = new DefaultMutableTreeNode("Perros");
								add(node_1);
							node_1 = new DefaultMutableTreeNode("Voluntarios");
								add(node_1);
							node_1 = new DefaultMutableTreeNode("Mapa");
								add(node_1);
							}
						}
					));
					scrollPane.setViewportView(tree);
					tree.setCellRenderer(new RenderizadoArbol());
				}
			}
			{
				panelCard = new JPanel();
				splitPane.setRightComponent(panelCard);
				panelCard.setLayout(new CardLayout(0, 0));
				
				{
					pnlPerros = new PestañaPerrosTabla();
					panelCard.add(pnlPerros, "Perros");
					
				}
				{
					pnlVoluntarios = new PestañaVoluntarios();
					panelCard.add(pnlVoluntarios, "Voluntarios");
				}
				{
					pnlMapa = new PestañaMapa();
					panelCard.add(pnlMapa, "Mapa");
				}
			}
		}
		{
			menuBar = new JMenuBar();
			contentPane.add(menuBar, BorderLayout.NORTH);
			{
				mnArchivo = new JMenu(MessagesVentanaPrincipalInter.getString("VentanaPrincipal.mnArchivo.text")); //$NON-NLS-1$
				menuBar.add(mnArchivo);
				{
					mntmAcercaDe = new JMenuItem(MessagesVentanaPrincipalInter.getString("VentanaPrincipal.mntmAcercaDe.text")); //$NON-NLS-1$
					mnArchivo.add(mntmAcercaDe);
					{
						mntmSalir = new JMenuItem(MessagesVentanaPrincipalInter.getString("VentanaPrincipal.mntmSalir.text")); //$NON-NLS-1$
						mntmSalir.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("cross.png")));
						mntmSalir.addActionListener(new MntmSalirActionListener());
						mnArchivo.add(mntmSalir);
					}
					mntmAcercaDe.addActionListener(new MntmAcercaDeActionListener());
				}
			}
			{
				mnAyuda = new JMenu(MessagesVentanaPrincipalInter.getString("VentanaPrincipal.mnAyuda.text")); //$NON-NLS-1$
				menuBar.add(mnAyuda);
				{
					mntmAyuda = new JMenuItem(MessagesVentanaPrincipalInter.getString("VentanaPrincipal.mntmAyuda.text")); //$NON-NLS-1$
					mntmAyuda.addActionListener(new MntmAyudaActionListener());
					mntmAyuda.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("ayuda.png")));
					mnAyuda.add(mntmAyuda);
				}
			}
		}
	}

	private class TreeTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent e) {
			System.out.println("Nodo seleccionado "+
					e.getPath().getLastPathComponent());
					String nodo = (e.getPath().getLastPathComponent()).toString();
					switch (nodo)
					{
					case "Perros":
					case "Voluntarios":
					case "Mapa":
					((CardLayout) panelCard.getLayout()).show(panelCard, nodo);
			
					}
		}
	}
	private class MntmAcercaDeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Creado por Jesus Enrique Aparicio\n"
					+ "y Alejandro Moreno");
		}
	}
	private class MntmSalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
	private class MntmAyudaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "En cualquiero caso de duda sobre el funcionamiento"
					+ "consulte el manual de usuario");
		}
	}
	
}

