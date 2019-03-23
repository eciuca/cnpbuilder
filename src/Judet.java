
public enum Judet {
    OLT("28"),
    ALBA("01");

    private String codJudet;

    Judet(String codJudet) {
        this.codJudet = codJudet;
    }

    public String getCodJudet() {
        return codJudet;
    }


    @Override
    public String toString() {
        return this.name() + " " + codJudet;
    }
}