package beans;

import java.sql.Timestamp;

public class Reimbursement {

	private int id;
	private int amount;
	private Timestamp timeSubmitted;
	private Timestamp timeResolved;
	private String description;
	// image format for receipt
	private String author;
	private String resolver;
	private String status;
	private String type;

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int id, int amount, Timestamp timeSubmitted, Timestamp timeResolved, String description,
			String author, String resolver, String status, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.timeSubmitted = timeSubmitted;
		this.timeResolved = timeResolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public Timestamp getTimeResolved() {
		return timeResolved;
	}

	public void setTimeResolved(Timestamp timeResolved) {
		this.timeResolved = timeResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimbursement( [id:" + id + ", amount:" + amount + ", timeSubmitted:" + timeSubmitted
				+ ", timeResolved:" + timeResolved + ", description:" + description + ", author:" + author
				+ ", resolver:" + resolver + ", status:" + status + ", type:" + type;
	}
}
