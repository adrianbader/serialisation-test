/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2021.
 */

package hashutil.kryobased;

import static lombok.AccessLevel.PRIVATE;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.hash.Hashing;
import java.io.ByteArrayOutputStream;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = PRIVATE)
public class HashUtil {

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
    Kryo kryo = new Kryo();
    kryo.setRegistrationRequired(false);

    long start = System.currentTimeMillis();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    Output output = new Output(byteArrayOutputStream);
    kryo.writeObject(output, objects);
    byte[] data = byteArrayOutputStream.toByteArray();
    time += System.currentTimeMillis() - start;
    bytes += data.length;

    return Hashing.sha256()
        .hashBytes(data)
        .toString();
  }
}
