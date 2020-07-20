package aman.project.springbootstarter.user.model;

import aman.project.springbootstarter.account.model.Account;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
	private String mobileNo;
	private String address;
	@Enumerated(EnumType.STRING)
	@Column
	private IdentityType identityType;
	private Integer age;

	@OneToMany(
			mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
