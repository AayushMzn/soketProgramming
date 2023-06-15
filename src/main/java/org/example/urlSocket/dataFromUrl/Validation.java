package org.example.urlSocket.dataFromUrl;

public class Validation {
    public boolean valid(String user,String pass) {
        boolean bool = true;
        if(!user.equals("^[a-zA-Z][ ]*$")){
            bool = false;
        }
        return bool;
    }
    public boolean emptyFieldValid(String data) {
        boolean bool = true;

        return bool;
    }
}
