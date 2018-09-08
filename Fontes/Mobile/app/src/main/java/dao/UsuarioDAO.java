package dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class UsuarioDAO {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private DAO dao = new DAO();

    public JSONObject ExisteEmail(String email) {
        try {
            String data = dao.doGet("/usuario/existe/" + email);
            JSONObject jObject = new JSONObject(data);

            return jObject;
        } catch (Exception e) {
            Log.e("Teste", e.toString());
            e.printStackTrace();
        }

        return null;
    }
}
