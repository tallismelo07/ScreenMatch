package packagescreenmatch.sistem.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import packagescreenmatch.sistem.modelos.Titulo;
import packagescreenmatch.sistem.modelos.TituloOmdb;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite um filme para buscar: ");
        var busca = sc.nextLine();
        // https://www.omdbapi.com/?t=avengers&apikey=72019bad
        String endereco = "https://www.omdbapi.com/?t=" + busca + "&apikey=72019bad";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);
        System.out.println();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        TituloOmdb segundoTitulo = gson.fromJson(json, TituloOmdb.class);

        try {
            Titulo primeiroTitulo = new Titulo(segundoTitulo);
            System.out.println("-------- FILMES CADASTRADOS -------");
            System.out.println(primeiroTitulo);
        } catch (NumberFormatException e){
            System.out.println("-------- ERROR -------");
            System.out.println("Teve um erro que e: " + e.getMessage());
        } finally {
            System.out.println("Sistema finalizado com sucesso!");

        }


    }
}
