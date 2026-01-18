package model;

import java.time.LocalDateTime;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime accountCreatedOn;


    public User(long id, String firstName, String lastName, String phoneNumber, LocalDateTime accountCreatedOn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.accountCreatedOn = accountCreatedOn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getAccountCreatedOn() {
        return accountCreatedOn;
    }

    public void setAccountCreatedOn(LocalDateTime accountCreatedOn) {
        this.accountCreatedOn = accountCreatedOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
