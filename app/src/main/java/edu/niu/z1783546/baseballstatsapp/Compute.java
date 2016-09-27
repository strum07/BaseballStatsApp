package edu.niu.z1783546.baseballstatsapp;

/**
 * Created by Sagar Sudhakar on 9/26/2016.
 */
public class Compute {
    private int etext1,etext2,etext3,etext4,etext5;
    private int bases;
    private float slug;

    public Compute(){

    }

    public void setEtext1(int param)
    {
        this.etext1=param;
    }

    public void setEtext2(int param)
    {
        this.etext2=param;
    }

    public void setEtext3(int param)
    {
        this.etext3=param;
    }

    public void setEtext4(int param)
    {
        this.etext4=param;
    }

    public void setEtext5(int param)
    {
        this.etext5=param;
    }

    public void computeValues(){

        bases = (etext1-(etext2+etext3+etext4))+(etext2*2)+(etext3*3)+(etext4*4);
        slug = (float)bases/etext5;
    }

    public float getSlug() {
        return slug;
    }

    public int getBases() {
        return bases;
    }
}
