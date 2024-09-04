package com.eduardocaio.movie_library_backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusUser {

    ATIVO ("A", "Ativo"),
    INATIVO("I", "Inativo"),
    PENDENTE("P", "Pendente");

    private String code;
    private String description;
    
    private StatusUser(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonCreator
    public static StatusUser status(String code){
        if(code.equals("A")){
            return ATIVO;
        }else if(code.equals("I")){
            return INATIVO;
        }
        else if(code.equals("P")){
            return PENDENTE;
        }
        else{
            return null;
        }
    }

}
