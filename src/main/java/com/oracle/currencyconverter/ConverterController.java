package com.oracle.currencyconverter;

import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class ConverterController implements Initializable {
    public ComboBox<String> drpFrom;
    public ComboBox<String> drpTo;
    public Button btnConvert;
    public Label toOut;
    public Label fromOut;

    private double xOffset;
    private double yOffset;

    public TextField inpAmount;
    public AnchorPane root;
    public ImageView loader;
    public ImageView exit;
    public AnchorPane slider;
    public Label menu;

    List<String> currencies = Arrays.asList(
            "AED : United Arab Emirates",
            "AFN : Afghanistan",
            "ALL : Albania",
            "AMD : Armenia",
            "ANG : Netherlands Antilles",
            "AOA : Angola",
            "ARS : Argentina",
            "AUD : Australia",
            "AWG : Aruba",
            "AZN : Azerbaijan",
            "BAM : Bosnia and Herzegovina",
            "BBD : Barbados",
            "BDT : Bangladesh",
            "BGN : Bulgaria",
            "BHD : Bahrain",
            "BIF : Burundi",
            "BMD : Bermuda",
            "BND : Brunei",
            "BOB : Bolivia",
            "BRL : Brazil",
            "BSD : Bahamas",
            "BTC : Bitcoin",
            "BTN : Bhutan",
            "BWP : Botswana",
            "BYN : Belarus",
            "BZD : Belize",
            "CAD : Canada",
            "CDF : Democratic Republic of the Congo",
            "CHF : Switzerland",
            "CLF : Chile",
            "CLP : Chile",
            "CNH : China",
            "CNY : China",
            "COP : Colombia",
            "CRC : Costa Rica",
            "CUC : Cuba",
            "CUP : Cuba",
            "CVE : Cape Verde",
            "CZK : Czech Republic",
            "DJF : Djibouti",
            "DKK : Denmark",
            "DOP : Dominican Republic",
            "DZD : Algeria",
            "EGP : Egypt",
            "ERN : Eritrea",
            "ETB : Ethiopia",
            "EUR : Europe",
            "FJD : Fiji",
            "FKP : Falkland Islands",
            "GBP : United Kingdom",
            "GEL : Georgia",
            "GGP : Guernsey",
            "GHS : Ghana",
            "GIP : Gibraltar",
            "GMD : Gambia",
            "GNF : Guinea",
            "GTQ : Guatemala",
            "GYD : Guyana",
            "HKD : Hong Kong",
            "HNL : Honduras",
            "HRK : Croatia",
            "HTG : Haiti",
            "HUF : Hungary",
            "IDR : Indonesia",
            "ILS : Israel",
            "IMP : Isle of Man",
            "INR : India",
            "IQD : Iraq",
            "IRR : Iran",
            "ISK : Iceland",
            "JEP : Jersey",
            "JMD : Jamaica",
            "JOD : Jordan",
            "JPY : Japan",
            "KES : Kenya",
            "KGS : Kyrgyzstan",
            "KHR : Cambodia",
            "KMF : Comoros",
            "KPW : North Korea",
            "KRW : South Korea",
            "KWD : Kuwait",
            "KYD : Cayman Islands",
            "KZT : Kazakhstan",
            "LAK : Laos",
            "LBP : Lebanon",
            "LKR : Sri Lanka",
            "LRD : Liberia",
            "LSL : Lesotho",
            "LYD : Libya",
            "MAD : Morocco",
            "MDL : Moldova",
            "MGA : Madagascar",
            "MKD : North Macedonia",
            "MMK : Myanmar",
            "MNT : Mongolia",
            "MOP : Macau",
            "MRU : Mauritania",
            "MUR : Mauritius",
            "MVR : Maldives",
            "MWK : Malawi",
            "MXN : Mexico",
            "MYR : Malaysia",
            "MZN : Mozambique",
            "NAD : Namibia",
            "NGN : Nigeria",
            "NIO : Nicaragua",
            "NOK : Norway",
            "NPR : Nepal",
            "NZD : New Zealand",
            "OMR : Oman",
            "PAB : Panama",
            "PEN : Peru",
            "PGK : Papua New Guinea",
            "PHP : Philippines",
            "PKR : Pakistan",
            "PLN : Poland",
            "PYG : Paraguay",
            "QAR : Qatar",
            "RON : Romania",
            "RSD : Serbia",
            "RUB : Russia",
            "RWF : Rwanda",
            "SAR : Saudi Arabia",
            "SBD : Solomon Islands",
            "SCR : Seychelles",
            "SDG : Sudan",
            "SEK : Sweden",
            "SGD : Singapore",
            "SHP : Saint Helena",
            "SLL : Sierra Leone",
            "SOS : Somalia",
            "SRD : Suriname",
            "SSP : South Sudan",
            "STD : São Tomé and Príncipe",
            "STN : São Tomé and Príncipe",
            "SVC : El Salvador",
            "SYP : Syria",
            "SZL : Eswatini",
            "THB : Thailand",
            "TJS : Tajikistan",
            "TMT : Turkmenistan",
            "TND : Tunisia",
            "TOP : Tonga",
            "TRY : Turkey",
            "TTD : Trinidad and Tobago",
            "TWD : Taiwan",
            "TZS : Tanzania",
            "UAH : Ukraine",
            "UGX : Uganda",
            "USD : United States",
            "UYU : Uruguay",
            "UZS : Uzbekistan",
            "VES : Venezuela",
            "VND : Vietnam",
            "VUV : Vanuatu",
            "WST : Samoa",
            "XAF : Central African CFA franc",
            "XAG : Silver (troy ounce)",
            "XAU : Gold (troy ounce)",
            "XCD : East Caribbean dollar",
            "XDR : Special Drawing Rights (International Monetary Fund)",
            "XOF : West African CFA franc",
            "XPD : Palladium (troy ounce)",
            "XPF : CFP franc",
            "XPT : Platinum (troy ounce)",
            "YER : Yemen",
            "ZAR : South Africa",
            "ZMW : Zambia",
            "ZWL : Zimbabwe"
    );

    private final String apiKey = "YOUR_API_KEY";
    

    public void convert(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader.setVisible(false);
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        inpAmount.setFocusTraversable(false);

        root.setOnMouseClicked(event -> {
            root.requestFocus();
            event.consume();
        });

        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(numberFilter);
        inpAmount.setTextFormatter(textFormatter);

        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(true);
            });
        });

        slider.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        slider.setOnMouseDragged(event -> {
            Stage stage = (Stage) slider.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() -yOffset);
        });

        drpFrom.getItems().addAll(currencies);
        drpTo.getItems().addAll(currencies);

        btnConvert.setOnAction(event -> {
            double amount = Double.parseDouble(inpAmount.getText());
            String fromCurrency = drpFrom.getValue().substring(0, 3);
            String toCurrency = drpTo.getValue().substring(0, 3);

            Task<Double> conversionTask = new Task<>() {
                @Override
                protected Double call() throws Exception {
                    return convertCurrency(amount, fromCurrency, toCurrency);
                }
            };

            conversionTask.setOnRunning(e -> loader.setVisible(true));
            conversionTask.setOnSucceeded(e -> {
                double convertedAmount = conversionTask.getValue();
                fromOut.setText(String.format("%.2f %s = ", amount, drpFrom.getValue().substring(5) ));
                toOut.setText(String.format("%.2f %s", convertedAmount, drpTo.getValue().substring(5)));
                loader.setVisible(false);
            });
            conversionTask.setOnFailed(e -> {
                toOut.setText("Conversion error");
                loader.setVisible(false);
            });

            Thread conversionThread = new Thread(conversionTask);
            conversionThread.start();
        });
    }


    private double convertCurrency(double amount, String fromCurrency, String toCurrency) throws IOException {
        String url = "https://openexchangerates.org/api/latest.json?app_id=" + apiKey;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(response.toString());

        JSONObject rates = json.getJSONObject("rates");
        double fromRate = rates.getDouble(fromCurrency);
        double toRate = rates.getDouble(toCurrency);

        return amount * (toRate / fromRate);
    }



}
