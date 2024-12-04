package packagescreenmatch.sistem.principal;

import packagescreenmatch.sistem.modelos.Filme;
import packagescreenmatch.sistem.modelos.Serie;
import packagescreenmatch.sistem.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PrincipalComListas {
    public static void main(String[] args) {
        var primeiroFilme = new Filme("O poderoso chefão", 1970);
        primeiroFilme.avalia(9);
        var segundoFilme = new Filme("Avatar", 2023);
        segundoFilme.avalia(6);
        var terceiroFilme = new Filme("Dogville", 2003);
        terceiroFilme.avalia(10);
        Serie lost = new Serie("Lost", 2000);

        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(primeiroFilme);
        lista.add(segundoFilme);
        lista.add(terceiroFilme);
        lista.add(lost);
        for (Titulo item: lista) {
            System.out.println(item.getNome());
            if (item instanceof  Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação " + filme.getClassificacao());
            }
        }

        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Adam Sandler");
        buscaPorArtista.add("Paulo");
        buscaPorArtista.add("Jacqueline");
        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println("Depois da ordenação");
        System.out.println(buscaPorArtista);
        System.out.println("Lista de titulos ordenados");
        Collections.sort(lista);
        System.out.println(lista);
        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println("Ordenando por ano");
        System.out.println(lista);



    }
}
