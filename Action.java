package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Action extends Thread
{
    public int value, turn, allYmove, allGmove, allRmove, allBmove, slide, Ymax, Gmax, Rmax, Bmax, Yfinish, Gfinish, Rfinish, Bfinish;
    private int[] Ymove = {0,0,0,0}, Gmove = {0,0,0,0}, Rmove = {0,0,0,0}, Bmove = {0,0,0,0}, YslideMarker = {0,0,0,0}, GslideMarker = {0,0,0,0}, RslideMarker = {0,0,0,0}, BslideMarker = {0,0,0,0};
    Button rzutKostka;
    Dice dice;
    Label valueLabel, choose;
    Pawn pawns;
    Group root;
    Label[] statements;

    public Action(Button rzutKostka, Label valueLabel, Pawn pawns, Group root, Label[] statements, Label choose)
    {
        this.rzutKostka = rzutKostka;
        this.valueLabel = valueLabel;
        this.turn = 0;
        this.pawns = pawns;
        this.root = root;
        this.statements = statements;
        this.allYmove = 0;
        this.allGmove = 0;
        this.allRmove = 0;
        this.allBmove = 0;
        this.choose = choose;
        this.slide = 10;
        this.Ymax = 380;
        this.Gmax = 375;
        this.Rmax = 560;
        this.Bmax = 555;
        this.Yfinish = 0;
        this.Gfinish = 0;
        this.Rfinish = 0;
        this.Bfinish = 0;

        dice = new Dice();
    }

    public void run()
    {
        valueLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        statements[0].setVisible(true);
        rzutKostka.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                value = dice.getValue();
                valueLabel.setText(""+value);

                if(value==6)
                {
                    choose.setVisible(true);
                    if(turn%4==0) valueLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                    else if(turn%4==1) valueLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    else if(turn%4==2) valueLabel.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    else if(turn%4==3) valueLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                else if (allYmove!=0 && turn%4==0)
                {
                    choose.setVisible(true);
                    valueLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                else if (allGmove!=0 && turn%4==1)
                {
                    choose.setVisible(true);
                    valueLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                else if (allRmove!=0 && turn%4==2)
                {
                    choose.setVisible(true);
                    valueLabel.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                }
                else if (allBmove!=0 && turn%4==3)
                {
                    choose.setVisible(true);
                    valueLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                }

                if (allYmove==0 && value!=6 && turn%4==0) yellowMove(0);
                else if (allGmove==0 && value!=6 && turn%4==1) greenMove(0);
                else if (allRmove==0 && value!=6 && turn%4==2) redMove(0);
                else if (allBmove==0 && value!=6 && turn%4==3) blueMove(0);
            }
        });
    }

    public void yellowMove(int which)
    {
        valueLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        if (Ymove[which] == 0 && value == 6)
        {
            pawns.yellowPawnView[which].setX(20);
            pawns.yellowPawnView[which].setY(375);
            allYmove++;
            Ymove[which]++;
            choose.setVisible(false);

            for (int i=0;i<4;i++)
            {
                if(i!=which)
                {
                    if(pawns.yellowPawnView[i].getX()==pawns.yellowPawnView[which].getX() && pawns.yellowPawnView[i].getY()==pawns.yellowPawnView[which].getY())
                    {
                        pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()+slide);
                        YslideMarker[which]++;
                    }
                }
            }
        }else if(allYmove>0)
        {
            if(YslideMarker[which]!=0)
            {
                while (YslideMarker[which]!=0)
                {
                    pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()-slide);
                    YslideMarker[which]--;
                }
            }

            for(int i=0;i<value;i++)
            {
                if (pawns.yellowPawnView[which].getY()==465 && pawns.yellowPawnView[which].getX()<470 && pawns.yellowPawnView[which].getX()+90*(value-i)>380) break;
                else if (pawns.yellowPawnView[which].getY()==555 && pawns.yellowPawnView[which].getX()<470 && pawns.yellowPawnView[which].getX()-90*(value-i)<-520) break;
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getY()==375  && pawns.yellowPawnView[which].getX() < 380 && pawns.yellowPawnView[which].getX()>=20) pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()+90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getX()==380  && pawns.yellowPawnView[which].getY() > 15  && pawns.yellowPawnView[which].getY()<=375) pawns.yellowPawnView[which].setY(pawns.yellowPawnView[which].getY()-90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getY()==15   && pawns.yellowPawnView[which].getX() < 560 && pawns.yellowPawnView[which].getX()>=380) pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()+90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getX()==560  && pawns.yellowPawnView[which].getY()<375   && pawns.yellowPawnView[which].getY()>=15) pawns.yellowPawnView[which].setY(pawns.yellowPawnView[which].getY()+90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getY()==375  && pawns.yellowPawnView[which].getX()<920   && pawns.yellowPawnView[which].getX()>=560) pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()+90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getX()==920  && pawns.yellowPawnView[which].getY()<555   && pawns.yellowPawnView[which].getY()>=375) pawns.yellowPawnView[which].setY(pawns.yellowPawnView[which].getY()+90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getY()==555  && pawns.yellowPawnView[which].getX()>560   && pawns.yellowPawnView[which].getX()<=920) pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()-90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getX()==560  && pawns.yellowPawnView[which].getY()<915   && pawns.yellowPawnView[which].getY()>=555) pawns.yellowPawnView[which].setY(pawns.yellowPawnView[which].getY()+90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getY()==915  && pawns.yellowPawnView[which].getX()>380   && pawns.yellowPawnView[which].getX()<=560) pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()-90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getX()==380  && pawns.yellowPawnView[which].getY()>555   && pawns.yellowPawnView[which].getY()<=915) pawns.yellowPawnView[which].setY(pawns.yellowPawnView[which].getY()-90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getY()==555  && pawns.yellowPawnView[which].getX()>20    && pawns.yellowPawnView[which].getX()<=380) pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()-90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getX()==20   && pawns.yellowPawnView[which].getY()>465   && pawns.yellowPawnView[which].getY()<=555) pawns.yellowPawnView[which].setY(pawns.yellowPawnView[which].getY()-90);
                else if (Ymove[which]!=0 && pawns.yellowPawnView[which].getY()==465  && pawns.yellowPawnView[which].getX()<Ymax   && pawns.yellowPawnView[which].getX()>=20) pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()+90);
            }
            Ymove[which]++;
        }

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<4;j++)
            {
                if (pawns.greenPawnView[j].getX()==pawns.yellowPawnView[which].getX() && pawns.greenPawnView[j].getY()==pawns.yellowPawnView[which].getY())
                {
                    if ((pawns.greenPawnView[j].getX()!=560 || pawns.greenPawnView[j].getY()!=15) && Ymove[which]>1)
                    {
                        pawns.greenPawnView[j].setX(pawns.Gx[j]);
                        pawns.greenPawnView[j].setY(pawns.Gy[j]);
                        allGmove--;
                        Gmove[j]=0;
                    }else
                    {
                        pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()+slide);
                        YslideMarker[which]++;
                    }
                }

                if (pawns.redPawnView[j].getX()==pawns.yellowPawnView[which].getX() && pawns.redPawnView[j].getY()==pawns.yellowPawnView[which].getY())
                {
                    if ((pawns.redPawnView[j].getX()!=920 || pawns.redPawnView[j].getY()!=555) && Ymove[which]>1)
                    {
                        pawns.redPawnView[j].setX(pawns.Rx[j]);
                        pawns.redPawnView[j].setY(pawns.Ry[j]);
                        allRmove--;
                        Rmove[j]=0;
                    }else
                    {
                        pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()+slide);
                        YslideMarker[which]++;
                    }
                }

                if (pawns.bluePawnView[j].getX()==pawns.yellowPawnView[which].getX() && pawns.bluePawnView[j].getY()==pawns.yellowPawnView[which].getY())
                {
                    if ((pawns.bluePawnView[j].getX()!=380 || pawns.bluePawnView[j].getY()!=915) && Ymove[which]>1)
                    {
                        pawns.bluePawnView[j].setX(pawns.Bx[j]);
                        pawns.bluePawnView[j].setY(pawns.By[j]);
                        allBmove--;
                        Bmove[j]=0;
                    }else
                    {
                        pawns.yellowPawnView[which].setX(pawns.yellowPawnView[which].getX()+slide);
                        YslideMarker[which]++;
                    }
                }
            }
        }

        if (value != 6)
        {
            statements[0].setVisible(false);
            statements[1].setVisible(true);
            statements[2].setVisible(false);
            statements[3].setVisible(false);
            choose.setVisible(false);
        }
        if (value!=6)
        {
            turn++;

        }
    }

    public void greenMove(int which)
    {
        valueLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        if(Gmove[which]==0 && value==6)
        {
            pawns.greenPawnView[which].setX(560);
            pawns.greenPawnView[which].setY(15);
            allGmove++;
            Gmove[which]++;
            choose.setVisible(false);

            for (int i=0;i<4;i++)
            {
                if(i!=which)
                {
                    if(pawns.greenPawnView[i].getX()==pawns.greenPawnView[which].getX() && pawns.greenPawnView[i].getY()==pawns.greenPawnView[which].getY())
                    {
                        pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()+slide);
                        GslideMarker[which]++;
                    }
                }
            }
        }else if(allGmove>0)
        {
            if(GslideMarker[which]!=0)
            {
                while (GslideMarker[which]!=0)
                {
                    pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()-slide);
                    GslideMarker[which]--;
                }
            }
            for(int i=0;i<value;i++)
            {
                if (pawns.greenPawnView[which].getX()==470 && pawns.greenPawnView[which].getY()<465 && pawns.greenPawnView[which].getY()+90*(value-i)>375) break;
                else if (pawns.greenPawnView[which].getX()==380 && pawns.greenPawnView[which].getY()<465 && pawns.greenPawnView[which].getY()-90*(value-i)<=-525) break;
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getY()==375  && pawns.greenPawnView[which].getX()<375 && pawns.greenPawnView[which].getX()>=15)  pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()+90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getX()==380  && pawns.greenPawnView[which].getY()>15  && pawns.greenPawnView[which].getY()<=375) pawns.greenPawnView[which].setY(pawns.greenPawnView[which].getY()-90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getY()==15   && pawns.greenPawnView[which].getX()<470 && pawns.greenPawnView[which].getX()>=375) pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()+90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getX()==560  && pawns.greenPawnView[which].getY()<375 && pawns.greenPawnView[which].getY()>=15)  pawns.greenPawnView[which].setY(pawns.greenPawnView[which].getY()+90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getY()==375  && pawns.greenPawnView[which].getX()<920 && pawns.greenPawnView[which].getX()>=560) pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()+90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getX()==920  && pawns.greenPawnView[which].getY()<470 && pawns.greenPawnView[which].getY()>=375) pawns.greenPawnView[which].setY(pawns.greenPawnView[which].getY()+90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getY()==555  && pawns.greenPawnView[which].getX()>560 && pawns.greenPawnView[which].getX()<=920) pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()-90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getX()==560  && pawns.greenPawnView[which].getY()<915 && pawns.greenPawnView[which].getY()>=555) pawns.greenPawnView[which].setY(pawns.greenPawnView[which].getY()+90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getY()==915  && pawns.greenPawnView[which].getX()>380 && pawns.greenPawnView[which].getX()<=560) pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()-90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getX()==380  && pawns.greenPawnView[which].getY()>555 && pawns.greenPawnView[which].getY()<=915) pawns.greenPawnView[which].setY(pawns.greenPawnView[which].getY()-90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getY()==555  && pawns.greenPawnView[which].getX()>20  && pawns.greenPawnView[which].getX()<=380) pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()-90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getX()==20   && pawns.greenPawnView[which].getY()>375 && pawns.greenPawnView[which].getY()<=555) pawns.greenPawnView[which].setY(pawns.greenPawnView[which].getY()-90);
                else if (Gmove[which]!=0 && pawns.greenPawnView[which].getX()==470  && pawns.greenPawnView[which].getY()<Gmax && pawns.greenPawnView[which].getY()>=15)  pawns.greenPawnView[which].setY(pawns.greenPawnView[which].getY()+90);
            }
            Gmove[which]++;
        }

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<4;j++)
            {
                if (pawns.yellowPawnView[j].getX()==pawns.greenPawnView[which].getX() && pawns.yellowPawnView[j].getY()==pawns.greenPawnView[which].getY())
                {
                    if ((pawns.yellowPawnView[j].getX()!=20 || pawns.yellowPawnView[j].getY()!=375) && Gmove[which]>1)
                    {
                        pawns.yellowPawnView[j].setX(pawns.Yx[j]);
                        pawns.yellowPawnView[j].setY(pawns.Yy[j]);
                        allYmove--;
                        Ymove[j]=0;
                    }else
                    {
                        pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()+slide);
                        GslideMarker[which]++;
                    }
                }

                if (pawns.redPawnView[j].getX()==pawns.greenPawnView[which].getX() && pawns.redPawnView[j].getY()==pawns.greenPawnView[which].getY())
                {
                    if ((pawns.redPawnView[j].getX()!=920 || pawns.redPawnView[j].getY()!=555) && Gmove[which]>1)
                    {
                        pawns.redPawnView[j].setX(pawns.Rx[j]);
                        pawns.redPawnView[j].setY(pawns.Ry[j]);
                        allRmove--;
                        Rmove[j]=0;
                    }else
                    {
                        pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()+slide);
                        GslideMarker[which]++;
                    }
                }

                if (pawns.bluePawnView[j].getX()==pawns.greenPawnView[which].getX() && pawns.bluePawnView[j].getY()==pawns.greenPawnView[which].getY())
                {
                    if ((pawns.bluePawnView[j].getX()!=380 || pawns.bluePawnView[j].getY()!=915) && Gmove[which]>1)
                    {
                        pawns.bluePawnView[j].setX(pawns.Bx[j]);
                        pawns.bluePawnView[j].setY(pawns.By[j]);
                        allBmove--;
                        Bmove[j]=0;
                    }else
                    {
                        pawns.greenPawnView[which].setX(pawns.greenPawnView[which].getX()+slide);
                        GslideMarker[which]++;
                    }
                }
            }
        }

        if (value != 6)
        {
            statements[0].setVisible(false);
            statements[1].setVisible(false);
            statements[2].setVisible(true);
            statements[3].setVisible(false);
            choose.setVisible(false);
        }
        if (value!=6)
        {
            turn++;

        }
    }

    public void redMove(int which)
    {
        valueLabel.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        if(Rmove[which]==0 && value==6)
        {
            pawns.redPawnView[which].setX(920);
            pawns.redPawnView[which].setY(555);
            allRmove++;
            Rmove[which]++;
            choose.setVisible(false);

            for(int i=0;i<4;i++)
            {
                if(i!=which)
                {
                    if(pawns.redPawnView[i].getX()==pawns.redPawnView[which].getX() && pawns.redPawnView[i].getY()==pawns.redPawnView[which].getY())
                    {
                        pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()+slide);
                        RslideMarker[which]++;
                    }
                }
            }
        }else if(allRmove>0)
        {
            if(RslideMarker[which]!=0)
            {
                while (RslideMarker[which]!=0)
                {
                    pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()-slide);
                    RslideMarker[which]--;
                }
            }
            for (int i=0;i<value;i++)
            {
                if(pawns.redPawnView[which].getY()==465 && pawns.redPawnView[which].getX()>470 && pawns.redPawnView[which].getX()-90*(value-i)<560) break;
                else if(pawns.redPawnView[which].getY()==375 && pawns.redPawnView[which].getX()>470 && pawns.redPawnView[which].getX()+90*(value-i)>=1460) break;
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getY()==375  && pawns.redPawnView[which].getX() < 380 && pawns.redPawnView[which].getX()>=20) pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()+90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getY() > 15 && pawns.redPawnView[which].getX()==380 && pawns.redPawnView[which].getY()<=375) pawns.redPawnView[which].setY(pawns.redPawnView[which].getY()-90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getY()==15 && pawns.redPawnView[which].getX() >= 380 && pawns.redPawnView[which].getX() < 560) pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()+90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getX()==560 && pawns.redPawnView[which].getY()<375 && pawns.redPawnView[which].getY()>=15) pawns.redPawnView[which].setY(pawns.redPawnView[which].getY()+90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getY()==375  && pawns.redPawnView[which].getX() < 920 && pawns.redPawnView[which].getX()>=560) pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()+90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getX()==920 && pawns.redPawnView[which].getY()<465 && pawns.redPawnView[which].getY()>=375) pawns.redPawnView[which].setY(pawns.redPawnView[which].getY()+90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getY()==555  && pawns.redPawnView[which].getX()>560 && pawns.redPawnView[which].getX()<=920) pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()-90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getX()==560 && pawns.redPawnView[which].getY()<915 && pawns.redPawnView[which].getY()>=555) pawns.redPawnView[which].setY(pawns.redPawnView[which].getY()+90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getY()==915  && pawns.redPawnView[which].getX()>380 && pawns.redPawnView[which].getX()<=560) pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()-90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getX()==380 && pawns.redPawnView[which].getY()>555 && pawns.redPawnView[which].getY()<=915) pawns.redPawnView[which].setY(pawns.redPawnView[which].getY()-90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getY()==555  && pawns.redPawnView[which].getX()>20 && pawns.redPawnView[which].getX()<=380) pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()-90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getX()==20 && pawns.redPawnView[which].getY()>375 && pawns.redPawnView[which].getY()<=555) pawns.redPawnView[which].setY(pawns.redPawnView[which].getY()-90);
                else if (Rmove[which]!=0 && pawns.redPawnView[which].getY()==465  && pawns.redPawnView[which].getX()>Rmax && pawns.redPawnView[which].getX()<=920) pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()-90);
            }
            Rmove[which]++;
        }

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<4;j++)
            {
                if (pawns.yellowPawnView[j].getX()==pawns.redPawnView[which].getX() && pawns.yellowPawnView[j].getY()==pawns.redPawnView[which].getY())
                {
                    if ((pawns.yellowPawnView[j].getX()!=20 || pawns.yellowPawnView[j].getY()!=375) && Rmove[which]>1)
                    {
                        pawns.yellowPawnView[j].setX(pawns.Yx[j]);
                        pawns.yellowPawnView[j].setY(pawns.Yy[j]);
                        allYmove--;
                        Ymove[j]=0;
                    }else
                    {
                        pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()+slide);
                        RslideMarker[which]++;
                    }
                }

                if (pawns.greenPawnView[j].getX()==pawns.redPawnView[which].getX() && pawns.greenPawnView[j].getY()==pawns.redPawnView[which].getY())
                {
                    if ((pawns.greenPawnView[j].getX()!=560 || pawns.greenPawnView[j].getY()!=15) && Rmove[which]>1)
                    {
                        pawns.greenPawnView[j].setX(pawns.Gx[j]);
                        pawns.greenPawnView[j].setY(pawns.Gy[j]);
                        allGmove--;
                        Gmove[j]=0;
                    }else
                    {
                        pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()+slide);
                        RslideMarker[which]++;
                    }
                }

                if (pawns.bluePawnView[j].getX()==pawns.redPawnView[which].getX() && pawns.bluePawnView[j].getY()==pawns.redPawnView[which].getY())
                {
                    if ((pawns.bluePawnView[j].getX()!=380 || pawns.bluePawnView[j].getY()!=915) && Rmove[which]>1)
                    {
                        pawns.bluePawnView[j].setX(pawns.Bx[j]);
                        pawns.bluePawnView[j].setY(pawns.By[j]);
                        allBmove--;
                        Bmove[j]=0;
                    }else
                    {
                        pawns.redPawnView[which].setX(pawns.redPawnView[which].getX()+slide);
                        RslideMarker[which]++;
                    }
                }
            }
        }

        if (value != 6)
        {
            statements[0].setVisible(false);
            statements[1].setVisible(false);
            statements[2].setVisible(false);
            statements[3].setVisible(true);
            choose.setVisible(false);
        }
        if (value!=6)
        {
            turn++;

        }
    }

    public void blueMove(int which)
    {
        valueLabel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        if(Bmove[which]==0 && value==6)
        {
            pawns.bluePawnView[which].setX(380);
            pawns.bluePawnView[which].setY(915);
            allBmove++;
            Bmove[which]++;
            choose.setVisible(false);

            for(int i=0;i<4;i++)
            {
                if(i!=which)
                {
                    if(pawns.bluePawnView[i].getX()==pawns.bluePawnView[which].getX() && pawns.bluePawnView[i].getY()==pawns.bluePawnView[which].getY())
                    {
                        pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()+slide);
                        BslideMarker[which]++;
                    }
                }
            }
        }else if(allBmove>0)
        {
            if(BslideMarker[which]!=0)
            {
                while (BslideMarker[which]!=0)
                {
                    pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()-slide);
                    BslideMarker[which]--;
                }
            }
            for (int i=0;i<value;i++)
            {
                if (pawns.bluePawnView[which].getX()==470 && pawns.bluePawnView[which].getY()>465 && pawns.bluePawnView[which].getY()-90*(value-i)<555) break;
                else if (pawns.bluePawnView[which].getX()==560 && pawns.bluePawnView[which].getY()>465 && pawns.bluePawnView[which].getY()+90*(value-i)>=1455) break;
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getY()==375  && pawns.bluePawnView[which].getX() < 380 && pawns.bluePawnView[which].getX()>=20) pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()+90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getY() > 15 && pawns.bluePawnView[which].getX()==380 && pawns.bluePawnView[which].getY()<=375) pawns.bluePawnView[which].setY(pawns.bluePawnView[which].getY()-90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getY()==15 && pawns.bluePawnView[which].getX() >= 380 && pawns.bluePawnView[which].getX() < 560) pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()+90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getX()==560 && pawns.bluePawnView[which].getY()<375 && pawns.bluePawnView[which].getY()>=15) pawns.bluePawnView[which].setY(pawns.bluePawnView[which].getY()+90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getY()==375  && pawns.bluePawnView[which].getX() < 920 && pawns.bluePawnView[which].getX()>=560) pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()+90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getX()==920 && pawns.bluePawnView[which].getY()<555 && pawns.bluePawnView[which].getY()>=375) pawns.bluePawnView[which].setY(pawns.bluePawnView[which].getY()+90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getY()==555  && pawns.bluePawnView[which].getX()>560 && pawns.bluePawnView[which].getX()<=920) pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()-90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getX()==560 && pawns.bluePawnView[which].getY()<915 && pawns.bluePawnView[which].getY()>=555) pawns.bluePawnView[which].setY(pawns.bluePawnView[which].getY()+90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getY()==915  && pawns.bluePawnView[which].getX()>470 && pawns.bluePawnView[which].getX()<=560) pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()-90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getX()==380 && pawns.bluePawnView[which].getY()>555 && pawns.bluePawnView[which].getY()<=915) pawns.bluePawnView[which].setY(pawns.bluePawnView[which].getY()-90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getY()==555  && pawns.bluePawnView[which].getX()>20 && pawns.bluePawnView[which].getX()<=380) pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()-90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getX()==20 && pawns.bluePawnView[which].getY()>375 && pawns.bluePawnView[which].getY()<=555) pawns.bluePawnView[which].setY(pawns.bluePawnView[which].getY()-90);
                else if (Bmove[which]!=0 && pawns.bluePawnView[which].getX()==470 && pawns.bluePawnView[which].getY()>Bmax && pawns.bluePawnView[which].getY()<=915) pawns.bluePawnView[which].setY(pawns.bluePawnView[which].getY()-90);
            }
            Bmove[which]++;
        }

        for (int i=0;i<3;i++)
        {
            for (int j=0;j<4;j++)
            {
                if (pawns.yellowPawnView[j].getX()==pawns.bluePawnView[which].getX() && pawns.yellowPawnView[j].getY()==pawns.bluePawnView[which].getY())
                {
                    if ((pawns.yellowPawnView[j].getX()!=20 || pawns.yellowPawnView[j].getY()!=375) && Bmove[which]>1)
                    {
                        pawns.yellowPawnView[j].setX(pawns.Yx[j]);
                        pawns.yellowPawnView[j].setY(pawns.Yy[j]);
                        allYmove--;
                        Ymove[j]=0;
                    }else
                    {
                        pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()+slide);
                        BslideMarker[which]++;
                    }
                }

                if (pawns.greenPawnView[j].getX()==pawns.bluePawnView[which].getX() && pawns.greenPawnView[j].getY()==pawns.bluePawnView[which].getY())
                {
                    if ((pawns.greenPawnView[j].getX()!=560 || pawns.greenPawnView[j].getY()!=15) && Bmove[which]>1)
                    {
                        pawns.greenPawnView[j].setX(pawns.Gx[j]);
                        pawns.greenPawnView[j].setY(pawns.Gy[j]);
                        allGmove--;
                        Gmove[j]=0;
                    }else
                    {
                        pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()+slide);
                        BslideMarker[which]++;
                    }
                }

                if (pawns.redPawnView[j].getX()==pawns.bluePawnView[which].getX() && pawns.redPawnView[j].getY()==pawns.bluePawnView[which].getY())
                {
                    if ((pawns.redPawnView[j].getX()!=920 || pawns.redPawnView[j].getY()!=555) && Bmove[which]>1)
                    {
                        pawns.redPawnView[j].setX(pawns.Rx[j]);
                        pawns.redPawnView[j].setY(pawns.Ry[j]);
                        allRmove--;
                        Rmove[j]=0;
                    }else
                    {
                        pawns.bluePawnView[which].setX(pawns.bluePawnView[which].getX()+slide);
                        BslideMarker[which]++;
                    }
                }
            }
        }

        if (value != 6)
        {
            statements[0].setVisible(true);
            statements[1].setVisible(false);
            statements[2].setVisible(false);
            statements[3].setVisible(false);
            choose.setVisible(false);
        }
        if (value!=6)
        {
            turn++;

        }
    }

    public void play(int which)
    {
        if (turn%4==0) yellowMove(which);
        else if (turn%4==1) greenMove(which);
        else if (turn%4==3) blueMove(which);
        else if (turn%4==2) redMove(which);
        choose.setVisible(false);
    }
}