package ispit2024jan1B;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ZamisliRec extends Application {

    static String zamisljenaRec;
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        zamisljenaRec = sc.next();
        launch(args);
    }


    public String izlistajReci(ArrayList<String> reci) {
        StringBuilder sb = new StringBuilder();
        for(String rec : reci) {
            sb.append(rec).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        List<String> linije = Files.readAllLines(Path.of("ZamisljeneReci.txt"));
        ArrayList<String> reci = new ArrayList<>();
        for (String linija : linije) {
            if (linija.charAt(0) == zamisljenaRec.charAt(0)) {
                String[] reci2;
                reci2 = linija.split(" ");
                for (String rec : reci2) {
                    reci.add(rec);
                }
                break;
            }
        }


        TreeMap<String, Integer> ucesnici = new TreeMap<>();

        VBox glavni = new VBox();
        glavni.setSpacing(10);
        glavni.setPadding(new Insets(10,5,5,5));

        //----------------------------------------------------------------
            VBox gornji = new VBox();
            gornji.setSpacing(10);

            //----------------------------------------------------------------
                Label lbSlovo = new Label ("Ana je zamislila rec na slovo " + zamisljenaRec.charAt(0));
                Label lbZamisli = new Label("Ana je zamislila neku od sledecih reci:");

                TextArea taListaReci = new TextArea();
                taListaReci.setText(izlistajReci(reci));
                taListaReci.setEditable(false);

                gornji.getChildren().addAll(lbSlovo, lbZamisli, taListaReci);
            //----------------------------------------------------------------
            HBox imeTakmicara = new HBox();
            imeTakmicara.setSpacing(10);

            //----------------------------------------------------------------
                Label lbIme = new Label("Ime takmicara: ");
                TextField tfImeTakmicara = new TextField();

                imeTakmicara.getChildren().addAll(lbIme, tfImeTakmicara);
            //----------------------------------------------------------------
            HBox unesiteRec = new HBox();
            unesiteRec.setSpacing(10);

            //----------------------------------------------------------------
                Label lbUnesiteRec = new Label("Unesite rec: ");
                TextField tfUnesiteRec = new TextField();

                unesiteRec.getChildren().addAll(lbUnesiteRec, tfUnesiteRec);
            //----------------------------------------------------------------
            Label lbIshod = new Label();
            VBox donji = new VBox();
            donji.setSpacing(10);
            donji.setAlignment(Pos.CENTER);
            //----------------------------------------------------------------
                Button btPotvrdi = new Button("Potvrdi");
                Button btOcisti = new Button("Ocisti");
                Button btPokazi = new Button("Pokazi");
                TextArea taPoeni = new TextArea();
                taPoeni.setEditable(false);

                donji.getChildren().addAll(btPotvrdi,btOcisti, btPokazi, taPoeni);
            //----------------------------------------------------------------


            btPotvrdi.setOnAction(event ->  {
                String ime = tfImeTakmicara.getText();
                if (ime.isEmpty()) {
                    return;
                }
                String unetaRec = tfUnesiteRec.getText();
                if (!ucesnici.containsKey(ime)) {
                    ucesnici.put(ime, 0);
                }
                if (unetaRec.equals(zamisljenaRec)) {
                    ucesnici.put(ime, ucesnici.get(ime) + 10);
                    lbIshod.setText("GG, +10 za gospodina (rizzuj Anu) " + ime);
                }
                else {
                    ucesnici.put(ime, ucesnici.get(ime) - 3);
                    lbIshod.setText("Anlaki :'(");
                }
                tfUnesiteRec.clear();
                tfImeTakmicara.clear();
                taPoeni.clear();

            });

            btOcisti.setOnAction(event -> {

                tfImeTakmicara.clear();
                tfUnesiteRec.clear();
                lbIshod.setText("");
                taPoeni.clear();

            });

            btPokazi.setOnAction(event -> {
                taPoeni.setText(izlistajPoene(ucesnici));


            });
        //----------------------------------------------------------------
        glavni.getChildren().addAll(gornji,imeTakmicara, unesiteRec, lbIshod, donji);

        Scene scena = new Scene(glavni, 450,500);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Na slovo, na slovo");
        stage.setScene(scena);
        stage.show();
    }

    private String izlistajPoene(TreeMap<String, Integer> ucesnici) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> primerak : ucesnici.entrySet()) {
            sb.append(primerak.getKey()).append(" ").append(primerak.getValue()).append("\n");
        }
        return sb.toString();
    }


}
