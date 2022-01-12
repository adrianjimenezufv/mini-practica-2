package com.ufv;

import com.google.gson.Gson;
import com.googlecode.gentyref.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Requests {
    private static final String api= "http://localhost:8095/%s/%s";
    private static HttpRequest request;
    private static HttpClient client= HttpClient.newBuilder().build();
    private static HttpResponse<String> response;
    //Post
    private static HttpRequest request1;
    private static HttpClient client1= HttpClient.newBuilder().build();
    private static HttpResponse<String> response1;

    //DELETE
    private static HttpRequest request2;
    private static HttpClient client2= HttpClient.newBuilder().build();
    private static HttpResponse<String> response2;


    public static ArrayList<Usuario> devolver_Users(){

        String resource = String.format(api, "Usuarios","");


        ArrayList<Usuario> userlist=null;
        try{

            request = HttpRequest.newBuilder()
                    .uri(new URI(resource))
                    .headers("Content-Type", "application/json")
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String usersFetched = response.body();
            Type listaUsuarios= new TypeToken<ArrayList<Usuario>>(){}.getType();

            Gson gson = new Gson ();
            userlist= gson.fromJson(usersFetched,listaUsuarios);

        }catch (URISyntaxException | IOException | InterruptedException e){
            e.printStackTrace();
        }

        return userlist;
    }
    public static  void añadir_Users(Usuario user) {
        String resource1 = String.format(api, "Usuarios","");
        String newUser=user.toString();


        try{

            request1 = HttpRequest.newBuilder()
                    .uri(new URI(resource1))
                    .headers("Content-Type", "application/json;charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(newUser))
                    .build();

            response1 = client1.send(request1, HttpResponse.BodyHandlers.ofString());
            System.out.println(response1);


        }catch (URISyntaxException | IOException | InterruptedException e){
            e.printStackTrace();
        }


    }
    public static  void eliminar_Users(String id) {
        String resource2 = String.format(api, "Usuarios","id_usuario="+ id);

        try{

            request2 = HttpRequest.newBuilder()
                    .uri(new URI(resource2))
                    .headers("Content-Type", "application/json")
                    .DELETE()
                    .build();

            response2 = client2.send(request2, HttpResponse.BodyHandlers.ofString());
            System.out.println(response2);


        }catch (URISyntaxException | IOException | InterruptedException e){
            e.printStackTrace();
        }


    }
}
