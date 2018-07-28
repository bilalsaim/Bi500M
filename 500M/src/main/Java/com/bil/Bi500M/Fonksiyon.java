package com.bil.Bi500M;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fonksiyon {

    final static Logger log = Logger.getLogger(Fonksiyon.class);
    ClassLoader classLoader = getClass().getClassLoader();

    public Map<String, ArrayList<String>> veriOku(String veriDosyaAdi) {

        String[] anahtar = new String[]{"Soru","A","B","C","D","Cevap","Duzey"};
        Map<String, ArrayList<String>> veri = new HashMap<String, ArrayList<String>>();

        try {
            BufferedReader buf = new BufferedReader(new FileReader(classLoader.getResource(veriDosyaAdi).getPath()));

            String satir = null;
            String[] veriDizisi;

            for(String her : anahtar){
                veri.put(her, new ArrayList<String>());
            }

            while(true){
                satir = buf.readLine();
                if(satir == null){
                    break;
                }else{
                    veriDizisi = satir.split("\t");
                    if(veriDizisi.length != anahtar.length){
                        log.debug("Hatalı satır: " + satir);
                        continue;
                    }

                    for(int i = 0; i < veriDizisi.length; i++){
                        veri.get(anahtar[i]).add(veriDizisi[i].trim());
                    }
                }
            }

            buf.close();
        } catch (Exception e) {
            log.error("Dosya okunamadı " + e.getMessage());
            JOptionPane.showMessageDialog(null,"Dosya Okunamadı", "Hata",
                    JOptionPane.ERROR_MESSAGE);
            return new HashMap<>();
        }

        return veri;
    }

    public void skorYaz(String skorDosyaAdi, String kullanici, String para) {
        Writer fw = null;
        try{
            fw = new FileWriter(getResourcePath() + skorDosyaAdi,true);
            fw.write(kullanici + "\t" + para+  "\t" + System.getProperty( "line.separator" ));
        }catch(IOException e){
            log.error("Skorlar yazılırken hata oluştu: " + e.getMessage());
        }finally{
            if(fw != null){
                try{
                    fw.close();
                } catch(IOException e){
                    log.error("Skorlar dosyasını kapatmaya çalışırken hata oluştu: " + e.getMessage());
                }
            }
        }
    }

    public String getResourcePath(){
        String url = classLoader.getResource("").getPath();
        if(url.startsWith("/"))
            url = url.substring(1);
        return url;
    }
}
