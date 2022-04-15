package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatedDateTest {


    /**
     * @desc Validates is a given date in the form of day, month and year is valid.
     * @subcontract 31 days in month {
     * @requires (month = = 1 | | month = = 3 | | month = = 5 | | month = = 7 | |
     ** month = = 8 | | month = = 10 | | month = = 12) && 1 <= day <= 31;
     * @ensures \result = true;
     * }
     **/

    @Test
    void validValidatedDateInstantiation31Days() {
        assertInstanceOf(ValidatedDate.class, new ValidatedDate(31, 3, 2022));
    }

    /**
     * @subcontract 30 days in month {
     * @requires (month = = 4 | | month = = 6 | | month = = 9 | | month = = 11) &&
     * 1 <= day <= 30;
     * @ensures \result = true;
     * }
     **/
    @Test
    void validValidatedDateInstantiation30Days() {
        assertInstanceOf(ValidatedDate.class, new ValidatedDate(30, 4, 2022));
    }

    /**
     * @subcontract 29 days in month {
     * @requires month == 2 && 1 <= day <= 29 &&
     * (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
     * @ensures \result = true;
     * }
     *
     **/
    @Test
    void validValidatedDateInstantiation29Days() {
        assertInstanceOf(ValidatedDate.class, new ValidatedDate(29, 2, 2020));
    }

    /**
     * @subcontract 28 days in month {
     * @requires month == 2 && 1 <= day <= 28 &&
     * (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
     * @ensures \result = true;
     * }
     **/
    @Test
    void validValidatedDateInstantiation28Days() {
        assertInstanceOf(ValidatedDate.class, new ValidatedDate(28, 2, 2022));
    }

    /**
     * @subcontract all other cases {
     * @requires no other accepting precondition;
     * @ensures \result = false;
     * }
     */

    @Test
    void invalidValidatedDateInstantiation() {
        assertThrows(IllegalArgumentException.class, () -> new ValidatedDate(31, 2, 2022));
        assertThrows(IllegalArgumentException.class, () -> new ValidatedDate(31, 4, 2022));
    }

    @Test
    void nullValidatedDateInstantiation() {
        assertThrows(NullPointerException.class, () -> new ValidatedDate(null));
    }


}