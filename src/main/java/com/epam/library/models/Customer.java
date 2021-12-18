package com.epam.library.models;

import java.util.Date;
import java.util.Objects;

public class Customer {

    private int id;
    private String name;
    private String surname;
    private String address;
    private String email;
    private java.util.Date dateOfSignUp;
    private PersonRole role;
    private Boolean locked;

    private String login;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Date getDateOfSignUp() {
        return (java.sql.Date) dateOfSignUp;
    }

    public void setDateOfSignUp(Date dateOfSignUp) {
        this.dateOfSignUp = dateOfSignUp;
    }

    public PersonRole getRole() {
        return role;
    }

    public void setRole(PersonRole role) {
        this.role = role;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(dateOfSignUp, customer.dateOfSignUp) &&
                role == customer.role &&
                Objects.equals(locked, customer.locked) &&
                Objects.equals(login, customer.login) &&
                Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, address, email, dateOfSignUp, role, locked, login, password);
    }
}
