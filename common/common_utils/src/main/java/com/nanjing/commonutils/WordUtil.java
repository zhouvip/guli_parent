package com.nanjing.commonutils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 通过word模板生成新的word工具类
 * @author yimo
 * @version 1.0
 * @date 2022/10/9 14:56
 */
public class WordUtil {

    /*如果遇到poi读取例如{name}不能识别为一个整体，可以使用word的域操作，
    如果不太清楚域的使用，可以这么操作，先在text文档中写好，例如{name}，
    然后再整个复制到word中，不要一个一个在word中敲，不然有可能不会被poi识别为一个整体
    作者：TryCatch菌
    链接：https://www.jianshu.com/p/6603b1ea3ad1
    来源：简书
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    如果想要把${title}作为一个整体，也是同样的做法，下面代码的判断要改成String key = "${"+textSet.getKey()+"}";
    */


    /**
     * 根据模板生成新word文档
     * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
     * @param textMap 需要替换的信息集合
     * @return 成功返回true,失败返回false
     */
    public static void changWord(InputStream inputStream, Map<String, String> textMap,int height,int width) {

        InputStream in = null;
        try {
            //1.获取word文档解析对象
            XWPFDocument document = new XWPFDocument(inputStream);
            //解析替换文本段落对象
            WordUtil.changeText(document, textMap);
            //解析替换表格对象
            WordUtil.changeTable(document, textMap);
            //替换文本中的图片
            WordUtil.changePicture(document,textMap,height,width);
            //将文档转为输入流
            /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write("D:\\a.docx".getBytes());
            //文档写入流
            document.write(baos);*/
            //上面代码有问题，用下面这个
            //5.生成新的word
            FileOutputStream outputStream = new FileOutputStream("D:\\a.docx");
            document.write(outputStream);
            outputStream.flush();
            outputStream.close();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 替换段落文本
     * @param document docx解析对象
     * @param textMap 需要替换的信息集合
     */
    public static void changeText(XWPFDocument document, Map<String, String> textMap) {
        //2.获取段落文本对象集合
        List<XWPFParagraph> paragraphs = document.getParagraphs();

        for (XWPFParagraph paragraph : paragraphs) {
            //判断此段落时候需要进行替换
            String text = paragraph.getText();
            if(checkText(text)){
                //3.获取run对象集合
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    //替换模板原来位置
                    String value = changeValue(run.toString(), textMap);
                    /*if (StringUtils.isNotEmpty(value)) {      //run.setText(changeValue(run.toString(), textMap),0);
                        run.setText(value, 0);
                    }*/
                    //如果不是把${title}当成一个整体，上面代码就只能部分替换了，保险起见，注掉
                    //4.设置文本内容
                    run.setText(value, 0);

                }
            }
        }

    }

    /**
     * 添加图片
     * @param document
     * @param filePath 图片路径
     * @param height 图片高
     * @param width 图片宽
     * @throws Exception
     */
    public static void addPicture(XWPFDocument document, String filePath, Integer height, Integer width) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(filePath);
            File file = new File(filePath);
            XWPFParagraph picture = document.createParagraph();
            XWPFRun run = picture.createRun();
            run.addPicture(in, XWPFDocument.PICTURE_TYPE_PNG, file.getName(), Units.toEMU(width), Units.toEMU(height));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * 指定替换模板中的图片
     * @param document
     * @param filePath
     * @param height
     * @param width
     */
    public static void changePicture(XWPFDocument document, Map<String,String> filePath, Integer height, Integer width) {
        Set<Entry<String, String>> textSets = filePath.entrySet();
        for (Entry<String, String> textSet : textSets) {
            FileInputStream in = null;
            try {
                //获取图片段落集合
                List<XWPFParagraph> paragraphs = document.getParagraphs();
                for (XWPFParagraph paragraph : paragraphs) {
                    //判断此段落时候需要进行替换
                    String text = paragraph.getText();
                    if(text.contains("&")){
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            //匹配模板与替换值 格式#{key}
                            String key = "&{"+textSet.getKey()+"}";
                            //与模板中的替换值进行匹配
                            String item=run.toString();
                            if(item.contains(key)) {
                                //将标签清楚
                                run.setText("",0);
                                in = new FileInputStream(textSet.getValue());
                                File file = new File(textSet.getValue());
                                //添加图片
                                run.addPicture(in, XWPFDocument.PICTURE_TYPE_PNG, file.getName(), Units.toEMU(width), Units.toEMU(height));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    /**
     * 替换表格对象方法
     * @param document docx解析对象
     * @param textMap 需要替换的信息集合
     */
    public static void changeTable(XWPFDocument document, Map<String, String> textMap){
        //获取表格对象集合
        List<XWPFTable> tables = document.getTables();
        for (int i = 0; i < tables.size(); i++) {
            //只处理行数大于等于2的表格，且不循环表头
            XWPFTable table = tables.get(i);
            if(table.getRows().size()>1){
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if(checkText(table.getText())){
                    List<XWPFTableRow> rows = table.getRows();
                    //遍历表格,并替换模板
                    eachTable(rows, textMap);
                }
            }
        }
    }


    /**
     * 遍历表格
     * @param rows 表格行对象
     * @param textMap 需要替换的信息集合
     */
    public static void eachTable(List<XWPFTableRow> rows ,Map<String, String> textMap){
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                //判断单元格是否需要替换
                if(checkText(cell.getText())){
                    List<XWPFParagraph> paragraphs = cell.getParagraphs();
                    for (XWPFParagraph paragraph : paragraphs) {
                        List<XWPFRun> runs = paragraph.getRuns();
                        for (XWPFRun run : runs) {
                            //替换模板原来位置
                            String value=changeValue(run.toString(), textMap);
                            if (StringUtils.isNotEmpty(value)) {
                                run.setText(value, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 为表格插入数据，行数不够添加新行
     * @param table 需要插入数据的表格
     * @param tableList 插入数据集合
     */
    public static void insertTable(XWPFTable table, List<String[]> tableList){
        //创建行,根据需要插入的数据添加新行，不处理表头
        for(int i = 1; i < tableList.size(); i++){
            XWPFTableRow row =table.createRow();
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        for(int i = 1; i < rows.size(); i++){
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for(int j = 0; j < cells.size(); j++){
                XWPFTableCell cell = cells.get(j);
                cell.setText(tableList.get(i-1)[j]);
            }
        }

    }


    /**
     * 判断文本中时候包含$
     * @param text 文本
     * @return 包含返回true,不包含返回false
     */
    public static boolean checkText(String text){
        boolean check  =  false;
        if(text.indexOf("$")!= -1){
            check = true;
        }
        return check;

    }

    /**
     * 匹配传入信息集合与模板
     * @param value 模板需要替换的区域
     * @param textMap 传入信息集合
     * @return 模板需要替换区域信息集合对应值
     */
    public static String changeValue(String value, Map<String, String> textMap){
        Set<Entry<String, String>> textSets = textMap.entrySet();
        for (Entry<String, String> textSet : textSets) {
            //匹配模板与替换值 格式${key}
            String key = "${"+textSet.getKey()+"}";  //${title}是一个整体
            //String key = "{"+textSet.getKey()+"}"; //$和{title}是分开的，需要{}去匹配
            if(value.indexOf(key)!= -1){
                value = textSet.getValue();
            }
        }
        //模板未匹配到区域替换为空
        if(checkText(value)){
            value = "";
        }
        return value;
    }


    public static void main(String[]  args) throws Exception {
        //从FTP读取文件模板
        InputStream is=new FileInputStream(new File("D://test.docx"));
        //填充文本和表格需要替换的数据
        Map<String,String> tempMap=new HashMap<>();
        tempMap.put("title","测试标题");
        tempMap.put("content","这是一份测试摘要");
        tempMap.put("param1","正常运行");
        tempMap.put("param2","良好");
        //调取数据生成Echarts图表
        //String options = "{\"title\":{\"text\":\"告警图\",\"subtext\":\"告警统计\",\"x\":\"CENTER\"},\"toolbox\": {\"feature\": {\"saveAsImage\": {\"show\": true,}}},\"tooltip\": {\"show\": true},\"legend\": {\"data\":[\"一级告警\",\"二级告警\",\"三级告警\",\"四级告警\"]}, \"series\":[{\"name\":\"告警等级\",\"type\":\"pie\",\"radius\": '55%',\"center\": ['50%', '60%'],\"data\":[{\"value\":335, \"name\":\"一级告警\"},{\"value\":310, \"name\":\"二级告警\"},{\"value\":234, \"name\":\"三级告警\"},{\"value\":135, \"name\":\"四级告警\"}]}]}";
        //String imagePath=echartsUtils.generateEChart(options);
        //tempMap.put("imagePath",imagePath);

        tempMap.put("imagePath","D://test.png");

        //生成新的word文档
        WordUtil.changWord(is,tempMap,160,120);

    }
}

