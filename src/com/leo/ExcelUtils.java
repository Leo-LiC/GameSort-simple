import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * @program: GameSorting
 * @description: Read the excel and write to hashMap ，Reference by https://witchlovelearning.iteye.com/blog/1986299
 * @author: li cong
 * @create: 2019-05-11 14:40
 */
public class ExcelUtils {
    /**
     *@Description:  read excel and write to List
     *@Param:
     *@return: sList
     *@Author: li cong
     *@date: 20190511
     */
    public static List<String> readExcel(File file){
        File excelFile = file; //excel file path
        InputStream inputStream = null;
        String cellStr = null;
        List<String> sList = new ArrayList<String>();

        try{
            if(!excelFile.exists()){
                System.out.println("文件不存在！");
                return null;
            }else {
                inputStream = new FileInputStream(excelFile);
                XSSFWorkbook wb = new XSSFWorkbook(inputStream); //创建Excel文件对象
                XSSFSheet sheet = wb.getSheetAt(0);

                //获取表格的总行数
                int rowCount = sheet.getLastRowNum();
                //获取表头的列数
                int columnCount = sheet.getRow(0).getLastCellNum();


                //开始循环遍历，跳过表头
                for(int i = 1;i <= sheet.getLastRowNum();i++){
                    XSSFRow row = sheet.getRow(i);  //行
                    if(row == null){
                        continue;
                    }

                    //循环遍历单元格
                    List<String> rowStr = new ArrayList<String>();


                    for(int j = 0;j < row.getLastCellNum();j++){  //从第一列开始，按行读取
                        XSSFCell xssfCell = row.getCell(j);  //获取单元格对象
                        if(xssfCell == null){
                            cellStr = "";
                        }else if(xssfCell.getCellType() == CellType.FORMULA){  //单元格为公式
                            cellStr = xssfCell.getCellFormula();
                        }else{
                            cellStr = xssfCell.getStringCellValue();
                        }

                        rowStr.add(cellStr);   //数据写入List

                        sList.add(cellStr); //数据写入sList
//                        System.out.println("rowStr is " + rowStr);

                    }
                    if("".equals(row.getCell(0))||row.getCell(0) == null){
                        break;
                    }else{
//                        sList.add(cellStr); //数据写入sList
                        System.out.println("sList is " + sList);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (inputStream == null){
                try{
                    inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return sList;
    }

/*
* 将排序后List<Map<String,Integer>> 写入Ecxel。参考资料：
* https://blog.csdn.net/u013067756/article/details/54604346
* https://blog.csdn.net/wangmuming/article/details/78518301
* */

    public static void writeExcel(File file, List<Map<String, Integer>> list){
        File filePath = file;      //结果输出文件路径，写入原文件中
//        List<Map<String, Integer>> resList = list;   //排序后的List
        LinkedHashMap<String,Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        OutputStream outputStream = null;
        XSSFWorkbook workbook = new XSSFWorkbook(); //创建Excel文件对象

        try{
            if(!filePath.exists()) {
                System.out.println("文件不存在！");
            }else {

//                XSSFWorkbook workbook = new XSSFWorkbook(); //创建Excel文件对象
                XSSFSheet sheet1 = workbook.createSheet("Result");


                //获得表头行对象
                XSSFRow titleRow = sheet1.createRow(0);
                String title = "排序结果";
                String title2 = "出现次数";
                titleRow.createCell(0).setCellValue(title);
                titleRow.createCell(1).setCellValue(title2);
//                System.out.println("titleRow is " + titleRow);

                for(int m=0;m < list.size();m++){
                    Map.Entry<String,Integer> entry = (Map.Entry<String, Integer>) list.get(m);  //将List<Map.Entry<String,Integer>> 转化为Map.Entry<String,Integer>
                    linkedHashMap.put(entry.getKey(),entry.getValue()); //LinkedHashMap 用于保存排序后Map元素
//                    System.out.println("linkedHashMap is " + linkedHashMap);

                    XSSFRow row1 = sheet1.createRow(m+1);
                    String key = entry.getKey();    //游戏名称
                    Integer value = entry.getValue(); //出现的次数

                    XSSFCell cell = row1.createCell(0);  //写入第一列
                    XSSFCell cell12 = row1.createCell(1);  //写入第二列
                    cell.setCellValue(key);
                    cell12.setCellValue(value);
//                        cell.setCellValue(resList.get(m));
//                    System.out.println("cell is " + cell);
                }
                System.out.println("linkedHashMap is " + linkedHashMap);




                outputStream = new FileOutputStream(filePath);
                workbook.write(outputStream);
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(outputStream == null){
            try{
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        }





    }
    /*
    * 将排序后List<Map<String,Integer>> 写入txt文件中，测试使用
    * */
    public static void writeTxt(File file, List<Map<String, Integer>> list){

        LinkedHashMap<String,Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        String line = System.getProperty("line.separator");




        for(int m=0;m<list.size();m++){
            Map.Entry<String,Integer> entry = (Map.Entry<String, Integer>) list.get(m);
            linkedHashMap.put(entry.getKey(),entry.getValue());
        }
        System.out.println("linkedHashMap is " + linkedHashMap);

        Set<Map.Entry<String,Integer>> entrySet = linkedHashMap.entrySet();

        Iterator<Map.Entry<String,Integer>> iterator = entrySet.iterator();

        StringBuffer buffer = new StringBuffer();

        try {
            FileWriter fileWriter = new FileWriter(file);
            while(iterator.hasNext()){
                Map.Entry<String,Integer> mey = iterator.next();
                buffer.append(mey.getKey()+ " —— " + mey.getValue()).append(line);
                System.out.println("mey is " + mey);
            }

            fileWriter.write(buffer.toString());
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
