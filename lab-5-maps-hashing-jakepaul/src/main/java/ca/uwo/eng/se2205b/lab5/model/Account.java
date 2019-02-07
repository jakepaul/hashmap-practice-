package ca.uwo.eng.se2205b.lab5.model;

import javax.annotation.ParametersAreNonnullByDefault;
import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 * Represents an Account with a list of time organized transactions.
 */
@ParametersAreNonnullByDefault
public final class Account {
    
    private TreeMap<LocalDateTime, Transaction> transactions;
    private double balance;
    
    public Account() {
        transactions = new TreeMap<>();
        balance = 0;
    }
    
    
    /**
     * Add a transaction to the account, updating the balance
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {
        // TODO SE2205B
        transactions.put(transaction.getDateTime(), transaction);
        balance += transaction.getAmount();
    
    }

    /**
     * Get the Balance of this account
     * @return Current balance
     */
    public double getBalance() {
        return balance;
    }
    
    public int hashCode(){
        int out = transactions.hashCode();
        out += 37*Double.hashCode(balance);
        return out;
    }
    
}
