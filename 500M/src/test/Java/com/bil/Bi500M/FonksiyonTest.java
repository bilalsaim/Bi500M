package com.bil.Bi500M;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FonksiyonTest {
    private Fonksiyon classUnderTest;

    @Before
    public void init(){
        classUnderTest = new Fonksiyon();
    }

    @Test
    public void sorulariOkuTest(){
        Map<String, ArrayList<String>> veri = classUnderTest.veriOku("SorularTest.txt");

        assertTrue(veri.get("Soru").get(0).equals("Bu programı kim yazmıştır?"));
        assertTrue(veri.get("Soru").get(1).equals("Türkiyenin başkenti neresidir?"));
        assertArrayEquals(Arrays.asList("Bilal","Bursa").toArray(), veri.get("A").toArray());
        assertArrayEquals(Arrays.asList("Tesla","Ankara").toArray(), veri.get("B").toArray());
        assertArrayEquals(Arrays.asList("Sokrates","Antalya").toArray(), veri.get("C").toArray());
        assertArrayEquals(Arrays.asList("Bill Gates","İzmir").toArray(), veri.get("D").toArray());
        assertArrayEquals(Arrays.asList("A","B").toArray(), veri.get("Cevap").toArray());
        assertArrayEquals(Arrays.asList("1","2").toArray(), veri.get("Duzey").toArray());
    }

    @Test
    public void testSkorYaz() throws IOException {
        String dosyaAdi = "SkorlarTest.txt";
        String dosyaYolu = classUnderTest.getResourcePath() + dosyaAdi;

        classUnderTest.skorYaz(dosyaAdi, "Bilal", "1000");

        String dosyaIcerigi = new String(Files.readAllBytes(Paths.get(dosyaYolu)));

        assertTrue(dosyaIcerigi.contains("Bilal\t1000"));

        Files.delete(Paths.get(dosyaYolu));
    }
}
