package com.njust.hsicloud.core.assist.dag;
import org.cloudbus.cloudsim.Cloudlet;

/**
 * Created by Tao on 2017/11/24.
 */
public class Edge {
    /**���*/
    private Cloudlet src;
    /**�յ�*/
    private Cloudlet dest;
    /**Ȩֵ*/
    private double weight;

    /**
     * ����Ȩֵ��һ����
     * @param src
     * @param dest
     */
    public Edge(Cloudlet src, Cloudlet dest) {
        this(src, dest, 0);
    }

    /**
     * ��Ȩֵ��һ����
     * @param src
     * @param dest
     * @param weight
     */
    public Edge(Cloudlet src, Cloudlet dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     *
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge edge = (Edge)obj;
        boolean srcId = this.getSource().getCloudletId() == edge.getSource().getCloudletId();
        boolean destId = this.getDest().getCloudletId() == edge.getDest().getCloudletId();
        if (srcId && destId) {
            return true;
        }
        return false;
    }
    /**
     * ��ȡ���
     * @return
     */
    public Cloudlet getSource() {
        return this.src;
    }

    /**
     * ��ȡ�յ�
     * @return
     */
    public Cloudlet getDest() {
        return this.dest;
    }

    /**
     * ��ȡȨֵ
     * @return
     */
    public double getWeight() {
        return this.weight;
    }
    /*
     * ����Ȩֵ
     * @see java.lang.Object#toString()
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        String ret = String.format("src : %s , dest : %s , weight : %s", src, dest, weight);
        return ret;
    }

}

