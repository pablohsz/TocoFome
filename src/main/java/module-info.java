module com.pucgo.cliserver.restauranti {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.codec;
    requires java.sql;

    opens com.pucgo.cliserver.tocofome to javafx.fxml;
    exports com.pucgo.cliserver.tocofome;
}