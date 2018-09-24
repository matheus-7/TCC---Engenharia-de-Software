package dao;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import models.Usuario;

public class UsuarioDAO {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private DAO dao = new DAO();

    public JSONObject Selecionar(String email) {
        try {
            String data = dao.doGet("/usuario/" + email);
            JSONObject jObject = new JSONObject(data);

            return jObject;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject Salvar(Usuario usuario) {
        try {
            Gson json = new Gson();
            String data = "";
            if (usuario.getId() != 0) {
                data = dao.doPut("/usuario/" + usuario.getId(), json.toJson(usuario));
            } else {
                data = dao.doPost("/usuario", json.toJson(usuario));
            }
            JSONObject jObject = new JSONObject(data);

            return jObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
