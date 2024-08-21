### Problem Statement

This problem involves creating a multi-threaded Java application that handles `Employee` data across different file formats (CSV, XML, and JSON). The problem is to implement classes, interfaces, and synchronization mechanisms to handle concurrent read and write operations on a shared collection of `Employee` objects.

#### 1. **Employee Class**
- **Fields**:
  - `String firstName`: Employee's first name.
  - `String lastName`: Employee's last name.
  - `Date dateOfBirth`: Employee's date of birth.
  - `double experience`: Employee's years of experience.
- **Purpose**: This class will represent an individual employee's data.

#### 2. **MyCollection Class**
- **Fields**:
  - `Employee[] employees`: An array to store 300 `Employee` objects.
  - `int writeCounter`: Keeps track of the current position in the array where the next `Employee` will be added.
  - `int readCounter`: Keeps track of the current position from which the next `Employee` will be read.
- **Methods**:
  - `synchronized void add(Employee employee)`: Adds an `Employee` object to the `employees` array at the position indicated by `writeCounter` and increments `writeCounter`.
  - `synchronized Employee get()`: Retrieves an `Employee` object from the `employees` array at the position indicated by `readCounter` and increments `readCounter`.
- **Purpose**: This class manages the collection of `Employee` objects, ensuring thread-safe access to the collection during concurrent operations.

#### 3. **MyFileHandler Interface**
- **Methods**:
  - `void read()`: A method for reading data from a file.
  - `void write(Employee employee)`: A method for writing an `Employee` object to a file.
- **Purpose**: This interface standardizes file handling operations for different file formats.

#### 4. **CSVFileHandler, XMLFileHandler, JsonFileHandler Classes**
- These classes implement the `MyFileHandler` interface, each handling a specific file format:
  - **CSVFileHandler**: Handles reading from and writing to CSV files.
  - **XMLFileHandler**: Handles reading from and writing to XML files.
  - **JsonFileHandler**: Handles reading from and writing to JSON files.
- **Purpose**: To provide file-specific implementations of the `read` and `write` methods.

#### 5. **MyController Class**
- **Logic**:
  1. **Thread Creation**:
     - Create three threads:
       - Thread 1 reads from the CSV file using `CSVFileHandler`.
       - Thread 2 reads from the XML file using `XMLFileHandler`.
       - Thread 3 reads from the JSON file using `JsonFileHandler`.
  2. **Reading Data**:
     - Each thread reads records from its respective file and inserts the `Employee` objects into the `MyCollection` instance.
  3. **Thread Synchronization**:
     - The main thread waits for all three threads to finish reading before proceeding.
     - Once all threads finish, the `MyCollection` should have 300 records, and the `writeCounter` should be 300.
  4. **Writing Data**:
     - Create three more threads:
       - Thread 1 writes records back to a new output CSV file.
       - Thread 2 writes records back to a new output XML file.
       - Thread 3 writes records back to a new output JSON file.
     - Each thread reads 100 records from `MyCollection` and writes them to its respective file format, ensuring no duplicates and no more than 100 records per file.
- **Purpose**: This class coordinates the entire process, managing threads and ensuring that the operations are synchronized correctly.
