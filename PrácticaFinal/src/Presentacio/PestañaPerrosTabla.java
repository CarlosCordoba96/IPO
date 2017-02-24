package Presentacio;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class PestañaPerrosTabla extends JPanel {
	private JPanel pnlInformacion;
	private JToolBar toolBar;
	private JButton btnAadir;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneFoto;
	private JTable tablaPerros;
	private List <Perros> perros = new ArrayList();
	private JPanel pnlDatos;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblRaza;
	private JTextField txtRaza;
	private JLabel lblSexo;
	private JTextField txtSexo;
	private JLabel lblVacunado;
	private JCheckBox chckbxVacunado;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JButton btnGuardar;
	private JPanel panel;
	private JLabel lblFoto;
	
	private BufferedImage imagen;

	/**
	 * Create the panel.
	 */
	public PestañaPerrosTabla() {
		setBorder(new TitledBorder(null, MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.this.borderTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null)); //$NON-NLS-1$
		setLayout(new BorderLayout(0, 0));
		{
			toolBar = new JToolBar();
			add(toolBar, BorderLayout.NORTH);
			{
				btnAadir = new JButton(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.btnAadir.text")); //$NON-NLS-1$
				btnAadir.addActionListener(new BtnAadirActionListener());
				btnAadir.setIcon(new ImageIcon (PestañaPerrosTabla.class.getResource("iconoanadir.png"))); //$NON-NLS-1$
				toolBar.add(btnAadir);
			}
			{
				btnEliminar = new JButton(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.btnEliminar.text")); //$NON-NLS-1$
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new BtnEliminarActionListener());
				btnEliminar.setIcon(new ImageIcon (PestañaPerrosTabla.class.getResource("iconoeliminar.png"))); //$NON-NLS-1$
				toolBar.add(btnEliminar);
			}
			{
				btnModificar = new JButton(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.btnModificar.text")); //$NON-NLS-1$
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new BtnModificarActionListener());
				btnModificar.setIcon(new ImageIcon (PestañaPerrosTabla.class.getResource("iconomodificar.png"))); //$NON-NLS-1$
				toolBar.add(btnModificar);
			}
			{
				btnGuardar = new JButton(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.btnGuardar.text")); //$NON-NLS-1$
				btnGuardar.setEnabled(false);
				btnGuardar.addActionListener(new BtnGuardarActionListener());
				btnGuardar.setIcon(new ImageIcon (PestañaPerrosTabla.class.getResource("iconoguardar.gif"))); //$NON-NLS-1$
				toolBar.add(btnGuardar);
			}
		}
		{
			scrollPane = new JScrollPane();
			add(scrollPane, BorderLayout.CENTER);
			{
				tablaPerros = new JTable();
				tablaPerros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				ListSelectionModel rowSM = tablaPerros.getSelectionModel();
				rowSM.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					ModeloTablaPerros modeloT = (ModeloTablaPerros) tablaPerros.getModel();
					int n = tablaPerros.getSelectedRow();
					try{	
						if (n!=-1){
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
							txtNombre.setText(perros.get(n).getNombre());
							txtRaza.setText(perros.get(n).getRaza());
							txtSexo.setText(perros.get(n).getSexo());
							chckbxVacunado.setSelected(perros.get(n).getVacunado());
							txtEstado.setText(perros.get(n).getEstado());
							System.out.println(perros.get(n).getNombre());
							lblFoto.setIcon(new ImageIcon(this.getClass().getResource(perros.get(n).getNombre()+".jpg"))); //$NON-NLS-1$
						}
					}catch (NullPointerException e1){}
					}
				}
				});

				ModeloTablaPerros modeloTabla= new ModeloTablaPerros();
				tablaPerros.setModel(modeloTabla);
				scrollPane.setViewportView(tablaPerros);
			}
		}
		{
			pnlInformacion = new JPanel();
			pnlInformacion.setPreferredSize(new Dimension(50, 180));
			add(pnlInformacion, BorderLayout.SOUTH);
			pnlInformacion.setLayout(new GridLayout(1, 2, 0, 0));
			{
				scrollPaneFoto = new JScrollPane();
				pnlInformacion.add(scrollPaneFoto);
				{
					panel = new JPanel();
					panel.setBorder(new TitledBorder(null, MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.panel.borderTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null)); //$NON-NLS-1$
					scrollPaneFoto.setViewportView(panel);
					{
						lblFoto = new JLabel(); //$NON-NLS-1$
						panel.add(lblFoto);
					}
				}
			}
			{
				pnlDatos = new JPanel();
				pnlInformacion.add(pnlDatos);
				pnlDatos.setLayout(new GridLayout(0, 2, 0, 0));
				{
					lblNombre = new JLabel(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.lblNombre.text")); //$NON-NLS-1$
					pnlDatos.add(lblNombre);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setEditable(false);
					pnlDatos.add(txtNombre);
					txtNombre.setColumns(10);
				}
				{
					lblRaza = new JLabel(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.lblRaza.text")); //$NON-NLS-1$
					pnlDatos.add(lblRaza);
				}
				{
					txtRaza = new JTextField();
					txtRaza.setEditable(false);
					pnlDatos.add(txtRaza);
					txtRaza.setColumns(10);
				}
				{
					lblSexo = new JLabel(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.lblSexo.text")); //$NON-NLS-1$
					pnlDatos.add(lblSexo);
				}
				{
					txtSexo = new JTextField();
					txtSexo.setEditable(false);
					pnlDatos.add(txtSexo);
					txtSexo.setColumns(10);
				}
				{
					lblVacunado = new JLabel(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.lblVacunado.text")); //$NON-NLS-1$
					pnlDatos.add(lblVacunado);
				}
				{
					chckbxVacunado = new JCheckBox(); //$NON-NLS-1$
					chckbxVacunado.setEnabled(false);
					chckbxVacunado.addChangeListener(new ChckbxVacunadoChangeListener());
					chckbxVacunado.setHorizontalAlignment(SwingConstants.CENTER);
					pnlDatos.add(chckbxVacunado);
				}
				{
					lblEstado = new JLabel(MessagesPerrosInter.getString("Pesta\u00F1aPerrosTabla.lblEstado.text")); //$NON-NLS-1$
					pnlDatos.add(lblEstado);
				}
				{
					txtEstado = new JTextField();
					txtEstado.setEditable(false);
					pnlDatos.add(txtEstado);
					txtEstado.setColumns(10);
				}
			}
		}
		cargarFichero();

	}
	public void cargarFichero() {
		String nombre;
		String raza;
		String sexo;
		String vacunado;
		String estado;
		Scanner leer;
		boolean vac=false;
		ModeloTablaPerros modelTable = (ModeloTablaPerros) tablaPerros.getModel();
		try {
			leer = new Scanner(new File("Perros.txt")); //$NON-NLS-1$
			while(leer.hasNext()){
				nombre=leer.nextLine();
				raza= leer.nextLine();
				sexo = leer.nextLine();
				vacunado = leer.nextLine();
				estado = leer.nextLine();
				if (vacunado.equalsIgnoreCase("true"))vac=true; //$NON-NLS-1$
				else vac=false;
				Object[] nuevaFila = {nombre, raza,sexo};
				modelTable.aniadeFila(nuevaFila);
				Perros perro=new Perros(nombre,raza, sexo, vac, estado);
				perros.add(perro);
			}
			leer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}

	public void guardarFichero(List <Perros> perro){
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("Perros.txt"); //$NON-NLS-1$
	            pw = new PrintWriter(fichero);
	            for (int i = 0; i<perro.size();i++)
	                pw.println(perro.get(i).toStringFichero());
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           // Nuevamente aprovechamos el finally para 
	           // asegurarnos que se cierra el fichero.
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	}
	}
	private class BtnAadirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			perros.add(new Perros());
			ModeloTablaPerros modeloTabla = (ModeloTablaPerros) tablaPerros.getModel();
			Object[] nuevaFila = {"...", "...","..."}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			modeloTabla.aniadeFila(nuevaFila);
			modeloTabla.fireTableDataChanged();
			cargarFotoNueva();
			
		}
	}
	private void cargarFotoNueva(){
		JFileChooser fc=new JFileChooser();
		int r=fc.showOpenDialog(null);
		if(r==JFileChooser.APPROVE_OPTION){
			try {
				imagen=ImageIO.read(fc.getSelectedFile().toURL());
				//extension=fc.getSelectedFile().toURL().toString().substring(fc.getSelectedFile().toURL().toString().length()-3, fc.getSelectedFile().toURL().toString().length()).toUpperCase();
				//System.out.println(extension);
				ImageIcon img=new ImageIcon(imagen);
				lblFoto.setIcon(img);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class ChckbxVacunadoChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			int seleccionado = tablaPerros.getSelectedRow();
			if (chckbxVacunado.isSelected()){
				
				perros.get(seleccionado).setVacunado(true);
			}
			else perros.get(seleccionado).setVacunado(false);
		}
	}
	private class BtnModificarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {			
				txtNombre.setEditable(true);
				txtRaza.setEditable(true);
				txtSexo.setEditable(true);
				chckbxVacunado.setEnabled(true);
				chckbxVacunado.setFocusable(true);
				txtEstado.setEditable(true);
				btnGuardar.setEnabled(true);
		
		}
	}

	private class BtnEliminarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int seleccion=tablaPerros.getSelectedRow();
			try{
			perros.remove(seleccion);
			
			txtNombre.setText(""); //$NON-NLS-1$
			txtRaza.setText(""); //$NON-NLS-1$
			txtSexo.setText(""); //$NON-NLS-1$
			chckbxVacunado.setSelected(false);
			txtEstado.setText(""); //$NON-NLS-1$
			ModeloTablaPerros modelo = (ModeloTablaPerros)tablaPerros.getModel(); 
			modelo.eliminaFila(seleccion);
			modelo.fireTableDataChanged();
			guardarFichero(perros);
			lblFoto.setIcon(null);	
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, MessagesPerrosInter.getString("PestañaPerrosTabla.NoSePudoCerrarPane")); //$NON-NLS-1$
			}
		
	}
	}
	private class BtnGuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int seleccion = tablaPerros.getSelectedRow();
			perros.get(seleccion).setNombre(txtNombre.getText());
			perros.get(seleccion).setRaza(txtRaza.getText());
			perros.get(seleccion).setSexo(txtSexo.getText());
			perros.get(seleccion).setVacunado(chckbxVacunado.isSelected());
			perros.get(seleccion).setEstado(txtEstado.getText());
			btnGuardar.setEnabled(false);
			guardarFotoCargada();
			guardarFichero(perros);
			txtNombre.setEditable(false);
			txtRaza.setEditable(false);
			txtSexo.setEditable(false);
			chckbxVacunado.setEnabled(false);
			chckbxVacunado.setFocusable(false);
			txtEstado.setEditable(false);
			btnGuardar.setEnabled(false);
			cambiarTabla();
		}
	}
	
	private void cambiarTabla(){
		ModeloTablaPerros modelo =(ModeloTablaPerros)tablaPerros.getModel();
		for (int j= 0; j<perros.size(); j++)
			modelo.eliminaFila(0);
		for (int i = 0; i<perros.size();i++){
			Object[] nuevaFila = {perros.get(i).getNombre(), perros.get(i).getRaza(),perros.get(i).getSexo()};
			modelo.aniadeFila(nuevaFila);
		}
		tablaPerros.setModel(modelo);
	}

	private void guardarFotoCargada(){
		JFileChooser fc=new JFileChooser();
		int r=fc.showSaveDialog(null);
		if(r==JFileChooser.APPROVE_OPTION){
			File archivo=fc.getSelectedFile();
			try {
				ImageIO.write(imagen, "jpg", archivo); //$NON-NLS-1$
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	

}
