package ca.uwo.eng.se2205b.lab5.model;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
/**
 * Represents a Bank
 */
@ParametersAreNonnullByDefault
public final class Bank {
    
    private final MyHashMap<String, Person> bankMemebers;
    
    public Bank() {
        bankMemebers = new MyHashMap<>();
    }


    /**
     * Get all of the accounts for a person
     * @param person Person whom has an account
     * @return Possibly empty {@code Set} of accounts for a Person
     *
     * @throws NullPointerException if {@code person} is {@code null}
     */
    public Set<Account> getAccounts(Person person) {
        if(person != null)
            return (Set<Account>) person.getAccounts();
        else
            throw new NullPointerException("person cannot be null");
    
    }

    /**
     * Opens a new {@link Account} for an individual.
     * @param person Person who is on an account
     * @return New {@code Account}
     *
     * @throws NullPointerException if {@code person} is {@code null}
     */
    public Account openAccount(Person person) {
        if(person != null) {
            Account newAccount = new Account();
            return person.addAccount(newAccount);
        }else
            throw new NullPointerException("person cannot be null");
    
    }

    /**
     * Closes an account for an individual
     * @param person Person who is on an account
     * @param account Account to close
     *
     * @throws NullPointerException if {@code person} or {@code account} is {@code null}
     * @throws AccountCloseException if the Account can not be closed
     */
    public void closeAccount(Person person, Account account) throws AccountCloseException {
        if(person != null || account != null){
            if(person.getAccounts().remove(account) == null){
                throw new IllegalArgumentException(person.getFirstName() + " does not have the specified account");
            }
        }
        else
            throw new NullPointerException("person or account was null");
    
    }

    /**
     * Get <em>all</em> of the wealth of a person, the total of all of a person's accounts.
     * @param person Non-{@code null} person to get the total wealth of.
     * @return Total wealth
     *
     * @throws NullPointerException if {@code person} is {@code null}
     */
    public double getTotalWealth(Person person) {
        if (person != null) {
            return 0.0;
        }
        else{
            throw new NullPointerException("person was a null value");
        }
        
    
    }
}
