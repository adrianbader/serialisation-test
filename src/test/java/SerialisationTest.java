import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SerialisationTest {

  @org.junit.Test
  public void test() {
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

    hashutil.jacksonbased.HashUtil.hashObjects(models);
    System.out.println(String.format("Jackson based time in ms: %d, bytes: %d", hashutil.jacksonbased.HashUtil.getTime(), hashutil.jacksonbased.HashUtil.getBytes()));

    hashutil.microstreambased.HashUtil.hashObjects(models);
    System.out.println(String.format("MicroStream based time in ms: %d, bytes: %d", hashutil.microstreambased.HashUtil.getTime(), hashutil.microstreambased.HashUtil.getBytes()));
  }
}
