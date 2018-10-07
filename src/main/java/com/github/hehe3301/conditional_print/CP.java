package com.github.hehe3301.conditional_print;

/**
 * Created by hehe3 on 10/7/2018.
 */
public class CP {
    public static void cLog(Boolean boolIn, String toPrint)
    {
        if(boolIn==Boolean.TRUE)
        {
            System.out.print(toPrint);
        }
    }

}
