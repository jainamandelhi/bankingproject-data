package aman.project.springbootstarter.transaction;

public class Transaction {

	private String id;
	private String sender;
	private String receiver;
	private int amount;
	
	public Transaction() {
		
	}
	

	public Transaction(String id, String sender, String receiver, int amount) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
		
	
}
