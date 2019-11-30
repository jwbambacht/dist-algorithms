import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Process extends UnicastRemoteObject implements ProcessInterface {
	
	private static final long serialVersionUID = 1L;
	public String host;
	public int port;
	public List<Integer> ports;
	public int[] vector;
	public List<Message> buffer;
	public List<HBPair> hbs;
	public List<String> hb;
	
	public Process(String host, List<Integer> ports, Integer port) throws RemoteException {
		this.host = host;
		this.port = port;
		this.ports = ports;
		this.vector = new int[ports.size()];
		this.buffer = new ArrayList<>();
		this.hb = new ArrayList<>();
	}
	
	public void broadcastMessage(Message message, List<Integer> delayedPorts) throws RemoteException {
		int i = this.ports.indexOf(this.port);
		this.vector[i] += 1;
		message.setVector(this.vector);
		
		System.out.println("Broadcasting from "+ message.getFrom() +", Message: '"+message.getMessage()+"' with messageID: '"+message.getMessageID()+"', Vector: "+Arrays.toString(message.getVector()));
		
		for(Integer port : this.ports) {
			
			if(port != this.port && !delayedPorts.contains(port)) {
				try {
					ProcessInterface process = (ProcessInterface) Naming.lookup("rmi://"+this.host+":"+port+"/process");
					process.retrieveMessage(message);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void retrieveMessage(Message message) throws RemoteException {
		System.out.println("---Receiving message on " + this.port+":");
		System.out.println("------Message vector: "+Arrays.toString(message.getVector())+", Vector on "+this.port+": "+Arrays.toString(this.vector));
		
		if(checkVector(message)) {
			System.out.println("------Message to delivery for "+this.port);
			this.deliverMessage(message);
		}else {
			System.out.println("------Message for "+this.port+" put in buffer due to HB relation");
			this.buffer.add(message);
		}
		
		processBuffer();
		
	}
	
	public void deliverMessage(Message message) throws RemoteException {
		
		System.out.println("---------Delivering messages on "+this.port+":");
		
		int[] vector = message.getVector();
		String m = message.getMessage();
		String mID = message.getMessageID();
		this.hb.add(mID);
		
		this.vector = vector;
		
		System.out.println("------------Message: '"+m+"' with messageID '"+mID+"' delivered on port "+this.port);
		System.out.println("------------New Vector for "+this.port+" is "+Arrays.toString(this.vector));
		
	}
	
	public void processBuffer() throws RemoteException {
		
		Iterator<Message> it = this.buffer.iterator();
		
		while(it.hasNext()) {
			Message m = it.next();
			
			if(checkVector(m)) {
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Reading from buffer on port "+this.port+" to look for messages to be accepted");
				System.out.println("------Message to delivery from buffer for "+this.port);
				deliverMessage(m);
				this.buffer.remove(m);
				it = this.buffer.iterator();
			}
		}
	}
	
	public boolean checkVector(Message m) throws RemoteException {
		
		boolean toDeliver = true;
		
		int[] messageVector = m.getVector();
		int indexFrom = this.ports.indexOf(m.getFrom());
		
		if(messageVector[indexFrom]-1 == this.vector[indexFrom]) {
			for(int i = 0; i < this.ports.size(); i++) {
				if(i != indexFrom) {
					if(messageVector[i] != this.vector[i]) {
						toDeliver = false;
						break;
					}
				}
			}
		}
		
		return toDeliver;
	}
	
	public int[] getVector() throws RemoteException {
		
		return this.vector;
		
	}
	
	public Set<String> getHB() throws RemoteException {
		
		Set<String> hbRelations = new HashSet<>();
		
		for(String i : this.hb) {
			for(int j = this.hb.indexOf(i)+1; j < this.hb.size(); j++) {
				hbRelations.add(i+" -> "+this.hb.get(j));
			}
		}
		
		return hbRelations;
		
	}
	
	@Override
	public String toString() {
		return this.host+" "+this.port;
	}

}
