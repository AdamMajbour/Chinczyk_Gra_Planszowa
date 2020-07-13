package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Map
{
    public Circle[] fields;

    public Map()
    {
        fields = new Circle[72];
        int x=50, y=50, r=40, pom=90;
//spawn
        fields[0] = new Circle(x,y,r, Color.YELLOW);
        fields[1] = new Circle(x+90,y,r, Color.YELLOW);
        fields[2] = new Circle(x,y+90,r, Color.YELLOW);
        fields[3] = new Circle(x+90,y+90,r, Color.YELLOW);
        for (int i=0;i<4;i++) fields[i].setStroke(Color.BLACK);

        fields[4] = new Circle(x+810,y,r, Color.GREEN);
        fields[5] = new Circle(x+900,y,r, Color.GREEN);
        fields[6] = new Circle(x+810,y+90,r, Color.GREEN);
        fields[7] = new Circle(x+900,y+90,r, Color.GREEN);
        for (int i=4;i<8;i++) fields[i].setStroke(Color.BLACK);

        fields[8] = new Circle(x+810,y+810,r, Color.RED);
        fields[9] = new Circle(x+900,y+810,r, Color.RED);
        fields[10] = new Circle(x+810,y+900,r, Color.RED);
        fields[11] = new Circle(x+900,y+900,r, Color.RED);
        for (int i=8;i<12;i++) fields[i].setStroke(Color.BLACK);

        fields[12] = new Circle(x,y+810,r, Color.LIGHTBLUE);
        fields[13] = new Circle(x,y+900,r, Color.LIGHTBLUE);
        fields[14] = new Circle(x+90,y+810,r, Color.LIGHTBLUE);
        fields[15] = new Circle(x+90,y+900,r, Color.LIGHTBLUE);
        for (int i=12;i<16;i++) fields[i].setStroke(Color.BLACK);
//start
        fields[16] = new Circle(x,y+360,r, Color.YELLOW);
        fields[17] = new Circle(x+90,y+450,r, Color.YELLOW);
        fields[18] = new Circle(x+180,y+450,r, Color.YELLOW);
        fields[19] = new Circle(x+270,y+450,r, Color.YELLOW);
        fields[20] = new Circle(x+360,y+450,r, Color.YELLOW);
        for (int i=16;i<21;i++) fields[i].setStroke(Color.BLACK);

        fields[21] = new Circle(x+540,y,r, Color.GREEN);
        fields[22] = new Circle(x+450,y+90,r, Color.GREEN);
        fields[23] = new Circle(x+450,y+180,r, Color.GREEN);
        fields[24] = new Circle(x+450,y+270,r, Color.GREEN);
        fields[25] = new Circle(x+450,y+360,r, Color.GREEN);
        for (int i=21;i<26;i++) fields[i].setStroke(Color.BLACK);

        fields[26] = new Circle(x+540,y+450,r, Color.RED);
        fields[27] = new Circle(x+630,y+450,r, Color.RED);
        fields[28] = new Circle(x+720,y+450,r, Color.RED);
        fields[29] = new Circle(x+810,y+450,r, Color.RED);
        fields[30] = new Circle(x+900,y+540,r, Color.RED);
        for (int i=26;i<31;i++) fields[i].setStroke(Color.BLACK);

        fields[31] = new Circle(x+450,y+540,r, Color.LIGHTBLUE);
        fields[32] = new Circle(x+450,y+630,r, Color.LIGHTBLUE);
        fields[33] = new Circle(x+450,y+720,r, Color.LIGHTBLUE);
        fields[34] = new Circle(x+450,y+810,r, Color.LIGHTBLUE);
        fields[35] = new Circle(x+360,y+900,r, Color.LIGHTBLUE);
        for (int i=31;i<36;i++) fields[i].setStroke(Color.BLACK);
//path
        //over yellow
        for (int i=36;i<40;i++)
        {
            fields[i] = new Circle(x+pom,y+360,r, Color.BLACK);
            pom+=90;
        }
        pom = 90;
        //left from green
        for (int i=40;i<44;i++)
        {
            fields[i] = new Circle(x+360,y+360-pom,r, Color.BLACK);
            pom+=90;
        }
        //over green
        fields[44] = new Circle(x+360+90,y,r, Color.BLACK);
        pom = 90;
        //right from green
        for (int i=45;i<49;i++)
        {
            fields[i] = new Circle(x+540,y+pom,r, Color.BLACK);
            pom+=90;
        }
        pom = 90;
        //over red
        for (int i=49;i<53;i++)
        {
            fields[i] = new Circle(x+540+pom,y+360,r, Color.BLACK);
            pom+=90;
        }
        //right form red
        fields[53] = new Circle(x+900,y+450,r, Color.BLACK);
        pom = 90;
        //under red
        for (int i=54;i<58;i++)
        {
            fields[i] = new Circle(x+900-pom,y+540,r, Color.BLACK);
            pom+=90;
        }
        pom = 90;
        //right from blue
        for (int i=58;i<62;i++)
        {
            fields[i] = new Circle(x+540,y+540+pom,r, Color.BLACK);
            pom+=90;
        }
        //under blue
        fields[62] = new Circle(x+450,y+900,r, Color.BLACK);
        pom = 90;
        //left from blue
        for (int i=63;i<67;i++)
        {
            fields[i] = new Circle(x+360,y+900-pom,r, Color.BLACK);
            pom+=90;
        }
        //under yellow
        pom=90;
        for (int i=67;i<71;i++)
        {
            fields[i] = new Circle(x+360-pom,y+540,r, Color.BLACK);
            pom+=90;
        }
        //left from yellow
        fields[71] = new Circle(x,y+450,r, Color.BLACK);
    }

    public Circle[] getFields()
    {
        return fields;
    }
}
