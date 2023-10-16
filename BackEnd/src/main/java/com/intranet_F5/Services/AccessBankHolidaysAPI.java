package com.intranet_F5.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.intranet_F5.DTO.DTOBankHolidays;
import com.intranet_F5.Model.SchoolDateModel;
import com.intranet_F5.Model.SchoolModel;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccessBankHolidaysAPI {

    private List<LocalDate> fetchHolidays(SchoolModel.StateCode state) {
        HttpClient myClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.generadordni.es/v2/holidays/holidays?year=2023&country=ES&state="+state))
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = myClient.send(request, BodyHandlers.ofString());
            System.out.println(respuesta.body());

            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);

            List<DTOBankHolidays> myBHDysList = mapper.readValue(respuesta.body(),
                    new TypeReference<List<DTOBankHolidays>>() {});

            List<LocalDate> myBHDYList = new ArrayList<>();
            for (DTOBankHolidays holiday : myBHDysList) {
                myBHDYList.add(holiday.getDate().toLocalDate());
            }
            return myBHDYList;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SchoolDateModel> getSchoolDateModelList(SchoolModel newSchool){
        AccessBankHolidaysAPI accessBankHolidaysAPI=new AccessBankHolidaysAPI();
        List<LocalDate> holidays = accessBankHolidaysAPI.fetchHolidays(newSchool.getSchoolStateCode());

        List<SchoolDateModel> schoolDateModels = new ArrayList<>();
        for (LocalDate date : holidays) {
            SchoolDateModel schoolDateModel = new SchoolDateModel();
            schoolDateModel.setDate(date);
            schoolDateModels.add(schoolDateModel);
        }
        return schoolDateModels;
    }
}
