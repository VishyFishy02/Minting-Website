package prospectPassCopier;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        final String path = "C:\\Users\\firep\\Desktop\\OfficialMintingWebsite\\assets\\";
        int idx = 0;
        System.out.println("hi");
        File pngFile = new File(path + idx + ".png");
        File mp4File = new File(path + idx + ".mp4");
        File jsonFile = new File(path + idx + ".json");

        try {
            for (int i = 1; i < 999; i++) {
                File copyPNG = new File(path + i + ".png");
                File copymp4 = new File(path + i + ".mp4");
                File copyJson = new File(path + i + ".json");

                Files.copy(pngFile.toPath(), copyPNG.toPath());
                Files.copy(mp4File.toPath(), copymp4.toPath());
                Files.copy(jsonFile.toPath(), copyJson.toPath());

                editFile(copyJson, i);
            }

        } catch (IOException e) {
            System.out.println("FILE NOT FOUND ERROR");
        }

    }

    public static void editFile(File copyJson, int idx) throws IOException {
        List<String> newLines = new ArrayList<>();

        for (String line : Files.readAllLines(
                Paths.get(copyJson.getAbsolutePath()),
                StandardCharsets.UTF_8)) {
            if (line.contains("0.")) {
                newLines.add(line.replace("0.", idx + "."));
            } else if (line.contains("\"value\"")) {
                newLines.add(line.replace("1", idx + 1 + ""));
            } else {
                newLines.add(line);
            }
        }

        System.out.println(Files.write(Paths.get(copyJson.getAbsolutePath()),
                newLines, StandardCharsets.UTF_8));
    }
}