package packagescreenmatch.sistem.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import packagescreenmatch.sistem.exeption.ErroDeConversaoDeAnoException;
import packagescreenmatch.sistem.modelos.Titulo;
import packagescreenmatch.sistem.modelos.TituloOmdb;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String busca = "";
        List<Titulo> listasDeFilmes = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.print("\nDigite um filme para buscar: ");
            busca = sc.nextLine();

            if (busca.equalsIgnoreCase("sair")){
                break;
            }

            String endereco = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=72019bad";

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);
                System.out.println();

                TituloOmdb segundoTitulo = gson.fromJson(json, TituloOmdb.class);


                Titulo primeiroTitulo = new Titulo(segundoTitulo);
                System.out.println("-------- FILMES CADASTRADOS -------");
                System.out.println(primeiroTitulo);

                listasDeFilmes.add(primeiroTitulo);
            } catch (NumberFormatException e) {
                System.out.println("-------- ERROR -------");
                System.out.println("Teve um erro que e: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro no modo de escrita na busca");
                System.out.println(e.getMessage());
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(listasDeFilmes);
        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(listasDeFilmes));
        escrita.close();
        System.out.println("Sistema finalizado com sucesso!");

    }
}
