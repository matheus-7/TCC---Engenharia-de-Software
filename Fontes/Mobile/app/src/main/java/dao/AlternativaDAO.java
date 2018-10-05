package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

public class AlternativaDAO {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private DAO dao = new DAO();

    public JSONArray Listar(int idQuestao) {
        try {
            String data = dao.doGet("/alternativa/" + idQuestao);
            JSONArray array = new JSONArray(data);
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
