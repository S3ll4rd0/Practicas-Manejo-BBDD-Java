package Practica101;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Monitores extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Monitor> listaMonitores = new ArrayList<Monitor>();
	private Connection conexion = null;
	private String url = "jdbc:postgresql://localhost:5432/";
	private String bd = "tienda";
	private String driver = "org.postgresql.Driver";
	private String user = "postgres";
	private String pass = "ShitPassWord";
	private JPanel contentPane;
	private String id;
	private String categoria;
	private String numSerie;
	private String tipo;
	private int pulgadas;
	private double precio;
	private int contador = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitores frame = new Monitores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Monitores() {
		setTitle("Tienda de ordenadores - Monitores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Elige el monitor que quieras:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 15, 250, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Identificador");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(75, 120, 100, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nº de Serie");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(75, 170, 100, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo de monitor");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(75, 220, 100, 20);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Pulgadas");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(75, 270, 100, 20);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Precio");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(75, 320, 100, 20);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("€");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(360, 320, 30, 20);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("\"");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_1.setBounds(360, 275, 30, 20);
		contentPane.add(lblNewLabel_6_1);
		
		JComboBox<String> comboBox = llenarComboBox();
		comboBox.setBounds(10, 50, 200, 25);
		contentPane.add(comboBox);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVisualizar.setBounds(250, 50, 100, 25);
		contentPane.add(btnVisualizar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificar.setBounds(125, 375, 125, 25);
		contentPane.add(btnModificar);
		
		JTextArea textAreaIdentificador = new JTextArea();
		textAreaIdentificador.setBounds(200, 120, 150, 25);
		contentPane.add(textAreaIdentificador);
		
		JTextArea textAreaNumeroSerie = new JTextArea();
		textAreaNumeroSerie.setBounds(200, 170, 150, 25);
		contentPane.add(textAreaNumeroSerie);
		
		JTextArea textAreaTipoMonitor = new JTextArea();
		textAreaTipoMonitor.setBounds(200, 220, 150, 25);
		contentPane.add(textAreaTipoMonitor);
		
		JTextArea textAreaPulgadas = new JTextArea();
		textAreaPulgadas.setBounds(200, 270, 150, 25);
		contentPane.add(textAreaPulgadas);
		
		JTextArea textAreaPrecio = new JTextArea();
		textAreaPrecio.setBounds(200, 320, 150, 25);
		contentPane.add(textAreaPrecio);
		
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if (index != -1) {
					Monitor monitor = listaMonitores.get(index);
					textAreaIdentificador.setText(monitor.getId());
					textAreaNumeroSerie.setText(monitor.getNumSerie());
					textAreaTipoMonitor.setText(monitor.getTipo());
					textAreaPulgadas.setText("" + monitor.getPulgadas());
					textAreaPrecio.setText("" + monitor.getPrecio());
				}
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if (index != -1) {
					Monitor monitor = listaMonitores.get(index);
					monitor.setId(textAreaIdentificador.getText());
					monitor.setNumSerie(textAreaNumeroSerie.getText());
					monitor.setTipo(textAreaTipoMonitor.getText());
					monitor.setPulgadas(Integer.parseInt(textAreaPulgadas.getText()));
					monitor.setPrecio(Double.parseDouble(textAreaPrecio.getText()));
					listaMonitores.set(index, monitor);
					actualizarBD(monitor, url, bd, user, pass);
				}
			}
		});
	}
	
	public JComboBox<String> llenarComboBox() {
		JComboBox<String> comboBox = new JComboBox<String>();
		conectar(url, bd, user, pass);
		while (contador < listaMonitores.size()) {
			Monitor monitor = listaMonitores.get(contador);
			comboBox.addItem(new String(monitor.getNumSerie()));
			contador++;
		}
		return comboBox;
	}
	
	public void conectar(String url, String bd, String user, String pass) {
		try {
			
			Class.forName(driver);
			conexion = DriverManager.getConnection(url+bd, user, pass);
			Statement st = conexion.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM MONITOR");
			
			while (res.next()) {
				id = res.getString("id");
				categoria = res.getString("categoria");
				numSerie = res.getString("numserie");
				tipo = res.getString("tipo");
				pulgadas = res.getInt("pulgadas");
				precio = res.getDouble("precio");
				
				Monitor monitor = new Monitor(id, categoria, numSerie, tipo, pulgadas, precio);
				listaMonitores.add(monitor);
				
			} 
			conexion.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {}
	}
	
	public void actualizarBD(Monitor monitor, String url, String bd, String user, String pass) {
		try {
			String update = "update monitor set numserie = ?, tipo = ?, pulgadas = ?, precio = ? where id = ?";
			
			Class.forName(driver);
			conexion = DriverManager.getConnection(url+bd, user, pass);
			PreparedStatement actualizacion;
			actualizacion = conexion.prepareStatement(update);
			
			actualizacion.setString(1, monitor.getNumSerie());
			actualizacion.setString(2, monitor.getTipo());
			actualizacion.setInt(3, monitor.getPulgadas());
			actualizacion.setDouble(4, monitor.getPrecio());
			actualizacion.setString(5, monitor.getId());
			
			actualizacion.executeUpdate();
			actualizacion.close();
			conexion.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {}
	}
}