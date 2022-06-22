package view;

import javax.swing.*;

import model.ImageProcessor;
import model.ImageProcessorModel;

public abstract class ViewArtifactsAbstract extends JPanel implements ViewArtifacts {
  ImageProcessor model;

  protected ViewArtifactsAbstract(ImageProcessor model) {
    this.model = model;
  }
}
