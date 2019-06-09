import java.util.*;

/**
 * @description: 将ArrayList中的值，按出现次数从多到少排序
 * @author: li cong
 * @create: 2019-05-12 11:27
 */

public class ArraySort {

    public static List<Map<String, Integer>> arraySort(List<String> list) {
        List<String> excelList = new ArrayList<String>(list);
        Map map = new HashMap();
        int count = 0;
        for(int i=0;i < excelList.size();i++){     //循环遍历List，计算出每个元素出现的次数
            String tmp = excelList.get(i);
            count = 0;
            for(int j=0;j < excelList.size();j++){
                if(!tmp.equals(excelList.get(j))){
                    continue;
                }else{
                    count ++;
                }
            }

            map.put(excelList.get(i),count);

            }
//           Map<String,Integer> sortMap = new TreeMap<String, Integer>(new MapKeyComparator());
            List<Map<String, Integer>> resList = sortMapByKey(map);

            System.out.println("Map is " + map);

            return resList;
    }

    /**
     *  按照Value对Map进行从大到小倒序排序
     */

    public static  List<Map.Entry<String,Integer>> sortMapByKey(Map<String,Integer> map){
        if(map == null || map.isEmpty()){
            return  null;
        }
        Map<String,Integer> sortMap = new TreeMap<String, Integer>(map);
        //将map.entrySet()转化为list
        List<Map.Entry<String,Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        //通过比较器进行排序
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
           //倒序排序
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });


        System.out.println("entryList is "+ entryList);

        return entryList;

    }
}
