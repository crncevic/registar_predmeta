/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.util.List;

/**
 *
 * @author Petar
 */
public class Predmet implements OpstiDomenskiObjekat {

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
    private String vrstaINivoStudija;
    private List<Obaveza> obaveze;
    private List<StavkaNastave> stavkeNastave;
    private List<Udzbenik> udzbenici;
    private List<PredmetNaStudijskomProgramu> predmetiNaStudijskimProgramima;

    public Predmet() {
    }

    public Predmet(String naziv, int brCasovaPredavanjaNedeljno, int brCasovaVezbiNedeljno, int ostaliCasovi, String drugiObliciNastave, String studijskiIstrazivackiRad, String cilj, String ishod, String uslov, String vrstaINivoStudija, List<Obaveza> obaveze, List<StavkaNastave> stavkeNastave, List<Udzbenik> udzbenici, List<PredmetNaStudijskomProgramu> predmetiNaStudijskimProgramima) {
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
        this.obaveze = obaveze;
        this.stavkeNastave = stavkeNastave;
        this.udzbenici = udzbenici;
        this.predmetiNaStudijskimProgramima = predmetiNaStudijskimProgramima;
    }

    public Predmet(int predmetId, String naziv, int brCasovaPredavanjaNedeljno, int brCasovaVezbiNedeljno, int ostaliCasovi, String drugiObliciNastave, String studijskiIstrazivackiRad, String cilj, String ishod, String uslov, String vrstaINivoStudija, List<Obaveza> obaveze, List<StavkaNastave> stavkeNastave, List<Udzbenik> udzbenici, List<PredmetNaStudijskomProgramu> predmetiNaStudijskimProgramima) {
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
        this.obaveze = obaveze;
        this.stavkeNastave = stavkeNastave;
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

    public String getVrstaINivoStudija() {
        return vrstaINivoStudija;
    }

    public void setVrstaINivoStudija(String vrstaINivoStudija) {
        this.vrstaINivoStudija = vrstaINivoStudija;
    }

    public List<Obaveza> getObaveze() {
        return obaveze;
    }

    public void setObaveze(List<Obaveza> obaveze) {
        this.obaveze = obaveze;
    }

    public List<StavkaNastave> getStavkeNastave() {
        return stavkeNastave;
    }

    public void setStavkeNastave(List<StavkaNastave> stavkeNastave) {
        this.stavkeNastave = stavkeNastave;
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

    @Override
    public String toString() {
        return "Predmet{" + "predmetId=" + predmetId + ", naziv=" + naziv + ", brCasovaPredavanjaNedeljno=" + brCasovaPredavanjaNedeljno + ", brCasovaVezbiNedeljno=" + brCasovaVezbiNedeljno + ", ostaliCasovi=" + ostaliCasovi + ", drugiObliciNastave=" + drugiObliciNastave + ", studijskiIstrazivackiRad=" + studijskiIstrazivackiRad + ", cilj=" + cilj + ", ishod=" + ishod + ", uslov=" + uslov + ", vrstaINivoStudija=" + vrstaINivoStudija + ", obaveze=" + obaveze + ", stavkeNastave=" + stavkeNastave + ", udzbenici=" + udzbenici + ", predmetiNaStudijskimProgramima=" + predmetiNaStudijskimProgramima + '}';
    }

    @Override
    public String vratiImeKlase() {
        return "predmet";
    }

    @Override
    public String vratiVrednostiAtributa() {
        return "'" + getNaziv() + "'," + getBrCasovaPredavanjaNedeljno() + "," + getBrCasovaVezbiNedeljno() + "," + getOstaliCasovi() + ",'" + getDrugiObliciNastave() + "','" + getStudijskiIstrazivackiRad() + "','" + getCilj() + "','" + getIshod() + "','" + getUslov() + "','" + getVrstaINivoStudija() + "'";
    }

}
