import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class RadarReadings {
    static int pos;
    static float dist;
    static Connection connection = SQL_Connection.connection;

    public static void deleteRows() {
        try {
            SQL_Connection.createSQLConnection();
            String query = "delete from radar";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getData() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        try {
            Thread dataExtractionThread = new Thread(() -> {
                try {
                    Statement statement = connection.createStatement();

                    long lastTimestamp = 0;

                    while (!Thread.interrupted()) {
                        String query = "SELECT pos, dist FROM radar WHERE timestamp_col > ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setLong(1, lastTimestamp);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        // Process the retrieved data
                        while (resultSet.next()) {
                            int position = resultSet.getInt("pos");
                            float distance = resultSet.getFloat("dist");
                            if (!list.contains(position)) {
                                setPos(position);
                                setDist(distance);
                                list.add(position);
                                if (list.size() >= 120) {
                                    list.clear();
                                    list.add(-1);
                                }
                            }
                        }

                        resultSet.close();
                        preparedStatement.close();

                        lastTimestamp = System.currentTimeMillis();

                        Thread.sleep(125);

                    }

                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // Start the thread for data extraction
            dataExtractionThread.start();

            // Wait indefinitely to keep the program running
            dataExtractionThread.join();

            connection.close();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void setPos(int val) {
        pos = val;
    }

    public static int getPosition() {
        return pos;
    }

    private static void setDist(float val) {
        dist = val;
    }

    public static float getDistance() {
        return dist;
    }
}


public class Sensor_Data {
    protected static void radarData() {
        String pythonScript = "python";
        String pythonFile = "src/RadarPosition.py";
        ProcessBuilder pb = new ProcessBuilder(pythonScript, pythonFile);
        try {
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
