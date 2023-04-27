package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.entities.Cell;
import models.entities.Island;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static void setHeader(){
        System.out.println("""


                ▒█▀▀▀█ ▒█▀▀█ ▀▀█▀▀ ▒█▀▀█ ▒█▀▀▀█ ▒█▀▀█\s
                ▒█░░▒█ ▒█░░░ ░▒█░░ ▒█▄▄█ ▒█░░▒█ ▒█▀▀▄\s
                ▒█▄▄▄█ ▒█▄▄█ ░▒█░░ ▒█░░░ ▒█▄▄▄█ ▒█▄▄█""");
        System.out.println("""

                Введите параметры Вашего острова:
                --------------------------------""");
    }

    public static int getRows() {
        System.out.print("Размерность острова по ВЕРТИКАЛИ составит (целое число): " );
        Scanner sc = new Scanner(System.in);
        String rowNums;
        while (true) {
            rowNums = sc.next();
            if (!validate(rowNums)) {
                System.out.print("(!) Введите целое положительное число: ");
            } else {
                break;
            }
        }
        return Integer.parseInt(rowNums);
    }
    public static int getCols() {
        System.out.print("Размерность острова по ГОРИЗОНТАЛИ составит (целое число): " );
        Scanner sc = new Scanner(System.in);
        String rowCols;
        while (true) {
            rowCols = sc.next();
            if (!validate(rowCols)) {
                System.out.print("(!) Введите целое положительное число: ");
            } else {
                break;
            }
        }
        return Integer.parseInt(rowCols);
    }

    public static boolean validate(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++ ){
            if (!Character.isDigit(chars[i]) | (Character.isDigit(chars[i]) && i == 0 && chars[i] == '0')){
                return false;
            }
        }
        return true;
    }

    public static void proceed() {
        System.out.print("\nПродолжить жизнь на Острове? (да\\нет): ");
        Scanner s = new Scanner(System.in);
        String answer;
        while (true) {
            answer = s.next();
            if (!(answer.equalsIgnoreCase("да") | answer.equalsIgnoreCase("нет"))) {
                System.out.print("(!) Введите \"да\" или \"нет\": ");
            } else {
                break;
            }
        }
        if (answer.equalsIgnoreCase("нет")) {
            System.out.println(" ➔ Работа Острова завершена");
            System.exit(0);
        }
    }

    public static ArrayList<String> getIslandArray (Island island) {
        int rows = island.getRows();
        int cols = island.getCols();
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String str = Integer.toString(i) + Integer.toString(j);
                array.add(str);
            }
        }
        return array;
    }

    public static Map<String, Integer> readJson(String fileName, String animalType) {
        Object o;
        JSONObject j;
        Map<String, Integer> map;
        try {
            o = new JSONParser().parse(new FileReader(fileName));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        j = (JSONObject) o;
        if (animalType != null) {
            j = (JSONObject) j.get(animalType);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = j.toString();

        try {
            map = objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public static String getJsonAttr(String fileName, String animalType, String attr) {
        Object o;
        JSONObject j;
        String value;
        try {
            o = new JSONParser().parse(new FileReader(fileName));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        j = (JSONObject) o;
        if (animalType != null && attr != null) {
            j = (JSONObject) j.get(animalType);
            value = (j.get(attr).toString());
        }
        else value = j.toString();
        return value;
    }

    public static int getRandomInt(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from,to);
    }

    public static Map<String, Integer> mergeMaps(Map<String, Integer> fromMap, Map<String, Integer> toMap) {
        fromMap.forEach((key, value) -> toMap.merge(key, value, (oldValue, newValue) -> (oldValue + newValue)));
        return toMap;
    }

    public static Map<String, Integer> getAnimalCount(Island island) {
        Map<String, Integer> animalCount = new HashMap<>();
        for (Cell[] rowCell : island.getField()) {
            for (Cell cell : rowCell) {
                for (var entry : cell.getBioSphere().entrySet()) {
                    animalCount.merge(entry.getKey(), entry.getValue().size(), (oldValue, newValue) -> (oldValue + newValue));
                }
            }
        }
        return animalCount;
    }
}
