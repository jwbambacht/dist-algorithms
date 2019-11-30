import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Server {
	
	static List<Integer> ports = new ArrayList<>(
			Arrays.asList(1099,2021,3000)
	);

	public static void main(String args[]) throws RemoteException, MalformedURLException, AlreadyBoundException, UnknownHostException, NotBoundException {
		
		String host = InetAddress.getLocalHost().getHostAddress();
		
		final List<Process> processes = new ArrayList<>();
		
		for(int i = 0; i<3; i++) {
		
			Integer port = ports.get(i);
		
			// Create process
			Process p = new Process(host,ports,port);
			processes.add(p);
			
			// Register and bind
			LocateRegistry.createRegistry(port);
			Naming.bind("rmi://"+host+":"+port+"/process", p);
		
			System.out.println("Server on host "+host+" on port "+port+" ready");
		
		}
		
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
	
		
		// Processes
		Process p0 = processes.get(0);
		Process p1 = processes.get(1);
		Process p2 = processes.get(2);
		
		
		// Delayed Ports lists for simulation of delayed messages
		List<Integer> listAll = new ArrayList<>();
		List<Integer> listNo1099 = new ArrayList<>();
		List<Integer> listNo2021 = new ArrayList<>();
		List<Integer> listNo3000 = new ArrayList<>();
		listNo1099.add(1099);
		listNo2021.add(2021);
		listNo3000.add(3000);
		
//		/// Test case 1
//		// List of messages to be sent
//		Message m11 = new Message("This is a message from 1099", 1099, "a");
//		Message m21 = new Message("This is a message from 2021", 2021, "b");
//		Message m31 = new Message("This is a message from 3000", 3000, "c");
//		Message m41 = new Message("This is a message from 3000", 3000, "d");
//		Message m51 = new Message("This is a message from 2021", 2021, "e");
//		
//		// List of broadcast messages 
//		p0.broadcastMessage(m11,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p1.broadcastMessage(m21,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p2.broadcastMessage(m31,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p2.broadcastMessage(m41,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p1.broadcastMessage(m51,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("Final vectors:");
//		for(Process p : processes) {
//			System.out.println("p"+processes.indexOf(p)+": "+Arrays.toString(p.getVector()));
//		}
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("HB Relations:");
//		HBRelations(processes);
	
		
		
//		/// Test case 2
//		// List of messages to be sent
//		Message m12 = new Message("This is a message from 1099", 1099, "a");
//		Message m22 = new Message("This is a message from 1099", 1099, "b");
//		Message m32 = new Message("This is a message from 1099", 1099, "c");
//		
//		// List of broadcast messages 
//		p0.broadcastMessage(m12,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p0.broadcastMessage(m22,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p0.broadcastMessage(m32,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("Final vectors:");
//		for(Process p : processes) {
//			System.out.println("p"+processes.indexOf(p)+": "+Arrays.toString(p.getVector()));
//		}
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("HB Relations:");
//		HBRelations(processes);

		
		
//		/// Test case 3
//		// List of messages to be sent
//		Message m13 = new Message("This is a message from 1099", 1099, "a");
//		Message m23 = new Message("This is a message from 2021", 2021, "b");
//		
//		// List of broadcast messages 
//		p0.broadcastMessage(m13,listNo3000);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p1.broadcastMessage(m23,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p0.vector = new int[]{0,0,0};
//		p0.broadcastMessage(m13, listNo2021);
//		p0.vector = new int[]{1,1,0};
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("Final vectors:");
//		for(Process p : processes) {
//			System.out.println("p"+processes.indexOf(p)+": "+Arrays.toString(p.getVector()));
//		}
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("HB Relations:");
//		HBRelations(processes);
	
		
		
		/// Test case 4
		// List of messages to be sent
		Message m14 = new Message("This is a message from 1099", 1099, "a");
		Message m24 = new Message("This is a message from 2021", 2021, "b");
		Message m34 = new Message("This is a message from 3000", 3000, "c");
		Message m44 = new Message("This is a message from 3000", 3000, "d");
		Message m54 = new Message("This is a message from 2021", 2021, "e");
		
		// List of broadcast messages 
		p0.broadcastMessage(m14,listNo3000);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		p1.broadcastMessage(m24,listAll);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		p0.vector = new int[]{0,0,0};
		p0.broadcastMessage(m14,listNo2021);
		p0.vector = new int[]{1,1,0};
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		p2.broadcastMessage(m34,listAll);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		p2.broadcastMessage(m44,listAll);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		p1.broadcastMessage(m54,listAll);
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Final vectors:");
		for(Process p : processes) {
			System.out.println("p"+processes.indexOf(p)+": "+Arrays.toString(p.getVector()));
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------");
		System.out.println("HB Relations:");
		HBRelations(processes);
		

		
//		/// Test case 5
//		// List of messages to be sent
//		Message m15 = new Message("This is a message from 3000", 3000, "a");
//		Message m25 = new Message("This is a message from 2021", 2021, "b");
//		
//		// List of broadcast messages 
//		p2.broadcastMessage(m15,listNo1099);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p1.broadcastMessage(m25,listAll);
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		p2.vector = new int[]{0,0,0};
//		p2.broadcastMessage(m15,listNo2021);
//		p2.vector = new int[]{0,1,1};
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("Final vectors:");
//		for(Process p : processes) {
//			System.out.println("p"+processes.indexOf(p)+": "+Arrays.toString(p.getVector()));
//		}
//		System.out.println("------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("HB Relations:");
//		HBRelations(processes);
		
	}
	
	public static void HBRelations(List<Process> processes) throws RemoteException {
		Set<String> hbs = new HashSet<>();
		
		for(Process p : processes) {
			hbs.addAll(p.getHB());
		}
		
		List<String> sortedList = new ArrayList<>(hbs);
		Collections.sort(sortedList);
		
		for(String hb : sortedList) {
			System.out.println(hb);
		}
		
	}

}
