package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentageTest {

    /**
     * @desc Validates if the input is within range of 0-100%
     *
     * @subcontract value within valid range {
     *   @requires 0 <= percentage <= 100;
     *   @ensures \result = true;
     * }
     **/
    @Test
    void validPercentageInstantiation() {
        assertInstanceOf(Percentage.class, new Percentage(50));
    }

    @Test
    void edgeCasePercentageInstantiation() {
        assertInstanceOf(Percentage.class, new Percentage(0));
        assertInstanceOf(Percentage.class, new Percentage(100));
    }

    /**
     * @subcontract value out of range low {
     *   @requires percentage < 0;
     *   @ensures \result = false;
     * }
     */
    @Test
    void invalidPercentageInstantiationOutOfRangeLow() {
        assertThrows(IllegalArgumentException.class, () -> new Percentage(-5));

    }
    /**
     * @subcontract value out of range high {
     *   @requires percentage > 100;
     *   @signals \result = false;
     * }
     *
     */
    @Test
    void invalidPercentageInstantiationOutOfRangeHigh() {
        assertThrows(IllegalArgumentException.class, () -> new Percentage(105));
    }

}