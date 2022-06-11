package util;

/**
 * This class is a utility class with methods that do not necessarily belong to any one class.
 */
public class Util {

  /**
   * This method checks if the argument being passed is null. If not, the value is returned.
   *
   * @param t   the argument being passed
   * @param <T> the generic type to parameterize the result to be of any type
   * @return the argument given, unmodified, if it is not null
   * @throws IllegalArgumentException if the argument given is null
   */
  public static <T> T checkNullness(T t) throws IllegalArgumentException {
    if (t == null) {
      throw new IllegalArgumentException("One or more argument(s) are null!");
    } else {
      return t;
    }
  }

}