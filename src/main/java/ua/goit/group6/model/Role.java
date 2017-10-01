package ua.goit.group6.model;


public class Role extends Model {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{ " + super.toString() +
                ", name='" + name + '\'' +
                "} " ;
    }
}
