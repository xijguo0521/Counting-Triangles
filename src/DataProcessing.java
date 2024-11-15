import java.util.*;
import java.io.*;

public class DataProcessing {

    private List<String> estimations;

    public DataProcessing() {
        estimations = new ArrayList<>();
    }

    public List<String> getEstimations() {
        return estimations;
    }

    public void addEst(String s) {
        estimations.add(s);
    }

    public void writeToFile(int sampleSize, boolean impr) {
        String algo = null;
        if (impr)
            algo = "impr";
        else
            algo = "base";
        String path = "../" + algo + String.valueOf(sampleSize) + ".txt";
        File file = new File(path);

        if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}


        String estStr = String.join(",", estimations);

        FileWriter fr = null;
        BufferedWriter br = null;

        try {
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            br.write(estStr);
            br.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
