package co.com.sofkau.biblioteca.biblioteca.dto;

import java.time.LocalDate;

public class ResourceDTO {

    private String id;
    private String name;
    private Boolean state;
    private LocalDate loanDate;
    private String type;
    private String thematic;

    public ResourceDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }


    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThematic() {
        return thematic;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }

    @Override
    public String toString() {
        return "ResourceDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", loanDate=" + loanDate +
                ", type='" + type + '\'' +
                ", thematic='" + thematic + '\'' +
                '}';
    }
}
