package com.bil.Bi500M;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FrameTest {

    private Frame classUnderTest = new Frame();
    @Test
    public void sorulariOkuTest(){
        classUnderTest.sorulariOku();
    }
}