package cn.smartrick.metaverse.utils.excel;

/**
 * @author zhuoda
 * @Date 2020/8/10
 */


import java.io.*;

/**
 * @Date: 2021年11月24日10:41:29
 * @Author: SmartRick
 * @Description: Excel工具
 */
public class SmartExcelReader {

    public static SmartExcel openExcel(String filePath) throws IOException {
        if(!new File(filePath).exists()){
            throw new FileNotFoundException();
        }
        return new SmartExcel(new File(filePath).getCanonicalPath());
    }

    public static SmartExcel openExcel(File file) throws IOException {
        return new SmartExcel(file.getCanonicalPath());
    }

    public static SmartExcel openExcel(InputStream ins, SmartExcelFileType fileType) throws IOException {
        return new SmartExcel(ins, fileType);
    }

    public static void main(String[] args) throws Exception {
        SmartExcel smartExcel = openExcel(new FileInputStream(new File("F:/privilege.xlsx")), SmartExcelFileType.XLSX);
        System.out.println(smartExcel.getSheetList());
    }

}
