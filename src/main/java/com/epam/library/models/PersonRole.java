package com.epam.library.models;

import java.util.Arrays;

public enum PersonRole {
    Admin,
    Customer;

    public static PersonRole getRoleByOrdinal(int ordinal){
        return Arrays.asList(PersonRole.values()).stream().filter(perRole -> ordinal == perRole.ordinal()).findFirst().get();
    }

}
