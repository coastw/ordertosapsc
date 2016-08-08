/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.util;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Coast
 */
public class XLSFileFilterTest {
    
    public XLSFileFilterTest() {
    }

    /**
     * Test of accept method, of class XLSFileFilter.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        File f = new File("1.xls");
        XLSFileFilter instance = new XLSFileFilter();
        boolean expResult = true;
        boolean result = instance.accept(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class XLSFileFilter.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        XLSFileFilter instance = new XLSFileFilter();
        String expResult = "xls格式";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }
    
}
