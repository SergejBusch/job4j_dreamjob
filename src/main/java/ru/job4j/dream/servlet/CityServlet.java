package ru.job4j.dream.servlet;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        var cities = PsqlStore.instOf().getAllCities();

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(cities, new TypeToken<List<City>>() { }.getType());

        if (!element.isJsonArray()) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        var jsonArray = element.getAsJsonArray();
        PrintWriter writer = resp.getWriter();
        writer.print(jsonArray);
        writer.flush();
    }
}
