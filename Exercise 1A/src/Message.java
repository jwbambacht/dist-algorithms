import java.io.Serializable;

public class Message  implements Serializable {

	private static final long serialVersionUID = 1L;
	public String messageID;
	public String message;
	public Integer from;
	public int[] vector;
	
	public Message(String message, int from, String messageID) {
		this.messageID = messageID;
		this.message = message;
		this.from = from;
		this.vector = new int[3];
	}
	
	public String getMessage() {
		return this.message;
	}

	public int[] getVector() {
		return this.vector;
	}
	
	public void setVector(int[] vector) {
		this.vector = vector;
	}
	
	public int getFrom() {
		return this.from;
	}
	
	public String getMessageID() {
		return this.messageID;
	}
	
	@Override
	public String toString() {
		return this.message;
	}
}
