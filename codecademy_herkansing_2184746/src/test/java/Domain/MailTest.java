package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailTest {


    /**
     * @desc Validates if mailAddress is valid. It should be in the form of:
     *   <at least 1 character>@<at least 1 character>.<at least 1 character>
     * @ensures \result = true;
     *
     * @subcontract no tld part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[1].length < 1;
     *   @ensures \throws IllegalArgumentException
     *   ;
     * }

     */
    @Test
    void validEmailInstantiation() {
        assertInstanceOf(Mail.class, new Mail("email@gmail.com"));
    }

    /**
     * @subcontract no mailbox part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[0].length < 1;
     *   @ensures \throws IllegalArgumentException
     *   ;
     * }
     **/

    @Test
    void invalidEmailInstantiationNoMailbox() {
        assertThrows(IllegalArgumentException.class, () -> new Mail("emailgmail.com"));
    }
    /**
     *
     * @subcontract subdomain-tld delimiter {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".").length > 2;
     *   @ensures \throws IllegalArgumentException
     *   ;
     * }
     **/

    @Test
    void invalidEmailInstantiationNoDelimiterSubdomain() {
        assertThrows(IllegalArgumentException.class, () -> new Mail("email@gmailcom"));
    }
    /**
     * @subcontract no subdomain part {
     *   @requires !mailAddress.contains("@") ||
     *             mailAddress.split("@")[1].split(".")[0].length < 1;
     *   @ensures \throws IllegalArgumentException
     *   ;
     * }
    **/
    @Test
    void invalidEmailInstantiation() {
        assertThrows(IllegalArgumentException.class, () -> new Mail("@gmail.com"));
        assertThrows(IllegalArgumentException.class, () -> new Mail("email@gmail."));
    }


}