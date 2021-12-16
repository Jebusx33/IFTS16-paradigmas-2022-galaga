/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invasion;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Jesus Arias
 */
public class ventana {
    
      // Propiedades del Juego
        int anchoVentana = 500;
        int largoVentana = 500;

        public ventana(){
        
        // Activar aceleracion de graficos en 2 dimensiones
        System.setProperty("sun.java2d.opengl", "true");

        // Crear un objeto de tipo JFrame que es la ventana donde va estar el juego
       JFrame ventana = new JFrame("Mi Juego");

        // Cerrar la aplicacion cuando el usuario hace click en la 'X'
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Mostrar la ventana
        ventana.setVisible(true);
        
          // Abrir la ventana en el centro de la pantalla
        ventana.setLocationRelativeTo(null);
        
       // ventana.setResizable(false);//para que no cambie de tamaño
       
        // Crear un "JPanel" llamado Juego y agregarlo a la ventana
        Juego panel = new Juego(anchoVentana, largoVentana);
       
        // Agregar a la ventana el JPanel (Panel hereda de JPanel)
        ventana.add(panel);

        // Achicar la ventana lo maximo posible para que entren los componentes
        ventana.pack();		
        
       ventana.addKeyListener(panel);
        
        // Crear un thread y pasarle como parametro al juego que implementa la interfaz
        // "Runnable"
        Thread thread = new Thread(panel);

        // Arrancar el juego
        thread.start();
        }

  
}
