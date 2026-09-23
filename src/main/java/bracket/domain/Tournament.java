package bracket.domain;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Tournament {

    private UUID id;
    private String code;
    private int maxTeams;

    private Tournament(String name, int maxTeams){

        this.code = generateCode(name);
        this.maxTeams = maxTeams;
        this.id = UUID.randomUUID();

    }

    public void of(String name, int maxTeams){

        if(name == null || name.isBlank()){

            throw new IllegalArgumentException("O nome do torneio inserido é inválido.");

        }

        if(maxTeams < 0){

            throw new IllegalArgumentException("Número de equipas inserido é inválido");

        }

        new Tournament(name, maxTeams);

    }

    private String generateCode(String name) {
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD);
        String slug = normalized.replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", "");

        String shortHash = UUID.randomUUID().toString().substring(0, 5).toUpperCase();

        return slug + "-" + shortHash;
    }
}
