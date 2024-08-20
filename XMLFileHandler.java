import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.text.SimpleDateFormat;

public class XMLFileHandler implements MyFileHandler {

    @Override
    public void read(MyCollection collection) throws Exception {
        XmlMapper mapper = new XmlMapper();
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
        Employee[] employees = mapper.readValue(new File("src/dataset.xml"), Employee[].class);
        for (Employee employee : employees) {
            collection.add(employee);
        }
    }

    @Override
    public void write(MyCollection collection) throws Exception {
        XmlMapper mapper = new XmlMapper();
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
        Employee[] employees = new Employee[100];
        for (int i = 0; i < 100; i++) {
            employees[i] = collection.get();
        }
        mapper.writeValue(new File("output.xml"), employees);
    }
}