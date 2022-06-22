package view;

import model.ImageProcessor;
import model.ImageProcessorModel;

public abstract class ViewArtifactsAbstract implements ViewArtifacts {
  ImageProcessor model;

  protected ViewArtifactsAbstract(ImageProcessor model) {
    this.model = model;
  }
}
