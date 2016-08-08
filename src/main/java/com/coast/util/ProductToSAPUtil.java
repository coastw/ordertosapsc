package com.coast.util;

import com.coast.model.Product;

/**
 *
 * @author Coast
 */
public class ProductToSAPUtil {

    private Product product;

    public ProductToSAPUtil(Product product) {
        this.product = product;
    }

    public String getBrand() {
        String brand;
        String brandCode = this.product.getFullSn().substring(0, 1);
        switch (brandCode) {
            case "K":
                brand = "sonca桑卡";
                break;
            default:
                brand = "三colour";
                break;
        }
        return brand;
    }

    /**
     * 单位
     *
     * @return
     */
    public String getUnit() {
        return "件";
    }

    /**
     * 色系: 红绿蓝紫粉黄灰白黑花
     *
     * @return
     */
    public String getColorType() {
        String colorType;
        String colorCode = this.product.getColorCode();
        if (colorCode.matches("^[1]{1}[0-9]{1}$")) {
            colorType = "红色";
        } else if (colorCode.matches("^[2]{1}[0-9]{1}$") || colorCode.equals("02")) {
            colorType = "黄色";
        } else if (colorCode.matches("^[3]{1}[0-9]{1}$") || colorCode.equals("03")) {
            colorType = "绿色";
        } else if (colorCode.matches("^[4]{1}[0-9]{1}$") || colorCode.equals("04")) {
            colorType = "蓝色";
        } else if (colorCode.matches("^[5]{1}[0-9]{1}$") || colorCode.equals("05")) {
            colorType = "紫色";
        } else if (colorCode.matches("^[6]{1}[0-9]{1}$") || colorCode.equals("06")) {
            colorType = "白色";
        } else if (colorCode.matches("^[7]{1}[0-9]{1}$") || colorCode.equals("07")) {
            colorType = "灰色";
        } else if (colorCode.matches("^[8]{1}[0-9]{1}$") || colorCode.equals("08")) {
            colorType = "棕色";
        } else if (colorCode.matches("^[9]{1}[0-9]{1}$")) {
            colorType = "黑色";
        } else if (colorCode.equals("01")) {
            colorType = "粉色";
        } else {
            colorType = "花色";
        }
        return colorType;
    }

    /**
     * 1级品类
     *
     * @return
     */
    public String getFirstType() {
        return "女装";
    }

    /**
     * 2级品类 K630231M00664
     *
     * @return
     */
    public String getSecondType() {
        String secondType;
        String typeCode = this.product.getFullSn().substring(7, 8).toUpperCase();
        switch (typeCode) {
            case "A":
            case "C":
            case "D":
            case "F":
            case "M":
            case "P":
            case "W":
            case "Z":
            case "G":
                secondType = "女装上装";
                break;
            case "K":
            case "N":
                secondType = "女装裤子";
                break;
            case "Q":
            case "L":
                secondType = "裙装";
                break;
            default:
                secondType = "#" + typeCode;
                break;
        }
        return secondType;
    }

    /**
     * 3级品类 K630231M00664
     *
     * @return
     */
    public String getThirdType() {
        String thirdType;
        String typeCode = this.product.getFullSn().substring(7, 8).toUpperCase();
        switch (typeCode) {
//            case "037":
//                thirdType = "打底裤";
//                break;
//            case "031":
//                thirdType = "短裤";
//                break;
            case "N":
                thirdType = "休闲裤";
                break;
            case "K":
                thirdType = "长裤";
                break;
            case "Q":
                thirdType = "半身裙";
                break;
//            case "W":
//                thirdType = "马甲";
//                break;
            case "L":
                thirdType = "连衣裙";
                break;
//            case "061":
//            case "062":
//            case "063":
//            case "064":
//            case "065":
//            case "121":
//            case "122":
//                thirdType = "T恤";
//                break;
            case "C":
                thirdType = "衬衣";
                break;
//            case "111":
//            case "112":
//            case "113":
//                thirdType = "大衣";
//                break;
//            case "051":
            case "F":
                thirdType = "风衣";
                break;
            case "Z":
            case "M":
                thirdType = "毛衣/针织衫";
                break;
//            case "131":
//            case "132":
//            case "133":
//                thirdType = "棉衣/棉服";
//                break;
            case "W":
            case "G":
                thirdType = "外套";
                break;
//            case "091":
//            case "092":
//            case "093":
//                thirdType = "羽绒服";
//                break;
//            case "141":
//                thirdType = "中款皮草";
//                break;
            default:
                thirdType = "#" + typeCode;
                break;
        }
        return thirdType;
    }

    /**
     * 国际尺码 K620076L20461 1234567890123
     *
     * @return
     */
    public String getInternationalSize() {
        String internationalSize;
        String sizeCode = this.product.getSizeCode();
        //下身
        String typeCode = this.product.getFullSn().substring(7, 8).toUpperCase();
        if (typeCode.equals("K") || typeCode.equals("Q")) {
            switch (sizeCode) {
                case "1":
                    internationalSize = "155/64A";
                    break;
                case "2":
                    internationalSize = "160/68A";
                    break;
                case "3":
                    internationalSize = "165/72A";
                    break;
                case "4":
                    internationalSize = "170/76A";
                    break;
                case "5":
                    internationalSize = "175/80A";
                    break;
                case "6":
                    internationalSize = "180/84A";
                    break;
                default:
                    internationalSize = "#" + sizeCode;
                    break;
            }
        } else {  //上身
            switch (sizeCode) {
                case "1":
                    internationalSize = "155/80A";
                    break;
                case "2":
                    internationalSize = "160/84A";
                    break;
                case "3":
                    internationalSize = "165/88A";
                    break;
                case "4":
                    internationalSize = "170/92A";
                    break;
                case "5":
                    internationalSize = "175/96A";
                    break;
                case "6":
                    internationalSize = "180/100A";
                    break;
                default:
                    internationalSize = "#" + sizeCode;
                    break;
            }
        }
        return internationalSize;
    }

    /**
     * 适合季节
     *
     * @return
     */
    public String getFitSeason() {
        String season;
        String seasonCode = this.product.getFullSn().substring(2, 3);
        switch (seasonCode) {
            case "1":
            case "2":
                season = "春/夏FP01";
                break;
            case "3":
            case "4":
                season = "秋/冬FP02";
                break;
            default:
                season = "无FP00";
                break;
        }
        return season;
    }

    /**
     * 年份 K630231M00664
     *
     * @return
     */
    public String getYear() {
        String year;
        String yearCode = this.product.getFullSn().substring(1, 2);
        switch (yearCode) {
            case "1":
                year = "2011年";
                break;
            case "2":
                year = "2012年";
                break;
            case "3":
                year = "2013年";
                break;
            case "4":
                year = "2014年";
                break;
            case "5":
                year = "2015年";
                break;
            case "6":
                year = "2016年";
                break;
            case "7":
                year = "2017年";
                break;
            case "8":
                year = "2018年";
                break;
            case "9":
                year = "2019年";
                break;
            default:
                year = "#" + yearCode;
                break;
        }
        return year;

    }

}
