import java.io.*;
import java.util.Arrays;

public class MyReader implements Runnable {
    private MultiThreadedFileCopier multiThreadedFileCopier;
    private File sourceFile;
    private byte[] newBuffer = new byte[4000];
    int volume;
    long copiedBytes = 0;
    double fileSize;

    public MyReader(MultiThreadedFileCopier multiThreadedFileCopier, File sourceFile) {
        this.multiThreadedFileCopier = multiThreadedFileCopier;
        this.sourceFile = sourceFile;
    }

    @Override
    public void run() {
        fileSize = sourceFile.length();
        try (InputStream inputStream = new FileInputStream(sourceFile)) {
            volume = inputStream.read(newBuffer);
            while (volume != -1) {
                copiedBytes += volume;
                System.out.println(String.format("Already copied : %.2f ",(copiedBytes / fileSize)*100)+"%");
                multiThreadedFileCopier.setBuffer(Arrays.copyOf(newBuffer, volume));
                volume = inputStream.read(newBuffer);
            }
            multiThreadedFileCopier.setEndOfFile(true);

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
