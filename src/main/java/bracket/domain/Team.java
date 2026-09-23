package bracket.domain;

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

    public void of(String name){

        if(name == null || name.isEmpty()){

            throw new IllegalArgumentException("Nome inválido");

        }

        new Team(name);

    }

    public void incrementLoss(){

        numberLosses += 1;

    }

    public void incrementWin(){

       numberWins += 1;

    }

}
