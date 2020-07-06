package aman.project.springbootstarter.user;

import aman.project.springbootstarter.account.Account;
import aman.project.springbootstarter.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private Integer age;
	//@Temporal(TemporalType.DATE)
	//private Date date;
	@OneToMany(
			mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Account> accountUser;
}







	/*@OneToMany(
			mappedBy = "senderId",fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
	)
	//@JoinColumn(name = "sender_id", referencedColumnName = "id")
	//@JsonIgnoreProperties(value = {"senderId"})
	@JsonManagedReference
	private List<Transaction> transactionSender;// = new ArrayList<Transaction>();

	@OneToMany(
			mappedBy = "receiverId",fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
	)
	//@JoinColumn(name = "receiver_id", referencedColumnName = "id")
	//@JsonIgnoreProperties(value = {"receiverId"})
	@JsonManagedReference
	private List<Transaction> transactionReceiver;// = new ArrayList<Transaction>();
	*/
