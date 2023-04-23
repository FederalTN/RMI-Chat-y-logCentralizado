import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.*;
import java.text.SimpleDateFormat;

class ClienteChat {

    private static final int PORT = 4002;

    static public void main(String args[]) {
        if (args.length != 1) {
            System.err.println("Uso: ClienteChat apodo");
            return;
        }

        try {

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", PORT);
            ServicioChat srv = (ServicioChat) registry.lookup("Chat");

            ClienteImpl c = new ClienteImpl();

            srv.alta(c);

            Scanner ent = new Scanner(System.in);

            String apodo = args[0];
            System.out.println(apodo + " bienvenido seas al chat! ");
            String msg;
            String msgExit = "EXIT";
            while (ent.hasNextLine()) {

                msg = ent.nextLine();
                if (msg.equals(msgExit)) {
                    srv.envio(c, apodo, " deja la sala de chat !!!!! ");
                    break;
                } else {
                    // Registro fecha acciòn
                    Date fecha = new Date();
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");
                    String fechaFormateada = formatoFecha.format(fecha);
                    String MensajeRegistro = fechaFormateada + ";" + apodo + ": " + msg;
                    srv.envio(c, apodo, MensajeRegistro);
                    System.out.println(MensajeRegistro);
                }

                System.out.println(apodo + " escribe algo -> ");

            }
            srv.baja(c);
            System.exit(0);
        } catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        } catch (Exception e) {
            System.err.println("Excepcion en ClienteChat:");
            e.printStackTrace();
        }
    }
}
