package com.enem.prep.mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import adapter.RankingAdapter;
import dao.ConquistaDAO;
import dao.RespostaDAO;
import dao.UsuarioDAO;
import models.Conquista;
import models.Resposta;
import models.Usuario;


public class RankingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ListView lvListaRanking = null;

    private Button btnGeral = null;
    private Button btnUniversidade = null;
    private Button btnCurso = null;

    private Usuario usuario = null;

    private List<Usuario> rankingGeral = null;
    private List<Usuario> rankingCurso = null;
    private List<Usuario> rankingUniversidade = null;

    private FragmentManager fragmentManager = null;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ConquistaDAO conquistaDAO = new ConquistaDAO();
    private final RespostaDAO respostaDAO = new RespostaDAO();

    private OnFragmentInteractionListener mListener;

    public RankingFragment() {

    }


    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
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

        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        SharedPreferences prefs = getActivity().getSharedPreferences("Preferencias", 0);
        String emailUsuario = prefs.getString("email", "defaultStringIfNothingFound");
        final Context cont = this.getActivity();

        btnGeral = (Button) view.findViewById(R.id.btnGeral);
        btnUniversidade = (Button) view.findViewById(R.id.btnUniversidade);
        btnCurso = (Button) view.findViewById(R.id.btnCurso);

        lvListaRanking = (ListView) view.findViewById(R.id.lvListaRanking);

        btnGeral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelecionarTipo(btnGeral);

                RankingAdapter adapter = new RankingAdapter(cont, rankingGeral, usuario, fragmentManager, getActivity());
                lvListaRanking.setAdapter(adapter);
            }
        });

        btnUniversidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelecionarTipo(btnUniversidade);

                RankingAdapter adapter = new RankingAdapter(cont, rankingUniversidade, usuario, fragmentManager, getActivity());
                lvListaRanking.setAdapter(adapter);
            }
        });

        btnCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelecionarTipo(btnCurso);

                RankingAdapter adapter = new RankingAdapter(cont, rankingCurso, usuario, fragmentManager, getActivity());
                lvListaRanking.setAdapter(adapter);
            }
        });

        new SelecionarTask(emailUsuario, cont).execute();

        return view;
    }

    private void SelecionarTipo(Button button) {
        btnGeral.setBackgroundColor(btnGeral.getContext().getResources().getColor(R.color.colorPrimary));
        btnUniversidade.setBackgroundColor(btnUniversidade.getContext().getResources().getColor(R.color.colorPrimary));
        btnCurso.setBackgroundColor(btnCurso.getContext().getResources().getColor(R.color.colorPrimary));

        btnGeral.setTextColor(btnGeral.getContext().getResources().getColor(R.color.white));
        btnUniversidade.setTextColor(btnUniversidade.getContext().getResources().getColor(R.color.white));
        btnCurso.setTextColor(btnCurso.getContext().getResources().getColor(R.color.white));

        button.setBackgroundColor(button.getContext().getResources().getColor(R.color.Default));
        button.setTextColor(btnGeral.getContext().getResources().getColor(R.color.colorPrimary));
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

                if (obj != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Usuario>() {
                    }.getType();
                    usuario = gson.fromJson(obj.toString(), type);
                }

                ListarRanking();
            }
            catch (Exception e){
                return "Não foi realizar esta ação. Tente novamente mais tarde!";
            }

            return "";
        }

        private void ListarRespostas(List<Usuario> usuarios){
            for(Usuario usu : usuarios) {
                try {
                    JSONArray = respostaDAO.Listar(usu.getId());
                } catch (Exception e) {

                }

                try {
                    if (JSONArray != null) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Resposta>>() {
                        }.getType();
                        List<Resposta> respostas = gson.fromJson(JSONArray.toString(), type);

                        usu.setRespostas(respostas);
                    }
                } catch (Exception E) {}
            }

        }

        private void ListarRanking(){
            boolean existe = false;

            try {
                JSONArray = usuarioDAO.ListarRanking(0, 0);
            } catch (Exception e) {}

            try {
                if (JSONArray != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Usuario>>() {
                    }.getType();
                    rankingGeral = gson.fromJson(JSONArray.toString(), type);

                    for(Usuario u : rankingGeral) {
                        if (u.getEmail().equals(usuario.getEmail())) existe = true;
                    }

                    if (!existe) rankingGeral.add(usuario);

                    ListarRespostas(rankingGeral);
                }
            } catch (Exception E) {}

            try {
                JSONArray = usuarioDAO.ListarRanking(usuario.getCurso().getId(), 0);
            } catch (Exception e) {}

            try {
                if (JSONArray != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Usuario>>() {
                    }.getType();
                    rankingCurso = gson.fromJson(JSONArray.toString(), type);

                    for(Usuario u : rankingCurso) {
                        if (u.getEmail().equals(usuario.getEmail())) existe = true;
                    }

                    if (!existe) rankingCurso.add(usuario);

                    ListarRespostas(rankingCurso);
                }
            } catch (Exception E) {}

            try {
                JSONArray = usuarioDAO.ListarRanking(0, usuario.getUniversidade().getId());
            } catch (Exception e) {}

            try {
                if (JSONArray != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Usuario>>() {
                    }.getType();
                    rankingUniversidade = gson.fromJson(JSONArray.toString(), type);

                    for(Usuario u : rankingUniversidade) {
                        if (u.getEmail().equals(usuario.getEmail())) existe = true;
                    }

                    if (!existe) rankingUniversidade.add(usuario);

                    ListarRespostas(rankingUniversidade);
                }
            } catch (Exception E) {}
        }

        @Override
        protected void onPostExecute(String result) {
            RankingAdapter adapter = new RankingAdapter(context, rankingGeral, usuario, fragmentManager, getActivity());
            lvListaRanking.setAdapter(adapter);

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
