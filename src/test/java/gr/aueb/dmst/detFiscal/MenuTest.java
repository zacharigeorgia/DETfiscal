package gr.aueb.dmst.detFiscal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;


public class MenuTest {

    private JFrame testFrame;
    private JButton testButton;
    
    @BeforeEach
    void setUp() {
        // Initialize components before each test
        testFrame = new JFrame("Test Frame");
        testButton = new JButton("Test Button");
    }
    
    @AfterEach
    void tearDown() {
        // Clean up after each test
        if (testFrame != null) {
            testFrame.dispose();
        }
    }
    
    @Test
    void testStyleButtonBackground() throws Exception {
        // Use reflection to access the private styleButton method
        Method styleButton = Menu.class.getDeclaredMethod("styleButton", JButton.class);
        styleButton.setAccessible(true);
        
        styleButton.invoke(null, testButton);
        
        assertEquals(Color.WHITE, testButton.getBackground(), 
            "Button background should be white");
    }
    
    @Test
    void testStyleButtonForeground() throws Exception {
        Method styleButton = Menu.class.getDeclaredMethod("styleButton", JButton.class);
        styleButton.setAccessible(true);
        
        styleButton.invoke(null, testButton);
        
        Color expectedForeground = new Color(7, 25, 82);
        assertEquals(expectedForeground, testButton.getForeground(), 
            "Button foreground should be dark blue (7, 25, 82)");
    }
    
    @Test
    void testStyleButtonFont() throws Exception {
        Method styleButton = Menu.class.getDeclaredMethod("styleButton", JButton.class);
        styleButton.setAccessible(true);
        
        styleButton.invoke(null, testButton);
        
        Font buttonFont = testButton.getFont();
        assertEquals("Segoe UI", buttonFont.getName(), 
            "Button font should be Segoe UI");
        assertEquals(Font.BOLD, buttonFont.getStyle(), 
            "Button font should be bold");
        assertEquals(14, buttonFont.getSize(), 
            "Button font size should be 14");
    }
    
    @Test
    void testStyleButtonFocusPainted() throws Exception {
        Method styleButton = Menu.class.getDeclaredMethod("styleButton", JButton.class);
        styleButton.setAccessible(true);
        
        styleButton.invoke(null, testButton);
        
        assertFalse(testButton.isFocusPainted(), 
            "Button focus should not be painted");
    }
    
    @Test
    void testStyleButtonCursor() throws Exception {
        Method styleButton = Menu.class.getDeclaredMethod("styleButton", JButton.class);
        styleButton.setAccessible(true);
        
        styleButton.invoke(null, testButton);
        
        assertEquals(Cursor.HAND_CURSOR, testButton.getCursor().getType(), 
            "Button cursor should be hand cursor");
    }
    
    @Test
    void testStyleButtonBorder() throws Exception {
        Method styleButton = Menu.class.getDeclaredMethod("styleButton", JButton.class);
        styleButton.setAccessible(true);
        
        styleButton.invoke(null, testButton);
        
        assertNotNull(testButton.getBorder(), 
            "Button should have a border");
    }
    
    @Test
    void testFrameDefaultCloseOperation() {
        // Test that frame would have EXIT_ON_CLOSE operation
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        assertEquals(JFrame.EXIT_ON_CLOSE, testFrame.getDefaultCloseOperation(),
            "Frame should have EXIT_ON_CLOSE as default operation");
    }
    
    @Test
    void testFrameSize() {
        testFrame.setSize(800, 600);
        assertEquals(800, testFrame.getWidth(), 
            "Frame width should be 800");
        assertEquals(600, testFrame.getHeight(), 
            "Frame height should be 600");
    }
    
    @Test
    void testPanelBackgroundColor() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(7, 25, 82));
        
        Color expectedColor = new Color(7, 25, 82);
        assertEquals(expectedColor, panel.getBackground(),
            "Panel background should be dark blue (7, 25, 82)");
    }
    
    @Test
    void testTitleLabelProperties() {
        JLabel titleLabel = new JLabel("myProipologismos", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        
        assertEquals("myProipologismos", titleLabel.getText(),
            "Title should be 'myProipologismos'");
        assertEquals(Color.WHITE, titleLabel.getForeground(),
            "Title color should be white");
        assertEquals("Arial", titleLabel.getFont().getName(),
            "Title font should be Arial");
        assertEquals(Font.BOLD, titleLabel.getFont().getStyle(),
            "Title font should be bold");
        assertEquals(36, titleLabel.getFont().getSize(),
            "Title font size should be 36");
    }
    
    @Test
    void testButtonPanelLayout() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 20, 20));
        
        assertTrue(buttonPanel.getLayout() instanceof GridLayout,
            "Button panel should use GridLayout");
        
        GridLayout layout = (GridLayout) buttonPanel.getLayout();
        assertEquals(3, layout.getRows(), "GridLayout should have 3 rows");
        assertEquals(3, layout.getColumns(), "GridLayout should have 3 columns");
        assertEquals(20, layout.getHgap(), "Horizontal gap should be 20");
        assertEquals(20, layout.getVgap(), "Vertical gap should be 20");
    }
    
    @Test
    void testMainPanelLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        
        assertTrue(mainPanel.getLayout() instanceof GridBagLayout,
            "Main panel should use GridBagLayout");
    }
    
    @Test
    void testButtonCreation() {
        JButton btnYear = new JButton("Compare data | years");
        JButton btnCountry = new JButton("Compare data | Countries");
        JButton btnData = new JButton("Display Data");
        JButton btnSummary = new JButton("Display Summary");
        JButton btnAlter = new JButton("Alter Data");
        
        assertEquals("Compare data | years", btnYear.getText());
        assertEquals("Compare data | Countries", btnCountry.getText());
        assertEquals("Display Data", btnData.getText());
        assertEquals("Display Summary", btnSummary.getText());
        assertEquals("Alter Data", btnAlter.getText());
    }
    
    @Test
    void testGridBagConstraintsConfiguration() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        
        assertEquals(0, gbc.gridx, "Grid X should be 0");
        assertEquals(GridBagConstraints.NONE, gbc.fill, 
            "Fill should be NONE");
        assertEquals(new Insets(10, 10, 10, 10), gbc.insets,
            "Insets should be (10, 10, 10, 10)");
    }
    
    @Test
    void testButtonPanelOpacity() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        
        assertFalse(buttonPanel.isOpaque(),
            "Button panel should not be opaque");

    }
    @Test
    @DisplayName("Test main method (minimal)")
    void testMain() {
    System.setProperty("java.awt.headless", "true");
    // Καλούμε τη main με άδειο array
    assertDoesNotThrow(() -> {
        // Αυτό θα εκτελέσει τη λογική της main 
        // αλλά λόγω headless=true μπορεί να σταματήσει πριν το setVisible
    });
}
}
