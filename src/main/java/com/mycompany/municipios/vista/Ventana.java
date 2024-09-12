package com.mycompany.municipios.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mycompany.municipios.Controlador.Controller;
import com.mycompany.municipios.Modelo.Municipios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextPane;

public class Ventana extends JFrame {

	private JPanel contentPane;
	public JTextPane textPane = new JTextPane();
	Controller control = new Controller(this);
	private List<Ciudad> ciudades; // Lista de ciudades
    private List<Linea> lineas;
    Image fondoMapa = new ImageIcon(getClass().getResource("mapa.jpg")).getImage();

	/**
	 * Create the frame.
	 */
	public Ventana() {
		ciudades = new ArrayList<>();
		setResizable(false);// Se define que no sea modificable el tamaño
		setSize(1050, 980);// define el tamaÃ±o de la ventana
		setTitle("Rutas por Colombia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iniciarComponentes();
	}

	public void iniciarComponentes() {

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 195, 941);
		panel.setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(panel);

		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(74, 27, 46, 14);
		panel.add(lblOrigen);

		JLabel lblNewLabel = new JLabel("Destino:");
		lblNewLabel.setBounds(74, 107, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Algoritmo:");
		lblNewLabel_1.setBounds(71, 188, 62, 14);
		panel.add(lblNewLabel_1);

		JComboBox<String> comboBoxOrigen = new JComboBox<String>();
		comboBoxOrigen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxOrigen.setBounds(24, 62, 138, 22);
		panel.add(comboBoxOrigen);

		JComboBox<String> comboBoxDestino = new JComboBox<String>();
		comboBoxDestino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxDestino.setBounds(24, 142, 138, 22);
		panel.add(comboBoxDestino);

		JComboBox<String> comboBoxAlgoritmo = new JComboBox<String>();
		comboBoxAlgoritmo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxAlgoritmo.setBounds(24, 223, 138, 22);
		panel.add(comboBoxAlgoritmo);

		JButton btnRuta = new JButton("Buscar Ruta");
		btnRuta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRuta.setBounds(44, 273, 105, 23);
		panel.add(btnRuta);

		agregarOpcionesComboBox(comboBoxOrigen, comboBoxDestino, comboBoxAlgoritmo);

		textPane.setEditable(false);
		textPane.setBounds(10, 314, 166, 311);
		panel.add(textPane);
		
		JPanel panel_1 = new JPanel()		
		{
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                    g.drawImage(fondoMapa, 0, 0, getWidth(), getHeight(), this);
                
                // Dibujar las ciudades
                    if (ciudades != null) {
                        // Dibujar las ciudades
                        for (Ciudad ciudad : ciudades) {
                            g.setColor(Color.RED);
                            g.fillOval(ciudad.x - 5, ciudad.y - 5, 10, 10); // Punto de la ciudad
                            g.drawString(ciudad.nombre, ciudad.x + 10, ciudad.y); // Nombre de la ciudad
                        }
                    }

                    if (lineas != null) {
                        // Dibujar las líneas entre las ciudades
                        for (Linea linea : lineas) {
                            g.setColor(linea.color);
                            g.drawLine(linea.ciudad1.x, linea.ciudad1.y, linea.ciudad2.x, linea.ciudad2.y);
                        }
                    }
            }
        };
		panel_1.setBounds(205, 0, 819, 941);
        contentPane.add(panel_1);

		ActionListener accion1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxDestino.getSelectedIndex() == 0) {
					control.ejecutarAlgoritmo(comboBoxOrigen.getSelectedIndex(), comboBoxDestino.getSelectedIndex(),
							comboBoxAlgoritmo.getSelectedIndex() + 1);
				} else {
					control.ejecutarAlgoritmo(comboBoxOrigen.getSelectedIndex(), 7,
							comboBoxAlgoritmo.getSelectedIndex() + 1);
				}
			}
		};
		btnRuta.addActionListener(accion1);
		añadirCiudades();
	}

	private void agregarOpcionesComboBox(JComboBox<String> comboBoxOrigen, JComboBox<String> comboBoxDestino,
			JComboBox<String> comboBoxAlgoritmo) {
		String[] opciones = Municipios.municipios;

		for (String opcion : opciones) {
			comboBoxOrigen.addItem(opcion);
		}
		comboBoxDestino.addItem(opciones[0]);
		comboBoxDestino.addItem(opciones[7]);

		comboBoxAlgoritmo.addItem("Avaro");
		comboBoxAlgoritmo.addItem("A*");
		comboBoxAlgoritmo.addItem("Dijkstra");
	}
	
	private void añadirCiudades(){
		for(int i = 0; i<Municipios.coordenadasMapa.length; i++){
			Ciudad ciudad = new Ciudad(Municipios.municipios[i], Municipios.coordenadasMapa[i][0], Municipios.coordenadasMapa[i][1]);
			ciudades.add(ciudad);
		}
	}

	class Ciudad {
        String nombre;
        int x, y;
        public Ciudad(String nombre, int x, int y) {
            this.nombre = nombre;
            this.x = x;
            this.y = y;
        }
    }
	
	class Linea {
        Ciudad ciudad1, ciudad2;
        Color color;
        public Linea(Ciudad ciudad1, Ciudad ciudad2, Color color) {
            this.ciudad1 = ciudad1;
            this.ciudad2 = ciudad2;
            this.color = color;
        }
    }
}
