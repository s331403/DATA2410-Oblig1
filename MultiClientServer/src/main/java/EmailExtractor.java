import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;


@Component
public class EmailExtractor {
    public String urlSearch(String urlToBeSearched) throws Exception {
        URL url = new URL(urlToBeSearched);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        String htmlString = "";
        while((inputLine = in.readLine()) != null) {
            htmlString += inputLine;
        }
        in.close();

        return emailMatcher(htmlString).stream().collect(Collectors.joining("\n"));
    }

    private List<String> emailMatcher(String http) {
        String regex = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        List<String> matches = Pattern.compile(regex)
                .matcher(http)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
        return matches;
    }
}
