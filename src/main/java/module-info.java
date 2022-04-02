module edu.ntnu.idatt1002.k01g08.fta {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.ntnu.idatt1002.k01g08.fta to javafx.fxml;
    exports edu.ntnu.idatt1002.k01g08.fta;
    exports edu.ntnu.idatt1002.k01g08.fta.registers;
    opens edu.ntnu.idatt1002.k01g08.fta.registers to javafx.fxml;
    exports edu.ntnu.idatt1002.k01g08.fta.objects;
    opens edu.ntnu.idatt1002.k01g08.fta.objects to javafx.fxml;
    exports edu.ntnu.idatt1002.k01g08.fta.controllers;
    opens edu.ntnu.idatt1002.k01g08.fta.controllers to javafx.fxml;
    exports edu.ntnu.idatt1002.k01g08.fta.guiControllers;
    opens edu.ntnu.idatt1002.k01g08.fta.guiControllers to javafx.fxml;
}