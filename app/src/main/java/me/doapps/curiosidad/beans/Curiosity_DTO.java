package me.doapps.curiosidad.beans;

/**
 * Created by mili on 30/12/2014.
 */
public class Curiosity_DTO {
    public String id;
    public String nameCuriosidad;
    public String descriptionCuriosidad;
    public String image_url;

    public Curiosity_DTO(){}

    public Curiosity_DTO(String id, String nameCuriosidad, String descriptionCuriosidad, String image_url) {
        this.id = id;
        this.nameCuriosidad = nameCuriosidad;
        this.descriptionCuriosidad = descriptionCuriosidad;
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCuriosidad() {
        return nameCuriosidad;
    }

    public void setNameCuriosidad(String nameCuriosidad) {
        this.nameCuriosidad = nameCuriosidad;
    }

    public String getDescriptionCuriosidad() {
        return descriptionCuriosidad;
    }

    public void setDescriptionCuriosidad(String descriptionCuriosidad) {
        this.descriptionCuriosidad = descriptionCuriosidad;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
