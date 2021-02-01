import java.util.List;
import java.util.ArrayList;

public class StringCal {
    public int add(String text) {
        List<String> listneg = new ArrayList<String>();

        if (text == null || text.isEmpty()) {
            return 0;
        }
        char delimiter = getDelimiter(text.split("\n")[0]);

        String[] chuncks = (delimiter == ',') ? 
                                text.split(",|\n") : text.split("\n|" + delimiter);
        int total = 0;
        int fl = (delimiter == ',') ? 0 : 2;
        for (String item : chuncks) {
            if (fl > 0) {
                --fl;
            } else {
                int num = Integer.parseInt(item);
                if (num < 0) {
                    listneg.add(item);
                }
                total += num;
            }
        }
        if (!listneg.isEmpty()) {
            throw new IllegalArgumentException(
                "listneg not allowed " + String.join(",", listneg));
        }
        return total;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private char getDelimiter(String line) {
        if (line == null || line.isEmpty()) {
            return ',';
        }
        if (isNumeric(line)) {
            return ',';
        }
        if (line.length() == 1) {
            return line.charAt(0);
        }
        return ',';
    }
}