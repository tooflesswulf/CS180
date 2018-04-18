import jdk.nashorn.internal.scripts.JO;
import sun.plugin.perf.PluginRollup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 * An add controller of a product inventory application.
 *
 * <p>CS18000 -- Spring 2018 -- Complex GUIs -- Homework</p>
 */
public final class AddController {
    /**
     * The inventory model of this add controller.
     */
    private InventoryModel inventoryModel;

    /**
     * The add view of this add controller.
     */
    private AddView addView;

    /**
     * Constructs a newly allocated {@code AddController} object with the specified inventory model and add view.
     *
     * @param inventoryModel the inventory model of this add controller
     * @param addView the add view of this add controller
     * @throws IllegalArgumentException if the {@code inventoryModel} argument or {@code addView} argument is
     * {@code null}
     */
    public AddController(InventoryModel inventoryModel, AddView addView) throws IllegalArgumentException {
        if (inventoryModel == null) {
            throw new IllegalArgumentException("inventoryModel argument is null");
        } else if (addView == null) {
            throw new IllegalArgumentException("addView argument is null");
        } else {
            this.inventoryModel = inventoryModel;
            this.addView = addView;

            this.addView.getAddButton().addActionListener(e -> this.getAddButtonSemantics());
    
            this.addView.getClearButton().addActionListener(e -> this.getClearButtonSemantics());
        } //end if
    } //AddController

    /**
     * Gets the semantics of an add view's add button.
     */
    private void getAddButtonSemantics() {
        String sku = addView.getSkuTextField().getText();
        String name = addView.getNameTextField().getText();
        String wholesalePrice = addView.getWholesalePriceTextField().getText();
        String retailPrice = addView.getRetailPriceTextField().getText();
        String quantity = addView.getQuantityTextField().getText();
    
        if(sku==null || sku.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "SKU cannot be empty.",
                    "Product inventory", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Product empty = new Product();
        empty.setSku(sku);
        if(inventoryModel.contains(empty)) {
            JOptionPane.showMessageDialog(null,
                    "The product could not be added to the inventory.\n" +
                            "A product with the specified SKU already exists.",
                    "Product inventory", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(name==null || name.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Name cannot be empty.",
                    "Product inventory", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double wPrice, rPrice;
        int q;
        try {
            wPrice = Double.valueOf(wholesalePrice);
            if(wPrice <= 0) throw new NumberFormatException("nonnegative");
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "The specified wholesale price is not a valid number.",
                    "Product inventory", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            rPrice = Double.valueOf(retailPrice);
            if(rPrice <= 0) throw new NumberFormatException("nonnegative");
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "The specified retail price is not a valid number.",
                    "Product inventory", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            q = Integer.valueOf(quantity);
            if(q <= 0) throw new NumberFormatException("nonnegative");
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "The specified quantity is not a valid integer.",
                    "Product inventory", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Product toAdd = new Product(sku, name, wPrice, rPrice, q);
        inventoryModel.add(toAdd);
        JOptionPane.showMessageDialog(null,
                "The product has been added.",
                "Product inventory", JOptionPane.INFORMATION_MESSAGE);
    } //getAddButtonSemantics

    /**
     * Gets the semantics of an add view's clear button.
     */
    private void getClearButtonSemantics() {
        addView.getSkuTextField().setText("");
        addView.getNameTextField().setText("");
        addView.getWholesalePriceTextField().setText("");
        addView.getRetailPriceTextField().setText("");
        addView.getQuantityTextField().setText("");
    } //getClearButtonSemantics

    /**
     * Gets the hash code of this add controller.
     *
     * @return the hash code of this add controller
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = 19 * result + (this.inventoryModel == null ? 0 : this.inventoryModel.hashCode());

        result = 19 * result + (this.addView == null ? 0 : this.addView.hashCode());

        return result;
    } //hashCode

    /**
     * Determines whether or not this add controller is equal to the specified object. {@code true} is returned if and
     * only if the specified object is an instance of {@code AddController}, and its field values are equal to this
     * add controller's.
     *
     * @param anObject the object to be compared
     * @return {@code true}, if this add controller is equal to the specified object, and {@code false} otherwise
     */
    @Override
    public boolean equals(Object anObject) {
        return (anObject instanceof AddController)
                && (this.inventoryModel == null ? ((AddController) anObject).inventoryModel ==  null : this.inventoryModel.equals(((AddController) anObject).inventoryModel))
                && (this.addView == null ? ((AddController) anObject).addView ==  null : this.addView.equals(((AddController) anObject).addView));
    } //equals

    /**
     * Gets a {@code String} representation of this add controller.
     *
     * @return a {@code String} representation of this add controller
     */
    @Override
    public String toString() {
        return String.format("AddController[%s, %s]", this.inventoryModel, this.addView);
    } //toString
}