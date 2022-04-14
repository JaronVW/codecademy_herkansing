package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZipcodeTest {

    @Test
    void validZipcodeInstantiation() {
        Zipcode zipcode = new Zipcode("1234AA");
        assertInstanceOf(Zipcode.class, zipcode);
    }

    @Test
    void invalidZipcodeInstantiation() {
        assertThrows(IllegalArgumentException.class, () -> new Zipcode("0234AA"));
        assertThrows(IllegalArgumentException.class, () -> new Zipcode("ABCDEF"));
        assertThrows(IllegalArgumentException.class, () -> new Zipcode("123456"));
    }

    @Test
    void nullZipcodeInstantiation() {
        assertThrows(NullPointerException.class, () -> new Zipcode(null));
    }

    @Test
    void zipcodeContainsSpaceOnFifth() {
        assertEquals(" ", String.valueOf(new Zipcode("1234AA").getZipcode().charAt(4)));
    }


}