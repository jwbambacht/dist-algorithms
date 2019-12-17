import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static String host;
	public static Integer f;
	public static Integer round;
	public static Integer n;
	public static List<Integer> undecidedProcesses;

	public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException, NotBoundException, AlreadyBoundException, InterruptedException {
		int[] ports = new int[] {1099,5000,5001,5002,5003,5004,5005,5006,5007,5008,5009,5010,5011,5012,5013,5014,5015,5016,5017,5018,5019,5020,5021,5022,5023};
		undecidedProcesses = new ArrayList<>();
		host = InetAddress.getLocalHost().getHostAddress();

		// If eclipse is used a testcase will be loaded, otherwise input from terminal will be used in format (port,value) for each component
		if(args.length == 0) {					

			args = new TestCases(ports).getTestCase(1);

		}

		// Retrieve number of total and byzantine processes
		n = (args.length-1)/2;
		f = Integer.parseInt(args[args.length-1]);
		round = 1;

		// Read processes and connect all processes together to create a completely connected network
		List<Process> processes = readProcesses(args);

		for(Process p : processes) {
			System.out.println(p.toString());
		}

		System.out.println("-----------------------------------------------------------------------------------------");

		processes = connectProcesses(processes);

		while(undecidedProcesses.size() > 0) {

			// Notification phase
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("NOTIFCATION PHASE ROUND "+round+":");
			System.out.println("-----------------------------------------------------------------------------------------");

			for(Process process : processes) {
				process.broadcastNotification();
			}


			// Proposal phase
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("PROPOSAL PHASE ROUND "+round+":");
			System.out.println("-----------------------------------------------------------------------------------------");

			for(Process process : processes) {

				if(process.getMessages(process.r,"N").size() >= (n-f)) {

					int zeroCount = 0;
					int oneCount = 0;

					for(Message m : process.getMessages(process.r,"N")) {
						if(m.getValue().equals(0)) {
							zeroCount++;
						}else if(m.getValue().equals(1)) {
							oneCount++;
						}
					}

					// Condition that there must be more than (n+f)/2 messages with 0 of 1, otherwise sent ?=2
					if(Math.max(zeroCount, oneCount) > (n+f)/2) {
						int w = 0;

						if(oneCount > zeroCount) {
							w = 1;
						}

						process.broadcastProposal(w);

					}else {

						process.broadcastProposal(2);

					}
				}
			}

			// Decision phase
			System.out.println("-----------------------------------------------------------------------------------------");
			System.out.println("DECISION PHASE ROUND "+round+":");
			System.out.println("-----------------------------------------------------------------------------------------");

			for(Process process : processes) {
				if(process.getDecided()) {
					System.out.println("--PROCESS " + process.getID() +" ALREADY DECIDED");
					continue;
				}

				if(process.getMessages(process.r,"P").size() >= (n-f)) {

					int zeroCount = 0;
					int oneCount = 0;

					for(Message m : process.getMessages(round, "P")) {
						if(m.getValue().equals(0)) {
							zeroCount++;
						}else if(m.getValue().equals(1)) {
							oneCount++;
						}
					}

					int nProposals = Math.max(zeroCount, oneCount);

					// Condition that the count of zeros or ones is bigger than the number of faulty processes
					if(nProposals > f) {

						int w = 0;

						if(oneCount > zeroCount) {
							w = 1;
						}

						System.out.println("--PROCESS " + process.getID() + " RECEIVED ENOUGH PROPOSALS OF VALUE 0 OR 1: " + nProposals + " > " + f + " WITH VALUE " + w);

						process.setValue(w);

						// Condition that there must be more than 3 times the number of faulty processes to accept proposal
						if(Math.max(zeroCount, oneCount) > 3*f) {

							System.out.println("----PROCESS "+ process.getID() + " DECIDES ON VALUE " + w + " IN ROUND " + round);
							process.setDecided();
							process.r++;
							undecidedProcesses.remove(process.getID());
						}
					}else {

						System.out.println("--PROCESS " + process.getID() + " RECEIVED NOT ENOUGH PROPOSALS ENOUGH PROPOSALS OF VALUE 0 OR 1: " + nProposals + " <= " + f);

						process.v = (int) Math.round(Math.random());
					}

				}

				process.r++;
			}
			round++;
		}

		round--;

		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("DECIDED IN " + round + " ROUND(S) ON VALUE " + processes.get(0).getValue());
		System.out.println("-----------------------------------------------------------------------------------------");

		System.exit(0);

	}

	// Read processes from arguments, determine id's of byzantine processes and create and register processes for RMI
	public static List<Process> readProcesses(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, InterruptedException {

		List<Integer> byzantineNodes = new ArrayList<>();

		while(byzantineNodes.size() < f) {
			Integer random = (int) Math.round(Math.random()*(n-1));

			if(!byzantineNodes.contains(random)) {
				byzantineNodes.add(random);
			}
		}

		Collections.sort(byzantineNodes);

		System.out.println("The systems consists of " + n + " processes from which " + f + " are byzantine: " + byzantineNodes.toString());

		List<Process> processes = new ArrayList<>();

		for(int i = 0; i < 2*n; i+=2) {

			Integer port = Integer.parseInt(args[i]);
			Integer v = Integer.parseInt(args[i+1]);
			boolean byzantine = false;

			if(byzantineNodes.contains(i/2)) {
				byzantine = true;
				v = (int) Math.round(Math.random()*2);
			}

			undecidedProcesses.add(i/2);

			Process p = new Process(host, port, i/2, byzantine, v,f);
			processes.add(p);

			LocateRegistry.createRegistry(port);
			Naming.bind("rmi://"+host+":"+port+"/process", p);

		}

		return processes;

	}

	// Put every other process in the list of processes for each process
	public static List<Process> connectProcesses(List<Process> processes) throws RemoteException {

		for(Process p : processes) {
			for(Process pp : processes) {
				p.addProcess(pp.getPort(),pp.getHost());

			}
		}

		return processes;

	}



}
