import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmailExtractorTest {

    @Test
    void urlSearch(String url) {
        EmailExtractor extractor = new EmailExtractor();
        try {
            String emails;
            emails = extractor.urlSearch(url);
            System.out.println(emails);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}