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
public class FileExtUtilTest {
    
    public FileExtUtilTest() {
    }

    /**
     * Test of getExtension method, of class FileExtUtil.
     */
    @Test
    public void testGetExtension() {
        System.out.println("getExtension");
        File f = new File("f:/1.java");
        String expResult = "java";
        String result = FileExtUtil.getExtension(f);
        assertEquals(expResult, result);
    }
    
}
