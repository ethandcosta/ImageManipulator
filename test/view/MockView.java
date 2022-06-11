package view;

import java.util.Objects;

/**
 * Represents a Mock view for testing purposes.
 */
public class MockView implements ImageProcessorView {
  StringBuilder log;

  public MockView(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void renderMessage(String s) {
    log.append("rendered message" + s);
  }

  @Override
  public void renderImage() {
    log.append("rendered image");
  }

}
