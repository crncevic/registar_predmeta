package thread;

import db.DbBroker;
import db.dao.impl.KorisnikDaoImpl;
import db.dao.impl.NastavnikDaoImpl;
import db.dao.impl.OsobaUVeziSaUdzbenikomDaoImpl;
import db.dao.impl.PredmetDaoImpl;
import db.dao.impl.TipNastaveDaoImpl;
import db.dao.impl.UdzbenikDaoImpl;
import db.dao.impl.VrstaINivoStudijaDaoImpl;
import domen.OsobaUVeziSaUdzbenikom;
import domen.Korisnik;
import domen.Nastavnik;
import domen.Predmet;
import domen.TipNastave;
import domen.Udzbenik;
import domen.VrstaINivoStudija;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
                            Udzbenik kreiranUdzbenik = UdzbenikDaoImpl.getInstance().kreirajUdzbenik((Udzbenik) requestObject.getData());
                            responseObject.setData(kreiranUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.AZURIRAJ_UDZBENIK:
                        try {
                            Udzbenik azuriranUdzbenik = UdzbenikDaoImpl.getInstance().azurirajUdzbenik((Udzbenik) requestObject.getData());
                            responseObject.setData(azuriranUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_UDZBENIK_PO_ID:
                        try {
                            Udzbenik udzbenikFromDb = UdzbenikDaoImpl.getInstance().pronadjiUdzbenikPoId((int) requestObject.getData());
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
                            Udzbenik obrisanUdzbenik = UdzbenikDaoImpl.getInstance().obrisiUdzbenik((int) requestObject.getData());
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
                                if (ouvsu.getUlogaUdzbenik().getNaziv().equalsIgnoreCase("recenzent")) {
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

                    case IOperation.PROVERI_KORISNIKA:
                        try {
                            Korisnik korisnikRequest = (Korisnik) requestObject.getData();
                            Korisnik korisnikFromDb = KorisnikDaoImpl.getInstance().vratiKorisnika(korisnikRequest.getUsername(), korisnikRequest.getPassword());
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(korisnikFromDb);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;

                    case IOperation.PRONADJI_PREDMET_PO_ID:
                        try {
                            Predmet predmet = PredmetDaoImpl.getInstance().pronadjiPredmetPoId((int) requestObject.getData());
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
}
