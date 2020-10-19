package com.njust.hsicloud.core.assist.algrithm;


import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Tao on 2017/12/1.
 */
public class Population {

    private LinkedHashMap<String,Integer> x = null;
    private int NumX = 0;
    private double crowd = 0.0;
    private int d = 0;                      //dominate
    private int bd = 0;                     //by dominated
    private ArrayList<Double> f = null;     //the result of ����Ŀ�꺯��

    /** SET */

    public void setBd(int bd)           {     this.bd = bd;    }
    public void setNumX(int numX)       {     NumX = numX;     }
    public void setCrowd(double crowd)  {     this.crowd = crowd;    }
    public void setD(int d)             {     this.d = d;      }

    public void setList(LinkedHashMap<String, Integer> listx) {    this.x = listx;   }
    public void setF(ArrayList<Double> f)         {    this.f = f;     }

    /** GET */
    public ArrayList<Double> getF()  {   return f;   }
//    public ArrayList<Integer> getX() {   return x;   }
    public LinkedHashMap<String,Integer> getX() {   return x;   }
    public double getCrowd()         {   return crowd;   }
    public int getBd()               {   return bd;  }
    public int getD()                {   return d;   }
    public int getNumX()             {   return NumX;    }
}
