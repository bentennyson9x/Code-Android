package com.example.saint.ungdungdocbao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ReadRSS extends AsyncTask <String,Void,String> {
    Context context;
    ArrayList<String> listTieuDe,listLink ;
    ArrayAdapter adapterListBaiBao;
    public ReadRSS(Context context) {
        this.context = context;
    }


    public ReadRSS(Context context, ArrayList<String> listTieuDe, ArrayList<String> listLink, ArrayAdapter adapterListBaiBao) {
        this.context = context;
        this.listTieuDe = listTieuDe;
        this.listLink = listLink;
        this.adapterListBaiBao = adapterListBaiBao;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line=bufferedReader.readLine())!= null){
                content.append(line);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        XMLDOMParser xmldomParser = new XMLDOMParser();
        Document document =   xmldomParser.getDocument(s);
        String tag = "item";
        NodeList nodeList = document.getElementsByTagName(tag);
        String Title;
        String Link;
        for(int i=0;i<nodeList.getLength();i++){
            Element element = (Element) nodeList.item(i);
            Title = xmldomParser.getValue(element,"title");
            Link = xmldomParser.getValue(element,"link");
            listTieuDe.add(Title);
            listLink.add(Link);
        }
        adapterListBaiBao.notifyDataSetChanged();
    }
}
