package com.aiproject3.aiproject3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class HelloApplication extends Application {

    private ArrayList<String> top5 = new ArrayList<>();
	private static HashMap<String,BiAndTriGram> corpus = new HashMap<>();
    ArrayList<String> words_for_combo = new ArrayList<>();

    @FXML
    private ComboBox<String> AnswerCombo;
    @FXML
    private ComboBox<String> WordsCombo = new ComboBox<String>();
    @FXML
    private TextField ExpTxt;

    @FXML
    void Run(ActionEvent event) {
        /*String[] x = ExpTxt.getText().split("\\s");
        HashMap<String,Row> biGram = new HashMap<>();
        HashMap<String,Row> triGram = new HashMap<>();
        double biProb = 1.0, triProb = 1.0;

        for(int i = 0; i < x.length; i++) {
            if(i + 2 < x.length) {
                if(corpus.containsKey(x[i] + " " + x[i + 1])) {
                    if(corpus.get(x[i] + " " + x[i + 1]).getLastWord().containsKey(x[i + 2])) {
                        triProb = triProb * corpus.get(x[i] + " " + x[i + 1]).getLastWord().get(x[i + 2]).getProbability();
                    }
                }
                if(i + 1 < x.length) {
                    if(corpus.containsKey(x[i])) {
                        if(corpus.get(x[i]).getLastWord().containsKey(x[i + 1])) {
                            biProb = biProb * corpus.get(x[i]).getLastWord().get(x[i + 1]).getProbability();
                        }
                    }
                }
            }
        }

        System.out.println(biProb + "***********");
        System.out.println(triProb + "***********");

        if(x.length >= 2) {
            if(corpus.containsKey(x[x.length-2] + " " + x[x.length - 1])) {
                triGram = corpus.get(x[x.length - 2] + x[x.length - 1]).getLastWord();
            }
        }

        if(corpus.containsKey(x[x.length-1])){
            biGram = corpus.get(x[x.length-1]).getLastWord();
        }

        for(Map.Entry<String, Row> entry : triGram.entrySet()) {
            if(biGram.containsKey(entry.getKey())) {
                biGram.get(entry.getKey()).setProbability(biGram.get(entry.getKey()).getProbability() * triProb * entry.getValue().getProbability());
            } else {
                System.out.println("***********************");
                top5.add(new Answer(entry.getKey(), (triProb * entry.getValue().getProbability())).toString());
            }
        }

        for(Map.Entry<String, Row> entry : biGram.entrySet()) {
            System.out.println("***********************");
            top5.add(new Answer(entry.getKey(), (biProb * entry.getValue().getProbability())).toString());
        }

        Collections.sort(top5);*/
    }

    @FXML
    void GetAnswers(Event event) {
        ObservableList<String> x2 = FXCollections.observableArrayList(top5);
        AnswerCombo.setItems(x2);
    }

    @FXML
    void GetWords(Event event) {
        String[] x = ExpTxt.getText().split("\\s");

        words_for_combo.addAll(Arrays.asList(x));

        ObservableList<String> x1 = FXCollections.observableArrayList(words_for_combo);
        WordsCombo.setItems(x1);
    }

    @FXML
    void MakeCsv(ActionEvent event) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\Smart\\Desktop\\IntelliJ\\AIProject3\\src\\TrainingData.txt"));
        ArrayList<String> list1 = new ArrayList<>();

        while(scanner.hasNext()) {
            String[] temp = scanner.next().split("\\s");
            list1.addAll(Arrays.asList(temp));
        }

        scanner.close();

        HashMap<String, Row> corpus = new HashMap<>();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < list1.size(); j++) {
                if(i == 0) {
                    if(corpus.containsKey(list1.get(j))) {
                        corpus.get(list1.get(j).trim()).setCount(corpus.get(list1.get(j).trim()).getCount() + 1);
                    } else {
                        corpus.put(list1.get(j).trim(), new Row(1, 0, i+1));
                    }
                } else if(i == 1) {
                    if(j + 1 < list1.size()) {
                        String temp = list1.get(j).trim() + " " + list1.get(j + 1).trim();
                        temp = temp.trim();

                        if(corpus.containsKey(temp)) {
                            corpus.get(temp).setCount(corpus.get(temp).getCount() + 1);
                            corpus.get(temp).setProbability((double) ((corpus.get(temp).getCount())) / (double) (corpus.get(list1.get(j).trim()).getCount()));
                        } else {
                            corpus.put(temp, new Row(1, 1 / (double) (corpus.get(list1.get(j).trim()).getCount()), i + 1));
                        }
                    }
                } else {
                    if(j + 2 < list1.size()) {
                        String temp = list1.get(j).trim() + " " + list1.get(j + 1).trim() + " " + list1.get(j + 2).trim();
                        temp = temp.trim();

                        if(corpus.containsKey(temp)) {
                            corpus.get(temp).setCount(corpus.get(temp).getCount() + 1);
                            corpus.get(temp).setProbability((double)((corpus.get(temp).getCount())) / (double)(corpus.get(list1.get(j).trim() + " " + list1.get(j + 1).trim()).getCount()));
                        } else {
                            corpus.put(temp, new Row(1, 1 / (double)(corpus.get(list1.get(j).trim()).getCount()), i + 1));
                        }
                    }
                }
            }
        }

        PrintWriter printWriter = new PrintWriter("languageModule.csv");

        String header = "Word,Count,Probability,Direction\n";
        printWriter.write(header);

        for(Map.Entry<String, Row> a : corpus.entrySet()) {
            printWriter.write(a.getKey() + "," + a.getValue().getCount() + "," + a.getValue().getProbability() + "," + a.getValue().getDirection() + "\n");
        }

        printWriter.close();
    }

    @SuppressWarnings("exports")
	@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Project 3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Smart\\Desktop\\IntelliJ\\AIProject3\\languageModule.csv"));
            String line;
            line = bufferedReader.readLine();

            while((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                if(Integer.parseInt(data[3].trim()) != 1) {
                    String[] temp = data[0].trim().split(",");
                    StringBuilder first = new StringBuilder();
                    String last = temp[temp.length-1].trim();

                    for(int i = 0; i < temp.length-1; i++) {
                        first.append(temp[i]).append(" ");
                    }
                    first = new StringBuilder(first.toString().trim());
                    
                    if(corpus.containsKey(first.toString())) {
                        corpus.get(first.toString()).getLastWord().put(last, new Row(Integer.parseInt(data[1].trim()), Double.parseDouble(data[2].trim()), Integer.parseInt(data[3].trim())));
                    } else {
                        HashMap<String, Row> tempH = new HashMap<>();
                        tempH.put(last, new Row(Integer.parseInt(data[1].trim()), Double.parseDouble(data[2].trim()), Integer.parseInt(data[3].trim())));
                        corpus.put(first.toString(), new BiAndTriGram(tempH));
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(corpus.get(first).toString());

        corpus.forEach((key, value) -> System.out.println(key + " " + value.toString()));

        launch();
    }

}
