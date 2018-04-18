import sun.font.TrueTypeFont;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * The view of the vending machine application.
 *
 * <p>Lab 12 -- GUIs</p>
 */
public class VendingMachineView {
    /**
     * The {@code JPanel} of the application.
     */
    private JPanel mainJPanel;

    /**
     * The menu components of the application.
     */
    private JMenuBar mainJMenuBar;
    private JMenu machineJMenu;
    private JMenuItem refillJMenuItem;
    private JMenuItem displayProfitJMenuItem;

    /**
     * The {@code JButtons} the user can press.
     */
    private JButton appleJButton;
    private JButton aubergineJButton;
    private JButton broccoliJButton;
    private JButton cherriesJButton;
    private JButton orangeJButton;
    private JButton pineappleJButton;
    private JButton bananaJButton;
    private JButton lemonJButton;
    private JButton raspberryJButton;
    private JButton strawberryJButton;
    private JButton tomatoJButton;
    private JButton watermelonJButton;

    /**
     * The {@code JLabel} used for item output.
     */
    private JLabel doorJLabel;

    /**
     * Constructs a newly allocated {@code VendingMachineView} object.
     */
    public VendingMachineView() {
        this.mainJMenuBar = new JMenuBar();
        this.machineJMenu = new JMenu("Machine");
        this.refillJMenuItem = new JMenuItem("Refill Item");
        this.displayProfitJMenuItem = new JMenuItem("Display Profit");

        this.machineJMenu.add(this.refillJMenuItem);
        this.machineJMenu.add(this.displayProfitJMenuItem);
        this.mainJMenuBar.add(this.machineJMenu);
        this.doorJLabel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
    } //VendingMachineView

    /*
     * Accessors for the components.
     */

    public JPanel getMainJPanel() {
        return this.mainJPanel;
    } //getMainJPanel

    public JMenuBar getMainJMenuBar() {
        return this.mainJMenuBar;
    } //getMainJMenuBar

    public JMenuItem getRefillJMenuItem() {
        return this.refillJMenuItem;
    } //getRefillJMenuItem

    public JMenuItem getDisplayProfitJMenuItem() {
        return this.displayProfitJMenuItem;
    } //getDisplayProfitJMenuItem

    public JButton getAppleJButton() {
        return this.appleJButton;
    } //getAppleJButton

    public JButton getAubergineJButton() {
        return this.aubergineJButton;
    } //getAubergineJButton

    public JButton getBroccoliJButton() {
        return this.broccoliJButton;
    } //getBroccoliJButton

    public JButton getCherriesJButton() {
        return this.cherriesJButton;
    } //getCherriesJButton

    public JButton getOrangeJButton() {
        return this.orangeJButton;
    } //getOrangeJButton

    public JButton getPineappleJButton() {
        return this.pineappleJButton;
    } //getPineappleJButton

    public JButton getBananaJButton() {
        return this.bananaJButton;
    } //getBananaJButton

    public JButton getLemonJButton() {
        return this.lemonJButton;
    } //getLemonJButton

    public JButton getRaspberryJButton() {
        return this.raspberryJButton;
    } //getRaspberryJButton

    public JButton getStrawberryJButton() {
        return this.strawberryJButton;
    } //getStrawberryJButton

    public JButton getTomatoJButton() {
        return this.tomatoJButton;
    } //getTomatoJButton

    public JButton getWatermelonJButton() {
        return this.watermelonJButton;
    } //getWatermelonJButton

    public JLabel getDoorJLabel() {
        return this.doorJLabel;
    } //getDoorJLabel
    
    public void enableButton(String name) {
        switch(name) {
            case "apple":
                appleJButton.setEnabled(true);
                break;
            case "aubergine":
                aubergineJButton.setEnabled(true);
                break;
            case "broccoli":
                broccoliJButton.setEnabled(true);
                break;
            case "cherries":
                cherriesJButton.setEnabled(true);
                break;
            case "orange":
                orangeJButton.setEnabled(true);
                break;
            case "pineapple":
                pineappleJButton.setEnabled(true);
                break;
            case "banana":
                bananaJButton.setEnabled(true);
                break;
            case "lemon":
                lemonJButton.setEnabled(true);
                break;
            case "raspberry":
                raspberryJButton.setEnabled(true);
                break;
            case "strawberry":
                strawberryJButton.setEnabled(true);
                break;
            case "tomato":
                tomatoJButton.setEnabled(true);
                break;
            case "watermelon":
                watermelonJButton.setEnabled(true);
                break;
        }
    }
}