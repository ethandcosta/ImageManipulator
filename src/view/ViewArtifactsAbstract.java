package view;

import model.ImageProcessor;
import model.ImageProcessorModel;

public abstract class ViewArtifactsAbstract implements ViewArtifacts {
  ImageProcessor model;

  protected ViewArtifactsAbstract() {
    this.model = new ImageProcessorModel();
  }
}
