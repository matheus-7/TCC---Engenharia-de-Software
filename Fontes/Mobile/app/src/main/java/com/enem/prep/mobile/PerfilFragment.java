package com.enem.prep.mobile;

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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import dao.UsuarioDAO;
import models.Usuario;


public class PerfilFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button btnUserConquistas = null;
    private TextView tvNomeUsuario = null;
    private Usuario usuario = null;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {

    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        final View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        final String emailUsuario = getArguments().getString("email", "defaultStringIfNothingFound");
        Boolean alteracaoPermitida = getArguments().getBoolean("AlteracaoPermitida", false);

        btnUserConquistas = (Button) view.findViewById(R.id.btnUserConquistas);
        tvNomeUsuario = (TextView) view.findViewById(R.id.tvNomeUsuario);

        if (alteracaoPermitida){
            btnUserConquistas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlterarPerfil(emailUsuario);
                }
            });
        }

        new SelecionarTask(emailUsuario, this.getActivity().getApplicationContext()).execute();

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void AlterarPerfil(String emailUsuario){
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();

        fragmentClass = AlterarPerfilFragment.class;

        bundle.putString("email", emailUsuario);

        try{
            fragment = (Fragment) fragmentClass.newInstance();

            fragment.setArguments(bundle);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
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
        private org.json.JSONArray JSONArray;
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
