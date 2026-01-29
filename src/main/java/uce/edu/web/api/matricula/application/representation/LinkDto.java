package uce.edu.web.api.matricula.application.representation;

public class LinkDto {

    //va a tener dos cosas, 1 el nombre que le vamos a dar a la url,
    public String href;
    public String rel;

    public LinkDto(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

    public LinkDto() {
    }
}
