package com.geroncio.consumirapicep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ESSA É A CLASSE QUE VAI SE COMUNICAR COM A API CEP
 * */

public class Conexao {

    /**
     * Quando eu chamar o metodo getDados na minha classe principal
     * eu vou informar como parametro (uri) o endereço htttp da minha API CEP, para fazer a conexão
     * e obter os dados da API*/

    public static String getDados(String uri){

        /**(1)*/
        BufferedReader bufferedReader = null;

        try {

            URL url = new URL(uri);

            /**(2)*/
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            /**(3)*/
            StringBuilder stringBuilder = new StringBuilder();

            bufferedReader = new BufferedReader(/**(4)*/new InputStreamReader(httpURLConnection.getInputStream()));

            /**
             * Em bufferedReader seram armazenados os dados que estão na API CEP*/

            String linha;

            /**
             * Criei um laço de repetição, a minha variavel string linha vai receber uma nova linha
             * do meu arquivo bufferedReader até acabar todas as linhas desse arquivo, retornado o valor null*/

            while ((linha = bufferedReader.readLine()) != null){
                stringBuilder.append(linha+"\n");

                /**
                 * StringBuilder serve justamente para concatenar dados de uma forma rápida.
                 * O método dessa classe que faz isso é o append, ele acrescenta dados novos
                 * aos dados já existentes da stringbuilder*/
            }

            /**
             * StringBuilder não é propriamente uma String, a classe que criamos prcisa retornar uma string
             * para isso basta converter o resultado final stringBuilder para uma String: .toString()*/

            return stringBuilder.toString();

        }catch (Exception e){

            e.printStackTrace();
            return null;

        }finally {

            /**
             * Finalmente se foram baixados os arquivos da API CEP, eu preciso fechar o meu arquivo de texto
             * bufferedReader, fechar no sentido de salvar ou finalizar o arquivo.*/

            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * (1) Arquivos tipo BufferedReader servem para ler arquivos em formato texto
 *
 * (2) Depois de instanciada a classe URL com o endereço, é a classe HttpURLConnection que será responsável
 *     por efetuar a conexão a este lugar apontado.
 *
 * (3) Essa classe permite criar e manipular dados de string DINAMICAMENTE, ou em tempo de execução,
 *     ou seja, podem criar variáveis de String modificaveis
 *
 * (4) A função do InputStreamReader é servir como um adaptador (ADAPTER) entre duas classes - lê bytes
 *     de um lado, converte em caracteres do outro.*/