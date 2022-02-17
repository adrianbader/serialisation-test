/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2021.
 */

package hashutil.jacksonbased;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = PRIVATE)
public class HashUtil {

  private static final ObjectMapper objectMapper = new ObjectMapper();

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
    byte[] data = objectMapper.writeValueAsString(objects).getBytes(StandardCharsets.UTF_8);
    time += System.currentTimeMillis() - start;
    bytes += data.length;

    return Hashing.sha256()
        .hashBytes(data)
        .toString();
  }
}
