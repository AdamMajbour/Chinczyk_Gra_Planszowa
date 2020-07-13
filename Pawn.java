package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pawn
{
    private Image bluePawn, yellowPawn, greenPawn, redPawn;
    public ImageView[] bluePawnView, yellowPawnView, greenPawnView, redPawnView;
    public int[] Yx={20,110,20,110},Yy={15,15,105,105},Gx={830,920,830,920},Gy={15,15,105,105},Rx={830,920,830,920},Ry={825,825,915,915},Bx={20,110,20,110},By={825,825,915,915};

    public Pawn() throws FileNotFoundException
    {
        bluePawn = new Image(new FileInputStream("C:\\Users\\adamm\\IdeaProjects\\JTP_Projekt\\src\\sample\\BluePawn.jpg"));
        yellowPawn = new Image(new FileInputStream("C:\\Users\\adamm\\IdeaProjects\\JTP_Projekt\\src\\sample\\YellowPawn.gif"));
        greenPawn = new Image(new FileInputStream("C:\\Users\\adamm\\IdeaProjects\\JTP_Projekt\\src\\sample\\GreenPawn.gif"));
        redPawn = new Image(new FileInputStream("C:\\Users\\adamm\\IdeaProjects\\JTP_Projekt\\src\\sample\\RedPawn.gif"));

        create();
    }

    public void create()
    {
        yellowPawnView = new ImageView[4];
        for (int i=0;i<4;i++)
        {
            yellowPawnView[i] = new ImageView(yellowPawn);
            yellowPawnView[i].setFitWidth(60);
            yellowPawnView[i].setFitHeight(60);
        }

        yellowPawnView[0].setX(20);
        yellowPawnView[0].setY(15);
        yellowPawnView[1].setX(110);
        yellowPawnView[1].setY(15);
        yellowPawnView[2].setX(20);
        yellowPawnView[2].setY(105);
        yellowPawnView[3].setX(110);
        yellowPawnView[3].setY(105);

        greenPawnView = new ImageView[4];
        for (int i=0;i<4;i++)
        {
            greenPawnView[i] = new ImageView(greenPawn);
            greenPawnView[i].setFitWidth(60);
            greenPawnView[i].setFitHeight(60);
        }

        greenPawnView[0].setX(830);
        greenPawnView[0].setY(15);
        greenPawnView[1].setX(920);
        greenPawnView[1].setY(15);
        greenPawnView[2].setX(830);
        greenPawnView[2].setY(105);
        greenPawnView[3].setX(920);
        greenPawnView[3].setY(105);

        redPawnView = new ImageView[4];
        for (int i=0;i<4;i++)
        {
            redPawnView[i] = new ImageView(redPawn);
            redPawnView[i].setFitWidth(60);
            redPawnView[i].setFitHeight(60);
        }

        redPawnView[0].setX(830);
        redPawnView[0].setY(825);
        redPawnView[1].setX(920);
        redPawnView[1].setY(825);
        redPawnView[2].setX(830);
        redPawnView[2].setY(915);
        redPawnView[3].setX(920);
        redPawnView[3].setY(915);

        bluePawnView = new ImageView[4];
        for (int i=0;i<4;i++)
        {
            bluePawnView[i] = new ImageView(bluePawn);
            bluePawnView[i].setFitWidth(60);
            bluePawnView[i].setFitHeight(60);
        }

        bluePawnView[0].setX(20);
        bluePawnView[0].setY(825);
        bluePawnView[1].setX(110);
        bluePawnView[1].setY(825);
        bluePawnView[2].setX(20);
        bluePawnView[2].setY(915);
        bluePawnView[3].setX(110);
        bluePawnView[3].setY(915);
    }

    public ImageView[] getBluePawnView()
    {
        return bluePawnView;
    }

    public ImageView[] getYellowPawnView()
    {
        return yellowPawnView;
    }

    public ImageView[] getGreenPawnView()
    {
        return greenPawnView;
    }

    public ImageView[] getRedPawnView()
    {
        return redPawnView;
    }
}
