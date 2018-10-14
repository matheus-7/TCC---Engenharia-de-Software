package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import models.Resposta;


public class RespostaDAO {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private DAO dao = new DAO();

    public JSONObject Salvar(List<Resposta> respostas) {
        try {
            Gson json = new Gson();
            String data = "";

            data = dao.doPost("/resposta", json.toJson(respostas));

            JSONObject jObject = new JSONObject(data);

            return jObject;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONArray Listar(int idUsuario) {
        try {
            String data = dao.doGet("/resposta/" + idUsuario);
            JSONArray array = new JSONArray(data);
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
