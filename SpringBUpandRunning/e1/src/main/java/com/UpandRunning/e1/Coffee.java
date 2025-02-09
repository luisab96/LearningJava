package com.UpandRunning.e1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Coffee {

    private final String id;
    private String name;

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonCreator
    public Coffee(@JsonProperty("name") String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getName() {
        return name;
    }

    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
}
