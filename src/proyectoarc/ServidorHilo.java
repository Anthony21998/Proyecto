package proyectoarc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHilo extends Thread{
    
    ServerSocket server;
    DatagramSocket socketUDP;

    public ServidorHilo(ServerSocket server, DatagramSocket socketUDP) {

        this.server = server;
        this.socketUDP = socketUDP;
    }

    @Override
    public void run(){
        try {
            
            DataInputStream in;
            DataOutputStream out;
            Socket sc;
            
            byte[] bufer = new byte[1000];
            
            /*Conexion TCP*/
            
            sc = server.accept();   //sc es para enviar y recibir datos con el cliente
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            
            String mensaje = in.readUTF();
            System.out.println(mensaje);
            System.out.println("El cliente TCP se ha desconectado");
            
            
            /*Conexion UDP*/
            DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

            socketUDP.receive(peticion);
            String men = new String(peticion.getData());
            
            System.out.println(men);
            DatagramPacket respuesta = new DatagramPacket(peticion.getData(), peticion.getLength(), peticion.getAddress(), peticion.getPort());

            socketUDP.send(respuesta);
            System.out.println("El cliente UDP se ha desconectado");
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
    
}
