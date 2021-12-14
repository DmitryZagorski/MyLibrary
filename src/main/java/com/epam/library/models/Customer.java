package com.epam.library.models;

import java.sql.Date;
import java.util.Objects;

public class Customer {

    private int id;
    private String name;
    private String surname;
    private java.util.Date birth;
    private String address;
    private java.util.Date dateOfSignUp;

    public Customer() {
    }

    public Customer(String name, String surname, Date birth, String address, Date dateOfSignUp) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.address = address;
        this.dateOfSignUp = dateOfSignUp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirth() {
        return (Date) birth;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfSignUp() {
        return (Date) dateOfSignUp;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfSignUp(Date dateOfSignUp) {
        this.dateOfSignUp = dateOfSignUp;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", address='" + address + '\'' +
                ", dateOfSignUp=" + dateOfSignUp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(birth, customer.birth) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(dateOfSignUp, customer.dateOfSignUp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birth, address, dateOfSignUp);
    }
}
