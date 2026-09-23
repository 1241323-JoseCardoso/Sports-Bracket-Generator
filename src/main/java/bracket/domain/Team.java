package bracket.domain;

public class Team {

    private String name;

    private Team(String name){

        this.name = name;

    }

    public void of(String name){

        new Team(name);

    }
}
