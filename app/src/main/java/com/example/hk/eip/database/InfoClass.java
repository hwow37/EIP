package com.example.hk.eip.database;

public class InfoClass {
    public int _id;
    public String name;
    public String meaning;
    public int check_word;

    public InfoClass(){}

    public InfoClass(int _id , String name , String meaning, int check_word){
        this._id = _id;
        this.name = name;
        this.meaning = meaning;
        this.check_word = check_word;
    }

    public String getName(){
        return name;
    }

    public String getMeaning(){
        return meaning;
    }
}
