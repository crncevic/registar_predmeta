package thread;


import domen.OsobaUVeziSaUdzbenikom;
import domen.Korisnik;
import domen.Nastavnik;
import domen.Predmet;
import domen.PredmetNaStudijskomProgramu;
import domen.Status;
import domen.StudijskiProgram;
import domen.TipNastave;
import domen.Udzbenik;
import domen.UlogaUdzbenik;
import domen.VrstaINivoStudija;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import session.Session;
import so.ApstraktnaGenerickaOperacija;
import so.AzurirajPredmet;
import so.AzurirajPredmetNaStudisjkomProgramu;
import so.AzurirajUdzbenik;
import so.KreirajPredmet;
import so.KreirajPredmetNaStudijskomProgramu;
import so.KreirajUdzbenik;
import so.ObrisiPredmet;
import so.ObrisiPredmetNastudijskomProgramu;
import so.ObrisiUdzbenik;
import so.VratiKorisnika;
import so.VratiNastavnikaZaId;
import so.VratiOsobeZaUdzbenik;
import so.VratiPredmetNaStudijskomPragramuZaId;
import so.VratiPredmetZaId;
import so.VratiPredmeteZaStudijskiProgram;
import so.VratiStatusZaId;
import so.VratiStudijskiProgramZaId;
import so.VratiSveNastavnike;
import so.VratiSvePredmete;
import so.VratiSveStatuse;
import so.VratiSveStudijskePrograme;
import so.VratiSveTipoveNastave;
import so.VratiSveUdzbenike;
import so.VratiSveVrsteStudija;
import so.VratiUdzbenikZaId;
import so.VratiUdzbenikZaNaziv;
import so.VratiUloguNaUdzbenikuZaNaziv;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;
import transfer.util.IOperation;
import transfer.util.IStatus;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Petar
 */
public class NitKlijent extends Thread {

    Socket socket;

    public NitKlijent(Socket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                //prihvati zahtev od klijenta
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                RequestObject requestObject = (RequestObject) input.readObject();
                ResponseObject responseObject = new ResponseObject();
                //obradi zahtev
                switch (requestObject.getOperation()) {
                    case IOperation.VRATI_SVE_UDZBENIKE:
                        try {
                            ApstraktnaGenerickaOperacija vratiSveUdzbenike = new VratiSveUdzbenike();
                            List<Udzbenik> udzbenici = (List<Udzbenik>) vratiSveUdzbenike.opsteIzvrsenje((Udzbenik) requestObject.getData());
                            responseObject.setData(udzbenici);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.KREIRAJ_UDZBENIK:
                        try {
                            ApstraktnaGenerickaOperacija kreirajUdzbenik = new KreirajUdzbenik();
                            Udzbenik kreiranUdzbenik = (Udzbenik) kreirajUdzbenik.opsteIzvrsenje((Udzbenik) requestObject.getData());
                            responseObject.setData(kreiranUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.AZURIRAJ_UDZBENIK:
                        try {
                            ApstraktnaGenerickaOperacija azurirajUdzbenik = new AzurirajUdzbenik();
                            Udzbenik azuriranUdzbenik = (Udzbenik) azurirajUdzbenik.opsteIzvrsenje((Udzbenik) requestObject.getData());
                            responseObject.setData(azuriranUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_UDZBENIK_PO_ID:
                        try {
                            ApstraktnaGenerickaOperacija vratiUdzbenikZaId = new VratiUdzbenikZaId();

                            Udzbenik udzbenikFromDb = (Udzbenik) vratiUdzbenikZaId.opsteIzvrsenje((Udzbenik) requestObject.getData());
                            responseObject.setData(udzbenikFromDb);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_UDZBENIK_PO_NAZIVU:
                        try {
                            ApstraktnaGenerickaOperacija vratiUdzbenikZaNaziv = new VratiUdzbenikZaNaziv();
                            Udzbenik udzbenikFromDb = (Udzbenik) vratiUdzbenikZaNaziv.opsteIzvrsenje((Udzbenik) requestObject.getData());
                            responseObject.setData(udzbenikFromDb);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.OBRISI_UDZBENIK:
                        try {
                            ApstraktnaGenerickaOperacija obrisiUdzbenik = new ObrisiUdzbenik();
                            Udzbenik obrisanUdzbenik = (Udzbenik) obrisiUdzbenik.opsteIzvrsenje((Udzbenik) requestObject.getData());
                            responseObject.setData(obrisanUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_AUTORE:
                        try {
                            ApstraktnaGenerickaOperacija vratiSveOsobeZaUdzbenik = new VratiOsobeZaUdzbenik();
                            List<OsobaUVeziSaUdzbenikom> osobe = (List<OsobaUVeziSaUdzbenikom>) vratiSveOsobeZaUdzbenik.opsteIzvrsenje((Udzbenik) requestObject.getData());
                            List<OsobaUVeziSaUdzbenikom> autori = new ArrayList<>();
                            for (OsobaUVeziSaUdzbenikom ouvsu : osobe) {
                                if (ouvsu.getUlogaUdzbenik().getNaziv().equalsIgnoreCase("autor")) {
                                    autori.add(ouvsu);
                                }
                            }
                            responseObject.setData(autori);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.VRATI_SVE_RECENZENTE:
                        try {
                            ApstraktnaGenerickaOperacija vratiOsobeZaUdzbenik = new VratiOsobeZaUdzbenik();
                            List<OsobaUVeziSaUdzbenikom> osobe = (List<OsobaUVeziSaUdzbenikom>) vratiOsobeZaUdzbenik.opsteIzvrsenje((Udzbenik) requestObject.getData());
                            List<OsobaUVeziSaUdzbenikom> recenzenti = new ArrayList<>();
                            for (OsobaUVeziSaUdzbenikom ouvsu : osobe) {
                                if (ouvsu.getUlogaUdzbenik().getNaziv().equalsIgnoreCase("recenzent")) {
                                    recenzenti.add(ouvsu);
                                }
                            }

                            responseObject.setData(recenzenti);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.LOGIN:
                        try {
                            ApstraktnaGenerickaOperacija vratiKorisnika = new VratiKorisnika();
                            Korisnik korisnikFromDb = (Korisnik) vratiKorisnika.opsteIzvrsenje((Korisnik) requestObject.getData());

                            if (korisnikFromDb != null) {
                                Session.getInstance().getMap().put(korisnikFromDb.getUsername(), korisnikFromDb);
                            }
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(korisnikFromDb);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.PRONADJI_PREDMET_PO_ID:
                        try {
                            ApstraktnaGenerickaOperacija vratiPredmetZaId = new VratiPredmetZaId();
                            Predmet predmet = (Predmet) vratiPredmetZaId.opsteIzvrsenje((Predmet) requestObject.getData());
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmet);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_VRSTE_I_NIVOE_STUDIJA:
                        try {
                            ApstraktnaGenerickaOperacija vratiSveVrsteStudija = new VratiSveVrsteStudija();

                            List<VrstaINivoStudija> vrste = (List<VrstaINivoStudija>) vratiSveVrsteStudija.opsteIzvrsenje((VrstaINivoStudija) requestObject.getData());
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(vrste);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_TIPOVE_NASTAVE:
                        try {
                            ApstraktnaGenerickaOperacija vratiSveTipoveNastave = new VratiSveTipoveNastave();

                            List<TipNastave> tipoviNastave = (List<TipNastave>) vratiSveTipoveNastave.opsteIzvrsenje((TipNastave) requestObject.getData());
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(tipoviNastave);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.VRATI_SVE_NASTAVNIKE:
                        try {
                            ApstraktnaGenerickaOperacija vratiSveNastavnike = new VratiSveNastavnike();
                            List<Nastavnik> nastavnici = (List<Nastavnik>) vratiSveNastavnike.opsteIzvrsenje(new Nastavnik());
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(nastavnici);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.KREIRAJ_PREDMET:
                        try {
                            ApstraktnaGenerickaOperacija kreirajPredmet = new KreirajPredmet();
                            Predmet predmet = (Predmet) kreirajPredmet.opsteIzvrsenje((Predmet) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmet);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_ULOGU_UDZBENIK_PO_NAZIVU:
                        try {
                            ApstraktnaGenerickaOperacija vratuUloguNaUdzbenikuZaNaziv = new VratiUloguNaUdzbenikuZaNaziv();
                            UlogaUdzbenik ulogaUdzbenik = (UlogaUdzbenik) vratuUloguNaUdzbenikuZaNaziv.opsteIzvrsenje((UlogaUdzbenik) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(ulogaUdzbenik);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_PREDMETE:
                        try {
                            ApstraktnaGenerickaOperacija vratiSvePredmete = new VratiSvePredmete();
                            List<Predmet> predmeti = (List<Predmet>) vratiSvePredmete.opsteIzvrsenje((Predmet) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmeti);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.AZURIRAJ_PREDMET:
                        try {
                            ApstraktnaGenerickaOperacija azurirajPredmet = new AzurirajPredmet();
                            Predmet predmet = (Predmet) azurirajPredmet.opsteIzvrsenje((Predmet) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmet);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.OBRISI_PREDMET:
                        try {
                            ApstraktnaGenerickaOperacija obrisiPredmet = new ObrisiPredmet();
                            Predmet predmet = (Predmet) obrisiPredmet.opsteIzvrsenje((Predmet) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmet);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.VRATI_SVE_STATUSE:
                        try {
                            ApstraktnaGenerickaOperacija vratiSveStatuse = new VratiSveStatuse();
                            List<Status> statusi = (List<Status>) vratiSveStatuse.opsteIzvrsenje((Status) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(statusi);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_STATUS_PO_ID:
                        try {
                            ApstraktnaGenerickaOperacija vratiStatusZaId = new VratiStatusZaId();
                            Status status = (Status) vratiStatusZaId.opsteIzvrsenje((Status) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(status);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.VRATI_SVE_STUDIJSKE_PROGRAME:
                        try {
                            ApstraktnaGenerickaOperacija vratiSveStudijskePrograme = new VratiSveStudijskePrograme();
                            List<StudijskiProgram> studijskiProgrami = (List<StudijskiProgram>) vratiSveStudijskePrograme.opsteIzvrsenje((StudijskiProgram) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(studijskiProgrami);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.PRONADJI_STUDIJSKI_PROGRAM_PO_ID:
                        try {
                            ApstraktnaGenerickaOperacija vratiStudijskiProgramZaId = new VratiStudijskiProgramZaId();
                            StudijskiProgram studijskiProgram = (StudijskiProgram) vratiStudijskiProgramZaId.opsteIzvrsenje((StudijskiProgram) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(studijskiProgram);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.KREIRAJ_PREDMET_NA_STUDIJSKOM_PROGRAMU:
                        try {
                            ApstraktnaGenerickaOperacija kreirajPredmetNaStudijskomProgramu = new KreirajPredmetNaStudijskomProgramu();
                            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = (PredmetNaStudijskomProgramu) kreirajPredmetNaStudijskomProgramu.opsteIzvrsenje((PredmetNaStudijskomProgramu) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_PREDMETE_ZA_STUDIJSKI_PROGRAM:
                        try {
                            ApstraktnaGenerickaOperacija vratiPredmeteZaStudijskiProgram = new VratiPredmeteZaStudijskiProgram();
                            List<PredmetNaStudijskomProgramu> predmetiNaStudijskomProgramu = (List<PredmetNaStudijskomProgramu>) vratiPredmeteZaStudijskiProgram.opsteIzvrsenje((StudijskiProgram) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetiNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.PRONADJI_PREDMET_NA_STUDIJSKOM_PROGRAMU_ZA_ID:
                        try {
                            ApstraktnaGenerickaOperacija vratiPredmetNaStudijskomProgramuZaId = new VratiPredmetNaStudijskomPragramuZaId();
                            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = (PredmetNaStudijskomProgramu) vratiPredmetNaStudijskomProgramuZaId.opsteIzvrsenje((PredmetNaStudijskomProgramu) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.AZURIRAJ_PREDMET_NA_STUDIJSKOM_PROGRAMU:
                        try {
                            ApstraktnaGenerickaOperacija azurirajPredmetNaStudijskomProgramu = new AzurirajPredmetNaStudisjkomProgramu();
                            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = (PredmetNaStudijskomProgramu) azurirajPredmetNaStudijskomProgramu.opsteIzvrsenje((PredmetNaStudijskomProgramu) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.OBRISI_PREDMET_NA_STUDIJSKOM_PROGRAMU:
                        try {
                            ApstraktnaGenerickaOperacija obrisiPredmetNaStudijskomProgramu = new ObrisiPredmetNastudijskomProgramu();

                            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = (PredmetNaStudijskomProgramu) obrisiPredmetNaStudijskomProgramu.opsteIzvrsenje((PredmetNaStudijskomProgramu) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.LOGOUT:
                        try {
                            Korisnik korisnik = (Korisnik) Session.getInstance().getMap().get(((Korisnik) requestObject.getData()).getUsername());
                            if (korisnik != null) {
                                Session.getInstance().getMap().remove(korisnik.getUsername());
                                responseObject.setData(korisnik);
                                responseObject.setCode(IStatus.OK);
                            } else {
                                responseObject.setCode(IStatus.ERROR);
                                responseObject.setData(null);
                            }

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.PRONADJI_NASTAVNIKA_PO_ID:
                        try {
                            ApstraktnaGenerickaOperacija vratiNastavnikaZaId = new VratiNastavnikaZaId();
                            Nastavnik nastavnik = (Nastavnik) vratiNastavnikaZaId.opsteIzvrsenje((Nastavnik) requestObject.getData());
                            if (nastavnik != null) {

                                responseObject.setData(nastavnik);
                                responseObject.setCode(IStatus.OK);
                            } else {
                                responseObject.setCode(IStatus.ERROR);
                                responseObject.setData(null);
                            }

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    default:
                        responseObject.setMessage("Server nije mogao da zakljuci o kom zahtevu se radi.");

                }
                //posalji odgovor

                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(responseObject);
                output.flush();
            } catch (Exception ex) {

            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
