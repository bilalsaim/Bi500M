package com.bil.Bi500M;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FrameTest {

    private Frame classUnderTest;

    @Before
    public void init(){
        classUnderTest = new Frame();
    }

    @Test
    public void sorulariOkuTest(){
        classUnderTest.setSorularDosyaAdi("SorularTest.txt");
        classUnderTest.sorulariDosyadanOku();
        char[] cevap = new char[100];
        cevap[0] = 'A';
        cevap[1] = 'B';

        Assert.assertArrayEquals(cevap, classUnderTest.cevap);
    }
}