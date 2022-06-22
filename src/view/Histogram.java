package view;


import java.awt.*;
import java.text.NumberFormat;

import javax.swing.*;

import model.ImageProcessor;
import model.ImageProcessorModel;

public class Histogram extends ViewArtifactsAbstract{

  private int[] arrayRed;
  private int[] arrayGreen;
  private int[] arrayBlue;
  private int[] arrayIntensity;

  private JProgressBar barsRed[];
  private JProgressBar barsGreen[];
  private JProgressBar barsBlue[];
  private JProgressBar barsIntensity[];


  private NumberFormat intFormat;

  //constant bin size
  private static final int SIZE = 256;

  public Histogram(ImageProcessor model){
    super(model);

    intFormat = NumberFormat.getIntegerInstance();
    intFormat.setMaximumIntegerDigits(3);

    arrayRed = new int[SIZE];
    arrayGreen = new int[SIZE];
    arrayBlue = new int[SIZE];
    arrayIntensity = new int[SIZE];
    barsRed = new JProgressBar[SIZE];
    barsGreen = new JProgressBar[SIZE];
    barsBlue = new JProgressBar[SIZE];
    barsIntensity = new JProgressBar[SIZE];

    for(int i = 0; i < SIZE; i++){
      arrayRed[i] = 0;
      arrayGreen[i] = 0;
      arrayBlue[i] = 0;
      arrayIntensity[i] = 0;
      barsRed[i] = new JProgressBar(SwingConstants.VERTICAL,0,256);
      barsGreen[i] = new JProgressBar(SwingConstants.VERTICAL,0,256);
      barsBlue[i] = new JProgressBar(SwingConstants.VERTICAL,0,256);
      barsIntensity[i] = new JProgressBar(SwingConstants.VERTICAL,0,256);
      barsRed[i].setBackground(new Color(255,0,0,50));
      barsGreen[i].setBackground(new Color(0,255,0,50));
      barsBlue[i].setBackground(new Color(0,0,255,50));
      barsIntensity[i].setBackground(new Color(255,255,255,50));
    }

    this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    GridBagConstraints c = new GridBagConstraints();
    c.ipadx = c.ipady = 0;
    c.gridwidth = 1;
    c.gridheight = 200;
    c.insets = new Insets(0,0,0,0);
    for (int i=0; i<SIZE; i++)
    {
      c.gridx = i+1;
      c.gridy = 1;

      //c.gridx = 2;
      //c.gridy = i+1;
      add(barsRed[i], c);
      add(barsGreen[i], c);
      add(barsBlue[i], c);
      add(barsIntensity[i], c);
    }
    updateDisplay(this.model);
  }

  /**
   * Updates the Histogram display
   */
  public void updateDisplay(ImageProcessor model) {
    for(int i =0; i < model.getImage().length; i++){
      for(int j = 0; j < model.getImage()[0].length;j++){
        arrayRed[model.getImage()[i][j].getColorValue("r")]+=1;
        arrayGreen[model.getImage()[i][j].getColorValue("g")]+=1;
        arrayBlue[model.getImage()[i][j].getColorValue("b")]+=1;
        arrayIntensity[(int)model.getImage()[i][j].getIntensity()]+=1;
      }
    }
    for (int i=0; i<SIZE; i++)
    {
      barsRed[i].setValue(arrayRed[i]*10);
      barsGreen[i].setValue(arrayGreen[i]*10);
      barsBlue[i].setValue(arrayBlue[i]*10);
      barsIntensity[i].setValue(arrayIntensity[i]*10);
    }
    //add into controller
//    JFrame frame = new JFrame();
//    JPanel panel = new Histogram(new ImageProcessorModel());
//    panel.setOpaque(true);
//
//    frame.setContentPane(panel);
//    frame.pack();
//    frame.setVisible(true);
  }




}
