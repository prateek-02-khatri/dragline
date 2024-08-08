import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel implements Dimensions
{
    Font menuFont = new Font("Arial", Font.PLAIN,18);
    JLabel dashboard;
    private final int gap = 20;
    private final int componentWidth = menuWidth - (gap*2);
    private final int componentHeight = 30;
    static JToggleButton frontView, rearView, graphicalAnalysis, performanceAnalyse;
    ButtonGroup buttonGroup;
    JButton profile;
    int [] Y = new int[4];

    public Menu()
    {
        setBounds(0,0, menuWidth, menuHeight);
        setLayout(null);
        setBackground(null);

        Y[0] = 125;
        for (int i=1; i<Y.length; i++)
        {
            Y[i] = Y[i-1] + 75;
        }

        buttonGroup = new ButtonGroup();
        initComponent();
        menuButtonFunction();

        add(dashboard);
    }
    private void FrontView(boolean flag)
    {
        frontView.setSelected(flag);
        MainPanel.frontCameraView.setVisible(flag);
    }
    private void RearView(boolean flag)
    {
        rearView.setSelected(flag);
        MainPanel.rearCameraView.setVisible(flag);
        MainPanel.radarView.setVisible(flag);
    }
    private void GraphicalAnalysis(boolean flag)
    {
        graphicalAnalysis.setSelected(flag);
        MainPanel.graphicalAnalysisView.setVisible(flag);
    }
    private void PerformanceAnalysis(boolean flag)
    {
        performanceAnalyse.setSelected(flag);
        MainPanel.performanceAnalysisView.setVisible(flag);
    }
    private void RepaintMainPanel()
    {
        MainPanel.frontCameraView.repaint();
        Main.mainPanel.repaint();
    }
    private void Profile(boolean flag)
    {
        MainPanel.profileView.setVisible(flag);
    }
    private void menuButtonFunction()
    {
        frontView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!frontView.isSelected())
                {
                    // Visible Front Camera Components
                    FrontView(true);

                    // Hide Rear View Components
                    RearView(false);

                    // Hide Graphical Analysis View
                    GraphicalAnalysis(false);

                    // Hide Performance Analysis View
                    PerformanceAnalysis(false);

                    // Hide Profile View
                    Profile(false);

                    // Repaint Panels
                    RepaintMainPanel();
                }
            }
        });
        rearView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!rearView.isSelected())
                {
                    // Hide Front View Components
                    FrontView(false);

                    // Visible Rear View Components
                    RearView(true);

                    // Hide Graphical Analysis View
                    GraphicalAnalysis(false);

                    // Hide Performance Analysis View
                    PerformanceAnalysis(false);

                    // Hide Profile View
                    Profile(false);

                    // Repaint Panels
                    RepaintMainPanel();
                }
            }
        });
        graphicalAnalysis.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!graphicalAnalysis.isSelected())
                {
                    // Hide Front View Components
                    FrontView(false);

                    // Hide Rear View Components
                    RearView(false);

                    // Visible Graphical Analysis View
                    GraphicalAnalysis(true);

                    // Hide Performance Analysis View
                    PerformanceAnalysis(false);

                    // Hide Profile View
                    Profile(false);

                    // Repaint Panels
                    RepaintMainPanel();
                }
            }
        });
        performanceAnalyse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (!performanceAnalyse.isSelected())
                {
                    // Hide Front View Components
                    FrontView(false);

                    // Hide Rear View Components
                    RearView(false);

                    // Hide Graphical Analysis View
                    GraphicalAnalysis(false);

                    // Visible Performance Analysis View
                    PerformanceAnalysis(true);

                    // Hide Profile View
                    Profile(false);

                    // Repaint Panels
                    RepaintMainPanel();
                }
            }
        });
        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide Front View Components
                FrontView(false);

                // Hide Rear View Components
                RearView(false);

                // Hide Graphical Analysis View
                GraphicalAnalysis(false);

                // Hide Performance Analysis View
                PerformanceAnalysis(false);

                // Visible Profile View
                Profile(true);

                // Repaint Panels
                RepaintMainPanel();
            }
        });
    }
    private void initComponent()
    {
        dashboard = new JLabel("   Dashboard");
        dashboard.setBounds(gap,35, componentWidth,30);
        dashboard.setForeground(Color.WHITE);
        dashboard.setFont(new Font("Arial", Font.BOLD, 24));
        dashboard.setIcon(new ImageIcon("src/Icons/dashboard.png"));

        frontView = getButton("Front view", Y[0]);
        add(frontView);

        rearView = getButton("Rear view", Y[1]);
        add(rearView);

        graphicalAnalysis = getButton("Graphical Analysis", Y[2]);
        add(graphicalAnalysis);

        performanceAnalyse = getButton("Performance Analysis", Y[3]);
        add(performanceAnalyse);

        int code = 175;
        profile = new JButton(" Profile");
        profile.setBounds(gap,menuHeight-componentHeight*2,componentWidth,componentHeight);
        profile.setFont(menuFont);
        profile.setForeground(Color.WHITE);
        profile.setBackground(new Color(code,code,code));
        profile.setBorderPainted(false);
        profile.setBorder(null);
        profile.setIcon(new ImageIcon("src/Icons/Profile.png"));
        add(profile);
    }

    private JToggleButton getButton(String text, int y)
    {
        JToggleButton button = new JToggleButton(text);
        button.setBounds(gap,y,componentWidth,componentHeight);
        button.setFont(menuFont);
        button.setForeground(Color.WHITE);
        button.setBackground(null);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setText(text);
        button.setSelected(false);

        button.addChangeListener(e ->
        {
            JToggleButton selectedButton = (JToggleButton) e.getSource();
            if (selectedButton.isSelected()) {
                selectedButton.setForeground(null);
                selectedButton.setBackground(Color.WHITE);
            } else {
                selectedButton.setForeground(Color.WHITE);
                selectedButton.setBackground(null);
            }
        });

        buttonGroup.add(button);

        return button;
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g.setPaint(gradientPaint);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintChildren(graphics);
    }

    public static void main(String[] args) {
        Main.run();
    }
}
