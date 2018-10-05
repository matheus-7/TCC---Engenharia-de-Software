package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

public class QuestaoDAO {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private DAO dao = new DAO();

    public JSONArray Listar(int idArea) {
        try {
            String data = dao.doGet("/questao/" + idArea);
            JSONArray array = new JSONArray(data);
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
