/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2021.
 */

package hashutil.microstreambased;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = PRIVATE)
public class HashUtil {

  private static final Serializer serializer = Serializer.get();

  public static Long getTime() {
    return time;
  }

  private static Long time = 0L;

  public static Long getBytes() {
    return bytes;
  }

  private static Long bytes = 0L;

  @SneakyThrows
  public static String hashObjects(Object... objects) {

    long start = System.currentTimeMillis();
    byte[] data = serializer.serialize(objects);
    time += System.currentTimeMillis() - start;
    bytes += data.length;

    return Hashing.sha256()
        .hashBytes(data)
        .toString();
  }
}
