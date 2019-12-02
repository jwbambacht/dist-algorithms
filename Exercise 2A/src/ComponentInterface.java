import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComponentInterface extends Remote {
	
	public Integer getTID() throws RemoteException;
	
	public Integer getNTID() throws RemoteException;
	
	public Integer getNNTID() throws RemoteException;
	
	public Integer getPort() throws RemoteException;
	
	public boolean getStatus() throws RemoteException;
	
	public void setLastPort(Integer port) throws RemoteException;
	
	public void setNextPort(Integer port) throws RemoteException;
	
	public void sendTID() throws RemoteException, MalformedURLException, NotBoundException;
	
	public void receiveNTID(Integer ntid) throws RemoteException;
	
	public void sendNNTID() throws RemoteException, MalformedURLException, NotBoundException;
	
	public void receiveNNTID(Integer nntid) throws RemoteException;
	
	public ComponentInterface getLastComponent() throws MalformedURLException, RemoteException, NotBoundException;
	
	public ComponentInterface getNextComponent() throws MalformedURLException, RemoteException, NotBoundException;

}
