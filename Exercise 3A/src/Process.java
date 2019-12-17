import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Process extends UnicastRemoteObject implements ProcessInterface {

	private static final long serialVersionUID = 1L;
	
	public Integer id;
	public Integer port;
	public String host;
	public boolean byzantine;
	public Integer v;
	public Integer w;
	public Integer r;
	public boolean decided;
	public Map<Integer,String> others;
	public List<Message> messages;
	
	public Process(String host, int port, int ID, boolean byzantine, int v, int f) throws RemoteException {
		this.id = ID;
		this.host = host;
		this.port = port;
		this.byzantine = byzantine;
		this.decided = false;
		this.others = new HashMap<>();
		this.messages = new ArrayList<>();
		this.r = 1;
		this.v = v;
	}
	
	public Integer getID() throws RemoteException {
		return this.id;
	}
	
	public String getHost() throws RemoteException {
		return this.host;
	}
	
	public Integer getPort() throws RemoteException {
		return this.port;
	}
	
	public Integer getValue() throws RemoteException {
		return this.v;
	}
	
	public boolean getDecided() throws RemoteException {
		return this.decided;
	}
	
	public void setDecided() throws RemoteException {
		this.decided = true;
	}
	
	public void setValue(Integer v) throws RemoteException {
		this.v = v;
	}
	
	public List<Message> getMessages(Integer round, String type) throws RemoteException {
		List<Message> result = new ArrayList<>();
		
		for(Message m : this.messages) {
			if(m.getRound().equals(round) && m.getType().equals(type)) {
				result.add(m);
			}
		}
		return result;
	}
	
	public void addProcess(Integer port, String host) throws RemoteException {
		this.others.put(port,host);
	}
	
	public void broadcastNotification() throws RemoteException, MalformedURLException, NotBoundException {
		Message m = new Message("N",this.r,this.v,this.host,this.port);
		
		if(!this.byzantine) {
			System.out.println("--NOTIFCATION FROM "+this.id +" WITH VALUE " + m.getValue());
			
			for(Map.Entry<Integer, String> entry : this.others.entrySet()) {
				
				ProcessInterface p = getProcess(entry.getValue(),entry.getKey());
				
				p.receiveMessage(m);
				
			}
		}else {
			
			if(Math.round(Math.random()) == 1) {
				int rand = (int) Math.round(Math.random()*2);
				
				System.out.println("--RANDOM NOTIFICATION FROM BYZANTINE "+this.id+" WITH VALUE " + rand);
				
				m = new Message("N", this.r, rand, this.host, this.port);
			
				for(Map.Entry<Integer, String> entry : this.others.entrySet()) {
					
					ProcessInterface p = getProcess(entry.getValue(),entry.getKey());
					
					p.receiveMessage(m);
					
				}
			}else {
				System.out.println("--NO NOTIFICATION FROM BYZANTINE "+this.id);
			}
			
		}
	}
	
	public void broadcastProposal(Integer w) throws RemoteException, MalformedURLException, NotBoundException {
		this.w = w;
		Message m = new Message("P",this.r,w,this.host,this.port);
		
		if(!this.byzantine) {
			System.out.println("--PROPOSAL FROM "+this.id +" WITH VALUE " + m.getValue());
			
			for(Map.Entry<Integer, String> entry : this.others.entrySet()) {

				ProcessInterface p = getProcess(entry.getValue(),entry.getKey());

				p.receiveMessage(m);

			}
		}else {
			
			if(Math.round(Math.random()) == 1) {
				
				int rand = (int) Math.round(Math.random()*2);
				
				System.out.println("--RANDOM PROPOSAL FROM BYZANTINE "+this.id+" WITH VALUE " + rand);
				
				m = new Message("P", this.r, rand, this.host, this.port);
			
				for(Map.Entry<Integer, String> entry : this.others.entrySet()) {
					
					ProcessInterface p = getProcess(entry.getValue(),entry.getKey());
					
					p.receiveMessage(m);
					
				}
			}else {
				System.out.println("--NO PROPOSAL FROM BYZANTINE "+this.id);
			}
		}
	}
	
	public void receiveMessage(Message m) throws RemoteException{
		
		this.messages.add(m);
	
	}
	
	public ProcessInterface getProcess(String host, int port) throws MalformedURLException, RemoteException, NotBoundException {
		
		ProcessInterface process = (ProcessInterface) Naming.lookup("rmi://"+host+":"+port+"/process");
		
		return process;
		
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + ", BYZANTINE: " + this.byzantine + ", V: "+this.v + ", HOST: " + this.host + ", PORT: " + this.port;
		
	}
	
	

}
