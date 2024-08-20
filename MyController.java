public class MyController {
    private MyCollection collection = new MyCollection();

    public void run() throws Exception {
        Thread csvThread = new Thread(() -> {
            try {
                new CSVFileHandler().read(collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread xmlThread = new Thread(() -> {
            try {
                new XMLFileHandler().read(collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread jsonThread = new Thread(() -> {
            try {
                new JsonFileHandler().read(collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        csvThread.start();
        xmlThread.start();
        jsonThread.start();

        try {
            csvThread.join();
            xmlThread.join();
            jsonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count of elements: " + collection.getWriteCounter());
    }

    public void writeData() throws Exception {
        Thread csvThread = new Thread(() -> {
            try {
                new CSVFileHandler().write(collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread xmlThread = new Thread(() -> {
            try {
                new XMLFileHandler().write(collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread jsonThread = new Thread(() -> {
            try {
                new JsonFileHandler().write(collection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        csvThread.start();
        xmlThread.start();
        jsonThread.start();

        try {
            csvThread.join();
            xmlThread.join();
            jsonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        MyController controller = new MyController();
        controller.run();
        controller.writeData();
    }
}
