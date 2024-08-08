import javax.swing.*;

public class Performance_Analysis_View extends JPanel implements Dimensions
{
    Performance_Analysis_View()
    {
        setBounds(0, 0, mainPanelWidth, mainPanelHeight);
        setBackground(null);
        setBorder(null);
        setLayout(null);
        setVisible(false);

        initComponents();
    }
    void initComponents()
    {
        add(Performance_Charts.chartFuelVsCycle);
        add(Performance_Charts.chartFuelVsWeek);
        add(Performance_Charts.chartFuelVsOverbudden);
    }
}
