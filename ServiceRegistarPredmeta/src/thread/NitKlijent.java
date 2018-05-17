package thread;

import db.DbBroker;
import domen.Autor;
import domen.Korisnik;
import domen.Recenzent;
import domen.Udzbenik;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
                            List<Udzbenik> udzbenici = DbBroker.getInstance().vratiSveUdzbenike();
                            responseObject.setData(udzbenici);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.KREIRAJ_UDZBENIK:
                        try {
                            Udzbenik kreiranUdzbenik = DbBroker.getInstance().kreirajUdzbenik((Udzbenik) requestObject.getData());
                            responseObject.setData(kreiranUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.AZURIRAJ_UDZBENIK:
                        try {
                            Udzbenik azuriranUdzbenik = DbBroker.getInstance().azurirajUdzbenik((Udzbenik) requestObject.getData());
                            responseObject.setData(azuriranUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_UDZBENIK_PO_ID:
                        try {
                            Udzbenik udzbenikFromDb = DbBroker.getInstance().pronadjiUdzbenikPoId((int) requestObject.getData());
                            responseObject.setData(udzbenikFromDb);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.PRONADJI_UDZBENIK_PO_NAZIVU:
                        try {
                            Udzbenik udzbenikFromDb = DbBroker.getInstance().pronadjiUdzbenikPoNazivu((String) requestObject.getData());
                            responseObject.setData(udzbenikFromDb);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.OBRISI_UDZBENIK:
                        try {
                            Udzbenik obrisanUdzbenik = DbBroker.getInstance().obrisiUdzbenik((int) requestObject.getData());
                            responseObject.setData(obrisanUdzbenik);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_AUTORE:
                        try {
                            List<Autor> autori = DbBroker.getInstance().vratiAutoreZaUdzbenik((int) requestObject.getData());
                            responseObject.setData(autori);
                            responseObject.setCode(IStatus.OK);
                        } catch (Exception e) {
                            responseObject.setCode(IStatus.ERROR);
                            responseObject.setMessage(e.getMessage());
                        }
                        break;
                    case IOperation.VRATI_SVE_RECENZENTE:
                        try {
                            List<Recenzent> recenzenti = DbBroker.getInstance().vratiRecenzenteZaUdzbenik((int) requestObject.getData());
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
                            Korisnik korisnikFromDb = DbBroker.getInstance().vratiKorisnika(korisnikRequest.getUsername(), korisnikRequest.getPassword());
                            responseObject.setCode(IStatus.OK);
                            responseObject.setData(korisnikFromDb);
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
