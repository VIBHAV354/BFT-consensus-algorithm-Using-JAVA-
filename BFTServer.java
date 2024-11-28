import java.util.*;

public class BFTServer {
    private List<Node> nodes;

    public BFTServer(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void proposeValue(int value) {
        List<Integer> proposedValues = new ArrayList<>();
        for (Node node : nodes) {
            int nodeValue = node.generateValue(value);
            proposedValues.add(nodeValue);
            node.storeValue(nodeValue);
        }

        int majorityValue = calculateConsensus(proposedValues);
        System.out.println("Consensus reached on value: " + majorityValue);
    }

    public void toggleMalicious(int nodeId) {
        Node node = nodes.get(nodeId - 1);
        boolean newState = !node.isMalicious();
        node.setMalicious(newState);
        System.out.println("Node " + nodeId + " malicious state: " + newState);
    }

    public void view() {
        for (Node node : nodes) {
            System.out.println("Node " + node.getFilePath() + ": " + node.getReceivedValues());
        }
    }

    private int calculateConsensus(List<Integer> proposedValues) {
        Map<Integer, Integer> valueCount = new HashMap<>();
        for (int value : proposedValues) {
            valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
        }

        int majorityValue = -1;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : valueCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                majorityValue = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return majorityValue;
    }
}
