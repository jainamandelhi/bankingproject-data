package aman.project.springbootstarter.transaction;

import aman.project.springbootstarter.account.Account;
import aman.project.springbootstarter.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    //private Status status;
    //public enum Status {PASSED, FAILED};
    // time

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "sender_id", nullable = false)
    @JsonBackReference
    private Account senderAccount;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "receiver_id", nullable = false)
    @JsonBackReference
    private Account receiverAccount;
}
