import java.rmi.*;
import java.rmi.server.*;

class ClienteImpl extends UnicastRemoteObject implements Cliente {
    private int id;
    ClienteImpl(int id) throws RemoteException {
        this.id = id;
    }
    public void notificacion(String apodo, String m) throws RemoteException {
        System.out.println("\n" + apodo + " dice > " + m);
    }
    public int getId() throws RemoteException {
        return id;
    }
}

