package Presentacio;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;

import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;

public class PestañaVoluntarios extends JPanel {
	private JToolBar toolBar;
	private JButton btnAadir;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JPanel pnlInformacion;
	private JScrollPane scrollPaneFoto;
	private List <Voluntario> voluntarios = new ArrayList<Voluntario>();
	private JPanel panel;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblTelfono;
	private JTextField txtTelefono;
	private JLabel lblDni;
	private JFormattedTextField txtDni;
	private JLabel lblEmail;
	private JTextField txtemail;
	private JLabel lblHorario;
	private JFormattedTextField txtHorario;
	private JLabel lblZonaAsignada;
	private JTextField txtZonaAsignada;
	private JButton btnGuardar;
	private JTable tablaVoluntarios;
	private JPanel panel_1;
	private JLabel lblFoto;

	private BufferedImage imagen;

	/**
	 * Create the panel.
	 */
	public PestañaVoluntarios() {
		setBorder(new TitledBorder(null, MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.this.borderTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null)); //$NON-NLS-1$
		setLayout(new BorderLayout(0, 0));
		{
			toolBar = new JToolBar();
			toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(toolBar, BorderLayout.NORTH);
			{
				btnAadir = new JButton(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.btnAadir.text")); //$NON-NLS-1$
				btnAadir.addActionListener(new BtnAadirActionListener());
				btnAadir.setIcon(new ImageIcon (PestañaPerrosTabla.class.getResource("iconoanadir.png")));
				toolBar.add(btnAadir);
			}
			{
				btnEliminar = new JButton(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.btnEliminar.text")); //$NON-NLS-1$
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new BtnEliminarActionListener());
				btnEliminar.setIcon(new ImageIcon (PestañaPerrosTabla.class.getResource("iconoeliminar.png")));
				toolBar.add(btnEliminar);
			}
			{
				btnModificar = new JButton(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.btnModificar.text")); //$NON-NLS-1$
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new BtnModificarActionListener());
				btnModificar.setIcon(new ImageIcon (PestañaPerrosTabla.class.getResource("iconomodificar.png")));
				toolBar.add(btnModificar);
			}
			{
				btnGuardar = new JButton(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.btnGuardar.text")); //$NON-NLS-1$
				btnGuardar.setEnabled(false);
				btnGuardar.addActionListener(new BtnGuardarActionListener());
				btnGuardar.setIcon(new ImageIcon(PestañaVoluntarios.class.getResource("/Presentacion/iconoguardar.gif")));
				toolBar.add(btnGuardar);
			}
		}
		{
			scrollPane = new JScrollPane();
			add(scrollPane, BorderLayout.CENTER);
			{
				tablaVoluntarios = new JTable();
			
				tablaVoluntarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				ListSelectionModel rowSM = tablaVoluntarios.getSelectionModel();
				rowSM.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					ModeloTablaVoluntarios modeloT = (ModeloTablaVoluntarios) tablaVoluntarios.getModel();
					int n = tablaVoluntarios.getSelectedRow();
					try{	
						if (n!=-1){
							btnEliminar.setEnabled(true);
							btnModificar.setEnabled(true);
							txtNombre.setText(voluntarios.get(n).getNombre());
							txtTelefono.setText(Integer.toString(voluntarios.get(n).getTelefono()));
							txtDni.setText(voluntarios.get(n).getDni());
							txtemail.setText(voluntarios.get(n).getEmail());
							txtHorario.setText(voluntarios.get(n).getHorario());
							txtZonaAsignada.setText(voluntarios.get(n).getZona_asignada());
							lblFoto.setIcon(new ImageIcon(this.getClass().getResource(voluntarios.get(n).getNombre()+".jpg")));
						}
					}catch (NullPointerException e1){}
					}
				}
				});						
				
				ModeloTablaVoluntarios modeloTabla= new ModeloTablaVoluntarios();
				tablaVoluntarios.setModel(modeloTabla);
				scrollPane.setViewportView(tablaVoluntarios);
				
				
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
					panel_1 = new JPanel();
					panel_1.setBorder(new TitledBorder(null, MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.panel_1.borderTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null)); //$NON-NLS-1$
					scrollPaneFoto.setViewportView(panel_1);
					{
						lblFoto = new JLabel(); //$NON-NLS-1$
						panel_1.add(lblFoto);
					}
				}
			}
			{
				panel = new JPanel();
				pnlInformacion.add(panel);
				panel.setLayout(new GridLayout(0, 2, 0, 0));
				{
					lblNombre = new JLabel(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.lblNombre.text")); //$NON-NLS-1$
					panel.add(lblNombre);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setEditable(false);
					panel.add(txtNombre);
					txtNombre.setColumns(10);
				}
				{
					lblTelfono = new JLabel(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.lblTelfono.text")); //$NON-NLS-1$
					panel.add(lblTelfono);
				}
				{
					txtTelefono = new JTextField();
					MaskFormatter formatoTlfno;
					try {
						formatoTlfno = new MaskFormatter(("### ### ###'")); //$NON-NLS-1$
						formatoTlfno.setPlaceholderCharacter('*');
						txtTelefono = new JFormattedTextField(formatoTlfno);
					} catch (ParseException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
					txtTelefono.setEditable(false);
					panel.add(txtTelefono);
					txtTelefono.setColumns(10);
				}
				{
					lblDni = new JLabel(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.lblDni.text")); //$NON-NLS-1$
					panel.add(lblDni);
				}
				{
					MaskFormatter formatoDNI;
					try {
						formatoDNI = new MaskFormatter(("########'-U")); //$NON-NLS-1$
						formatoDNI.setPlaceholderCharacter('X');
						txtDni = new JFormattedTextField(formatoDNI);
					} catch (ParseException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
					txtDni.setEditable(false);
					panel.add(txtDni);
					txtDni.setColumns(10);
				}
				{
					lblEmail = new JLabel(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.lblEmail.text")); //$NON-NLS-1$
					panel.add(lblEmail);
				}
				{
					txtemail = new JTextField();
					txtemail.setEditable(false);
					panel.add(txtemail);
					txtemail.setColumns(10);
				}
				{
					lblHorario = new JLabel(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.lblHorario.text")); //$NON-NLS-1$
					panel.add(lblHorario);
				}
				{
					MaskFormatter formato;
					try {
						formato = new MaskFormatter("##':##'-##':##'");
						formato.setPlaceholderCharacter('X');
						txtHorario = new JFormattedTextField(formato);
						txtHorario.setEditable(false);
						panel.add(txtHorario);
						txtHorario.setColumns(10);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//txtHorario = new JFormattedTextField(formato);

				}
				{
					lblZonaAsignada = new JLabel(MessagesVoluntariosInter.getString("Pesta\u00F1aVoluntarios.lblZonaAsignada.text")); //$NON-NLS-1$
					panel.add(lblZonaAsignada);
				}
				{
					txtZonaAsignada = new JTextField();
					txtZonaAsignada.setEditable(false);
					panel.add(txtZonaAsignada);
					txtZonaAsignada.setColumns(10);
				}
			}
		}
		cargarFichero();
	}

	public void cargarFichero() {
		String nombre;
		int telefono;
		String dni;
		String email;
		String horario;
		String zona_asignada;

		Scanner leer;
		
		ModeloTablaVoluntarios modelTable = (ModeloTablaVoluntarios) tablaVoluntarios.getModel();
		try {
			leer = new Scanner(new File("Voluntarios.txt"));
			while(leer.hasNext()){
				nombre=leer.nextLine();
				telefono = Integer.parseInt((leer.nextLine()));
				dni = leer.nextLine();
				email= leer.nextLine();
				horario=leer.nextLine();
				zona_asignada=leer.nextLine();
	
				Object[] nuevaFila = {nombre, telefono,dni};
				
				modelTable.aniadeFila(nuevaFila);		
				Voluntario voluntario=new Voluntario(nombre, telefono, dni,email,horario,zona_asignada);
				voluntarios.add(voluntario);
			}
			leer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
	

	private class BtnAadirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			voluntarios.add(new Voluntario());

			ModeloTablaVoluntarios modeloTabla = (ModeloTablaVoluntarios) tablaVoluntarios.getModel();
			Object[] nuevaFila = {"...", "...","..."};
			modeloTabla.aniadeFila(nuevaFila);
			modeloTabla.fireTableDataChanged();
			cargarFotoNueva();
		}
	}
	
	
	
	private class BtnModificarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
				txtNombre.setEditable(true);
				txtTelefono.setEditable(true);
				txtDni.setEditable(true);
				txtemail.setEditable(true);
				txtHorario.setEditable(true);
				txtZonaAsignada.setEditable(true);
				btnGuardar.setEnabled(true);
		}
	}
	private class BtnEliminarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int seleccion = tablaVoluntarios.getSelectedRow();
			voluntarios.remove(seleccion);
			
			txtNombre.setText("");
			txtTelefono.setText("");
			txtDni.setText("");
			txtemail.setText("");
			txtHorario.setText("");
			txtZonaAsignada.setText("");
			ModeloTablaVoluntarios modelo = (ModeloTablaVoluntarios)tablaVoluntarios.getModel(); 
			modelo.eliminaFila(seleccion);
			modelo.fireTableDataChanged();
			guardarFichero(voluntarios);
			lblFoto.setIcon(null);
	}
	}
	
	private class BtnGuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int seleccion = tablaVoluntarios.getSelectedRow();
			
			voluntarios.get(seleccion).setNombre(txtNombre.getText());
			voluntarios.get(seleccion).setTelefono(Integer.parseInt(txtTelefono.getText()));
			voluntarios.get(seleccion).setDni(txtDni.getText());
			voluntarios.get(seleccion).setEmail(txtemail.getText());
			voluntarios.get(seleccion).setHorario(txtHorario.getText());
			voluntarios.get(seleccion).setZona_asignada(txtZonaAsignada.getText());
			btnGuardar.setEnabled(false);
			guardarFichero(voluntarios);
			guardarFotoCargada();
			cambiarTabla();
		}
	}
	
	

	public void guardarFichero(List <Voluntario> voluntario){
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("Voluntarios.txt");
	            pw = new PrintWriter(fichero);
	            for (int i = 0; i<voluntario.size();i++)
	                pw.println(voluntario.get(i).toString());
	 
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
	
	private void guardarFotoCargada(){
		JFileChooser fc=new JFileChooser();
		int r=fc.showSaveDialog(null);
		if(r==JFileChooser.APPROVE_OPTION){
			File archivo=fc.getSelectedFile();
			try {
				ImageIO.write(imagen, "jpg", archivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	private void cambiarTabla(){
		ModeloTablaVoluntarios modelo =(ModeloTablaVoluntarios)tablaVoluntarios.getModel();
		for (int j= 0; j<voluntarios.size(); j++)
			modelo.eliminaFila(0);
		for (int i = 0; i<voluntarios.size();i++){
			Object[] nuevaFila = {voluntarios.get(i).getNombre(), voluntarios.get(i).getTelefono(),voluntarios.get(i).getDni()};
			modelo.aniadeFila(nuevaFila);
		}
		tablaVoluntarios.setModel(modelo);
	}
}
