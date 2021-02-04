import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ExcelFilterTest {

//    public static String fields = "[{\"path\":[\"DailyFX\"],\"level\":0,\"type\":\"folder\",\"format\":\"String\",\"shareWith\":[{\"buySideShare\":true}]},{\"path\":[\"DailyFX\",\"GBP\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"BRL\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CNY\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"DKK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"INR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"JPY\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"KRW\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MYR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MXN\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NOK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SEK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"ZAR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SGD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CHF\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"TWD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"THB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"VEB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Broad Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Major Currencies Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Other Important Trading Partners Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"AUD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"NZD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CAD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"HKD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"LKR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]}]";
    
/*
[
    {\"path\":[\"DailyFX\"],\"level\":0,\"type\":\"folder\",\"format\":\"String\",\"shareWith\":[{\"buySideShare\":true}]},
    {\"path\":[\"DailyFX\",\"GBP\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},
    {\"path\":[\"DailyFX\",\"INR\",\"Test\"],\"level\":2,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},
    {\"path\":[\"DailyFX\",\"JPY\"],\"level\":1,\"type\":\"folder\,\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},
    {\"path\":[\"DailyFX\",\"VEB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},
    {\"path\":[\"DailyFX\",\"AUD\"],\"level\:1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},
    {\"path\":[\"DailyFX\",\"LKR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]}
]

[{\"path\":[\"DailyFX\"],\"level\":0,\"type\":\"folder\",\"format\":\"String\",\"shareWith\":[{\"buySideShare\":true}]},{\"path\":[\"DailyFX\",\"GBP\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"BRL\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"CNY\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"DKK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"INR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"JPY\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"KRW\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MYR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MXN\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NOK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SEK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"ZAR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SGD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CHF\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"TWD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"THB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"VEB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Broad Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Major Currencies Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Other Important Trading Partners Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"AUD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NZD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CAD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"HKD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"LKR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"GBP\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"BRL\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CNY\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"DKK\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"INR\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"JPY\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"KRW\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MYR\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MXN\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NOK\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SEK\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"ZAR\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SGD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CHF\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"TWD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"THB\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"VEB\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Broad Dollar Index \",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Major Currencies Dollar Index \",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Other Important Trading Partners Dollar Index \",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"AUD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NZD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CAD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"HKD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"LKR\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]}]
*/

// public static String fields = "[{\"path\":[\"DailyFX\"],\"level\":0,\"type\":\"folder\",\"format\":\"String\",\"shareWith\":[{\"buySideShare\":true}]},{\"path\":[\"DailyFX\",\"GBP\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"BRL\",\"Test\"],\"level\":2,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"JPY\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"VEB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"AUD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"HKD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"LKR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]}]";
public static String fields = "[{\"path\":[\"DailyFX\"],\"level\":0,\"type\":\"folder\",\"format\":\"String\",\"shareWith\":[{\"buySideShare\":true}]},{\"path\":[\"DailyFX\",\"GBP\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"BRL\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"JPY\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"VEB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"AUD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"HKD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"LKR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]}]";
    //public static String fields = "[{\"path\":[\"DailyFX\"],\"level\":0,\"type\":\"folder\",\"format\":\"String\",\"shareWith\":[{\"buySideShare\":true}]},{\"path\":[\"DailyFX\",\"GBP\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"BRL\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"CNY\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[{\"banksShare\":true}]},{\"path\":[\"DailyFX\",\"DKK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"INR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"JPY\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"KRW\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MYR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MXN\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NOK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SEK\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"ZAR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SGD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CHF\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"TWD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"THB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"VEB\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Broad Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Major Currencies Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Other Important Trading Partners Dollar Index \"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"AUD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NZD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CAD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"HKD\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"LKR\"],\"level\":1,\"type\":\"folder\",\"format\":\"string\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"GBP\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"BRL\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CNY\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"DKK\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"INR\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"JPY\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"KRW\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MYR\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"MXN\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NOK\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SEK\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"ZAR\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"SGD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CHF\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"TWD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"THB\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"VEB\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Broad Dollar Index \",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Major Currencies Dollar Index \",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"Nominal Other Important Trading Partners Dollar Index \",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"AUD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"NZD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"CAD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"HKD\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]},{\"path\":[\"DailyFX\",\"LKR\",\"1\"],\"level\":2,\"type\":\"\",\"format\":\"number\",\"shareWith\":[]}]";

    public static String participantType = "BuySide";

    public static void main (String[] args) {
        System.out.println ("Testing Filters");
        System.out.println ("Original: " + getShareWithFiltersOriginal(participantType, fields));
        System.out.println ("Modified: " + getShareWithFiltersModified(participantType, fields));
    }


    public static Map<String, List<String>> getShareWithFiltersModified(String participantType, String fields) {
        Map<String, List<String>> entitlementsFilter = new HashMap<>();
        Boolean isRoot = false;
        JsonElement jsonElement = JsonParser.parseString(fields);

        for (JsonElement element : jsonElement.getAsJsonArray()) {

            JsonArray header = element.getAsJsonObject().get("path").getAsJsonArray();
            Integer level = element.getAsJsonObject().get("level").getAsInt();

            if (element.getAsJsonObject().get("shareWith").getAsJsonArray().size() > 0) {
                for (JsonElement shareWithElement : element.getAsJsonObject().get("shareWith").getAsJsonArray()) {

                    JsonObject obj = shareWithElement.getAsJsonObject();

                    Set<Map.Entry<String, JsonElement>> entries = obj.entrySet().stream().filter(shareWith -> shareWith.getKey().endsWith("Share")).collect(Collectors.toSet());
                    for (Map.Entry<String, JsonElement> entry : entries) {
                        if (entry.getKey().toUpperCase().startsWith(participantType.toUpperCase()) && entry.getValue().getAsBoolean()) {
                            String headerName = null;
                            if (level == 1) {
                                headerName = header.get(header.size() - 1).getAsString();
                                entitlementsFilter.put(headerName, new ArrayList<>());
                                entitlementsFilter.get(headerName).add("");
//                                break;
                            } else if (level == 2) {
                                headerName = header.get(header.size() - 2).getAsString();
                                String value = header.get(2).getAsString();
                                if (!entitlementsFilter.containsKey(headerName)) {
                                    entitlementsFilter.put(headerName, new ArrayList<>());
                                }
                                entitlementsFilter.get(headerName).add(value);
//                                break;
                            } else if (level == 0) {
                                isRoot = true;
//                                break;
                            }
                        }
                    }
                }
            } 
            if (isRoot && level > 0) {
                String headerName = header.get(1).getAsString();
                if (level == 1) {
                    entitlementsFilter.put(headerName, new ArrayList<>());
                    entitlementsFilter.get(headerName).add("");
//                    continue;
                } else if (level == 2) {
                    String value = header.get(2).getAsString();
                    if (!entitlementsFilter.containsKey(headerName)) {
                        entitlementsFilter.put(headerName, new ArrayList<>());
                    }
                    entitlementsFilter.get(headerName).add(value);
//                    continue;
                }
            } else {
//                continue;
            }
        }
        for (Map.Entry<String, List<String>> entry : entitlementsFilter.entrySet()) {
            if (entry.getValue().size() > 1 && entry.getValue().get(0).isEmpty()) {
                entry.getValue().remove(0);
            }
        }
        if (isRoot){
            entitlementsFilter.forEach( (header, filter) -> {
                entitlementsFilter.computeIfPresent(header, (k, v) -> Arrays.asList(""));
                //entitlementsFilter.put(header, Collections.emptyList());
            });
        }
        //System.out.println(entitlementsFilter);
        return entitlementsFilter;
    }

    public static Map<String, List<String>> getShareWithFiltersOriginal(String participantType, String fields) {
        Map<String, List<String>> entitlementsFilter = new HashMap<>();
        Boolean isRoot = false;
        JsonElement jsonElement = JsonParser.parseString(fields);

        for (JsonElement element : jsonElement.getAsJsonArray()) {
            if (element.getAsJsonObject().get("shareWith").getAsJsonArray().size() > 0) {
                for (JsonElement shareWithElement : element.getAsJsonObject().get("shareWith").getAsJsonArray()) {

                    JsonArray header = element.getAsJsonObject().get("path").getAsJsonArray();
                    Integer level = element.getAsJsonObject().get("level").getAsInt();
                    JsonObject obj = shareWithElement.getAsJsonObject();

                    Set<Map.Entry<String, JsonElement>> entries = obj.entrySet().stream().filter(shareWith -> shareWith.getKey().endsWith("Share")).collect(Collectors.toSet());
                    for (Map.Entry<String, JsonElement> entry : entries) {
                        if (entry.getKey().toUpperCase().startsWith(participantType.toUpperCase()) && entry.getValue().getAsBoolean()) {
                            String headerName = null;
                            if (level == 1) {
                                headerName = header.get(header.size() - 1).getAsString();
                                entitlementsFilter.put(headerName, new ArrayList<>());
                                entitlementsFilter.get(headerName).add("");
                                break;
                            } else if (level == 2) {
                                headerName = header.get(header.size() - 2).getAsString();
                                String value = header.get(2).getAsString();
                                if (!entitlementsFilter.containsKey(headerName)) {
                                    entitlementsFilter.put(headerName, new ArrayList<>());
                                }
                                entitlementsFilter.get(headerName).add(value);
                                break;
                            } else if (level == 0) {
                                isRoot = true;
                                break;
                            }
                        }
                    }
                }
            } else if (isRoot) {
                JsonArray header = element.getAsJsonObject().get("path").getAsJsonArray();
                Integer level = element.getAsJsonObject().get("level").getAsInt();

                String headerName = header.get(1).getAsString();
                if (level == 1) {
                    entitlementsFilter.put(headerName, new ArrayList<>());
                    entitlementsFilter.get(headerName).add("");
                    continue;
                } else if (level == 2) {
                    String value = header.get(2).getAsString();
                    if (!entitlementsFilter.containsKey(headerName)) {
                        entitlementsFilter.put(headerName, new ArrayList<>());
                    }
                    entitlementsFilter.get(headerName).add(value);
                    continue;
                }
            } else {
                continue;
            }
        }
        for (Map.Entry<String, List<String>> entry : entitlementsFilter.entrySet()) {
            if (entry.getValue().size() > 1 && entry.getValue().get(0).isEmpty()) {
                entry.getValue().remove(0);
            }
        }
        if (isRoot){
            entitlementsFilter.forEach( (header, filter) -> {
                entitlementsFilter.computeIfPresent(header, (k, v) -> Arrays.asList(""));
                //entitlementsFilter.put(header, Collections.emptyList());
            });
        }
        //System.out.println(entitlementsFilter);
        return entitlementsFilter;
    }
}