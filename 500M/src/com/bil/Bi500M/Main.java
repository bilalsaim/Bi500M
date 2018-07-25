package com.bil.Bi500M;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.swing.JOptionPane;



/**
 *
 * @author asus
 */
public class Main {

    //dosyaOku() fonksiyonu için global değişkenler
    public static String[] sorular = new String[100];
    public static String[] a = new String[100];
    public static String[] b = new String[100];
    public static String[] c = new String[100];
    public static String[] d = new String[100];
    public static char[] cevap = new char[100];
    public static int[] duzey = new int[100];
    public static int[] cikti = new int[100];
    public static int sSayi;

    public static void main(String[] args) {
        dosyaOku();//Dosyadan verilerin okunması için gerekli işlemleri yapan fonksiyon.

        Frame frm1 = new Frame();
        frm1.init();
    }


    private static void dosyaOku() {
        FileInputStream dosya;
        int veri=-1;
        int i=0;

        try {
            dosya = new FileInputStream("Sorular.txt");
            InputStreamReader isr = new InputStreamReader(dosya,Charset.forName("ISO8859-9")); //Dosyadan çekilen verideki türkçe karakterleri algılaması için
            BufferedReader bfr = new BufferedReader(isr);


            /*String değişkenlerin başlangıç değerlerini boş yapar.
             * Eğer başlangıç değerleri atanmasaydı string
             * dizilere altta += işlemi kullanıldığında
             * başlangıç değerine null yazdırırdı.*/
            sorular[i]="";
            a[i]="";
            b[i]="";
            c[i]="";
            d[i]="";

            int x=0;
            do{

                veri = bfr.read();

                if (veri!=-1){

                    if((char)veri=='\n')//Dosyada bir alt satıra geçildiğinde yapılacak işlemler
                    {
                        x=0;
                        i++;
                        sSayi=i+1;//Satır sayısını tutar
                        sorular[i]="";
                        a[i]="";
                        b[i]="";
                        c[i]="";
                        d[i]="";
                        continue;
                    }
                    else if((char)veri=='\t') //Dosyada tab karakterini gördüğünde yapılacak işlemler
                    {
                        x++;
                        continue;
                    }

                    if(x==0)
                    {
                        sorular[i]+=(char)veri;
                    }
                    else if(x==1)
                    {
                        a[i]+=(char)veri;
                    }
                    else if(x==2)
                    {
                        b[i]+=(char)veri;
                    }
                    else if(x==3)
                    {
                        c[i]+=(char)veri;
                    }
                    else if(x==4)
                    {
                        d[i]+=(char)veri;
                    }
                    else if(x==5)
                    {
                        cevap[i]=(char)veri;
                    }
                    else if(x==6)
                    {
                        duzey[i]=Character.digit((char)veri, 10);
                    }

                }

            }while( veri!=-1);

            dosya.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Dosya Okunamadı", "Hata",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

}