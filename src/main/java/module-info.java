module com.oracle.currencyconverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.json;


    opens com.oracle.currencyconverter to javafx.fxml;
    exports com.oracle.currencyconverter;
}