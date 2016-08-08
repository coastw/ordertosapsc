/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Coast
 */
public class XLSFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = FileExtUtil.getExtension(f);
        if (extension != null) {
            if (extension.equals(FileExtUtil.XLS)) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    @Override
    public String getDescription() {
        return "xls格式";
    }

}
