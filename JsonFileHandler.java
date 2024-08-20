import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.text.SimpleDateFormat;

public class JsonFileHandler implements MyFileHandler {

    @Override
    public void read(MyCollection collection) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
        Employee[] employees = mapper.readValue(new File("src/MOCK_DATA.json"), Employee[].class);
        for (Employee employee : employees) {
            collection.add(employee);
        }
    }

    @Override
    public void write(MyCollection collection) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
        Employee[] employees = new Employee[100];
        for (int i = 0; i < 100; i++) {
            employees[i] = collection.get();
        }
        mapper.writeValue(new File("output.json"), employees);
    }
}