package Domain;

public enum Gender {
    M {
        public String toString() {
            return "M";
        }
    },
    F {
        public String toString() {
            return "F";
        }
    },
    NB {
        public String toString() {
            return "NB";
        }
    };


}
