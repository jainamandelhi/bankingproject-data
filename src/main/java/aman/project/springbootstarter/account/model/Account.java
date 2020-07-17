package aman.project.springbootstarter.account.model;
import aman.project.springbootstarter.transaction.model.Transaction;
import aman.project.springbootstarter.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "accounts")
public class Account {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(
            mappedBy = "senderAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> debitTransactions;

    @OneToMany(
            mappedBy = "receiverAccount",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> creditTransactions;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
