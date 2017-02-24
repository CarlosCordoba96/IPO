/*Jesus Enrique Aparicio
 * Alejandro Moreno
 */
package Presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Login {

	private JFrame frmLogin;
	private JPanel pnlLogin;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblPassword;
	private JPasswordField pswfPassword;
	private JButton btnBorrar;
	private JButton btnEntrar;
	private JPanel pnlAvisos;
	private JLabel lblNotificaciones;
	Registrados r;
	
	Hashtable <String, Registrados> table = new Hashtable<String, Registrados>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cargarRegistrados();
		frmLogin = new JFrame();
		frmLogin.setTitle(MessagesAplicacionInter.getString("Login.frmLogin.title")); //$NON-NLS-1$
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 397, 180);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new BorderLayout(0, 0));
		{
			pnlLogin = new JPanel();
			frmLogin.getContentPane().add(pnlLogin, BorderLayout.CENTER);
			GridBagLayout gbl_pnlLogin = new GridBagLayout();
			gbl_pnlLogin.columnWidths = new int[]{40, 95, 89, 87, 0, 0};
			gbl_pnlLogin.rowHeights = new int[]{36, 24, 27, 0, 0};
			gbl_pnlLogin.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_pnlLogin.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnlLogin.setLayout(gbl_pnlLogin);
			{
				lblUsuario = new JLabel(MessagesAplicacionInter.getString("Login.lblUsuario.text")); //$NON-NLS-1$
				lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
				gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
				gbc_lblUsuario.gridx = 1;
				gbc_lblUsuario.gridy = 1;
				pnlLogin.add(lblUsuario, gbc_lblUsuario);
			}
			{
				txtUsuario = new JTextField();
				txtUsuario.addActionListener(new TextFieldActionListener());
				GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
				gbc_txtUsuario.gridwidth = 2;
				gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
				gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtUsuario.gridx = 2;
				gbc_txtUsuario.gridy = 1;
				pnlLogin.add(txtUsuario, gbc_txtUsuario);
				txtUsuario.setColumns(10);
			}
			{
				lblPassword = new JLabel(MessagesAplicacionInter.getString("Login.lblPassword.text")); //$NON-NLS-1$
				lblPassword.setEnabled(false);
				GridBagConstraints gbc_lblPassword = new GridBagConstraints();
				gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
				gbc_lblPassword.gridx = 1;
				gbc_lblPassword.gridy = 2;
				pnlLogin.add(lblPassword, gbc_lblPassword);
			}
			{
				pswfPassword = new JPasswordField();
				pswfPassword.addActionListener(new PswfPasswordActionListener());
				pswfPassword.setEnabled(false);
				GridBagConstraints gbc_pswfPassword = new GridBagConstraints();
				gbc_pswfPassword.gridwidth = 2;
				gbc_pswfPassword.insets = new Insets(0, 0, 5, 5);
				gbc_pswfPassword.fill = GridBagConstraints.HORIZONTAL;
				gbc_pswfPassword.gridx = 2;
				gbc_pswfPassword.gridy = 2;
				pnlLogin.add(pswfPassword, gbc_pswfPassword);
			}
			{
				btnBorrar = new JButton(MessagesAplicacionInter.getString("Login.btnBorrar.text")); //$NON-NLS-1$
				btnBorrar.addActionListener(new BtnBorrarActionListener());
				GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
				gbc_btnBorrar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnBorrar.insets = new Insets(0, 0, 0, 5);
				gbc_btnBorrar.gridx = 2;
				gbc_btnBorrar.gridy = 3;
				pnlLogin.add(btnBorrar, gbc_btnBorrar);
			}
			{
				btnEntrar = new JButton(MessagesAplicacionInter.getString("Login.btnEntrar.text")); //$NON-NLS-1$
				btnEntrar.setEnabled(false);
				btnEntrar.addActionListener(new BtnEntrarActionListener());
				GridBagConstraints gbc_btnEntrar = new GridBagConstraints();
				gbc_btnEntrar.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnEntrar.insets = new Insets(0, 0, 0, 5);
				gbc_btnEntrar.gridx = 3;
				gbc_btnEntrar.gridy = 3;
				pnlLogin.add(btnEntrar, gbc_btnEntrar);
			}
		}
		{
			pnlAvisos = new JPanel();
			frmLogin.getContentPane().add(pnlAvisos, BorderLayout.SOUTH);
			{
				lblNotificaciones = new JLabel(); //$NON-NLS-1$
				pnlAvisos.add(lblNotificaciones);
			}
		}
	}

	private class BtnEntrarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, r.toString(), MessagesAplicacionInter.getString("Login.InformacionRegistro"), JOptionPane.PLAIN_MESSAGE); //$NON-NLS-1$
			table.get(r.getNombre()).setFecha(obtenerFechaActual());
			guardarFicheroRegistrados();
				VentanaPrincipal ppal;
				try {
					
					ppal = new VentanaPrincipal();
					ppal.setVisible(true);
					frmLogin.dispose();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		}
	}
	private class TextFieldActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(table.containsKey(txtUsuario.getText())){
				lblPassword.setEnabled(true);
				pswfPassword.setEnabled(true);
				lblNotificaciones.setText(MessagesAplicacionInter.getString("Login.NombreCorrecto")); //$NON-NLS-1$
				pnlAvisos.setBackground(Color.GREEN);
				lblNotificaciones.setForeground(Color.BLACK);
			}else {
				lblNotificaciones.setText(MessagesAplicacionInter.getString("Login.NombreNoValido")); //$NON-NLS-1$
				pnlAvisos.setBackground(Color.RED);
				lblNotificaciones.setForeground(Color.WHITE);
			}
			
		}
	}
	private class BtnBorrarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtUsuario.setText(""); //$NON-NLS-1$
			pswfPassword.setText(""); //$NON-NLS-1$
			pswfPassword.setEnabled(false);
			lblPassword.setEnabled(false);
			btnEntrar.setEnabled(false);
			pnlAvisos.setBackground(null);
			lblNotificaciones.setText(""); //$NON-NLS-1$
		}
	}
	private class PswfPasswordActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			r = table.get(txtUsuario.getText());
			if (String.valueOf(pswfPassword.getPassword()).toString().equals(r.getPassword())) {
				btnEntrar.setEnabled(true);
				lblNotificaciones.setText(MessagesAplicacionInter.getString("Login.Contrase�aCorrecta")); //$NON-NLS-1$
				pnlAvisos.setBackground(Color.GREEN);
				lblNotificaciones.setForeground(Color.BLACK);
			}
			else{
				lblNotificaciones.setText(MessagesAplicacionInter.getString("Login.Contrase�aIncorrecta")); //$NON-NLS-1$
				pnlAvisos.setBackground(Color.RED);
				lblNotificaciones.setForeground(Color.WHITE);
			}
			}
		}

	public void cargarRegistrados(){
		Scanner leer;
		String nombre;
		String password;
		String fecha;

		try {
			leer = new Scanner(new File("Registrados.txt")); //$NON-NLS-1$
			while(leer.hasNext()){
				nombre=leer.nextLine();
				password= leer.nextLine();
				fecha = leer.nextLine();
				r = new Registrados(nombre, password, fecha);
				table.put(nombre, r);
			}
			leer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	}
	}
	
	private static String obtenerFechaActual(){
		Calendar fecha = Calendar.getInstance();
		int diaSemana = fecha.get(Calendar.DAY_OF_WEEK);
		String ds = diaDeLaSemana(diaSemana);
		int dia = fecha.get(Calendar.DATE);
		int mes = fecha.get(Calendar.MONTH);
		int anio = fecha.get(Calendar.YEAR);
		int hora = fecha.get(Calendar.HOUR_OF_DAY);
		int minuto = fecha.get(Calendar.MINUTE);
		int segundo = fecha.get(Calendar.SECOND);
		return ds+" "+dia+"/"+(1+mes)+"/"+anio+MessagesAplicacionInter.getString("Login.Alas")+hora+":"+minuto+":"+segundo; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	}
	private static String diaDeLaSemana(int c){
		String dia=""; //$NON-NLS-1$
		switch (c){
		case 1:
			dia = MessagesAplicacionInter.getString("Login.Lunes"); //$NON-NLS-1$
			break;
		case 2:
			dia = MessagesAplicacionInter.getString("Login.Martes"); //$NON-NLS-1$
			break;
		case 3:
			dia = MessagesAplicacionInter.getString("Login.Miercoles"); //$NON-NLS-1$
			break;
		case 4:
			dia = MessagesAplicacionInter.getString("Login.Jueves"); //$NON-NLS-1$
			break;
		case 5:
			dia = MessagesAplicacionInter.getString("Login.Viernes"); //$NON-NLS-1$
			break;
		case 6: 
			dia =MessagesAplicacionInter.getString("Login.Sabado"); //$NON-NLS-1$
			break;
		case 7:
			dia = MessagesAplicacionInter.getString("Login.Domingo"); //$NON-NLS-1$
			break;
		}
		return dia;
	}
	private void guardarFicheroRegistrados(){
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("Registrados.txt"); //$NON-NLS-1$
	            pw = new PrintWriter(fichero);
	            Enumeration e = table.keys();
	            while(e.hasMoreElements()){
	            	String clave = (String) e.nextElement();
	                pw.println(table.get(clave).toStringFichero());
	            }
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

	public JFrame getFrame() {
		return frmLogin;
	}
}

