package aman.project.springbootstarter.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	private String id;
	private String name;
	private String age;
	private double balance;
	
	public User() {
		
		
	}
	
	public User(String id, String name, String age, double balance) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.balance = balance;
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "Name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "Age", nullable = false)
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	@Column(name = "Balance", nullable = false)
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
