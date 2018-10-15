package com.enem.prep.mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import java.util.ArrayList;
import java.util.List;

import adapter.ConquistasAdapter;
import dao.ConquistaDAO;
import dao.UsuarioDAO;
import models.Conquista;
import models.ConquistaConfig;
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
        View view = inflater.inflate(R.layout.fragment_conquistas, container, false);

        String emailUsuario = getArguments().getString("email", "defaultStringIfNothingFound");

        btnUserConquistas = (Button) view.findViewById(R.id.btnUserConquistas);
        tvNomeUsuario = (TextView) view.findViewById(R.id.tvNomeUsuario);
        lvListaConquistas = (ListView) view.findViewById(R.id.lvListaConquistas);

        ConquistaConfig conf = new ConquistaConfig();
        conf.setTitulo("Teste");

        ConquistaConfig conf2 = new ConquistaConfig();
        conf.setTitulo("Teste2");

        Conquista conq = new Conquista();
        conq.setConquistaConfig(conf);

        Conquista conq2 = new Conquista();
        conq2.setConquistaConfig(conf2);

        List<Conquista> conqs = new ArrayList<Conquista>();
        conqs.add(conq);
        conqs.add(conq2);

        btnUserConquistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Class fragmentClass = null;
                Bundle bundle = new Bundle();
                fragmentClass = PerfilFragment.class;

                bundle.putString("email", usuario.getEmail());
                bundle.putBoolean("AlteracaoPermitida", true);

                try{
                    fragment = (Fragment) fragmentClass.newInstance();

                    fragment.setArguments(bundle);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
                getActivity().setTitle("Perfil");
            }
        });

        new SelecionarTask(emailUsuario, this.getActivity()).execute();

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
                usuario.setEmail(email);

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
            ConquistasAdapter adapter = new ConquistasAdapter(context, usuario.getConquistas());
            lvListaConquistas.setAdapter(adapter);

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
