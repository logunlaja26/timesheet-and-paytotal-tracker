import com.lyomann.scheduling.SpringSchedulingConfig;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(classes = { SpringSchedulingConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ScheduledFixedRateExampleIntegrationTest {
    @Test
    public void testScheduledFixedRateAnnotation() throws InterruptedException {
        Thread.sleep(5000);
    }
}
