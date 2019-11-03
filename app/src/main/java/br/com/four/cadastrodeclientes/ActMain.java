package br.com.four.cadastrodeclientes;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import br.com.four.cadastrodeclientes.database.DadosAppOpenHelper;
import br.com.four.cadastrodeclientes.dominio.entidades.Cliente;
import br.com.four.cadastrodeclientes.dominio.repositorio.ClienteRepositorio;

public class ActMain extends AppCompatActivity {

    private RecyclerView lstDados;
    private FloatingActionButton fab;
    private ConstraintLayout LayoutContentMain;
    private SQLiteDatabase conexao;
    private DadosAppOpenHelper dadosAppOpenHelper;
    private ClienteRepositorio clienteRepositorio;
    private ClienteAdapter clienteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        lstDados = (RecyclerView)findViewById(R.id.lstDados);
        LayoutContentMain = (ConstraintLayout)findViewById(R.id.LayoutContentMain);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent (ActMain.this, ActCadCliente.class);
                startActivity(it);

            }
        });

        criarConexao();

        lstDados.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);

        clienteRepositorio = new ClienteRepositorio(conexao);

        List<Cliente> dados = clienteRepositorio.buscarTodos();

        clienteAdapter = new ClienteAdapter(dados);

        lstDados.setAdapter(clienteAdapter);

    }

    private void criarConexao(){

        try{

            dadosAppOpenHelper = new DadosAppOpenHelper(this);

            conexao = dadosAppOpenHelper.getWritableDatabase();

            Snackbar.make(LayoutContentMain, R.string.message_conexao_criada_com_sucesso, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.action_ok, null).show();

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle (getString(R.string.title_erro));
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();

        }

    }

    public void cadastrar (View view){

        Intent it = new Intent(ActMain.this, ActCadCliente.class);
        startActivity(it);

    }

}
