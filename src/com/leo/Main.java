import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {

        File excelPath = null;
        //生成窗口UI
        UI gUI = new UI();
        excelPath = gUI.createAndShowUI();


//        String excelPath = null;     //Excel Path
//        String excelPath = "E:\\log\\Top30.xlsx";
//        System.out.println("请输入游戏Excel表格所在路径：（以回车键结束！）");
//        Scanner sc = new Scanner(System.in);
//        excelPath = sc.nextLine();



        File excelFile = excelPath;
        File resultFile = new File(excelFile.getParent()+ "\\result.xlsx" );
//        File resultTxt = new File(excelFile.getParent() + "\\result.txt");
        List<String> excelList = null;

//        List<List<String>> sortList = new ArrayList<String>(excelList);


        if(excelFile == null || !excelFile.exists()){
            System.out.println("输入路径有误！" );
        }else{
            excelList = ExcelUtils.readExcel(excelFile);
            System.out.println("excelList  is "  + excelList);

            List<Map<String, Integer>> resList = ArraySort.arraySort(excelList);
            System.out.println("resList  is  "  + resList);


            if(!resultFile.exists()){
                try{
                    resultFile.createNewFile();
                    System.out.println("Create ResultFile!");
                    ExcelUtils.writeExcel(resultFile,resList);
//                    ExcelUtils.writeTxt(resultTxt,resList);
                    System.out.println("ResultFile is " + resultFile);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else{
                try{
                    resultFile.delete();

                    resultFile.createNewFile();
                    System.out.println("Create ResultFile!");
                    ExcelUtils.writeExcel(resultFile,resList);
//                    ExcelUtils.writeTxt(resultTxt,resList);
                    System.out.println("ResultFile is " + resultFile);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }


    }
}
