package views;

import java.io.IOException;
import java.util.List;
import com.google.maps.errors.ApiException;
import assignment3webcrawler.KijijiData;

public abstract class DataOutput {
  protected String outputType;
  protected List<KijijiData> data;
  
  public abstract void outputToView() throws ApiException, InterruptedException, IOException;
  public void setOutputType(String outputType) {
    this.outputType = outputType;
  }
  
  public void setData(List<KijijiData> data) {
    this.data = data;
  }
}
