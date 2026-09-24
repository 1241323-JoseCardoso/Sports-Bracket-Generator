package bracket.domain;

import java.util.Objects;
import java.util.UUID;

public class Team {

    private UUID id;
    private String name;
    private int numberLosses;
    private int numberWins;

    private Team(String name){

        this.name = name;
        this.id = UUID.randomUUID();
        this.numberLosses = 0;
        this.numberWins = 0;

    }

    public static Team of(String name){

        if(name == null || name.isBlank()){

            throw new IllegalArgumentException("Nome inválido");

        }

        return new Team(name);

    }

    public void incrementLoss(){

        numberLosses += 1;

    }

    public void incrementWin(){

       numberWins += 1;

    }

    public UUID getId(){

        return id;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;
        return Objects.equals(this.id, team.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
