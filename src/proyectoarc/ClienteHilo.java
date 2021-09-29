package proyectoarc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteHilo extends Thread {
    
    @Override
    public void run(){
        
        final int PuertoTCP = 5000;
        final int PuertoUDP = 5001;
        DataInputStream in;
        DataOutputStream out;
        
        

        try {
            /*Conexion TCP*/
            InetAddress host = InetAddress.getByName("localhost");
            
            Socket scTCP = new Socket(host, PuertoTCP);
            in = new DataInputStream(scTCP.getInputStream());
            out = new DataOutputStream(scTCP.getOutputStream());
         
            out.writeUTF("Soy el clienteTCP ");
            
            scTCP.close();
            
            /*Conexion UDP*/
            DatagramSocket socketUDP = new DatagramSocket();
            
            String mensaje = "Soy el cliente UDP";
            byte[] men = mensaje.getBytes();
    
            DatagramPacket peticion = new DatagramPacket(men, men.length, host, PuertoUDP);
            socketUDP.send(peticion);
            
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(respuesta);
            
            socketUDP.close();
            
        } catch (IOException ex) {
            
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
        
       
            
            
    
    
}
