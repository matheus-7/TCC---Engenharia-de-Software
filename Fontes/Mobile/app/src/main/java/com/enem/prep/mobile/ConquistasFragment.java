package com.enem.prep.mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import dao.ConquistaDAO;
import dao.UsuarioDAO;
import models.Conquista;
import models.Questao;
import models.Usuario;

import static java.lang.Integer.parseInt;


public class ConquistasFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btnUserConquistas = null;
    private TextView tvNomeUsuario = null;
    private ListView lvListaConquistas;
    private Usuario usuario = null;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ConquistaDAO conquistaDAO = new ConquistaDAO();

    private OnFragmentInteractionListener mListener;

    public ConquistasFragment() {

    }

    public static ConquistasFragment newInstance(String param1, String param2) {
        ConquistasFragment fragment = new ConquistasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_conquistas, container, false);

        String emailUsuario = getArguments().getString("email", "defaultStringIfNothingFound");

        btnUserConquistas = (Button) view.findViewById(R.id.btnUserConquistas);
        tvNomeUsuario = (TextView) view.findViewById(R.id.tvNomeUsuario);
        lvListaConquistas = (ListView) view.findViewById(R.id.lvListaConquistas);

        new SelecionarTask(emailUsuario, this.getActivity().getApplicationContext()).execute();

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    private class SelecionarTask extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;
        private String email;
        private JSONArray JSONArray;
        private Context context;


        public SelecionarTask(String email, Context context) {
            this.email = email;
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {

            usuario = new Usuario();

            try
            {
                JSONObject obj = usuarioDAO.Selecionar(email);

                usuario.setId(obj.getInt("id"));
                usuario.setNome(obj.getString("nome"));

                btnUserConquistas.setText(usuario.getNome().substring(0, 1));
                tvNomeUsuario.setText(usuario.getNome());

                try {
                    JSONArray = conquistaDAO.Listar(usuario.getId());
                } catch (Exception e) {
                    return null;
                }

                try {
                    if (JSONArray != null) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Conquista>>() {
                        }.getType();
                        List<Conquista> conquistas = gson.fromJson(JSONArray.toString(), type);

                        usuario.setConquistas(conquistas);

                        ArrayAdapter<Conquista> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, conquistas);
                        lvListaConquistas.setAdapter(adapter);
                    }
                } catch (Exception E) {
                    return null;
                }
            }
            catch (Exception e){
                return "Não foi realizar esta ação. Tente novamente mais tarde!";
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
            dialog.setMessage("Carregando...");
            dialog.setIndeterminate(true);
            dialog.show();
        }
    }
}
