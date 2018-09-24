package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import models.Avaliacao;

public class AvaliacaoDAO {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private DAO dao = new DAO();

    public JSONObject Salvar(Avaliacao avaliacao) {
        try {
            Gson json = new Gson();
            String data = "";

            dao.doPost("/avaliacao", json.toJson(avaliacao));

            JSONObject jObject = new JSONObject(data);

            return jObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
