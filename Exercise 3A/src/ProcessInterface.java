import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProcessInterface extends Remote {
	
	public Integer getID() throws RemoteException;
	
	public String getHost() throws RemoteException;
	
	public Integer getPort() throws RemoteException;
	
	public Integer getValue() throws RemoteException;
	
	public boolean getDecided() throws RemoteException;
	
	public void setDecided() throws RemoteException;
	
	public void setValue(Integer v) throws RemoteException;
	
	public List<Message> getMessages(Integer round, String type) throws RemoteException;
	
	public void addProcess(Integer port, String host) throws RemoteException;
	
	public void broadcastNotification() throws RemoteException, MalformedURLException, NotBoundException;
	
	public void broadcastProposal(Integer w) throws RemoteException, MalformedURLException, NotBoundException;
	
	public void receiveMessage(Message m) throws RemoteException;
	
	public ProcessInterface getProcess(String host, int port) throws MalformedURLException, RemoteException, NotBoundException;

}
