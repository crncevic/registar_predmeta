package thread;

import db.DbBroker;
import db.dao.KorisnikDaoImpl;
import db.dao.NastavnikDaoImpl;
import db.dao.OsobaUVeziSaUdzbenikomDaoImpl;
import db.dao.PredmetDaoImpl;
import db.dao.PredmetNaStudijskomProgramuDaoImpl;
import db.dao.StatusDaoImpl;
import db.dao.StudijskiProgramDaoImpl;
import db.dao.TipNastaveDaoImpl;
import db.dao.UdzbenikDaoImpl;
import db.dao.UlogaUdzbenikDaoImpl;
import db.dao.VrstaINivoStudijaDaoImpl;
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
                            List<Udzbenik> udzbenici = UdzbenikDaoImpl.getInstance().vratiSveUdzbenike();
                            responseObject.setData(udzbenici);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.KREIRAJ_UDZBENIK:
                        try {
                            Udzbenik kreiranUdzbenik = UdzbenikDaoImpl.getInstance().kreiraj((Udzbenik) requestObject.getData());
                            responseObject.setData(kreiranUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.AZURIRAJ_UDZBENIK:
                        try {
                            Udzbenik azuriranUdzbenik = UdzbenikDaoImpl.getInstance().azuriraj((Udzbenik) requestObject.getData());
                            responseObject.setData(azuriranUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_UDZBENIK_PO_ID:
                        try {
                            Udzbenik udzbenikFromDb = UdzbenikDaoImpl.getInstance().vratiPoId((int) requestObject.getData());
                            responseObject.setData(udzbenikFromDb);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_UDZBENIK_PO_NAZIVU:
                        try {
                            Udzbenik udzbenikFromDb = UdzbenikDaoImpl.getInstance().pronadjiUdzbenikPoNazivu((String) requestObject.getData());
                            responseObject.setData(udzbenikFromDb);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.OBRISI_UDZBENIK:
                        try {
                            Udzbenik obrisanUdzbenik = UdzbenikDaoImpl.getInstance().obrisi((Predmet) requestObject.getData());
                            responseObject.setData(obrisanUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_AUTORE:
                        try {
                            List<OsobaUVeziSaUdzbenikom> osobe = OsobaUVeziSaUdzbenikomDaoImpl.getInstance().vratiOsobeZaUdzbenik((int) requestObject.getData());
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
                            List<OsobaUVeziSaUdzbenikom> osobe = OsobaUVeziSaUdzbenikomDaoImpl.getInstance().vratiOsobeZaUdzbenik((int) requestObject.getData());
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
                            Korisnik korisnikRequest = (Korisnik) requestObject.getData();
                            Korisnik korisnikFromDb = KorisnikDaoImpl.getInstance().vratiKorisnika(korisnikRequest.getUsername(), korisnikRequest.getPassword());
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
                            Predmet predmet = PredmetDaoImpl.getInstance().vratiPoId((int) requestObject.getData());
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmet);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_VRSTE_I_NIVOE_STUDIJA:
                        try {
                            List<VrstaINivoStudija> vrste = VrstaINivoStudijaDaoImpl.getInstance().vratiSveVrsteStudija();
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(vrste);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_TIPOVE_NASTAVE:
                        try {
                            List<TipNastave> tipoviNastave = TipNastaveDaoImpl.getInstance().vratiSveTipoveNastave();
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(tipoviNastave);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.VRATI_SVE_NASTAVNIKE:
                        try {
                            List<Nastavnik> nastavnici = NastavnikDaoImpl.getInstance().vratiSveNastavnike();
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(nastavnici);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.KREIRAJ_PREDMET:
                        try {
                            Predmet predmet = PredmetDaoImpl.getInstance().kreiraj((Predmet) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmet);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_ULOGU_UDZBENIK_PO_NAZIVU:
                        try {
                            UlogaUdzbenik ulogaUdzbenik = UlogaUdzbenikDaoImpl.getInstance().vratiUloguNaUdzbenikuZaNaziv((String) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(ulogaUdzbenik);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_PREDMETE:
                        try {
                            List<Predmet> predmeti = PredmetDaoImpl.getInstance().vratiSvePredmete();

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmeti);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.AZURIRAJ_PREDMET:
                        try {
                            Predmet predmet = PredmetDaoImpl.getInstance().azuriraj((Predmet) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmet);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.OBRISI_PREDMET:
                        try {
                            Predmet predmet = PredmetDaoImpl.getInstance().obrisi((Predmet) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmet);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.VRATI_SVE_STATUSE:
                        try {
                            List<Status> statusi = StatusDaoImpl.getInstance().vratiSveStatuse();

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(statusi);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_STATUS_PO_ID:
                        try {
                            Status status = StatusDaoImpl.getInstance().vratiPoId((int) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(status);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.VRATI_SVE_STUDIJSKE_PROGRAME:
                        try {
                            List<StudijskiProgram> studijskiProgrami = StudijskiProgramDaoImpl.getInstance().vratiSveStudijskePrograme();

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(studijskiProgrami);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.PRONADJI_STUDIJSKI_PROGRAM_PO_ID:
                        try {
                            StudijskiProgram studijskiProgram = StudijskiProgramDaoImpl.getInstance().vratiPoId((int) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(studijskiProgram);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.KREIRAJ_PREDMET_NA_STUDIJSKOM_PROGRAMU:
                        try {
                            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = PredmetNaStudijskomProgramuDaoImpl.getInstance().kreiraj((PredmetNaStudijskomProgramu) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_PREDMETE_ZA_STUDIJSKI_PROGRAM:
                        try {
                            List<PredmetNaStudijskomProgramu> predmetiNaStudijskomProgramu = PredmetNaStudijskomProgramuDaoImpl.getInstance()
                                    .vratiPredmetZaStudijskiProgram((int) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetiNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.PRONADJI_PREDMET_NA_STUDIJSKOM_PROGRAMU_ZA_ID:
                        try {
                            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = PredmetNaStudijskomProgramuDaoImpl.getInstance()
                                    .vratiPredmetNaStudijskomProgramuZaId(((PredmetNaStudijskomProgramu) requestObject.getData()).getPredmet().getPredmetId(), ((PredmetNaStudijskomProgramu) requestObject.getData()).getStudijskiProgram().getStudijskiProgramId());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.AZURIRAJ_PREDMET_NA_STUDIJSKOM_PROGRAMU:
                        try {
                            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = PredmetNaStudijskomProgramuDaoImpl.getInstance()
                                    .azuriraj((PredmetNaStudijskomProgramu) requestObject.getData());

                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(predmetNaStudijskomProgramu);

                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.OBRISI_PREDMET_NA_STUDIJSKOM_PROGRAMU:
                        try {
                            PredmetNaStudijskomProgramu predmetNaStudijskomProgramu = PredmetNaStudijskomProgramuDaoImpl.getInstance()
                                    .obrisi(((PredmetNaStudijskomProgramu) requestObject.getData()).getPredmet().getPredmetId(), ((PredmetNaStudijskomProgramu) requestObject.getData()).getStudijskiProgram().getStudijskiProgramId());

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
                            Nastavnik nastavnik = NastavnikDaoImpl.getInstance().vratiPoId((int)requestObject.getData());
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
                ex.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
