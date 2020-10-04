package com.github.pkg.square;

public class Square implements SquareValidation {

    private final int X;
    private final int Y;
    private int value;
    private Square greaterThan;
    private Square lessThan;

    /**
     * Construct a Square.
     * @param x - X location on the grid
     * @param y - Y location on the grid
     */
    public Square(int x, int y) {
        X = x;
        Y = y;
        value = 0;
    }

    /**
     * Has this Square been assigned a value?
     * @return boolean - whether the square has a value
     */
    public boolean hasValue() {
        return value != 0;
    }

    /**
     * Returns the Squares value.
     * @return int - the value of the Square
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the Squares value.
     * @param value - the value of the Square
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets the greater than constraint for this Square.
     * @param square - the Square that this Square should have a greater value than.
     */
    public void greaterThan(Square square) {
        this.greaterThan = square;
    }

    /**
     * Sets the less than constraint for this Square.
     * @param square - the Square that this Square should have a lower value than.
     */
    public void lessThan(Square square) {
        this.lessThan = square;
    }

    /**
     * Validates that if the provided value was used as this Square's value that it would not break
     * the less than constraint.
     *
     * @param value - the value to check
     * @return boolean - whether or not the value is valid considering the less than constraint.
     */
    @Override
    public boolean lessThanIsValid(int value) {
        if (this.lessThan == null || !lessThan.hasValue()) {
            return true;
        }

        return value < lessThan.getValue();
    }

    /**
     * Validates that if the provided value was used as this Square's value that it would not break
     * the greater than constraint.
     *
     * @param value - the value to check
     * @return boolean - whether or not the value is valid considering the greater than constraint.
     */
    @Override
    public boolean greaterThanIsValid(int value) {
        if (this.greaterThan == null || !greaterThan.hasValue()) {
            return true;
        }

        return value > greaterThan.getValue();
    }
}
