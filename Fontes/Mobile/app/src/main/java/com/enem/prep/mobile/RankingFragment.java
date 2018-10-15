package com.enem.prep.mobile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.RankingAdapter;
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

        btnGeral = (Button) view.findViewById(R.id.btnGeral);
        btnUniversidade = (Button) view.findViewById(R.id.btnUniversidade);
        btnCurso = (Button) view.findViewById(R.id.btnCurso);

        lvListaRanking = (ListView) view.findViewById(R.id.lvListaRanking);

        btnGeral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelecionarTipo(btnGeral);
            }
        });

        btnUniversidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelecionarTipo(btnUniversidade);
            }
        });

        btnCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelecionarTipo(btnCurso);
            }
        });

        Usuario usu1 = new Usuario();
        Usuario usu2 = new Usuario();
        Usuario usu3 = new Usuario();

        usu1.setNome("Matheus Socoloski Velho");
        usu2.setNome("Maiana Zatti");
        usu3.setNome("Larissa Rodrigues dos Santos");

        List<Usuario> usuarios = new ArrayList<Usuario>();

        usuarios.add(usu1);
        usuarios.add(usu2);
        usuarios.add(usu3);
        usuarios.add(usu3);
        usuarios.add(usu3);
        usuarios.add(usu3);
        usuarios.add(usu3);
        usuarios.add(usu3);

        RankingAdapter adapter = new RankingAdapter(this.getActivity(), usuarios);
        lvListaRanking.setAdapter(adapter);

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
