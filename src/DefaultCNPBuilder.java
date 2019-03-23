import java.time.LocalDate;
import java.util.Random;

public class DefaultCNPBuilder implements ICNPBuilder {

    private int sexSiSecol;
    private LocalDate dataNasterii;
    private Judet judet;

    public int getSexSiSecol() {
        return sexSiSecol;
    }

    public DefaultCNPBuilder withSexSiSecol(int sexSiSecol) {
        this.sexSiSecol = sexSiSecol;
        return this;
    }

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

    public DefaultCNPBuilder withDataNasterii(LocalDate dataNasterii) {
        this.dataNasterii = dataNasterii;
        return this;
    }

    public Judet getJudet() {
        return judet;
    }

    public DefaultCNPBuilder withJudet(Judet judet) {
        this.judet = judet;
        return this;
    }

    @Override
    public CNP creeazaCNP() {
        CNP cnp = new CNP();
        cnp.setSexSiSecol(this.sexSiSecol);
        cnp.setDataNasterii(this.dataNasterii);
        cnp.setJudet(this.judet);
        cnp.setCodPersoana(new Random().nextInt(1000));
        cnp.calculeazaCifraControl();

        return cnp;
    }
}