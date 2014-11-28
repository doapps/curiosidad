package me.doapps.beans;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Random;

@ParseClassName("Curiosities")
public class Curiosidad_DTO extends ParseObject {

    public String initialCuriosidad;
    public String nameCuriosidad;
    public String descriptionCuriosidad;
    public String immage_url;

    public String getInitialCuriosidad() {
        return String.valueOf(getNameCuriosidad().charAt(new Random().nextInt(getNameCuriosidad().length())));
    }

    public String getNameCuriosidad() {
        return getString("name");
    }

    public void setNameCuriosidad(String nameCuriosidad) {
        this.nameCuriosidad = nameCuriosidad;
    }

    public String getDescriptionCuriosidad() {
        return getString("description");
    }

    public void setDescriptionCuriosidad(String descriptionCuriosidad) {
        this.descriptionCuriosidad = descriptionCuriosidad;
    }

    public String getImmage_url(){
        return getString("image_url");
    }
    public void setImmage_url(String image_url){
        put("image_url", image_url);
    }

    public static ParseQuery<Curiosidad_DTO> getQuery() {
        return ParseQuery.getQuery(Curiosidad_DTO.class);
    }
}
