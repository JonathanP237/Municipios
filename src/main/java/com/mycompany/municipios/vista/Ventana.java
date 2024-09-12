package com.mycompany.municipios.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mycompany.municipios.Controlador.Controller;
import com.mycompany.municipios.Modelo.Municipios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextPane;

public class Ventana extends JFrame {

	private JPanel contentPane;
	public JTextPane textPane = new JTextPane();
	Controller control = new Controller(this);

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setResizable(false);// Se define que no sea modificable el tamaño
		setSize(1050, 980);// define el tamaÃ±o de la ventana
		setTitle("Rutas por Colombia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iniciarComponentes();
	}

	public void iniciarComponentes() {

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1034, 961);
		panel.setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(panel);

		JLabel mapaLabel = new JLabel("");
		mapaLabel.setIcon(new ImageIcon(Ventana.class.getResource("/com/mycompany/municipios/vista/mapa.jpg")));
		mapaLabel.setBounds(0, 0, 848, 950);
		panel.add(mapaLabel);

		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setBounds(922, 29, 46, 14);
		panel.add(lblOrigen);

		JLabel lblNewLabel = new JLabel("Destino:");
		lblNewLabel.setBounds(922, 109, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Algoritmo:");
		lblNewLabel_1.setBounds(919, 190, 62, 14);
		panel.add(lblNewLabel_1);

		JComboBox<String> comboBoxOrigen = new JComboBox<String>();
		comboBoxOrigen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxOrigen.setBounds(872, 64, 138, 22);
		panel.add(comboBoxOrigen);

		JComboBox<String> comboBoxDestino = new JComboBox<String>();
		comboBoxDestino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxDestino.setBounds(872, 144, 138, 22);
		panel.add(comboBoxDestino);

		JComboBox<String> comboBoxAlgoritmo = new JComboBox<String>();
		comboBoxAlgoritmo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBoxAlgoritmo.setBounds(872, 225, 138, 22);
		panel.add(comboBoxAlgoritmo);

		JButton btnRuta = new JButton("Buscar Ruta");
		btnRuta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRuta.setBounds(892, 275, 105, 23);
		panel.add(btnRuta);

		agregarOpcionesComboBox(comboBoxOrigen, comboBoxDestino, comboBoxAlgoritmo);

		textPane.setEditable(false);
		textPane.setBounds(858, 316, 166, 154);
		panel.add(textPane);

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
