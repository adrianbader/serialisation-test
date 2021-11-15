import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Model {

  List<String> names;
  Set<Long> values;
}
