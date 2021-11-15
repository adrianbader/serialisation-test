/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2021.
 */

package hashutil.microstreambased;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * <pre>
 * Provide easy to use hash functions based on SHA-256.
 * Attention: The output may change for same input in future code improvements.
 * Therefore, use it only for hashes that do not need to be long term stable.
 * </pre>
 */
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
