module com.project5.project_5_jaredplante_cedricklubin_marcussmith {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires java.net.http;

    opens com.project5.project_5_jaredplante_cedricklubin_marcussmith to javafx.fxml;
    exports com.project5.project_5_jaredplante_cedricklubin_marcussmith;
}