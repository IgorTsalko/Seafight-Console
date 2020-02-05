public class Robot extends Player {
    public Robot(String name) {
        super(name);
    }

    public String shotOfRobot() {
        int randomCell = (int) (Math.random() * getField().getAvailableShots().size());
        return getField().getAvailableShots().get(randomCell);
    }
}
