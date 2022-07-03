package Practica100;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PantallaTienda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Categoria> productos = new ArrayList<Categoria>();
	private JPanel contentPane;
	private Connection conexion = null;
	private String url = "jdbc:postgresql://localhost:5432/";
	private String bd = "tienda";
	private String driver = "org.postgresql.Driver";
	private String user = "postgres";
	private String pass = "ShitPassWord";
	private String text = "";
	private int contador = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaTienda frame = new PantallaTienda();
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
	public PantallaTienda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCargarCategorias = new JMenuItem("Cargar categorias");
		mnNewMenu.add(mntmCargarCategorias);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnNewMenu.add(mntmSalir);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textAreaProductos = new JTextArea();
		textAreaProductos.setEditable(false);
		textAreaProductos.setBounds(10, 10, 570, 325);
		contentPane.add(textAreaProductos);
		
		mntmCargarCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectar(url, bd, user, pass);
				textAreaProductos.setText("ID Producto" + "\t" + "Nombre producto" + "\t" + "Descripcion\n");
				while (contador < productos.size()) {
					Categoria category = productos.get(contador);
					text = textAreaProductos.getText();
					textAreaProductos.setText(text + category.getID() + "\t" + category.getNombre() + "\t\t" + category.getDescripcion() + "\n");
					contador++;
				}
			}
		});
		
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void conectar(String url, String bd, String user, String pass) {
		try {
			
			Class.forName(driver);
			conexion = DriverManager.getConnection(url+bd, user, pass);
			Statement st = conexion.createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM CATEGORIA");
			
			while (res.next()) {
				int id = res.getInt("id");
				String nombre = res.getString("nombre");
				String descripcion = res.getString("descrip");
				Categoria categoria = new Categoria(id, nombre, descripcion);
				productos.add(categoria);
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
}
