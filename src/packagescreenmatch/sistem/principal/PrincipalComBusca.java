package packagescreenmatch.sistem.principal;

import com.google.gson.Gson;
import packagescreenmatch.sistem.modelos.Titulo;

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

        Gson gson = new Gson();
        Titulo primeiroTitulo = gson.fromJson(json, Titulo.class);
        System.out.println(primeiroTitulo);


    }
}
