package com.enem.prep.mobile;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import dao.CidadeDAO;
import dao.CursoDAO;
import dao.EstadoDAO;
import dao.UniversidadeDAO;
import dao.UsuarioDAO;
import models.Cidade;
import models.Curso;
import models.Estado;
import models.Universidade;
import models.Usuario;


public class AlterarPerfilFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private int cont = 0;

    private Button btnUserConquistas = null;
    private Button btnSalvar = null;

    private EditText edtNome = null;

    private Spinner spEstado = null;
    private Spinner spCidade = null;
    private Spinner spUniversidade = null;
    private Spinner spCurso = null;

    private Usuario usuario = null;

    private List<Estado> estados = null;
    private List<Universidade> universidades = null;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final EstadoDAO estadoDAO = new EstadoDAO();
    private final CidadeDAO cidadeDAO = new CidadeDAO();
    private final UniversidadeDAO universidadeDAO = new UniversidadeDAO();
    private final CursoDAO cursoDAO = new CursoDAO();

    private OnFragmentInteractionListener mListener;

    public AlterarPerfilFragment() {

    }

    public static AlterarPerfilFragment newInstance(String param1, String param2) {
        AlterarPerfilFragment fragment = new AlterarPerfilFragment();
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
        View view =  inflater.inflate(R.layout.fragment_alterar_perfil, container, false);

        String emailUsuario = getArguments().getString("email", "defaultStringIfNothingFound");

        btnUserConquistas = (Button) view.findViewById(R.id.btnUserConquistas);
        btnSalvar = (Button) view.findViewById(R.id.btnSalvar);

        edtNome = (EditText) view.findViewById(R.id.edtNome);

        spEstado = (Spinner) view.findViewById(R.id.spEstado);
        spCidade = (Spinner) view.findViewById(R.id.spCidade);
        spUniversidade = (Spinner) view.findViewById(R.id.spUniversidade);
        spCurso = (Spinner) view.findViewById(R.id.spCurso);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usu = new Usuario();

                MontarUsuario(usu);

                InserirUsuario(usu);
            }
        });

        spEstado.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (cont == 0){
                    try {
                        for (int c = 0; c < spEstado.getCount(); c++){
                            if (spEstado.getItemAtPosition(c).toString().equals(usuario.getCidade().getEstado().toString())){
                                spEstado.setSelection(c);
                            }
                        }
                    }
                    catch(Exception ex){

                    }
                }

                Estado estado = (Estado)spEstado.getSelectedItem();

                AtualizarCidades(estado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spUniversidade.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Universidade universidade = (Universidade)spUniversidade.getSelectedItem();

                if (cont == 0){
                    try {
                        for (int c = 0; c < spUniversidade.getCount(); c++){
                            if (spUniversidade.getItemAtPosition(c).toString().equals(usuario.getUniversidade().toString())){
                                spUniversidade.setSelection(c);
                            }
                        }
                    }
                    catch(Exception ex){

                    }
                }

                AtualizarCursos(universidade);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCidade.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (cont == 0){
                    try {
                        for (int c = 0; c < spCidade.getCount(); c++){
                            if (spCidade.getItemAtPosition(c).toString().equals(usuario.getCidade().toString())){
                                spCidade.setSelection(c);
                            }
                        }
                    }
                    catch(Exception ex){

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spCurso.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (cont == 0){
                    try {
                        for (int c = 0; c < spCurso.getCount(); c++){
                            if (spCurso.getItemAtPosition(c).toString().equals(usuario.getCurso().toString())){
                                spCurso.setSelection(c);
                            }
                        }
                    }
                    catch(Exception ex){

                    }

                    cont++;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        new SelecionarTask(emailUsuario, this.getActivity().getApplicationContext()).execute();

        return view;
    }

    private void AtualizarCidades(Estado estado){
        ArrayAdapter adapterCidade = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, estado.getCidades());

        spCidade.setAdapter(adapterCidade);
    }

    private void AtualizarCursos(Universidade universidade){
        ArrayAdapter adapterCurso = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, universidade.getCursos());

        spCurso.setAdapter(adapterCurso);
    }

    private void MontarUsuario(Usuario usu){
        Cidade cidade = (Cidade)spCidade.getSelectedItem();
        Universidade universidade = (Universidade)spUniversidade.getSelectedItem();
        Curso curso = (Curso)spCurso.getSelectedItem();

        usu.setNome(edtNome.getText().toString());
        usu.setCidade(cidade);
        usu.setUniversidade(universidade);
        usu.setCurso(curso);

        usu.setId(usuario.getId());
    }

    private void InserirUsuario(Usuario usu){
        if (usu.getNome().equals("")){
            showToast("Preencha o campo Nome para prosseguir!");
            return;
        }

        new AtualizarTask(usu).execute();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void showToast(String mensagem) {
        Toast.makeText(getActivity(), mensagem, Toast.LENGTH_SHORT).show();
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

                if (obj != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Usuario>() {
                    }.getType();
                    usuario = gson.fromJson(obj.toString(), type);
                }
            }
            catch (Exception e){
                return "Não foi realizar esta ação. Tente novamente mais tarde!";
            }

            ListarEstados();
            ListarCidades();
            ListarUniversidades();
            ListarCursos();

            return "";
        }

        private void PreencherDadosUsuario() {
            btnUserConquistas.setText(usuario.getNome().substring(0, 1));
            edtNome.setText(usuario.getNome());
        }

        @Override
        protected void onPostExecute(String result) {
            PreencherDadosUsuario();

            ArrayAdapter adapterEstado = new ArrayAdapter(getActivity(),
                    android.R.layout.simple_spinner_item, estados);
            ArrayAdapter adapterUniversidade = new ArrayAdapter(getActivity(),
                    android.R.layout.simple_spinner_item, universidades);

            spEstado.setAdapter(adapterEstado);
            spUniversidade.setAdapter(adapterUniversidade);

            dialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
            dialog.setMessage("Carregando...");
            dialog.setIndeterminate(true);
            dialog.show();
        }

        private void ListarEstados(){
            try {
                JSONArray = estadoDAO.Listar();
            } catch (Exception e) {

            }

            try {
                if (JSONArray != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Estado>>() {
                    }.getType();
                    estados = gson.fromJson(JSONArray.toString(), type);
                }
            } catch (Exception E) {

            }
        }

        private void ListarCidades(){
            for (Estado estado : estados){
                try {
                    JSONArray = cidadeDAO.Listar(estado.getId());

                    if (JSONArray != null) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Cidade>>() {
                        }.getType();
                        List<Cidade> cidades = gson.fromJson(JSONArray.toString(), type);
                        estado.setCidades(cidades);
                    }
                } catch (Exception e) {

                }
            }
        }

        private void ListarUniversidades(){
            try {
                JSONArray = universidadeDAO.Listar();
            } catch (Exception e) {

            }

            try {
                if (JSONArray != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Universidade>>() {
                    }.getType();
                    universidades = gson.fromJson(JSONArray.toString(), type);
                }
            } catch (Exception E) {

            }
        }

        private void ListarCursos(){
            for (Universidade universidade : universidades){
                try {
                    JSONArray = cursoDAO.Listar(universidade.getId());

                    if (JSONArray != null) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Curso>>() {
                        }.getType();
                        List<Curso> cursos = gson.fromJson(JSONArray.toString(), type);
                        universidade.setCursos(cursos);
                    }
                } catch (Exception e) {

                }
            }
        }
    }



    private class AtualizarTask extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;
        private Usuario user;

        public AtualizarTask(Usuario usuario) {
            this.user = usuario;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                JSONObject obj = usuarioDAO.Salvar(user);
            }
            catch (Exception e){
                return "Não foi possível atualizar o cadastro. Tente novamente mais tarde!";
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();

            if (!result.equals("")) showToast(result);
            else{
                Fragment fragment = null;
                Class fragmentClass = null;
                Bundle bundle = new Bundle();
                fragmentClass = PerfilFragment.class;

                bundle.putString("email",usuario.getEmail());
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
            }
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
