package proyectoarc;

public class Cliente {

    public static void main(String[] args){

            for(int i = 1; i <= 3; i++){
                ClienteHilo hilo = new ClienteHilo();
                hilo.start();  
            }
    }
}
