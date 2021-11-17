import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class GetPortFromSettingsTest {
    Server server = new Server();
    File file = new File("D://settings.txt");

    @Test
    void notGetPort() {
        int result = server.getPortFromSettings(file);
        assertNotEquals(4444, result);
    }
    @Test
    void getPort() {
        int result = server.getPortFromSettings(file);
        assertEquals(25623, result);
    }
}
