import hashutil.kryobased.HashUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;

public class SerialisationTest {

  @Test
  public void serializeModelAndOutputMeasuredTimes() {
    Set<Model> models = new HashSet<>();
    for (int i = 0; i < 250000; i++) {
      models.add(Model.builder()
          .names(List.of(
              UUID.randomUUID().toString(),
              UUID.randomUUID().toString(),
              UUID.randomUUID().toString(),
              UUID.randomUUID().toString(),
              UUID.randomUUID().toString()))
          .values(Set.of(
              ThreadLocalRandom.current().nextLong(),
              ThreadLocalRandom.current().nextLong(),
              ThreadLocalRandom.current().nextLong(),
              ThreadLocalRandom.current().nextLong(),
              ThreadLocalRandom.current().nextLong(),
              ThreadLocalRandom.current().nextLong()))
          .build());
    }

    System.out.print("kryo based...");
    hashutil.kryobased.HashUtil.hashObjects(models);
    System.out.printf(" time in ms: %d, bytes: %d%n", HashUtil.getTime(), HashUtil.getBytes());

    System.out.print("Jackson based...");
    hashutil.jacksonbased.HashUtil.hashObjects(models);
    System.out.printf(" time in ms: %d, bytes: %d%n", hashutil.jacksonbased.HashUtil.getTime(), hashutil.jacksonbased.HashUtil.getBytes());

    System.out.print("MicroStream based...");
    hashutil.microstreambased.HashUtil.hashObjects(models);
    System.out.printf(" time in ms: %d, bytes: %d%n", hashutil.microstreambased.HashUtil.getTime(), hashutil.microstreambased.HashUtil.getBytes());
  }
}
