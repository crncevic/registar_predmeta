/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Petar
 */
public interface IOperation {

    public static final int VRATI_SVE_UDZBENIKE = 1;
    public static final int VRATI_SVE_AUTORE = 2;
    public static final int VRATI_SVE_RECENZENTE = 3;
    public static final int LOGIN = 4;
    public static final int KREIRAJ_UDZBENIK = 5;
    public static final int AZURIRAJ_UDZBENIK = 6;
    public static final int PRONADJI_UDZBENIK_PO_ID = 7;
    public static final int PRONADJI_UDZBENIK_PO_NAZIVU = 8;
    public static final int OBRISI_UDZBENIK = 9;
    public static final int PRONADJI_PREDMET_PO_ID = 10;
    public static final int VRATI_SVE_VRSTE_I_NIVOE_STUDIJA = 11;
    public static final int VRATI_SVE_TIPOVE_NASTAVE = 12;
    public static final int VRATI_SVE_NASTAVNIKE = 13;
    public static final int KREIRAJ_PREDMET = 14;
    public static final int VRATI_ULOGU_UDZBENIK_PO_NAZIVU = 15;
    public static final int VRATI_SVE_PREDMETE = 16;
    public static final int AZURIRAJ_PREDMET = 17;
    public static final int OBRISI_PREDMET = 18;
    public static final int VRATI_SVE_STATUSE = 19;
    public static final int PRONADJI_STATUS_PO_ID = 20;
    public static final int VRATI_SVE_STUDIJSKE_PROGRAME = 21;
    public static final int PRONADJI_STUDIJSKI_PROGRAM_PO_ID = 22;
    public static final int KREIRAJ_PREDMET_NA_STUDIJSKOM_PROGRAMU = 23;
    public static final int VRATI_PREDMETE_ZA_STUDIJSKI_PROGRAM = 24;
    public static final int PRONADJI_PREDMET_NA_STUDIJSKOM_PROGRAMU_ZA_ID = 25;
    public static final int AZURIRAJ_PREDMET_NA_STUDIJSKOM_PROGRAMU = 26;
    public static final int OBRISI_PREDMET_NA_STUDIJSKOM_PROGRAMU = 27;
    public static final int LOGOUT = 28;

}
