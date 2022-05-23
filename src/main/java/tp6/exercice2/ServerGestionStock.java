import com.hamma.tp6.exercice2.Medicament;
import com.hamma.tp6.exercice2.MoisEnum;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServerGestionStock extends Thread {

    final static int port = 9632;
    static final File file = new File("medicaments-stocks.txt");
    private Socket socket;

    public static void main(String[] args) {
        try {
            ServerSocket socketServeur = new ServerSocket(port);
            System.out.println("Lancement du serveur");
            if (!file.exists()) {
                file.createNewFile();
            }

            while (true) {
                Socket socketClient = socketServeur.accept();
                ServerGestionStock t = new ServerGestionStock(socketClient);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerGestionStock(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        traitements();
    }

    public void traitements() {
        BufferedWriter bw = null;
        try {
            String message = "";
            System.out.println("Connexion avec le client : " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            while (true) {

                message = in.readLine();
                if (message.indexOf("+") != -1) {
                    bw.write(message);
                    out.println("Ajout Médicament au stock avec succès ");
                }
                if (message.equals("LIST")) {
                    List<Medicament> medicaments = new ArrayList<>();
                    Medicament medicament = new Medicament();
                    medicament.setDescription("Doliprane");
                    medicament.setAnneePreemption(2025);
                    medicament.setMoisPreemption(MoisEnum.JANVIER);
                    medicament.setQuantite(200);
                    medicaments.add(medicament);
                    Medicament medicament1 = new Medicament();
                    medicament1.setDescription("Aspirine");
                    medicament1.setAnneePreemption(2024);
                    medicament1.setMoisPreemption(MoisEnum.JANVIER);
                    medicament1.setQuantite(200);
                    medicaments.add(medicament1);
                    out.println("List des médicaments>> " + medicaments.toString());
                    out.flush();
                }
                if (message.equals("LISTPR")) {
                    List<Medicament> medicaments = new ArrayList<>();
                    Medicament medicament = new Medicament();
                    medicament.setDescription("Doliprane");
                    medicament.setAnneePreemption(2025);
                    medicament.setMoisPreemption(MoisEnum.JANVIER);
                    medicament.setQuantite(200);
                    medicaments.add(medicament);
                    Medicament medicament1 = new Medicament();
                    medicament1.setDescription("Aspirine");
                    medicament1.setAnneePreemption(2024);
                    medicament1.setMoisPreemption(MoisEnum.JANVIER);
                    medicament1.setQuantite(200);
                    medicaments.add(medicament1);
                    LocalDate dateJour = LocalDate.now();
                    out.println("List des médicaments>> " + medicaments.stream().filter((e) -> e.getAnneePreemption() >= dateJour.getYear()
                            && e.getMoisPreemption().getMois() > dateJour.getMonthValue()).toString());
                    out.flush();
                }
                if (message.equals("Rechercher description")) {
                    out.println("Aucun médicament trouvé");
                    out.flush();
                }
                if (message.equals("DELETE")) {
                    out.println("Médicament supprimé avec succès");
                    out.flush();
                }
                if (message.equals("QUIT")) {
                    socket.close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}