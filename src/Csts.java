import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jdk.internal.icu.lang.UCharacter.getType;

public class Csts {
    public static String getProfileIDT(String jmeno, String prijmeni) {
        try {
            URL url = new URL("https://www.csts.cz/cs/Clenove/Hledat?registrovane=True");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("POST");

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.6167.160 Safari/537.36");

            // Enable output and set content length
            connection.setDoOutput(true);
            String requestBody = "idt=&prijmeni=" + prijmeni + "&jmeno=" + jmeno + "&hledat=Hledat";
            byte[] postData = requestBody.getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Content-Length", String.valueOf(postData.length));

            // Write data to the connection
            try (OutputStream os = connection.getOutputStream()) {
                os.write(postData);
            }

            // Get response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read response
            // ... (use InputStream or BufferedReader to read the response)
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String responseBody = response.toString();

            String regex = "/en/evidence/clenove/detailni-udaje-clena/(\\d+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(responseBody);
            if (matcher.find()) {
                return matcher.group(1);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Soutez> getSouteze(String idt) {
        try {
            InputStream input;
            input = new URL("https://www.csts.cz/api/evidence/clenove/detail-clena/vysledky-soutezi/" + idt + "?%24count=true&%24skip=0&%24top=20&%24orderby=Datum%20desc").openStream();
            String json = new Scanner(input).useDelimiter("\\A").next();
            Gson gson = new Gson();
            OuterObject outerObject = gson.fromJson(json, OuterObject.class);

            // Access the "Items" array and convert it to a list
            List<Soutez> soutezeList = outerObject.Items;
            for (int i = 20; i < outerObject.Count; i = i + 20) {
                input = new URL("https://www.csts.cz/api/evidence/clenove/detail-clena/vysledky-soutezi/" + idt + "?%24count=true&%24skip=" + i + "&%24top=20&%24orderby=Datum%20desc").openStream();
                json = new Scanner(input).useDelimiter("\\A").next();
                gson = new Gson();
                outerObject = gson.fromJson(json, OuterObject.class);
                soutezeList.addAll(outerObject.Items);
            }
            return soutezeList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void getNadchazejiciSouteze() {
        try {
            Document html = Jsoup.connect("https://www.csts.cz/cs/KalendarSoutezi/Seznam?OdData=02%2F01%2F2024%2000%3A00%3A00&DoData=05%2F31%2F2024%2000%3A00%3A00&Region=0").get();
            Elements elements = html.select(".kalendar-box-2");
            for (Element element : elements) {
                String htmlString =element.select(".plista-b").html();

                String regex = "/cs/KalendarSoutezi/SeznamPrihlasenych/(\\d++)";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(htmlString);

                while (matcher.find()) {
                    Document html2 = Jsoup.connect("https://www.csts.cz/cs/KalendarSoutezi/SeznamPrihlasenych/" + matcher.group(1)).get();
                    Element h2 = html2.selectFirst("h2");
                    Elements categories = html2.select(".pso-box1");
                    for (Element category : categories) {
                        if(category.select(".pso-box2").html().contains("Dospělí-B-LAT")){

                            System.out.println(h2.text());
                            System.out.println(category.select(".pso-box2").text());
                            System.out.println(category.select("table").html());
                        }
                    }

                }

            }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
