import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

class GraphicalChartsDummyData {
    List<Integer> cycles;
    List<String> week;
    List<String> time;
    List<Double> overburdenWeights1, overburdenWeights2, overburdenWeights3;
    Random random = new Random();

    // Chart_Overbudden_VS_Cycle
    public void initList1() {
        cycles = generateCyclesData(10);
        overburdenWeights1 = generateOverburdenWeightData1(cycles.size());
    }

    public List<Integer> generateCyclesData(int count) {
        List<Integer> cycles = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            cycles.add(i);
        }
        return cycles;
    }

    public List<Double> generateOverburdenWeightData1(int count) {
        List<Double> overburdenWeights = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            double weight = random.nextDouble(15, 24);
            overburdenWeights.add(weight);
        }
        return overburdenWeights;
    }

    // Chart_Overbudden_VS_Week
    public void initList2() {
        week = generateWeekData();
        overburdenWeights2 = generateOverburdenWeightData2(week.size());
    }

    public List<String> generateWeekData() {
        List<String> week = new ArrayList<>();
        week.add("Monday");
        week.add("Tuesday");
        week.add("Wednesday");
        week.add("Thursday");
        week.add("Friday");
        week.add("Saturday");
        week.add("Sunday");
        return week;
    }

    public List<Double> generateOverburdenWeightData2(int count) {
        List<Double> overburdenWeights = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            double weight = random.nextDouble(15, 24);
            overburdenWeights.add(weight);
        }
        return overburdenWeights;
    }

    // Chart_Overbudden_VS_Time
    public void initList3() {
        time = generateTimeData();
        overburdenWeights3 = generateOverburdenWeightData3(time.size());
    }

    public List<String> generateTimeData() {
        List<String> week = new ArrayList<>();
        week.add("10 A.M. - 11 A.M.");
        week.add("11 A.M. - 12 P.M.");
        week.add("12 P.M. - 01 P.M.");
        week.add("01 P.M. - 02 P.M.");
        week.add("02 P.M. - 03 P.M.");
        return week;
    }

    public List<Double> generateOverburdenWeightData3(int count) {
        List<Double> overburdenWeights = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            double weight = random.nextDouble(15, 24);
            overburdenWeights.add(weight);
        }
        return overburdenWeights;
    }
}

class Chart_Overbudden_VS_Cycle extends JPanel implements Dimensions {
    DefaultCategoryDataset dataset;
    JFreeChart chart_overbudden_vs_cycle;
    ChartPanel chartPanel;
    String x_axis = "Cycle", y_axis = "Overbudden Weight";
    GraphicalChartsDummyData graphicalChartsDummyData;

    Chart_Overbudden_VS_Cycle() {
        setBounds(mainChartX, mainChartY, mainChartWidth, mainChartHeight);
        setBackground(Color.WHITE);

        graphicalChartsDummyData = new GraphicalChartsDummyData();
        graphicalChartsDummyData.initList1();

        createDataset();
        createGraph();
        designGraph();
        addChart();
    }

    void createDataset() {
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < graphicalChartsDummyData.cycles.size(); i++) {
            dataset.addValue(graphicalChartsDummyData.overburdenWeights1.get(i), "Overburden Weight", String.valueOf(graphicalChartsDummyData.cycles.get(i)));
        }
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Overburden Weight vs Cycles", x_axis, y_axis, dataset);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Customizing the bars
        renderer.setSeriesPaint(0, Color.RED); // Set bar color
        renderer.setMaximumBarWidth(0.2); // Set maximum bar width
        renderer.setDrawBarOutline(true); // Draw bar outlines
        renderer.setSeriesOutlinePaint(0, Color.BLACK); // Set outline color
        renderer.setSeriesOutlineStroke(0, new BasicStroke(1)); // Set outline stroke

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); // Set integer ticks for Y-axis

        return chart;
    }

    private void createGraph() {
        chart_overbudden_vs_cycle = createChart(dataset);
    }

    private void designGraph() {
        chart_overbudden_vs_cycle.setBackgroundPaint(Color.WHITE);
    }

    private void addChart() {
        chartPanel = new ChartPanel(chart_overbudden_vs_cycle);
        chartPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(chartPanel);
    }
}

class Chart_Overbudden_VS_Week extends JPanel implements Dimensions {
    DefaultCategoryDataset dataset;
    JFreeChart chart_overbudden_vs_week;
    ChartPanel chartPanel;
    String x_axis = "Days", y_axis = "Overbudden Weight";
    GraphicalChartsDummyData graphicalChartsDummyData;

    void createDataset() {
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < graphicalChartsDummyData.week.size(); i++) {
            dataset.addValue(graphicalChartsDummyData.overburdenWeights2.get(i), "Overburden Weight", graphicalChartsDummyData.week.get(i));
        }
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Overburden Weight vs Week", x_axis, y_axis, dataset);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Customizing the bars
        renderer.setSeriesPaint(0, Color.BLUE); // Set bar color
        renderer.setMaximumBarWidth(0.2); // Set maximum bar width
        renderer.setDrawBarOutline(true); // Draw bar outlines
        renderer.setSeriesOutlinePaint(0, Color.BLACK); // Set outline color
        renderer.setSeriesOutlineStroke(0, new BasicStroke(1)); // Set outline stroke

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); // Set integer ticks for Y-axis

        return chart;
    }

    private void createGraph() {
        chart_overbudden_vs_week = createChart(dataset);
    }

    private void designGraph() {
        chart_overbudden_vs_week.setBackgroundPaint(Color.WHITE);
    }

    private void addChart() {
        chartPanel = new ChartPanel(chart_overbudden_vs_week);
        chartPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(chartPanel);
    }

    Chart_Overbudden_VS_Week() {
        setBounds(subChartX1, subChartY, subChartWidth, subChartHeight);
        setBackground(Color.WHITE);

        graphicalChartsDummyData = new GraphicalChartsDummyData();
        graphicalChartsDummyData.initList2();

        createDataset();
        createGraph();
        designGraph();
        addChart();
    }
}

class Chart_Overbudden_VS_Time extends JPanel implements Dimensions {
    DefaultCategoryDataset dataset;
    JFreeChart chart_overbudden_vs_time;
    ChartPanel chartPanel;
    String x_axis = "Time", y_axis = "Overbudden Weight";
    GraphicalChartsDummyData graphicalChartsDummyData;

    void createDataset() {
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < graphicalChartsDummyData.time.size(); i++) {
            dataset.addValue(graphicalChartsDummyData.overburdenWeights3.get(i), "Overburden Weight", graphicalChartsDummyData.time.get(i));
        }
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Overburden Weight vs Time", x_axis, y_axis, dataset);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Customizing the bars
        renderer.setSeriesPaint(0, Color.GREEN); // Set bar color
        renderer.setMaximumBarWidth(0.2); // Set maximum bar width
        renderer.setDrawBarOutline(true); // Draw bar outlines
        renderer.setSeriesOutlinePaint(0, Color.BLACK); // Set outline color
        renderer.setSeriesOutlineStroke(0, new BasicStroke(1)); // Set outline stroke

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); // Set integer ticks for Y-axis

        return chart;
    }

    private void createGraph() {
        chart_overbudden_vs_time = createChart(dataset);
    }

    private void designGraph() {
        chart_overbudden_vs_time.setBackgroundPaint(Color.WHITE);
    }

    private void addChart() {
        chartPanel = new ChartPanel(chart_overbudden_vs_time);
        chartPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(chartPanel);
    }

    Chart_Overbudden_VS_Time() {
        setBounds(subChartX2, subChartY, subChartWidth, subChartHeight);
        setBackground(Color.WHITE);

        graphicalChartsDummyData = new GraphicalChartsDummyData();
        graphicalChartsDummyData.initList3();

        createDataset();
        createGraph();
        designGraph();
        addChart();
    }
}

public class Graphical_Charts {
    static Chart_Overbudden_VS_Cycle chartOverbuddenVsCycle = new Chart_Overbudden_VS_Cycle();
    static Chart_Overbudden_VS_Week chartOverbuddenVsWeek = new Chart_Overbudden_VS_Week();
    static Chart_Overbudden_VS_Time chartOverbuddenVsTime = new Chart_Overbudden_VS_Time();
}
