import java.io.File;

public class Main {
    public static void main(String[] args) {
        MultiThreadedFileCopier multiThreadedFileCopier = new MultiThreadedFileCopier();
        File sourceFile = new File("./src/main/resources/Fristfile.xlsx");
        File targetFile = new File("./src/main/resources/Secondfile.xlsx");
        Thread readThread = new Thread(new MyReader(multiThreadedFileCopier,sourceFile));
        Thread writeThread = new Thread(new MyWriter(multiThreadedFileCopier,targetFile));
        readThread.start();
        writeThread.start();
    }
}
