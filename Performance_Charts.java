import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PerformanceChartsDummyData {
    List<Integer> cycles;
    List<String> week;
    List<Double> overburden;
    List<Double> fuelConsumed1, fuelConsumed2, fuelConsumed3;
    Random random = new Random();

    // Chart_Fuel_VS_Cycle
    public void initList1() {
        cycles = generateCyclesData(5);
        fuelConsumed1 = generateFuelConsumedData1(cycles.size());
    }

    public List<Integer> generateCyclesData(int count) {
        List<Integer> cycles = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            cycles.add(i);
        }
        return cycles;
    }

    public List<Double> generateFuelConsumedData1(int count) {
        List<Double> fuelConsumption = new ArrayList<>();
        Random random = new Random();

        // Generating random fuel consumption for each cycle
        for (int i = 0; i < count; i++) {
            double consumption = 100 + (200 - 100) * random.nextDouble(); // Generating values between 100 and 200
            fuelConsumption.add(consumption);
        }
        return fuelConsumption;
    }

    // Chart_Fuel_VS_Week
    public void initList2() {
        week = generateWeekData();
        fuelConsumed2 = generateFuelConsumedData2(week.size());
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

    public List<Double> generateFuelConsumedData2(int count) {
        List<Double> fuelConsumption = new ArrayList<>();
        Random random = new Random();

        // Generating random fuel consumption for each cycle
        for (int i = 0; i < count; i++) {
            double consumption = 100 + (200 - 100) * random.nextDouble(); // Generating values between 100 and 200
            fuelConsumption.add(consumption);
        }
        return fuelConsumption;
    }

    // Chart_Overbudden_VS_Time
    public void initList3() {
        overburden = generateOverBurdenData(5);
        fuelConsumed3 = generateFuelConsumedData3(overburden.size());
    }

    public List<Double> generateOverBurdenData(int count) {
        List<Double> overburdenWeights = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            double weight = random.nextDouble(15, 24);
            overburdenWeights.add(weight);
        }
        return overburdenWeights;
    }

    public List<Double> generateFuelConsumedData3(int count) {
        List<Double> fuelConsumption = new ArrayList<>();
        Random random = new Random();

        // Generating random fuel consumption for each cycle
        for (int i = 0; i < count; i++) {
            double consumption = 100 + (200 - 100) * random.nextDouble(); // Generating values between 100 and 200
            fuelConsumption.add(consumption);
        }
        return fuelConsumption;
    }

}

class Chart_Fuel_VS_Cycle extends JPanel implements Dimensions {
    DefaultCategoryDataset dataset;
    JFreeChart chart_fuel_vs_cycle;
    ChartPanel chartPanel;
    String x_axis = "Cycle", y_axis = "Fuel Consumed";
    PerformanceChartsDummyData performanceChartsDummyData;

    Chart_Fuel_VS_Cycle() {
        setBounds(mainChartX, mainChartY, mainChartWidth, mainChartHeight);
        setBackground(Color.WHITE);

        performanceChartsDummyData = new PerformanceChartsDummyData();
        performanceChartsDummyData.initList1();

        createDataset();
        createGraph();
        designGraph();
        addChart();
    }

    void createDataset() {
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < performanceChartsDummyData.cycles.size(); i++) {
            dataset.addValue(performanceChartsDummyData.fuelConsumed1.get(i), "Fuel Consumed", String.valueOf(performanceChartsDummyData.cycles.get(i)));
        }
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Fuel Consumed vs Cycles", x_axis, y_axis, dataset);

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
        chart_fuel_vs_cycle = createChart(dataset);
    }

    private void designGraph() {
        chart_fuel_vs_cycle.setBackgroundPaint(Color.WHITE);
    }

    private void addChart() {
        chartPanel = new ChartPanel(chart_fuel_vs_cycle);
        chartPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(chartPanel);
    }
}

class Chart_Fuel_VS_Week extends JPanel implements Dimensions {
    DefaultCategoryDataset dataset;
    JFreeChart chart_fuel_vs_week;
    ChartPanel chartPanel;
    String x_axis = "Days", y_axis = "Fuel Consumed";
    PerformanceChartsDummyData performanceChartsDummyData;

    Chart_Fuel_VS_Week() {
        setBounds(subChartX1, subChartY, subChartWidth, subChartHeight);
        setBackground(Color.WHITE);

        performanceChartsDummyData = new PerformanceChartsDummyData();
        performanceChartsDummyData.initList2();

        createDataset();
        createGraph();
        designGraph();
        addChart();
    }

    void createDataset() {
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < performanceChartsDummyData.week.size(); i++) {
            dataset.addValue(performanceChartsDummyData.fuelConsumed2.get(i), "Fuel Consumed", String.valueOf(performanceChartsDummyData.week.get(i)));
        }
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Fuel Consumed vs Week", x_axis, y_axis, dataset);

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
        chart_fuel_vs_week = createChart(dataset);
    }

    private void designGraph() {
        chart_fuel_vs_week.setBackgroundPaint(Color.WHITE);
    }

    private void addChart() {
        chartPanel = new ChartPanel(chart_fuel_vs_week);
        chartPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(chartPanel);
    }
}

class Chart_Fuel_VS_Overbudden extends JPanel implements Dimensions {
    DefaultCategoryDataset dataset;
    JFreeChart chart_fuel_vs_overbudden;
    ChartPanel chartPanel;
    String x_axis = "Overbudden", y_axis = "Fuel Consumed";
    PerformanceChartsDummyData performanceChartsDummyData;

    Chart_Fuel_VS_Overbudden() {
        setBounds(subChartX2, subChartY, subChartWidth, subChartHeight);
        setBackground(Color.WHITE);

        performanceChartsDummyData = new PerformanceChartsDummyData();
        performanceChartsDummyData.initList3();

        createDataset();
        createGraph();
        designGraph();
        addChart();
    }


    private void createDataset() {
        dataset = new DefaultCategoryDataset();
        for (int i = 0; i < performanceChartsDummyData.overburden.size(); i++) {
            dataset.addValue(performanceChartsDummyData.fuelConsumed3.get(i), "Fuel Consumed", String.valueOf(performanceChartsDummyData.overburden.get(i)));
        }
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Fuel Consumed vs Overbudden", x_axis, y_axis, dataset);

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
        chart_fuel_vs_overbudden = createChart(dataset);
    }

    private void designGraph() {
        chart_fuel_vs_overbudden.setBackgroundPaint(Color.WHITE);
    }

    private void addChart() {
        chartPanel = new ChartPanel(chart_fuel_vs_overbudden);
        chartPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(chartPanel);
    }
}

public class Performance_Charts {
    static Chart_Fuel_VS_Cycle chartFuelVsCycle = new Chart_Fuel_VS_Cycle();
    static Chart_Fuel_VS_Week chartFuelVsWeek = new Chart_Fuel_VS_Week();
    static Chart_Fuel_VS_Overbudden chartFuelVsOverbudden = new Chart_Fuel_VS_Overbudden();
}
