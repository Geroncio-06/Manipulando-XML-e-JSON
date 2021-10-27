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

public class MainActivity3 extends AppCompatActivity {

    private Button button3;
    private TextView textView3;
    private List<CepJson> cepList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button3 = findViewById(R.id.buttonView3);
        textView3 = findViewById(R.id.textView3);


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTarefa3 tarefa = new NewTarefa3();
                tarefa.execute("https://viacep.com.br/ws/RS/Porto%20Alegre/Domingos%20Jose/json");
            }
        });
    }

    /**
     * Eu não posso chamar a Classe conexão como outras Classes
     * Ela tem que ser chamado em uma Thread (tarefa), ou seja,
     * deve funcionar em segundo plano, (Async), tentando uma conexão
     */

    private class NewTarefa3 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String retorno = Conexao.getDados(strings[0]);
            return retorno;
        }

        @Override
        protected void onPostExecute(String s) {

            cepList = ConsumirJson.jsonDados(s);
            exibirDados();
        }

        private void exibirDados() {
            if (cepList != null) {
                for (CepJson cep : cepList) {
                    textView3.append(cep.getLogradouro() + "\n");
                }

                //textView3.setText(cepList.get(1).getLogradouro());
                //outra opção, se quiser um arquivo especifico da lista.
            }
        }
    }

}
