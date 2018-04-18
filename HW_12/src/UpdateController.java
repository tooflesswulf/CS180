import javax.swing.*;
import java.util.List;
import java.util.Optional;

/**
 * An update controller of a product inventory application.
 *
 * <p>CS18000 -- Spring 2018 -- Complex GUIs -- Homework</p>
 */
public final class UpdateController {
    /**
     * The inventory model of this update controller.
     */
    private InventoryModel inventoryModel;

    /**
     * The update view of this update controller.
     */
    private UpdateView updateView;

    /**
     * Constructs a newly allocated {@code UpdateController} object with the specified inventory model and update view.
     *
     * @param inventoryModel the inventory model of this update controller
     * @param updateView the update view of this update controller
     * @throws IllegalArgumentException if the {@code inventoryModel} argument or {@code updateView} argument is
     * {@code null}
     */
    public UpdateController(InventoryModel inventoryModel, UpdateView updateView) throws IllegalArgumentException {
        if (inventoryModel == null) {
            throw new IllegalArgumentException("inventoryModel argument is null");
        } else if (updateView == null) {
            throw new IllegalArgumentException("updateView argument is null");
        } else {
            this.inventoryModel = inventoryModel;
            this.updateView = updateView;

            this.updateView.getUpdateButton().addActionListener(e -> this.getUpdateButtonSemantics());

            this.updateView.getClearButton().addActionListener(e -> this.getClearButtonSemantics());
        } //end if
    } //UpdateController

    /**
     * Gets the semantics of an update view's update button.
     */
    private void getUpdateButtonSemantics() {
        String sku = updateView.getSkuTextField().getText();
        int choice = updateView.getFieldComboBox().getSelectedIndex();
        String newVal = updateView.getNewFieldValueTextField().getText();
        
        Optional<Product> searchResult = inventoryModel.searchBySku(sku);
        if(!searchResult.isPresent()) {
            JOptionPane.showMessageDialog(null,
                    "A product with the specified SKU could not be found.",
                    "Product inventory", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Product item = searchResult.get();
        
        switch (choice) {
            case 0: //SKU
                if(newVal==null || newVal.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "SKU cannot be empty.",
                            "Product inventory", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Product empty = new Product();
                empty.setSku(newVal);
                if(inventoryModel.contains(empty)) {
                    JOptionPane.showMessageDialog(null,
                            "The product could not be updated to that sku.\n" +
                                    "A product with the specified SKU already exists.",
                            "Product inventory", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                item.setSku(newVal);
                break;
            case 1: //Name
                if(newVal==null || newVal.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Name cannot be empty.",
                            "Product inventory", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                item.setName(newVal);
                break;
            case 2: //Wholesale price
                try {
                    double p=Double.valueOf(newVal);
                    item.setWholesalePrice(p);
                    if(p <= 0) throw new NumberFormatException("nonnegative");
                } catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "The specified price is not a valid number.",
                            "Product inventory", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case 3: //Retail price
                try {
                    double p=Double.valueOf(newVal);
                    item.setRetailPrice(p);
                    if(p <= 0) throw new NumberFormatException("nonnegative");
                } catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "The specified price is not a valid number.",
                            "Product inventory", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case 4: //Quantity
                try {
                    int q = Integer.valueOf(newVal);
                    item.setQuantity(q);
                } catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "The specified quantity is not a valid number.",
                            "Product inventory", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "Please select an update option.",
                        "Product inventory", JOptionPane.ERROR_MESSAGE);
                return;
        }
    
        JOptionPane.showMessageDialog(null,
                "Successfully updated the value.",
                "Product inventory", JOptionPane.INFORMATION_MESSAGE);
    } //getUpdateButtonSemantics

    /**
     * Gets the semantics of an update view's clear button.
     */
    private void getClearButtonSemantics() {
        updateView.getSkuTextField().setText("");
        updateView.getFieldComboBox().setSelectedIndex(-1);
        updateView.getNewFieldValueTextField().setText("");
    } //getClearButtonSemantics

    /**
     * Gets the hash code of this update controller.
     *
     * @return the hash code of this update controller
     */
    @Override
    public int hashCode() {
        int result = 23;

        result = 19 * result + (this.inventoryModel == null ? 0 : this.inventoryModel.hashCode());

        result = 19 * result + (this.updateView == null ? 0 : this.updateView.hashCode());

        return result;
    } //hashCode

    /**
     * Determines whether or not this update controller is equal to the specified object. {@code true} is returned if
     * and only if the specified object is an instance of {@code UpdateController}, and its field values are equal to
     * this update controller's.
     *
     * @param anObject the object to be compared
     * @return {@code true}, if this update controller is equal to the specified object, and {@code false} otherwise
     */
    @Override
    public boolean equals(Object anObject) {
        return (anObject instanceof UpdateController)
                && (this.inventoryModel == null ? ((UpdateController) anObject).inventoryModel ==  null : this.inventoryModel.equals(((UpdateController) anObject).inventoryModel))
                && (this.updateView == null ? ((UpdateController) anObject).updateView ==  null : this.updateView.equals(((UpdateController) anObject).updateView));
    } //equals

    /**
     * Gets a {@code String} representation of this update controller.
     *
     * @return a {@code String} representation of this update controller
     */
    @Override
    public String toString() {
        return String.format("UpdateController[%s, %s]", this.inventoryModel, this.updateView);
    } //toString
}