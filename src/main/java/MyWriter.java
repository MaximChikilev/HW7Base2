import java.io.*;

public class MyWriter implements Runnable {
    private MultiThreadedFileCopier multiThreadedFileCopier;
    private File targetFile;

    public MyWriter(MultiThreadedFileCopier multiThreadedFileCopier, File targetFile) {
        this.multiThreadedFileCopier = multiThreadedFileCopier;
        this.targetFile = targetFile;
    }

    @Override
    public void run() {
        try (OutputStream outputStream = new FileOutputStream(targetFile)) {
            while (!multiThreadedFileCopier.isEndOfFile()){
                outputStream.write(multiThreadedFileCopier.getBuffer());
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
