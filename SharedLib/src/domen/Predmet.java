/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import db.dao.impl.VrstaINivoStudijaDaoImpl;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Petar
 */
public class Predmet implements OpstiDomenskiObjekat, Serializable {

    private int predmetId;
    private String naziv;
    private int brCasovaPredavanjaNedeljno;
    private int brCasovaVezbiNedeljno;
    private int ostaliCasovi;
    private String drugiObliciNastave;
    private String studijskiIstrazivackiRad;
    private String cilj;
    private String ishod;
    private String uslov;
    private VrstaINivoStudija vrstaINivoStudija;
    private List<TematskaCelina> sadrzajTematskeCeline;
    private List<NastavnikNaPredmetu> nastavnici;
    private String sadrzajTekst;

    private List<Udzbenik> udzbenici;
    private List<PredmetNaStudijskomProgramu> predmetiNaStudijskimProgramima;

    public Predmet() {
    }

    public Predmet(String naziv, int brCasovaPredavanjaNedeljno, int brCasovaVezbiNedeljno, int ostaliCasovi, String drugiObliciNastave, String studijskiIstrazivackiRad, String cilj, String ishod, String uslov, VrstaINivoStudija vrstaINivoStudija, List<TematskaCelina> sadrzajTematskeCeline, List<NastavnikNaPredmetu> nastavnici, String sadrzajTekst, List<Udzbenik> udzbenici, List<PredmetNaStudijskomProgramu> predmetiNaStudijskimProgramima) {
        this.naziv = naziv;
        this.brCasovaPredavanjaNedeljno = brCasovaPredavanjaNedeljno;
        this.brCasovaVezbiNedeljno = brCasovaVezbiNedeljno;
        this.ostaliCasovi = ostaliCasovi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.studijskiIstrazivackiRad = studijskiIstrazivackiRad;
        this.cilj = cilj;
        this.ishod = ishod;
        this.uslov = uslov;
        this.vrstaINivoStudija = vrstaINivoStudija;
        this.sadrzajTematskeCeline = sadrzajTematskeCeline;
        this.nastavnici = nastavnici;
        this.sadrzajTekst = sadrzajTekst;
        this.udzbenici = udzbenici;
        this.predmetiNaStudijskimProgramima = predmetiNaStudijskimProgramima;
    }

    public Predmet(int predmetId, String naziv, int brCasovaPredavanjaNedeljno, int brCasovaVezbiNedeljno, int ostaliCasovi, String drugiObliciNastave, String studijskiIstrazivackiRad, String cilj, String ishod, String uslov, VrstaINivoStudija vrstaINivoStudija, List<TematskaCelina> sadrzajTematskeCeline, List<NastavnikNaPredmetu> nastavnici, String sadrzajTekst, List<Udzbenik> udzbenici, List<PredmetNaStudijskomProgramu> predmetiNaStudijskimProgramima) {
        this.predmetId = predmetId;
        this.naziv = naziv;
        this.brCasovaPredavanjaNedeljno = brCasovaPredavanjaNedeljno;
        this.brCasovaVezbiNedeljno = brCasovaVezbiNedeljno;
        this.ostaliCasovi = ostaliCasovi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.studijskiIstrazivackiRad = studijskiIstrazivackiRad;
        this.cilj = cilj;
        this.ishod = ishod;
        this.uslov = uslov;
        this.vrstaINivoStudija = vrstaINivoStudija;
        this.sadrzajTematskeCeline = sadrzajTematskeCeline;
        this.nastavnici = nastavnici;
        this.sadrzajTekst = sadrzajTekst;
        this.udzbenici = udzbenici;
        this.predmetiNaStudijskimProgramima = predmetiNaStudijskimProgramima;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrCasovaPredavanjaNedeljno() {
        return brCasovaPredavanjaNedeljno;
    }

    public void setBrCasovaPredavanjaNedeljno(int brCasovaPredavanjaNedeljno) {
        this.brCasovaPredavanjaNedeljno = brCasovaPredavanjaNedeljno;
    }

    public int getBrCasovaVezbiNedeljno() {
        return brCasovaVezbiNedeljno;
    }

    public void setBrCasovaVezbiNedeljno(int brCasovaVezbiNedeljno) {
        this.brCasovaVezbiNedeljno = brCasovaVezbiNedeljno;
    }

    public int getOstaliCasovi() {
        return ostaliCasovi;
    }

    public void setOstaliCasovi(int ostaliCasovi) {
        this.ostaliCasovi = ostaliCasovi;
    }

    public String getDrugiObliciNastave() {
        return drugiObliciNastave;
    }

    public void setDrugiObliciNastave(String drugiObliciNastave) {
        this.drugiObliciNastave = drugiObliciNastave;
    }

    public String getStudijskiIstrazivackiRad() {
        return studijskiIstrazivackiRad;
    }

    public void setStudijskiIstrazivackiRad(String studijskiIstrazivackiRad) {
        this.studijskiIstrazivackiRad = studijskiIstrazivackiRad;
    }

    public String getCilj() {
        return cilj;
    }

    public void setCilj(String cilj) {
        this.cilj = cilj;
    }

    public String getIshod() {
        return ishod;
    }

    public void setIshod(String ishod) {
        this.ishod = ishod;
    }

    public String getUslov() {
        return uslov;
    }

    public void setUslov(String uslov) {
        this.uslov = uslov;
    }

    public VrstaINivoStudija getVrstaINivoStudija() {
        return vrstaINivoStudija;
    }

    public void setVrstaINivoStudija(VrstaINivoStudija vrstaINivoStudija) {
        this.vrstaINivoStudija = vrstaINivoStudija;
    }

    public List<TematskaCelina> getSadrzajTematskeCeline() {
        return sadrzajTematskeCeline;
    }

    public void setSadrzajTematskeCeline(List<TematskaCelina> sadrzajTematskeCeline) {
        this.sadrzajTematskeCeline = sadrzajTematskeCeline;
    }

    public String getSadrzajTekst() {
        return sadrzajTekst;
    }

    public void setSadrzajTekst(String sadrzajTekst) {
        this.sadrzajTekst = sadrzajTekst;
    }

    public List<Udzbenik> getUdzbenici() {
        return udzbenici;
    }

    public void setUdzbenici(List<Udzbenik> udzbenici) {
        this.udzbenici = udzbenici;
    }

    public List<PredmetNaStudijskomProgramu> getPredmetiNaStudijskimProgramima() {
        return predmetiNaStudijskimProgramima;
    }

    public void setPredmetiNaStudijskimProgramima(List<PredmetNaStudijskomProgramu> predmetiNaStudijskimProgramima) {
        this.predmetiNaStudijskimProgramima = predmetiNaStudijskimProgramima;
    }

    public List<NastavnikNaPredmetu> getNastavnici() {
        return nastavnici;
    }

    public void setNastavnici(List<NastavnikNaPredmetu> nastavnici) {
        this.nastavnici = nastavnici;
    }

    @Override
    public String toString() {
        return "Predmet{" + "predmetId=" + predmetId + ", naziv=" + naziv + ", brCasovaPredavanjaNedeljno=" + brCasovaPredavanjaNedeljno + ", brCasovaVezbiNedeljno=" + brCasovaVezbiNedeljno + ", ostaliCasovi=" + ostaliCasovi + ", drugiObliciNastave=" + drugiObliciNastave + ", studijskiIstrazivackiRad=" + studijskiIstrazivackiRad + ", cilj=" + cilj + ", ishod=" + ishod + ", uslov=" + uslov + ", vrstaINivoStudija=" + vrstaINivoStudija + ", tematskeCeline=" + sadrzajTematskeCeline + ", udzbenici=" + udzbenici + ", predmetiNaStudijskimProgramima=" + predmetiNaStudijskimProgramima + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "predmet";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'," + getBrCasovaPredavanjaNedeljno() + "," + getBrCasovaVezbiNedeljno() + "," + getOstaliCasovi() + ",'" + getDrugiObliciNastave() + "','" + getStudijskiIstrazivackiRad() + "','" + getCilj() + "','" + getIshod() + "','" + getUslov() + "'," + getVrstaINivoStudija().getVrstaINivoId() + ",'" + getSadrzajTekst() + "'";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Predmet other = (Predmet) obj;
        if (this.predmetId != other.predmetId) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "predmetId=" + getPredmetId();
    }

    @Override
    public String vratiUslovZaNadjiSlogove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String postaviVrednostAtributa() {
        return "naziv='" + getNaziv() + "',br_casova_predavanja_nedeljno=" + getBrCasovaPredavanjaNedeljno() + ",br_casova_vezbi_nedeljno=" + getBrCasovaVezbiNedeljno() + ",ostali_casovi=" + getOstaliCasovi() + ",drugi_oblici_nastave='" + getDrugiObliciNastave() + "',studijski_istrazivacki_rad='" + getStudijskiIstrazivackiRad() + "',cilj='" + getCilj() + "',ishod='" + getIshod() + "',uslov='" + getUslov() + "',vrsta_i_nivo_studija=" + getVrstaINivoStudija().getVrstaINivoId() + ",sadrzaj_tekst='" + getSadrzajTekst() + "'";
    }

    @Override
    public OpstiDomenskiObjekat napraviDomenskiObjekat(ResultSet rs) throws Exception {
        return new Predmet(rs.getInt("predmetId"), rs.getString("naziv"), rs.getInt("br_casova_predavanja_nedeljno"), rs.getInt("br_casova_vezbi_nedeljno"), rs.getInt("ostali_casovi"), rs.getString("drugi_oblici_nastave"), rs.getString("studijski_istrazivacki_rad"), rs.getString("cilj"), rs.getString("ishod"), rs.getString("uslov"), VrstaINivoStudijaDaoImpl
                .getInstance()
                .vratiVrstuINivoStudijaZaId(rs.getInt("vrsta_i_nivo_studija")), null, null, rs.getString("sadrzaj_tekst"), null, null);
    }

    @Override
    public String vratiNaziveAtributaZaKreiraj() {
        return "naziv,br_casova_predavanja_nedeljno,br_casova_vezbi_nedeljno,ostali_casovi,drugi_oblici_nastave,studijski_istrazivacki_rad,cilj,ishod,uslov,vrsta_i_nivo_studija,sadrzaj_tekst";
    }

}
