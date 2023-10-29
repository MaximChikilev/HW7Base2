import java.io.File;

public class MultiThreadedFileCopier {
    private boolean status = false;
    long wasCopied = 0;

    private boolean endOfFile = false;
    private byte[] buffer = new byte[4000];

    public MultiThreadedFileCopier() {
    }

    public synchronized void setBuffer(byte[] buffer) {
        for (; status == true; ) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        this.buffer = buffer;
        status = true;
        notifyAll();
    }

    public synchronized byte[] getBuffer() {
        for (; status == false; ) {
            try {
                wait();
            } catch (InterruptedException e) {

            }

        }
        status = false;
        wasCopied = wasCopied + buffer.length;
        notifyAll();
        return this.buffer;
    }

    public void setEndOfFile(boolean endOfFile) {
        this.endOfFile = endOfFile;
    }

    public boolean isEndOfFile() {
        return endOfFile;
    }
}
