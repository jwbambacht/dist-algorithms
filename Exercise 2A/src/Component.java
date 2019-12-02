import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Component extends UnicastRemoteObject implements ComponentInterface {

	private static final long serialVersionUID = 1L;
	
	public Integer id;
	public Integer tid;
	public Integer ntid;
	public Integer nntid;
	public Integer port;
	public String host;
	public Integer lastPort;
	public Integer nextPort;
	public Integer phase;
	public boolean status;
	
	public Component(Integer id, Integer port, String host) throws RemoteException {
		this.id = id;
		this.tid = id;
		this.port = port;
		this.host = host;
		this.status = true;
	}
	
	public Integer getTID() throws RemoteException {
		return this.tid;
	}
	
	public Integer getNTID() throws RemoteException {
		return this.ntid;
	}
	
	public Integer getNNTID() throws RemoteException {
		return this.nntid;
	}
	
	public Integer getPort() throws RemoteException {
		return this.port;
	}
	
	public boolean getStatus() throws RemoteException {
		return this.status;
	}
	
	public void setLastPort(Integer port) throws RemoteException {
		this.lastPort = port;
	}
	
	public void setNextPort(Integer port) throws RemoteException {
		this.nextPort = port;
	}
	
	public void sendTID() throws RemoteException, MalformedURLException, NotBoundException {
		ComponentInterface next = getNextComponent();
		
		next.receiveNTID(this.tid);
	}
	
	public void receiveNTID(Integer ntid) throws RemoteException {
		this.ntid = ntid;
	}
	
	public void sendNNTID() throws RemoteException, MalformedURLException, NotBoundException {
		ComponentInterface next = getNextComponent();
		
		next.receiveNNTID(Math.max(this.tid, this.ntid));
	}
	
	public void receiveNNTID(Integer nntid) throws RemoteException {
		this.nntid = nntid;
	}
	
	public boolean checkCondition() throws RemoteException {
		if(this.ntid >= this.tid && this.ntid >= this.nntid) {
			System.out.println("Component "+this.tid + " is still active, now with tid " +  this.ntid + " because ("+ this.ntid + " >= " + this.tid + " AND " + this.ntid + " >= " + this.nntid +")");
			
			this.tid = this.ntid;
		}else {
			this.status = false;
			System.out.println("Component "+ this.tid + " is set passive because ("+ this.ntid + " < " + this.tid + " OR " + this.ntid + " < " + this.nntid +")");
		}
		
		return this.status;
	}
	
	public ComponentInterface getLastComponent() throws MalformedURLException, RemoteException, NotBoundException {
		return (ComponentInterface) Naming.lookup("rmi://"+host+":"+this.lastPort+"/component");
	}
	
	public ComponentInterface getNextComponent() throws MalformedURLException, RemoteException, NotBoundException {
		return (ComponentInterface) Naming.lookup("rmi://"+host+":"+this.nextPort+"/component");
	}

}
