package com.bil.Bi500M;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.log4j.BasicConfigurator;

public class Ana {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Pencere frm1 = new Pencere();
        frm1.init();
    }

}