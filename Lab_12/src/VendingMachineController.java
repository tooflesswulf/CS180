import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;

/**
 * The controller of the vending machine application.
 *
 * <p>Lab 12 -- GUIs</p>
 */
public class VendingMachineController {
    /**
     * The model of the vending machine application.
     */
    private VendingMachineModel model;

    /**
     * The view of the vending machine application.
     */
    private VendingMachineView view;

    /**
     * Constructs a newly allocated {@code VendingMachineController} object.
     */
    public VendingMachineController() {
        this.model = new VendingMachineModel();
        this.view = new VendingMachineView();

        //TODO implement your code here
        view.getRefillJMenuItem().addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }
    
            @Override
            public void putValue(String key, Object value) {
        
            }
    
            @Override
            public void setEnabled(boolean b) {
        
            }
    
            @Override
            public boolean isEnabled() {
                return false;
            }
    
            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
        
            }
    
            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
        
            }
    
            @Override
            public void actionPerformed(ActionEvent e) {
                String resp = JOptionPane.showInputDialog("Enter the name of the bin to refill:");
                
                if(resp == null) return;
                
                for(Bin b : model.getBins()) {
                    System.out.println(b.getName());
                    if(b.getName().equalsIgnoreCase(resp)) {
                        System.out.println("equality treu");
                        b.refill();
                        view.enableButton(resp.toLowerCase());
                        return;
                    }
                }
            }
        });
        
        view.getDisplayProfitJMenuItem().addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }
    
            @Override
            public void putValue(String key, Object value) {
        
            }
    
            @Override
            public void setEnabled(boolean b) {
        
            }
    
            @Override
            public boolean isEnabled() {
                return false;
            }
    
            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
        
            }
    
            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {
        
            }
    
            @Override
            public void actionPerformed(ActionEvent e) {
//                double prof = model.getProfit();
                JOptionPane.showMessageDialog(null, String.format("Profit:\t%.2f", model.getProfit()));
            }
        });
    
        this.setUpGUI();
    } //VendingMachineController

    /**
     * Sets up the GUI to handle user interaction.
     */
    private void setUpGUI() {
        /*
         * Adds action listeners to the components.
         */

        this.view.getAppleJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Apple");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getAppleJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/apple.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getAubergineJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Aubergine");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getAubergineJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/aubergine.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getBroccoliJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Broccoli");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getBroccoliJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/broccoli.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getCherriesJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Cherries");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getCherriesJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/cherries.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getOrangeJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Orange");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getOrangeJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/orange.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getPineappleJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Pineapple");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getPineappleJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/pineapple.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getBananaJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Banana");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getBananaJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/banana.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getLemonJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Lemon");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getLemonJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/lemon.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getRaspberryJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Raspberry");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getRaspberryJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/raspberry.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getStrawberryJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Strawberry");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getStrawberryJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/strawberry.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getTomatoJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Tomato");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getTomatoJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/tomato.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        this.view.getWatermelonJButton().addActionListener(e -> {
            Optional<Bin> binOptional = this.model.vendItem("Watermelon");

            if (binOptional.isPresent()) {
                if (binOptional.get().isEmpty()) {
                    this.view.getWatermelonJButton().setEnabled(false);
                } //end if
            } //end if

            this.view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/watermelon.png"));
            this.view.getDoorJLabel().revalidate();
            this.view.getDoorJLabel().repaint();

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    Thread.sleep(5000);
                    view.getDoorJLabel().setIcon(new ImageIcon("foodIcons/bars.png"));
                    view.getDoorJLabel().revalidate();
                    view.getDoorJLabel().repaint();
                    return null;
                } //doInBackground
            }.execute();
        });

        /*
         * Sets up and displays the JFrame.
         */

        JFrame frame = new JFrame();
        frame.setJMenuBar(this.view.getMainJMenuBar());
        frame.setContentPane(this.view.getMainJPanel());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    } //setUpGUI

    /**
     * Run your program here.
     */
    public static void main(String[] args) {
        new VendingMachineController();
    } //main
}
