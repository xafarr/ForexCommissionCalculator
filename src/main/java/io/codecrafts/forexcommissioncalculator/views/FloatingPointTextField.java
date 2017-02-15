package io.codecrafts.forexcommissioncalculator.views;

import javafx.scene.control.TextField;

/**
 * @author Muhammad Zafar
 *         <p>
 *         http://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
 */
public class FloatingPointTextField extends TextField {

    /**
     * Creates a {@code TextField} with empty text content.
     */
    public FloatingPointTextField() {
        super();
        this.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                this.setText(this.getText().endsWith(".") ||
                        this.getText().isEmpty() ? (this.getText() + "0") : this.getText());
            }
        });
    }

    /**
     * Creates a {@code TextField} with initial text content.
     *
     * @param text A string for text content.
     */
    public FloatingPointTextField(String text) {
        super(text);
    }

    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        if (!validate(this.getText())) {
            this.deletePreviousChar();
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text) {
        return text.matches("^[0-9]+\\.?[0-9]{0,4}$");
    }
}
