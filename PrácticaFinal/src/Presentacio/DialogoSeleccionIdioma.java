package Presentacio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class DialogoSeleccionIdioma extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JRadioButton rdbtnSpanish;
	private JRadioButton rdbtnEnglish;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogoSeleccionIdioma dialog = new DialogoSeleccionIdioma();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogoSeleccionIdioma() {
		setBounds(100, 100, 450, 125);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblSelectLanguage = new JLabel("Select Language");
			contentPanel.add(lblSelectLanguage);
		}
		{
			rdbtnSpanish = new JRadioButton("Spanish");
			rdbtnSpanish.setIcon(new ImageIcon(DialogoSeleccionIdioma.class.getResource("banderaEsp.gif")));
			contentPanel.add(rdbtnSpanish);
		}
		{
			rdbtnEnglish = new JRadioButton("English");
			rdbtnEnglish.setIcon(new ImageIcon(DialogoSeleccionIdioma.class.getResource("banderaIng.gif")));
			contentPanel.add(rdbtnEnglish);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkButtonActionListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private class OkButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (rdbtnEnglish.isSelected()){
				MessagesAplicacionInter.setIdioma("ingl�s");
				MessagesPerrosInter.setIdioma("ingl�s");
				MessagesVentanaPrincipalInter.setIdioma("ingl�s");
				MessagesVoluntariosInter.setIdioma("ingl�s");
			}
			Login ventanaLog = new Login();
			ventanaLog.getFrame().setVisible(true);
			dispose();
		}
	}
}
