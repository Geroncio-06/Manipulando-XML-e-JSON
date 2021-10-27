package com.geroncio.consumirapicep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button, novaAtividade;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.buttonView);
        novaAtividade = findViewById(R.id.buttonNewActivity);
        textView = findViewById(R.id.textView);

        novaAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarefa tarefa = new Tarefa();
                tarefa.execute("https://viacep.com.br/ws/68660959/json/");
            }
        });
    }

    /**
     * Eu não posso chamar a Classe conexão como outras Classes
     * Ela tem que ser chamado em uma Thread (tarefa), ou seja,
     * deve funcionar em segundo plano tentando uma conexão*/

    private class Tarefa extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            String retorno = Conexao.getDados(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }
    }
}