package proyectoarc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor {
    
    public static void main(String[] arg) throws InterruptedException{
        
        ServerSocket servidor = null;
        final int PuertoTCP = 5000;
        final int PuertoUDP = 5001;
            
        try {
           
            servidor = new ServerSocket(PuertoTCP); 
            System.out.println("Conexion TCP establecida");
            DatagramSocket socketUDP = new DatagramSocket(PuertoUDP);
            System.out.println("Conexion UDP establecida");
            
            while(true){
           
                for(int i = 1; i <= 3; i++){
                    
                    ServidorHilo hilo = new ServidorHilo(servidor, socketUDP);
                    hilo.start();
                    
                }
            }
             
        } catch (IOException ex) {
            
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
}
