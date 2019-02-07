package ca.uwo.eng.se2205b.lab5.model;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * Represents a Person
 */
@ParametersAreNonnullByDefault
@Immutable
public final class Person {

    private final String firstName;
    private final String lastName;
    private final MyHashMap<String, Account> accounts;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = new MyHashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public Account addAccount(Account account){
        return (Account)accounts.put(firstName, account);
    }
    
    public MyHashMap<String, Account> getAccounts(){
        return accounts;
    }
    
    public int hashCode(){
        int out = firstName.hashCode();
        out += 37*lastName.hashCode();
        //cant just call hashCOde on accounts
        out += 37*accounts.hashCode();//fix
        return out;
    }
    
}
