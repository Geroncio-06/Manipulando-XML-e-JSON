package com.geroncio.consumirapicep;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ConsumirXml {

    public static List<Cep> xmlDados(String conteudo){
        try {

            boolean dadosNaTag = false;
            String tagAtual = "";
            Cep cep = null;

            List<Cep> cepList = new  ArrayList<>();

            /**
             * Essa classe serve para criar arquivos XML*/
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser objectXmlPullParser = factory.newPullParser();
            objectXmlPullParser.setInput(new StringReader(conteudo));

                    /**StringReader permite que você leia uma cadeia de caracteres
                     * de forma sincrona ou assincrona*/


            int eventType = objectXmlPullParser.getEventType();

            /**Enquanto o evento eventType, for diferente do final do documento
             * haverá uma repetição*/
            while (eventType != XmlPullParser.END_DOCUMENT){

                /**
                 * O switch vai funcionar como um interruptor, pois dependendo da entrada que você der
                 * a ele, ele vai acionar somente certos comondos dentre os que você disponibilizou.
                 * É como se você criasse um menu, ou cardápio, e com o switch você escolhesse o que vai querer*/

                switch (eventType){

                    /**
                     * Se for uma TAG de inicialização*/
                    case  XmlPullParser.START_TAG:
                        tagAtual = objectXmlPullParser.getName();

                        if (tagAtual.endsWith("endereco")){
                            dadosNaTag = true;

                            cep = new Cep();
                            cepList.add(cep);
                        }

                    break;

                    /**
                     * Se for o fechamento da TAG */
                    case XmlPullParser.END_TAG:

                        if (objectXmlPullParser.getName().equals("endereco")){
                            dadosNaTag = false;
                        }

                        tagAtual = "";

                    break;

                    case XmlPullParser.TEXT:
                        if (dadosNaTag && cep != null){

                            switch (tagAtual){

                                case "cep":
                                    cep.setCep(objectXmlPullParser.getText());
                                    break;

                                case "logradouro":
                                    cep.setLogradouro(objectXmlPullParser.getText());
                                    break;

                                case "complemento":
                                    cep.setComplemento(objectXmlPullParser.getText());
                                    break;

                                case "bairro":
                                    cep.setBairro(objectXmlPullParser.getText());
                                    break;

                                case "localidade":
                                    cep.setLocalidade(objectXmlPullParser.getText());
                                    break;

                                case "uf":
                                    cep.setUf(objectXmlPullParser.getText());
                                    break;

                                case "unidade":
                                    cep.setUnidade(objectXmlPullParser.getText());
                                    break;

                                case "ibge":
                                    cep.setIbge(objectXmlPullParser.getText());
                                    break;

                                case "gia":
                                    cep.setGia(objectXmlPullParser.getText());
                                    break;
                            }
                        }
                    break;
                }

                /**
                 * Depois de obter os dados da primeira TAG, ele vai para a proxima TAG*/
                eventType = objectXmlPullParser.next();
            }

            return cepList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
