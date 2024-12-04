package packagescreenmatch.sistem.principal;

import packagescreenmatch.sistem.calculos.FiltroRecomendacao;
import packagescreenmatch.sistem.modelos.Episodio;
import packagescreenmatch.sistem.modelos.Filme;
import packagescreenmatch.sistem.modelos.Serie;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Filme primeiroFilme = new Filme("O Poderoso Chefão", 1972);
        primeiroFilme.setDuracaoEmMinutos(180);
        primeiroFilme.exibeFichaTecnica();
        primeiroFilme.avalia(9);
        primeiroFilme.avalia(10);
        primeiroFilme.avalia(4);
        System.out.println("Duração do filme: " + primeiroFilme.getDuracaoEmMinutos());
        System.out.println("Total de avaliações: " + primeiroFilme.getTotalDeAvaliacoes());
        System.out.println(primeiroFilme.pegaMedia());

        Filme segundoFilme = new Filme("Star Wars: O Império Contra-Ataca", 1980);
        segundoFilme.setDuracaoEmMinutos(200);

        var terceiroFilme = new Filme("Clube da Luta", 2000);
        terceiroFilme.setDuracaoEmMinutos(190);
        terceiroFilme.avalia(10);

        Serie lost = new Serie("Lost", 2000);
        lost.exibeFichaTecnica();
        lost.setTemporadas(10);
        lost.setEpisodiosPorTemporada(10);
        lost.setMinutosPorEpisodio(50);
        System.out.println("Duração para maratonar Lost: " + lost.getDuracaoEmMinutos());

        FiltroRecomendacao filtro = new FiltroRecomendacao();
        filtro.filtra(primeiroFilme);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(lost);
        episodio.setTotalVisualizacoes(300);
        filtro.filtra(episodio);


        ArrayList<Filme> listaDeFilmes = new ArrayList<>();
        listaDeFilmes.add(terceiroFilme);
        listaDeFilmes.add(primeiroFilme);
        listaDeFilmes.add(segundoFilme);
        System.out.println("Tamanho da lista " + listaDeFilmes.size());
        System.out.println("Primeiro filme " + listaDeFilmes.get(0).getNome());
        System.out.println(listaDeFilmes);
        System.out.println("toString do filme " + listaDeFilmes.get(0).toString());

    }
}
