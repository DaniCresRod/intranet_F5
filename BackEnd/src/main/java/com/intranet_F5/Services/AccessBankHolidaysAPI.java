package com.intranet_F5.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.intranet_F5.DTO.DTOBankHolidays;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import static org.hibernate.type.SqlTypes.JSON;

public class AccessBankHolidaysAPI {

    public static void main(String[] args) {
        AccessBankHolidaysAPI api = new AccessBankHolidaysAPI();
        api.fetchHolidays();
    }

    public void fetchHolidays() {
        HttpClient myClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.generadordni.es/v2/holidays/holidays?year=2023&country=ES"))
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = myClient.send(request, BodyHandlers.ofString());
            System.out.println(respuesta.body());

           // JSONObject myobj=new JSONObject(respuesta.body());

            ObjectMapper mapper=new ObjectMapper();
            //mapper.registerModule(new JavaTimeModule());
            List<DTOBankHolidays> myBHDysList = mapper.readValue(respuesta.body(),
                    new TypeReference<List<DTOBankHolidays>>() {});

            // Ahora tienes la lista de DTOBankHolidays para trabajar con ella
                    for (DTOBankHolidays holiday : myBHDysList) {
                        System.out.println(holiday);
                    }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
