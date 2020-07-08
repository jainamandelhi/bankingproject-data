package aman.project.springbootstarter.transaction.model;

import aman.project.springbootstarter.account.model.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column
    private TransactionType transactionType;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "sender_id", nullable = false)
    @JsonBackReference
    private Account senderAccount;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "receiver_id", nullable = false)
    @JsonBackReference
    private Account receiverAccount;
}
