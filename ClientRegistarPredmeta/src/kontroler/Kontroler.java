/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.OsobaUVeziSaUdzbenikom;
import domen.Korisnik;
import domen.Predmet;
import domen.Udzbenik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import session.Session;
import transfer.request.RequestObject;
import transfer.response.ResponseObject;
import transfer.util.IOperation;
import transfer.util.IStatus;

/**
 *
 * @author Petar
 */
public class Kontroler {

    private static Kontroler instance;

    private Kontroler() throws Exception {

    }

    public static Kontroler getInstance() throws Exception {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public List<Udzbenik> vratiSveUdzbenike() throws Exception {

        RequestObject request = new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_UDZBENIKE);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (List<Udzbenik>) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikaciji");
        }

    }

    public Udzbenik kreirajUdzbenik(Udzbenik udzbenik) throws Exception {

        RequestObject request = new RequestObject();
        request.setOperation(IOperation.KREIRAJ_UDZBENIK);
        request.setData(udzbenik);
        Socket socket = Session.getInstance().getSocket();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (Udzbenik) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikaciji sa serverom!");
        }

    }

    public Udzbenik AzurirajUdzbenik(Udzbenik udzbenik) throws Exception {

        RequestObject request = new RequestObject();
        request.setOperation(IOperation.AZURIRAJ_UDZBENIK);
        request.setData(udzbenik);
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (Udzbenik) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikaciji sa serverom!");
        }

    }

    public Udzbenik pronadjiUdzbenikPoId(int id) throws Exception {

        RequestObject request = new RequestObject();
        request.setOperation(IOperation.PRONADJI_UDZBENIK_PO_ID);
        request.setData(id);
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (Udzbenik) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikaciji sa serverom!");
        }

    }

    public Udzbenik pronadjiUdzbenikePoNazivu(String naziv) throws Exception {

        RequestObject request = new RequestObject();
        request.setOperation(IOperation.PRONADJI_UDZBENIK_PO_NAZIVU);
        request.setData(naziv);
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (Udzbenik) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikaciji sa serverom!");
        }

    }

    public Udzbenik ObrisiUdzbenik(int id) throws Exception {

        RequestObject request = new RequestObject();
        request.setOperation(IOperation.OBRISI_UDZBENIK);
        request.setData(id);
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (Udzbenik) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikaciji sa serverom!");
        }
    }

    public List<OsobaUVeziSaUdzbenikom> vratiSveAutoreZaUdzbenik(int udzbenikId) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_AUTORE);
        request.setData(udzbenikId);
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (List<OsobaUVeziSaUdzbenikom>) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikaciji sa serverom!");
        }
    }

    public List<OsobaUVeziSaUdzbenikom> vratiSveRecenzenteZaUdzbenik(int udzbenikId) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(IOperation.VRATI_SVE_RECENZENTE);
        request.setData(udzbenikId);
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (List<OsobaUVeziSaUdzbenikom>) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikaciji sa serverom!");
        }
    }
    public Korisnik vratiKorisnika(String username, String password) throws Exception {

        RequestObject request = new RequestObject();
        request.setOperation(IOperation.PROVERI_KORISNIKA);
        Korisnik korisnik = new Korisnik(username, password);
        request.setData(korisnik);
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            Korisnik korisnikFromDb = new Korisnik();
            korisnikFromDb = (Korisnik) response.getData();
            return korisnikFromDb;
        } else {
            throw new Exception("Dogodila se greska u komunikacii sa serverom");
        }
    }

    public Predmet pronadjiPredmetPoId(int id) throws IOException, ClassNotFoundException, Exception {
        RequestObject request = new RequestObject();
        request.setData(id);
        request.setOperation(IOperation.PRONADJI_PREDMET_PO_ID);
        Socket socket = Session.getInstance().getSocket();

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        int code = response.getCode();

        if (code == IStatus.OK) {
            return (Predmet) response.getData();
        } else {
            throw new Exception("Dogodila se greska u komunikacii sa serverom");
        }

    }
}
