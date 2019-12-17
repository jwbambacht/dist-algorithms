import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public String type;
	public Integer round;
	public Integer value;
	public String sourceHost;
	public Integer sourcePort;
	
	public Message(String type, Integer round, Integer value, String host, Integer port) {
		this.type = type;
		this.round = round;
		this.value = value;
		this.sourceHost = host;
		this.sourcePort = port;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Integer getRound() {
		return this.round;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return "Type: " + this.type + ", Round: " + this.round + ", Value: " + this.value + ", From: " + this.sourceHost + ":" + this.sourcePort;
	}

}
