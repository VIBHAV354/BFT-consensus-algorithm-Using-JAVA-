import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            nodes.add(new Node(i));
        }

        BFTServer bftServer = new BFTServer(nodes);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Commands: PROPOSE:<value>, TOGGLE:<nodeId>, VIEW");
            String input = scanner.nextLine();

            if (input.startsWith("PROPOSE:")) {
                int proposedValue = Integer.parseInt(input.split(":")[1]);
                bftServer.proposeValue(proposedValue);
            } else if (input.startsWith("TOGGLE:")) {
                int nodeId = Integer.parseInt(input.split(":")[1]);
                bftServer.toggleMalicious(nodeId);
            } else if (input.equals("VIEW")) {
                bftServer.view();
            }
        }
    }
}
