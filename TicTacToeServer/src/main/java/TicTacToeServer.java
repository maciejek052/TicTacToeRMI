import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class TicTacToeServer {

    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            System.setProperty("java.rmi.server.codebase","file:/C:/Users/macie/Documents/NetBeansProjects/TicTacToeRMI/TicTacToeServer/target/classes");
            //System.setProperty("java.rmi.server.hostname", "192.168.1.248");
            System.out.println("Codebase: " + System.getProperty("java.rmi.server.codebase"));
            LocateRegistry.createRegistry(1099);
            ServerImpl obj1 = new ServerImpl();
            Naming.rebind("//localhost/ABC", obj1);
            System.out.println("Serwer oczekuje ...");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
