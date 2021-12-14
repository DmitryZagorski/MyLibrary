package com.epam.library.exceptions;

public class CustomerNotFoundException extends CustomerException {

    private Integer id;
    private String name;
    private String surname;

    public CustomerNotFoundException(Integer id){
        this.id = id;
    }

    public CustomerNotFoundException(Integer id, Throwable e){
        super(e);
        this.id = id;
    }

    public CustomerNotFoundException(String name, String surname, Throwable e){
        super(e);
        this.name = name;
        this.surname = surname;
    }

}
