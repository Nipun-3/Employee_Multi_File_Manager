import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVFileHandler implements MyFileHandler {

    @Override
    public void read(MyCollection collection) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("src/mock_data.csv"));
        String line;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        // Skip the header row
        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            try {
                Date dateOfBirth = dateFormat.parse(fields[2]);
                Employee employee = new Employee(fields[0], fields[1], dateOfBirth, Double.parseDouble(fields[3]));
                collection.add(employee);
            } catch (ParseException e) {
                System.out.println("Skipping unparseable date: " + fields[2]);
                // Optionally, log the error or skip the record
            } catch (NumberFormatException e) {
                System.out.println("Skipping improperly formatted number: " + fields[3]);
                // Handle the case where experience is not a valid number
            }
        }
        reader.close();
    }

    @Override
    public void write(MyCollection collection) throws Exception {
        FileWriter writer = new FileWriter("output.csv");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        for (int i = 0; i < 100; i++) {
            Employee employee = collection.get();
            if (employee != null) {
                String dateOfBirth = dateFormat.format(employee.getDateOfBirth());
                writer.write(employee.getFirstName() + "," +
                        employee.getLastName() + "," +
                        dateOfBirth + "," +
                        employee.getExperience() + "\n");
            }
        }
        writer.close();
    }
}