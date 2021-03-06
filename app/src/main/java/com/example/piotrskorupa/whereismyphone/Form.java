package com.example.piotrskorupa.whereismyphone;

/**
 * Created by Piotr Skorupa on 2017-11-15.
 */

public class Form {

    private String login;
    private String password;
    private String eui;
    private int interval;
    private String secret;

    private static Form INSTANCE;

    private Form(){
        login = "";
        password = "";
        eui = "";
        interval = 1000;
        secret = "";
    }

    public static Form getInstance(){
        if(INSTANCE == null)
        {
            INSTANCE = new Form();
        }
        return INSTANCE;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public String getEui(){
        return eui;
    }

    public int getInterval(){
        return interval;
    }

    public String getSecret(){
        return secret;
    }

    public void setLogin(String text)
    {
        login = text;
    }

    public void setPassword(String text)
    {
        password = text;
    }

    public void setEui(String text)
    {
        eui = text;
    }

    public void setInterval(int text)
    {
        interval = text;
    }

    public void setSecret(String text)
    {
        secret = text;
    }


}
