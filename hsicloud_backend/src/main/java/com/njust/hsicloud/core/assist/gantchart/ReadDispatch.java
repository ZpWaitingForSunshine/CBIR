package com.njust.hsicloud.core.assist.gantchart;/* this java ，we read the xml of Dispatch result from scheduling algrithm
 create by
nwk 2019/12/9/9:46
logic:
1.read xml
2.queue it
3.save
 */
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


//import org.cloudbus.cloudsim.core.CloudSim;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*import dag.Dag;
import dag.Node;
import flanagan.analysis.PCA;
import java.util.HashMap;
*/


//create by shell
//2019.03.22
//解析xml文件，并将DAG图存成对应的格式
public class ReadDispatch {
    // data for xml info
    public String  dagname;
    public int VMnum;
    public String ImageId;
    public String object;
    public static smallAlgrithm[] smallAlgrithms;
    private void readXml(String filePath) throws Exception{
        //creat the flow of xml
        SAXReader reader = new SAXReader();
        File file = new File(filePath);
        Document document = reader.read(file);
        //get root element
        Element root = document.getRootElement();
        //read info in file
        this.dagname = root.element("DagInfo").element("dagname").getTextTrim();
        this.VMnum =  Integer.valueOf(root.element("DagInfo").element("VMnum").getTextTrim());
        this.ImageId =root.element("DagInfo").element("ImageId").getTextTrim();
        this.object = root.element("DagInfo").element("object").getTextTrim();
        //read every small algrithm info
        //1.create list to show small algrithm in xml
        List<Element> nodeList = root.element("DagInfo").elements("TaskInfo");
        System.out.println("long"+nodeList.size()+"  "+this.object);
        this.smallAlgrithms = new smallAlgrithm[nodeList.size()];
        for(int i=0;i< nodeList.size();i++)
        {
            Element nodeListElement = nodeList.get(i);
            smallAlgrithm tmp = new smallAlgrithm();
            tmp.CloudletID = nodeListElement.element("CloudletID").getTextTrim();
            tmp.VMID = Integer.parseInt(nodeListElement.element("VMID").getTextTrim());
            tmp.FinishTime = Double.parseDouble(nodeListElement.element("FinishTime").getTextTrim());
            tmp.StartTime = Double.parseDouble(nodeListElement.element("StartTime").getTextTrim());
            tmp.Time = Double.parseDouble(nodeListElement.element("Time").getTextTrim());
            tmp.par = Integer.parseInt(nodeListElement.element("par").getTextTrim());
            this.smallAlgrithms[i]  = tmp;
        }
    }
    public void ReadDis(String filepath) throws Exception {
        readXml(filepath);
        //System.out.println("zhengpeng");
    }
    public int getVMnum()  {
        return VMnum;
    }
}
