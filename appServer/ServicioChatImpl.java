import java.io.FileWriter;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.text.SimpleDateFormat;
import java.util.*;

class ServicioChatImpl implements ServicioChat {
    private List<Cliente> l;

    ServicioChatImpl() throws RemoteException {
        l = new LinkedList<Cliente>();
    }

    public void alta(Cliente c) throws RemoteException {
        l.add(c);
        int id = l.indexOf(c) + 1;
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");
        String fechaFormateada = formatoFecha.format(fecha);
        String registro = "inicio de conexion;cliente" + id + ";" + fechaFormateada;
        System.out.println(registro);
        // Guardar registro en archivo
        FileWriter fw;
        try {
            fw = new FileWriter("log.txt", true); // true indica que se agregará al final del archivo
            fw.write(registro + "\n"); // agregar salto de línea para separar registros
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void baja(Cliente c) throws RemoteException {
        l.remove(l.indexOf(c));
    }

    public void envio(Cliente esc, String apodo, String m) throws RemoteException {
        for (Cliente c: l) {
            if (!c.equals(esc)) {
                c.notificacion(apodo, m);
            }
        }
    }
}
