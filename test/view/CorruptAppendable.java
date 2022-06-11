package view;

import java.io.IOException;

/**
 * This class represents a testing artifact to test for errors in the view.
 */
public class CorruptAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Corrupt exception.");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Corrupt exception.");
  }

  /**
   * Appends the specified character to this {@code Appendable}.
   *
   * @param c The character to append
   * @return A reference to this {@code Appendable}
   * @throws IOException If an I/O error occurs
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Corrupt exception.");
  }
}
