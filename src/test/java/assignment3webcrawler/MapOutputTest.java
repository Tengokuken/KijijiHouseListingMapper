package assignment3webcrawler;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.google.maps.errors.ApiException;
import views.MapOutput;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;

public class MapOutputTest {
  private MapOutput moObj;
  private KijijiData kjObj;
  private String expected, actual;


  @Before
  public void SetUp() throws Exception {
    kjObj = mock(KijijiData.class);
    moObj = MapOutput.returnMapOutputInstance();
    expected = actual = "";
  }
  
  @Test
  public void testAddress() throws ApiException, InterruptedException, IOException {
    when(kjObj.getAddress()).thenReturn("Test Address");
  }
}
