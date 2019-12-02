import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface ProcessInterface extends Remote {
	
	public void broadcastMessage(Message message, List<Integer> delayedPorts) throws RemoteException;
	
	public void retrieveMessage(Message message) throws RemoteException;
	
	public void deliverMessage(Message message) throws RemoteException;
	
	public void processBuffer() throws RemoteException;
	
	public boolean checkVector(Message m) throws RemoteException;
	
	public int[] getVector() throws RemoteException;
	
	public Set<String> getHB() throws RemoteException;

}
