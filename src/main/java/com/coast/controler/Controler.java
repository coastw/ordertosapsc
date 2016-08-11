/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coast.controler;

import com.coast.model.Product;
import com.coast.model.ResultMSG;
import com.coast.util.POIUtil;
import com.coast.util.ProductToSAPUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Coast
 */
public class Controler {

    public static ResultMSG merge(String sapFile, String exportFile, String mergedFilePath) {
        ResultMSG resultMSG = new ResultMSG();
        try {
            ArrayList<Product> products;
            //要导入到模板的SAP文件
            products = readProductsFromMyExcel(sapFile, resultMSG);
            //从上品网站导出的模板文件
            String inFile = exportFile;
            //最后上传到上品网站的文件
            int lastSlash = sapFile.lastIndexOf(File.separator);
            String outFileName = sapFile.substring(lastSlash + 1, sapFile.length() - 4) + "_merged.xlsx";
            String outFile = mergedFilePath + File.separator + outFileName;
            //执行
            writeProductsToExcel(products, inFile, outFile, resultMSG);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return resultMSG;
        }
    }

    public static void writeProductsToExcel(ArrayList<Product> products, String inFile, String outFile, ResultMSG resultMSG) throws Exception {

        int sum = 0;
        InputStream is = null;
        OutputStream os = null;
//        String shopinSAPTemplateFileName = "temp5k.xlsx";
//        String path = Controler.class.getClassLoader().getResource("").getPath(); 
//        String shopinSAPTemplateFile = path+shopinSAPTemplateFileName;
        try {
            File f = new File(outFile);
            f.delete();
            is = new FileInputStream(new File(inFile));
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);

            //供应商名称：
            Cell supplierCell = sheet.getRow(1).getCell(1);
            supplierCell.setCellValue("卓尚服饰（杭州）有限公司");
            //品牌名称：
//            Cell brandCell = sheet.getRow(2).getCell(1);
//            brandCell.setCellValue("sonca桑卡");
            String brand = null;
            Iterator<Product> iter = products.iterator();

            int row = 4;
            while (iter.hasNext()) {
                Product product = iter.next();
                ProductToSAPUtil sapUtil = new ProductToSAPUtil(product);
                if (brand == null) {
                    brand = sapUtil.getBrand();
                    Cell brandCell = sheet.getRow(2).getCell(1);
                    brandCell.setCellValue(brand);
                }
                //条码 
                Cell fullSnCell = sheet.getRow(row).getCell(0);
                fullSnCell.setCellValue(product.getFullSn());
                //款号
                Cell snCell = sheet.getRow(row).getCell(1);
                snCell.setCellValue(product.getSnCode());
                //单位
                Cell unitCell = sheet.getRow(row).getCell(2);
                unitCell.setCellValue(sapUtil.getUnit());
                //颜色信息
                Cell colorCell = sheet.getRow(row).getCell(3);
                colorCell.setCellValue(product.getColorCode());
                //色系
                Cell colorTypeCell = sheet.getRow(row).getCell(4);
                colorTypeCell.setCellValue(sapUtil.getColorType());
                //1级品类名称
                Cell firstTypeCell = sheet.getRow(row).getCell(5);
                firstTypeCell.setCellValue(sapUtil.getFirstType());
                //2级品类名称
                Cell secondTypeCell = sheet.getRow(row).getCell(6);
                secondTypeCell.setCellValue(sapUtil.getSecondType());
                //3极品类名称
                Cell thirdTypeCell = sheet.getRow(row).getCell(7);
                thirdTypeCell.setCellValue(sapUtil.getThirdType());
                //国际尺码
                Cell internationalSizeCell = sheet.getRow(row).getCell(11);
                internationalSizeCell.setCellValue(product.getSize() + "(" + sapUtil.getInternationalSize() + ")");
                //企业尺码
//                Cell brandSizeCell = sheet.getRow(row).getCell(11);
//                brandSizeCell.setCellValue(product.getSize());
                //适合季节
                Cell fitSeasonCell = sheet.getRow(row).getCell(14);
                fitSeasonCell.setCellValue(sapUtil.getFitSeason());
                //年份
                Cell yearCell = sheet.getRow(row).getCell(15);
                yearCell.setCellValue(sapUtil.getYear());
                //attribute
                Cell attributeCell = sheet.getRow(row).getCell(16);
                attributeCell.setCellValue("无");
                //price
                Cell orgPricecell = sheet.getRow(row).getCell(20);
                orgPricecell.setCellValue(product.getOrgPrice());

                //21 now price
//                cell = currentRow.getCell(21);
//                String persent = DiscountUtil.getDiscount(info.getSn());
                //23
                Cell calculateCell = sheet.getRow(row).createCell(23, Cell.CELL_TYPE_FORMULA);
                String formula = "INT(U" + (row+1) + "*W" + (row+1) + ")";
                calculateCell.setCellFormula(formula);

                //下一行
                sum += product.getAmount();
                row++;
            }
            //Formula
            wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
            os = new FileOutputStream(new File(outFile));
            wb.write(os);
            os.flush();
            resultMSG.setWriteMessage("写入完成,共:" + sum + "件");
        } catch (Exception e) {
            e.printStackTrace();
            resultMSG.setWriteMessage("写入出错,共:" + sum + "件,错误:" + e.toString());
        } finally {
            is.close();
            os.close();
        }
    }

    public static ArrayList<Product> readProductsFromMyExcel(String file, ResultMSG resultMSG) throws Exception {
        ArrayList<Product> products = new ArrayList<Product>();
        InputStream is = null;
        int sum = 0;
        int row = 1;
        try {
            File f = new File(file);
            is = new FileInputStream(f);
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);

            POIUtil poiUtil = new POIUtil();
            int lastRowNum = sheet.getLastRowNum();
            while (row <= lastRowNum) {
                //款号为空就停止
                Cell firstCell = sheet.getRow(row).getCell(0);
                if (firstCell == null) {
                    break;
                }
                if (firstCell.getRichStringCellValue().toString().toUpperCase() == "") {
                    break;
                }

                //fullsn
                Cell fullSnCell = sheet.getRow(row).getCell(0);
                String fullSn = poiUtil.getCellContentToString(fullSnCell);
                int len = fullSn.length();
                String snCode = fullSn.substring(0, len - 3);
                String colorCode = fullSn.substring(len - 3, len - 1);
                String sizeCode = fullSn.substring(len - 1, len);
//                String sizeRegex = convertSizeToRegex(sizeCode);

                //type
                Cell typeCell = sheet.getRow(row).getCell(1);
                String type = poiUtil.getCellContentToString(typeCell);

                //color
                Cell colorCell = sheet.getRow(row).getCell(2);
                String color = poiUtil.getCellContentToString(colorCell);

                //size
                Cell sizeCell = sheet.getRow(row).getCell(3);
                String size = poiUtil.getCellContentToString(sizeCell);

                //price
                Cell priceCell = sheet.getRow(row).getCell(5);
                String orgPrice = poiUtil.getCellContentToString(priceCell);

                //amount
                Cell amountCell = sheet.getRow(row).getCell(10);
                int amount = Integer.parseInt(poiUtil.getCellContentToString(amountCell));

                //Porduct
                Product product = new Product(fullSn, snCode, colorCode, sizeCode, type, color, size, orgPrice, amount);

                products.add(product);

                sum += product.getAmount();
                row++;
            }
            resultMSG.setReadMessage("读取完成,共:" + sum + "件!");
        } catch (Exception e) {
            System.out.println("readProductsFromMyExcel出现异常:行=" + row + "列=目前无法确定" + e.toString());
            products = null;
            e.printStackTrace();
            resultMSG.setReadMessage("读取出错,共:" + sum + "件!错误:" + e.toString());
        } finally {
            is.close();
            System.out.println("读取总数:" + sum);
            return products;
        }
    }

    /**
     * 在上品导出的excel中找到对应的行
     *
     * @param sheet
     * @param sn
     * @param color
     * @param size
     * @return
     * @throws Exception
     */
    public static int getRowNum(Sheet sheet, String sn, String color, String size) throws Exception {
        int lastRowNum = sheet.getLastRowNum();//excell中左后一行显示为lastRowNum+1;
        int rowNum = lastRowNum;
        while (rowNum > 0) {
            Cell snCell = sheet.getRow(rowNum).getCell(3);
            Cell colorCell = sheet.getRow(rowNum).getCell(4);
            Cell sizeCell = sheet.getRow(rowNum).getCell(5);
            POIUtil poiUtil = new POIUtil();
            String targetSn = poiUtil.getCellContentToString(snCell);
            String targetColor = poiUtil.getCellContentToString(colorCell);
            String targetSize = poiUtil.getCellContentToString(sizeCell);
            if (targetSn.equals(sn)
                    && targetColor.equals(color)
                    && targetSize.matches(size)) {
                System.out.println("Find! snCell:" + snCell.toString());
                return rowNum;
            }
            rowNum--;   //从下往上找
        }
        return 0;
    }

//    public static String trimSize(String size) {
//        String editedSize = "";
//        String regex = "[cm]";
//        editedSize = size.replaceAll(regex, "");
//        return editedSize;
//    }
//    private static String convertSizeToRegex(String size) {
//        String result;
//        switch (size) {
//            case "1":
//                result = "^155.*";
//                break;
//            case "2":
//                result = "^160.*";
//                break;
//            case "3":
//                result = "^165.*";
//                break;
//            case "4":
//                result = "^170.*";
//                break;
//            case "5":
//                result = "^175.*";
//                break;
//            case "6":
//                result = "^180.*";
//                break;
//            default:
//                result = "";
//                break;
//        }
//        return result;
//    }
}
