package com.guillermo.gestor.util;

import com.guillermo.gestor.principal.view.PrincipalView;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Common {
    public static String globalPath = "C:" + File.separator;

    public String selectPath() {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.
                showDialog(PrincipalView.stage);
        if (file.getPath().isBlank()) {
            return globalPath;
        }
        return file.getPath();

    }
}
