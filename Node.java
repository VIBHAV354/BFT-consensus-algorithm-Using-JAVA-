import java.io.*;
import java.util.*;

public class Node {
    private List<Integer> receivedValues;
    private String filePath;
    private boolean isMalicious;

    public Node(int nodeId) {
        this.receivedValues = new ArrayList<>();
        this.filePath = "node" + nodeId + ".txt";
        this.isMalicious = false;
    }

    public String getFilePath() {
        return filePath;
    }

    public List<Integer> getReceivedValues() {
        return receivedValues;
    }

    public void addReceivedValue(int value) {
        this.receivedValues.add(value);
        saveToFile(value);
    }

    public void setMalicious(boolean malicious) {
        this.isMalicious = malicious;
    }

    public boolean isMalicious() {
        return isMalicious;
    }

    public void storeValue(int value) {
        this.receivedValues.add(value);
        saveToFile(value);
    }

    public int generateValue(int correctValue) {
        if (isMalicious) {
            return correctValue + (int) (Math.random() * 10 + 1);
        }
        return correctValue;
    }

    private void saveToFile(int value) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(value + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
