/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubesolversimulator;

/**
 *
 * @author Akarsh
 */
public class DataBridge {
    public static java.awt.Color mid[];
    public static int center;
    public static void setMedium(int col[])
    {
       mid=new java.awt.Color[9];
       center=col[4];
       for(int i=0;i<9;i++)
       {
           switch(col[i])
           {
               case 0: mid[i]=java.awt.Color.BLUE;
                       break;
               case 1: mid[i]=java.awt.Color.GREEN;
                       break;
               case 2: mid[i]=java.awt.Color.RED;
                       break;
               case 3: mid[i]=java.awt.Color.YELLOW;
                       break;
               case 4: mid[i]=java.awt.Color.ORANGE;
                       break;
               case 5: mid[i]=java.awt.Color.WHITE;
                       break;
           }
           System.out.println(mid[i]);
           setGrid();
       }
    }
    
    public static void setGrid()
    {
        switch(center)
        {
            case 0:for(int i=0,k=0;i<3;i++)
                   {
                       for(int j=0;j<3;j++,k++)
                       {
                           Main.B[i][j]=mid[k];
                       }                       
                   }
                   break;
            case 1:for(int i=0,k=0;i<3;i++)
                   {
                       for(int j=0;j<3;j++,k++)
                       {
                           Main.G[i][j]=mid[k];
                       }                       
                   }
                   break;
            case 2:for(int i=0,k=0;i<3;i++)
                   {
                       for(int j=0;j<3;j++,k++)
                       {
                           Main.R[i][j]=mid[k];
                       }                       
                   }
                   break;
            case 3:for(int i=0,k=0;i<3;i++)
                   {
                       for(int j=0;j<3;j++,k++)
                       {
                           Main.Y[i][j]=mid[k];
                       }                       
                   }
                   break;
            case 4:for(int i=0,k=0;i<3;i++)
                   {
                       for(int j=0;j<3;j++,k++)
                       {
                           Main.O[i][j]=mid[k];
                       }                       
                   }
                   break;
            case 5:for(int i=0,k=0;i<3;i++)
                   {
                       for(int j=0;j<3;j++,k++)
                       {
                           Main.W[i][j]=mid[k];
                       }                       
                   }
                   break;
            default: System.out.println("Error");
        }  
    }
}