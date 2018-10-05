package com.enem.prep.mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Rating;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONObject;

import dao.AvaliacaoDAO;
import dao.UsuarioDAO;
import models.Avaliacao;
import models.Usuario;

import static java.lang.Integer.parseInt;


public class AvaliarFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RatingBar rbNota = null;
    private EditText edtComentario = null;

    private final AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

    public AvaliarFragment() {
        // Required empty public constructor
    }

    public static AvaliarFragment newInstance(String param1, String param2) {
        AvaliarFragment fragment = new AvaliarFragment();
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
        final View view = inflater.inflate(R.layout.fragment_avaliar, container, false);

        Button btnEnviar = (Button) view.findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Avaliacao avaliacao = montarAvaliacao(view);

                new InserirTask(avaliacao, getActivity()).execute();
            }
        });

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

    private void showToast(String mensagem) {
        Toast.makeText(getActivity(), mensagem, Toast.LENGTH_SHORT).show();
    }

    private void getCampos(View view){
        rbNota = (RatingBar) view.findViewById(R.id.rbNota);
        edtComentario = (EditText) view.findViewById(R.id.edtComentario);
    }

    private Avaliacao montarAvaliacao(View view) {
        SharedPreferences prefs = getActivity().getSharedPreferences("Preferencias", 0);
        Usuario usuario = new Usuario();
        getCampos(view);

        usuario.setId(prefs.getInt("idUsuario", 0));

        Avaliacao avaliacao = new Avaliacao(
                0,
                rbNota.getRating(),
                edtComentario.getText().toString(),
                usuario,
                null
        );

        return avaliacao;
    }


    private class InserirTask extends AsyncTask<String, Void, String> {

        private Activity activity;
        private ProgressDialog dialog;
        private Avaliacao avaliacao;

        public InserirTask(Avaliacao avaliacao, Activity activity) {
            this.avaliacao = avaliacao;
            this.activity = activity;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                JSONObject obj = avaliacaoDAO.Salvar(avaliacao);
            }
            catch (Exception e){
                return "Não foi possível realizar o cadastro. Tente novamente mais tarde!";
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();

            if (!result.equals("")) showToast("Ocorreu um erro ao tentar inserir a avaliação!");
            else{
                Intent intentEntrar = new Intent(activity, MainActivity.class);
                startActivity(intentEntrar);
            }
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(activity);
            dialog.setIndeterminate(true);
            dialog.setMessage("Carregando...");
            dialog.show();
        }
    }
}
