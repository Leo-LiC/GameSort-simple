
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UI {

    /*
    * 创建桌面窗口
    * 用以接收用户输入Excel文件路径
    * 并将生成结果的路径反回
    * */

    public File createAndShowUI(){

        JFrame frame = new JFrame();

        //设置窗口位置位于屏幕中间
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screensize.getWidth() / 2 - 450 / 2;
        int y = (int) screensize.getHeight() / 2 - 220 / 2;
        frame.setLocation(x,y);

        //设置显示窗口标题
        frame.setTitle("游戏名单筛选小程序");
        //设置显示尺寸
        frame.setSize(450,220);
        //设置窗口是否可关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //创建面板，用以指定控件位置
        JPanel panel = new JPanel();



        //设置文本框
        JLabel label1 = new JLabel("选择Excel文件,结果文件为原路径父目录下result.xlsx文件！");
        JLabel label2 = new JLabel("解析结果路径：");
        label1.setBounds(20,30,240,30);
        label2.setBounds(20,60,240,30);
        panel.add(label1);
        panel.add(label2);


        //设置文本框
        JTextField  textField = new JTextField();
        textField.setBounds(20,90,260,30);
//        textField.setText("结果文件路径：" + file.getParent() + "\\result.xlsx");
        panel.add(textField);

//
//        //设置按钮
//        final  JButton jButton = new JButton("确认");
//        jButton.setBounds(180,200,40,30);
//        panel.add(jButton);

        File filePath = null;
        //添加文件选择器
        JFileChooser fileChooser = new JFileChooser();
        //仅显示文件
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showDialog(new JLabel(),"选择Top游戏名单Excel文件");
        filePath =fileChooser.getSelectedFile();
        System.out.println("文件路径为：" + filePath);
        textField.setText(filePath.getName());


        String file1 = filePath.getParent()+"\\result.xlsx";
        textField.setText(file1);
        frame.add(panel);

        //设置窗口是否可见
        frame.setVisible(true);

        return filePath;
    }


}
