package njust.tools;

import java.util.*;

public class Operation {
    public static double singleCZV2(Map<String,String> map1, Map<String,String> map2){
        //map1存放的是一条向量波长(横坐标)与反射率(纵坐标)的对应关系
        List<String> li=new ArrayList();//存放公共范围的x坐标
        List<String> kli1=new ArrayList(map1.keySet());
        List<String> kli2=new ArrayList(map2.keySet());
        //1.确定公共范围

        double min1=Double.parseDouble(kli1.get(0));
        double min2=Double.parseDouble(kli2.get(0));
        double max1=Double.parseDouble(kli1.get(kli1.size()-1));
        double max2=Double.parseDouble(kli2.get(kli2.size()-1));
        double fmin;
        double fmax;
        double res=-1.0;
        if(min1>max2 || min2>max1){
            //此种情况没有公共部分!
        }else{
            //下边界为较大的最小值,上边界为较小的最大值
            if(min1>min2){
                fmin=min1;
            }else{
                fmin=min2;
            }
            if(max1<max2){
                fmax=max1;
            }else{
                fmax=max2;
            }
            for(String tempStr:kli1){
                double temp=Double.parseDouble(tempStr);
                if(temp>fmin && temp<fmax){
                    li.add(temp+"");
                }
            }
            for(String tempStr:kli2){
                double temp=Double.parseDouble(tempStr);
                if(temp>fmin && temp<fmax){
                    li.add(temp+"");
                }
            }
            List<String> vLi1=new ArrayList();
            //至此所有x轴坐标圈定完毕
            for(int i=0;i<li.size();i++){
                String keyStr=li.get(i);
                if(map1.containsKey(keyStr)){
                    vLi1.add(map1.get(keyStr));
                }else{
                    //这种情况需要执行单线性插值!
                    double x=Double.parseDouble(keyStr);
                    double x1;
                    double y1;
                    double x2;
                    double y2;
                    for(int j=0;j<kli1.size();j++){
                        double tempK=Double.parseDouble(kli1.get(j));
                        if(tempK>x){
                            x1=Double.parseDouble(kli1.get(j-1));
                            y1=Double.parseDouble(map1.get(kli1.get(j-1)));
                            x2=tempK;
                            y2=Double.parseDouble(map1.get(kli1.get(j)));

                            double val=((x2-x)/(x2-x1))*y1+((x-x1)/(x2-x1))*y2;
                            vLi1.add(String.valueOf(val));
                            break;
                        }
                    }
                }
            }

            //取得光谱库向量
            List<String> vLi2=new ArrayList();
            for(int i=0;i<li.size();i++){
                String keyStr=li.get(i);
                if(map2.containsKey(keyStr)){
                    vLi2.add(map2.get(keyStr));
                }else{
                    //这种情况需要执行单线性插值!
                    double x=Double.parseDouble(keyStr);
                    double x1;
                    double y1;
                    double x2;
                    double y2;
                    for(int j=0;j<kli2.size();j++){
                        double tempK=Double.parseDouble(kli2.get(j));
                        if(tempK>x){
                            x1=Double.parseDouble(kli2.get(j-1));
                            y1=Double.parseDouble(map2.get(kli2.get(j-1)));
                            x2=tempK;
                            y2=Double.parseDouble(map2.get(kli2.get(j)));

                            double val=((x2-x)/(x2-x1))*y1+((x-x1)/(x2-x1))*y2;
                            vLi2.add(String.valueOf(val));
                            break;
                        }
                    }
                }
            }

            //至此,两条可以进行sad的向量已准备好
            //List转数组

            double [] v1=new double[vLi1.size()];
            for(int k=0;k<vLi1.size();k++){
                v1[k]=Double.parseDouble(vLi1.get(k));
            }
            double [] v2=new double[vLi2.size()];
            for(int k=0;k<vLi2.size();k++){
                v2[k]=Double.parseDouble(vLi2.get(k));
            }
            res=sad(v1,v2);
        }
        return res;
    }


    public static double sad(double [] v1,double [] v2){
        double vProduct=0.0;//分子
        double sum1=0.0;
        double sum2=0.0;
        for(int i=0;i<v1.length;i++){
            vProduct+=v1[i]*v2[i];
            sum1+=v1[i]*v1[i];
            sum2+=v2[i]*v2[i];
        }
        double denominator=Math.sqrt(sum1)*(Math.sqrt(sum2));//分母
        //计算反余弦函数
        double res=Math.acos(vProduct/denominator);
        return res;
    }
}
