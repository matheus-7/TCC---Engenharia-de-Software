package com.enem.prep.mobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ListaAreasFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListaAreasFragment() {
        // Required empty public constructor
    }

    public static ListaAreasFragment newInstance(String param1, String param2) {
        ListaAreasFragment fragment = new ListaAreasFragment();
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
        final View view = inflater.inflate(R.layout.fragment_lista_areas, container, false);

        Button btnLinguagens = (Button) view.findViewById(R.id.btnLinguagens);
        Button btnHumanas = (Button) view.findViewById(R.id.btnHumanas);
        Button btnMatematica = (Button) view.findViewById(R.id.btnMatematica);
        Button btnNatureza = (Button) view.findViewById(R.id.btnNatureza);

        btnLinguagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEntrar = new Intent(getActivity(), RespostasActivity.class);
                intentEntrar.putExtra("Area", "Linguagens");

                startActivity(intentEntrar);
            }
        });

        btnHumanas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEntrar = new Intent(getActivity(), RespostasActivity.class);
                intentEntrar.putExtra("Area", "Humanas");

                startActivity(intentEntrar);
            }
        });

        btnMatematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEntrar = new Intent(getActivity(), RespostasActivity.class);
                intentEntrar.putExtra("Area", "Matemática");

                startActivity(intentEntrar);
            }
        });

        btnNatureza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEntrar = new Intent(getActivity(), RespostasActivity.class);
                intentEntrar.putExtra("Area", "Natureza");

                startActivity(intentEntrar);
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
}
