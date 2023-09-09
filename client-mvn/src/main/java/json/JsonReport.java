package json;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import domain.Component;
import domain.Tehnolog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonReport {
	private static Map<Tehnolog, LocalDateTime> loginT;
	private static Map<Component, LocalDateTime> newC;
	
	final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static void generateReportT() {
        JsonObject jsonReport1 = new JsonObject();
        addLoginInfo(jsonReport1);
        writeFileLogin(jsonReport1);
      
    }
	public static void generateReportC() {
        JsonObject jsonReport2 = new JsonObject();
        addComponentInfo(jsonReport2);
        writeFileComp(jsonReport2);
      
    }
	private static void addComponentInfo(JsonObject jsonReport) {
        JsonArray compJsonArray = new JsonArray();

        for (Map.Entry<Component, LocalDateTime> entry : newC.entrySet()) {
            Component c = entry.getKey();
            LocalDateTime addC = entry.getValue();

            JsonObject compJson = new JsonObject();
            compJson.addProperty("component", c.toString());
            compJson.addProperty("date", addC.format(DATE_FORMAT));
            compJsonArray.add(compJson);

        }

        jsonReport.add("newC", compJsonArray);
    }
	private static void addLoginInfo(JsonObject jsonReport) {
        JsonArray loginJsonArray = new JsonArray();

        for (Map.Entry<Tehnolog, LocalDateTime> entry : loginT.entrySet()) {
            Tehnolog t = entry.getKey();
            LocalDateTime login = entry.getValue();

            JsonObject loginJson = new JsonObject();
            loginJson.addProperty("tehnolog", t.toString());
            loginJson.addProperty("date", login.format(DATE_FORMAT));
            loginJsonArray.add(loginJson);

        }

        jsonReport.add("loginT", loginJsonArray);
    }

    private static void writeFileLogin(JsonObject jsonReport) {
        try (FileWriter out = new FileWriter("login_report.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            out.write(gson.toJson(jsonReport));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeFileComp(JsonObject jsonReport) {
        try (FileWriter out = new FileWriter("component_report.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            out.write(gson.toJson(jsonReport));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLoginTehn(Map<Tehnolog, LocalDateTime> loginTehn) {
        JsonReport.loginT = loginTehn;
    }
    
    public static void setNewComp(Map<Component, LocalDateTime> newComp) {
        JsonReport.newC = newComp;
    }
}
