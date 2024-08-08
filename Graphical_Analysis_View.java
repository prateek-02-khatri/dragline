import javax.swing.*;

public class Graphical_Analysis_View extends JPanel implements Dimensions
{
    Graphical_Analysis_View()
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
        add(Graphical_Charts.chartOverbuddenVsCycle);
        add(Graphical_Charts.chartOverbuddenVsWeek);
        add(Graphical_Charts.chartOverbuddenVsTime);
    }
}
