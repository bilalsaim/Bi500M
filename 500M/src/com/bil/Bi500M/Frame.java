package com.bil.Bi500M;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Frame extends Applet implements ActionListener
{

    //Global değişkenler
    private JRadioButton a,b,c,d;
    JButton cev,pas,jo1,jo2,jo3,jo4;
    private JTextArea tf1;
    JFrame fr;
    Panel pn1,pn2,pn3,pn4,pn5;
    Panel pnsec;
    ButtonGroup grup;
    int soru=0,boyut;
    int para;
    Random rst;
    boolean x2=false;
    private ImageIcon konumRes,konumCarpi;
    private JLabel res,carpi1,carpi2,carpi3,carpi4;
    int resx=372;


    public void init()
    {
        cikanSorular();//Oyun başında tüm soruları daha ekrana gelmemiş kabul edip 0 atadık
        boyut=rstSoru();
        konumRes = new ImageIcon("res.png");
        konumCarpi = new ImageIcon("carpi.png");
        res= new JLabel() {
            public void paintComponent(Graphics g) {
                g.drawImage(konumRes.getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        carpi1 = new JLabel() {
            public void paintComponent(Graphics g) {
                g.drawImage(konumCarpi.getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        carpi2 = new JLabel() {
            public void paintComponent(Graphics g) {
                g.drawImage(konumCarpi.getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        carpi3 = new JLabel() {
            public void paintComponent(Graphics g) {
                g.drawImage(konumCarpi.getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };
        carpi4 = new JLabel() {
            public void paintComponent(Graphics g) {
                g.drawImage(konumCarpi.getImage(), 0, 0, null);
                super.paintComponent(g);
            }
        };


        res.setOpaque(false);
        carpi1.setOpaque(false);
        carpi2.setOpaque(false);
        carpi3.setOpaque(false);
        carpi4.setOpaque(false);

        grup= new ButtonGroup();
        fr=new JFrame("Kim Milyoner Olmak İster?");
        //fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);


        try {
            fr.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("arka.png")))));
        } catch (IOException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }


        cev=new JButton("Cevapla");
        pas=new JButton("Pas");
        jo1=new JButton("x2");
        jo2=new JButton("50:50");
        jo3=new JButton("Seyirci");
        jo4=new JButton("Telefon");
        jo1.setEnabled(false);
        pas.setEnabled(false);
        a=new JRadioButton(Main.a[boyut]);
        b=new JRadioButton(Main.b[boyut]);
        c=new JRadioButton(Main.c[boyut]);
        d=new JRadioButton(Main.d[boyut]);
        grup.add(a);
        grup.add(b);
        grup.add(c);
        grup.add(d);

        tf1=new JTextArea((soru+1)+". "+ Main.sorular[boyut]);

        a.setBounds(98, 318, 230, 20);
        b.setBounds(450, 318, 230, 20);
        c.setBounds(98, 366, 230, 20);
        d.setBounds(450, 366, 230, 20);
        tf1.setBounds(110,235,550,50);
        cev.setBounds(626,415,94,32);
        pas.setBounds(49,414,91,31);
        jo1.setBounds(164,414,90,31);
        jo2.setBounds(281,414,90,32);
        jo3.setBounds(396,414,90,32);
        jo4.setBounds(512,415,92,32);
        res.setBounds(792,resx,220,30);
        carpi1.setBounds(777,25,53,53);
        carpi2.setBounds(839,25,53,53);
        carpi3.setBounds(900,25,53,53);
        carpi4.setBounds(960,25,53,53);


        carpi2.setVisible(false);
        carpi3.setVisible(false);
        carpi4.setVisible(false);

        tf1.setEditable(false);
        tf1.setOpaque(false);
        a.setOpaque(false);
        b.setOpaque(false);
        c.setOpaque(false);
        d.setOpaque(false);


        Font font = new Font("Verdana", Font.BOLD, 11);
        tf1.setFont(font);
        tf1.setForeground(Color.WHITE);
        a.setFont(font);
        a.setForeground(Color.WHITE);
        b.setFont(font);
        b.setForeground(Color.WHITE);
        c.setFont(font);
        c.setForeground(Color.WHITE);
        d.setFont(font);
        d.setForeground(Color.WHITE);

        fr.add(a);
        fr.add(b);
        fr.add(c);
        fr.add(d);
        fr.add(pas);
        fr.add(jo1);
        fr.add(jo2);
        fr.add(jo3);
        fr.add(jo4);
        fr.add(tf1);
        fr.add(cev);
        fr.add(res);
        fr.add(carpi1);
        fr.add(carpi2);
        fr.add(carpi3);
        fr.add(carpi4);
        fr.setSize(1038,518);
        fr.pack();
        fr.setVisible(true);

        cev.addActionListener(this);
        pas.addActionListener(this);
        jo1.addActionListener(this);
        jo2.addActionListener(this);
        jo3.addActionListener(this);
        jo4.addActionListener(this);

        fr.addWindowListener ( new WindowAdapter()
        {
            public void windowClosing(WindowEvent event )
            {
                fr.setVisible(false);
                fr.dispose();
                System.exit(0);
            }
        });

    }

    private int rstSoru() {
        rst = new Random();
        int i=0;
        while(true)
        {
            i++;
            if(i==1000)
            {
                JOptionPane.showMessageDialog(fr,"Sorular.txt' de yeterince soru yok!!!", "Hata",
                        JOptionPane.ERROR_MESSAGE);
                fr.setVisible(false);
                fr.dispose();
                System.exit(0);
                break;
            }
            boyut=rst.nextInt(Main.sSayi);
            if(soru<2)
            {
                if(Main.duzey[boyut]==1&& Main.cikti[boyut]==0)
                {
                    Main.cikti[boyut]=1;
                    break;
                }
            }
            else if(soru>1&&soru<7&& Main.cikti[boyut]==0)
            {
                if(Main.duzey[boyut]==2)
                {
                    Main.cikti[boyut]=1;
                    break;
                }
            }
            else if(soru>6&&soru<11&& Main.cikti[boyut]==0)
            {
                if(Main.duzey[boyut]==3)
                {
                    Main.cikti[boyut]=1;
                    break;
                }
            }
            else if(soru==11&& Main.cikti[boyut]==0)
            {
                if(Main.duzey[boyut]==4)
                {
                    Main.cikti[boyut]=1;
                    break;
                }
            }

        }

        return boyut;
    }

    public void actionPerformed(ActionEvent e)
    {
        Object[] secenek = { "EVET", "HAYIR" };

        if (e.getSource() == cev)
        {
            boolean kontrol=false;

            if(Main.cevap[boyut]=='A')
            {
                if(a.isSelected())
                {
                    soru++;
                    kontrol=true;
                }
            }
            else if(Main.cevap[boyut]=='B')
            {
                if(b.isSelected())
                {
                    soru++;
                    kontrol=true;
                }
            }
            else if(Main.cevap[boyut]=='C')
            {
                if(c.isSelected())
                {
                    soru++;
                    kontrol=true;
                }
            }
            else if(Main.cevap[boyut]=='D')
            {
                if(d.isSelected())
                {
                    soru++;
                    kontrol=true;
                }
            }

            if(soru==12)
            {
                int sonuc=JOptionPane.showOptionDialog(fr,"Tebrikler Milyoner Oldunuz!!!"+" Skorunuzu dosyaya kaydetmek ister misiniz?", "Tam 1.000.000 TL Kazandınız",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        secenek ,
                        secenek [1]);
                oyunBitimi(sonuc);
            }

            if(!(a.isSelected()||b.isSelected()||c.isSelected()||d.isSelected()))
            {
                JOptionPane.showMessageDialog(fr,"Seçenekleri boş bırakamazsınız!!!", "Boş Seçenek",
                        JOptionPane.ERROR_MESSAGE);

            }
            else
            {
                pas.setEnabled(true);

                if(kontrol)
                {
                    boyut=rstSoru();
                    resx-=24;
                    res.setBounds(792,resx,220,30);
                    a.setText(Main.a[boyut]);
                    b.setText(Main.b[boyut]);
                    c.setText(Main.c[boyut]);
                    d.setText(Main.d[boyut]);
                    tf1.setText((soru+1)+". "+ Main.sorular[boyut]);
                    a.setVisible(true);
                    b.setVisible(true);
                    c.setVisible(true);
                    d.setVisible(true);
                    if(soru==7)
                    {
                        carpi1.setVisible(false);
                        jo1.setEnabled(true);
                    }
                }
                else
                {
                    if(x2)
                    {
                        if(a.isSelected())
                            a.setVisible(false);
                        if(b.isSelected())
                            b.setVisible(false);
                        if(c.isSelected())
                            c.setVisible(false);
                        if(d.isSelected())
                            d.setVisible(false);

                        x2=false;
                    }
                    else
                    {
                        int durum;
                        String yazi;

                        if(soru<2)
                        {

                            yazi="Malesef Hiç Para Kazanamadınız.";
                            para=0;
                        }
                        else if(soru>1||soru<7)
                        {
                            yazi="Tebrikler 1.000TL Kazandınız";
                            para=1000;
                        }
                        else if(soru>6||soru<12)
                        {
                            yazi="Tebrikler 15.000TL Kazandınız";
                            para=15000;
                        }
                        else
                        {
                            yazi="HATA";
                        }


                        if(soru>1)
                        {

                            durum=JOptionPane.showOptionDialog(fr,yazi+" Skorunuzu dosyaya kaydetmek ister misiniz?", "Yanlış Cevap",
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    secenek ,
                                    secenek [1]);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(fr, yazi, "Yanlış Cevap", JOptionPane.QUESTION_MESSAGE);
                            durum=1;
                        }


                        oyunBitimi(durum);

                    }//x2 else komut bitimi
                }//kontrol else komut bitimi
            }//Boş seçenek else komut bitimi
            grup.clearSelection();
        } // Cevap butonu komut bitimi

        else if (e.getSource() == pas)
        {
            int durum;


            if(soru==1)
            {
                para=500;
            }
            else if(soru==2)
            {
                para=1000;
            }
            else if(soru==3)
            {
                para=2000;
            }
            else if(soru==4)
            {
                para=3000;
            }
            else if(soru==5)
            {
                para=5000;
            }
            else if(soru==6)
            {
                para=7500;
            }
            else if(soru==7)
            {
                para=15000;
            }
            else if(soru==8)
            {
                para=30000;
            }
            else if(soru==9)
            {
                para=60000;
            }
            else if(soru==10)
            {
                para=125000;
            }
            else if(soru==11)
            {
                para=250000;
            }

            durum=JOptionPane.showOptionDialog(fr,"Tebrikler "+para+ " TL Kazandınız."+" Skorunuzu dosyaya kaydetmek ister misiniz?", "Oyun Bitti",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    secenek ,
                    secenek [1]);

            oyunBitimi(durum);

        }//Pas buton komutları sonu


        else if (e.getSource() == jo1)
        {
            x2=true;
            jo1.setEnabled(false);
            grup.clearSelection();
            carpi1.setVisible(true);
        }//jo1(x2) joker butonu komutları sonu

        else if (e.getSource() == jo2)
        {
            int sayi;
            rst = new Random();
            int yok=0;
            int cik=0;
            while(true)
            {
                sayi = 1+rst.nextInt(4);
                if(sayi==1&&yok!=1)
                {
                    if(Main.cevap[boyut]!='A')
                    {
                        a.setVisible(false);
                        cik++;
                        yok=1;
                    }
                }
                else if(sayi==2&&yok!=2)
                {
                    if(Main.cevap[boyut]!='B')
                    {
                        b.setVisible(false);
                        cik++;
                        yok=2;
                    }
                }
                else if(sayi==3&&yok!=3)
                {
                    if(Main.cevap[boyut]!='C')
                    {
                        c.setVisible(false);
                        cik++;
                        yok=3;
                    }
                }
                else if(sayi==4&&yok!=4)
                {
                    if(Main.cevap[boyut]!='D')
                    {
                        d.setVisible(false);
                        cik++;
                        yok=4;
                    }
                }
                grup.clearSelection();
                if(cik==2)
                    break;

            }
            jo2.setEnabled(false);
            carpi2.setVisible(true);
        }//jo2(50:50) joker butonu komutları sonu

        else if (e.getSource() == jo3)
        {

            rst=new Random();
            int yuzde= 50+rst.nextInt(40);

            JOptionPane.showMessageDialog(fr, "Seyircilerden %"+yuzde+" oranında '"+
                    Main.cevap[boyut]+ "' seçeneği geldi.", "Seyirci Joker Hakkı", JOptionPane.QUESTION_MESSAGE);
            jo3.setEnabled(false);
            carpi3.setVisible(true);
        }//jo3(Seyirci) joker butonu komutları sonu

        else if (e.getSource() == jo4)
        {
            rst=new Random();
            int sec2= 1+rst.nextInt(3);
            int sCev= 1+rst.nextInt(4);
            String mesaj="";
            String[] secenekler = { "Prf. Dr. Ahmet", "Manav Hayri", "Komşu Pelin Hanım" };
            String sec = (String) JOptionPane.showInputDialog(null, "Aranacak kişiyi seçiniz: ",
                    "Telefon Joker Hakkı", JOptionPane.QUESTION_MESSAGE, null,
                    secenekler,
                    secenekler[1]);

            if(sec2==1)
            {
                mesaj=sec+": \"Soru hakkında hiç bilgim yok.\"";
            }
            else if(sec2==2)
            {
                if(sCev==1)
                    mesaj=sec+": \"Sorudan emin değilim ama A şıkkı olabilir.\"";
                if(sCev==2)
                    mesaj=sec+": \"Sorudan emin değilim ama B şıkkı olabilir.\"";
                if(sCev==3)
                    mesaj=sec+": \"Sorudan emin değilim ama C şıkkı olabilir.\"";
                if(sCev==4)
                    mesaj=sec+": \"Sorudan emin değilim ama D şıkkı olabilir.\"";
            }
            else if(sec2==3)
            {
                mesaj=sec+": \"Kesinlikle '"+ Main.cevap[boyut]+"' şıkkı.\"";
            }

            JOptionPane.showMessageDialog(fr, mesaj, "Telefon Joker Hakkı", JOptionPane.QUESTION_MESSAGE);
            jo4.setEnabled(false);
            carpi4.setVisible(true);
        }//jo4(Telefon) joker butonu komutları sonu
    }

    private void dosyaYaz(String kullanici) {
        Writer fw = null;

        try{

            fw = new FileWriter("Skorlar.txt",true);

            fw.write(kullanici+"\t"+para+"\t"+System.getProperty( "line.separator" ));


        }catch(IOException e){

            System.err.println("Dosya olusturulamadi");

        }finally{

            if(fw !=null)

                try{

                    fw.close();

                }catch(IOException e){

                }

        }

    }

    private void oyunBitimi(int durum) {
        Object[] secenek = { "EVET", "HAYIR" };
        int durum2;

        if(durum==0)
        {
            String kullanici = JOptionPane.showInputDialog(fr,
                    "İsminizi giriniz: ",
                    "Oyun Bitti", JOptionPane.INFORMATION_MESSAGE);
            dosyaYaz(kullanici);
        }


        durum2=JOptionPane.showOptionDialog(fr,"Tekrar Oynamak İstiyor musunuz?", "Kim Milyoner Olmak İster?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                secenek ,
                secenek[1]);

        if(durum2==1)
        {
            fr.setVisible(false);
            fr.dispose();
            System.exit(0);
        }
        else if(durum2==0)
        {
            cikanSorular();

            boyut=rstSoru();
            pas.setEnabled(false);
            soru=0;
            a.setText(Main.a[boyut]);
            b.setText(Main.b[boyut]);
            c.setText(Main.c[boyut]);
            d.setText(Main.d[boyut]);
            tf1.setText((soru+1)+". "+ Main.sorular[boyut]);
            a.setVisible(true);
            b.setVisible(true);
            c.setVisible(true);
            d.setVisible(true);
            jo1.setEnabled(false);
            jo2.setEnabled(true);
            jo3.setEnabled(true);
            jo4.setEnabled(true);
            grup.clearSelection();
            resx=372;
            res.setBounds(792,resx,220,30);
            carpi1.setVisible(false);
            carpi2.setVisible(false);
            carpi3.setVisible(false);
            carpi4.setVisible(false);


        }
        else
        {
            fr.setVisible(false);
            fr.dispose();
            System.exit(0);
        }
    }

    private void cikanSorular()
    {
        for(int i = 0; i< Main.sSayi; i++)
        {
            Main.cikti[i]=0;
        }
    }
}