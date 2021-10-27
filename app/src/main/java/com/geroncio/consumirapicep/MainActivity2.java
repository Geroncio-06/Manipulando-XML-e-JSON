package com.geroncio.consumirapicep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Button button2, novaAtividade;
    private TextView textView2;
    private List<Cep> cepList = new  ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button2 = findViewById(R.id.buttonView2);
        novaAtividade = findViewById(R.id.buttonNewActivity2);
        textView2 = findViewById(R.id.textView2);

        novaAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity3.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTarefa tarefa = new NewTarefa();
                tarefa.execute("https://viacep.com.br/ws/PA/Belem/Mundurucus/xml");
            }
        });
    }

    /**
     * Eu não posso chamar a Classe conexão como outras Classes
     * Ela tem que ser chamado em uma Thread (tarefa), ou seja,
     * deve funcionar em segundo plano tentando uma conexão*/

    private class NewTarefa extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String retorno = Conexao.getDados(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {

            cepList = ConsumirXml.xmlDados(s);
            exibirDados();
        }

        private void exibirDados(){
            if (cepList != null){
                for (Cep cep : cepList){
                    textView2.append(cep.getLogradouro()+"\n");
                }
            }
        }
    }
}