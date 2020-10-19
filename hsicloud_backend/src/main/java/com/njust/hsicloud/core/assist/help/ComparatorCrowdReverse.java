package com.njust.hsicloud.core.assist.help;

import java.util.Comparator;

import com.njust.hsicloud.core.assist.algrithm.Population;

/**
 * Created by Tao on 2017/12/12.
 */
public class ComparatorCrowdReverse implements Comparator{
    public int compare(Object p1, Object p2) {
        Population x1 = (Population)p1;
        Double c1 = x1.getCrowd();
        Population x2 = (Population)p2;
        Double c2 = x2.getCrowd();
        int flag = c2.compareTo(c1);
        return flag;
    }
}