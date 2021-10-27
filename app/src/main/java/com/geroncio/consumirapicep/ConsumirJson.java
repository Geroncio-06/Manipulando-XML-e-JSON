package com.geroncio.consumirapicep;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsumirJson {

    public static List<CepJson> jsonDados(String conteudo){
        try {

            List<CepJson> cepList = new ArrayList<>();
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;

            /**
             * O arquivo json que vamos ler é uma arraylist de objetos,
             * onde cada objeto tem um conjunto de atributos key/values
             * */

            jsonArray = new JSONArray(conteudo);

            for (int i = 0; i<jsonArray.length();i++){
                /**
                 * Cada indice na arraylist, de i=0 até o ultimo, é um objeto json*/

                jsonObject = jsonArray.getJSONObject(i);

                CepJson cep = new CepJson();

                /**
                 * Atributos do objeto Json que vamos obter e passar para o objeto cep
                 * da nossa classe Cepjson*/

                cep.setSiafi(jsonObject.getString("siafi"));
                cep.setGia(jsonObject.getString("gia"));
                cep.setIbge(jsonObject.getString("ibge"));
                cep.setDdd(jsonObject.getString("ddd"));
                cep.setUf(jsonObject.getString("uf"));
                cep.setLocalidade(jsonObject.getString("localidade"));
                cep.setBairro(jsonObject.getString("bairro"));
                cep.setComplemento(jsonObject.getString("complemento"));
                cep.setLogradouro(jsonObject.getString("logradouro"));
                cep.setCep(jsonObject.getString("cep"));

                /**
                 * Cada objeto cep da nossa classe Cepjson, sera adicionado a uma List*/
                cepList.add(cep);
            }

            return cepList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
