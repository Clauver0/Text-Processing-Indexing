package ti.wawan;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.id.IndonesianAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Preprocessing {

    public static List<String> tokenize(String text) throws IOException {
        List<String> tokens = new ArrayList<>();

        try (Analyzer analyzer = new StandardAnalyzer();
            TokenStream stream = analyzer.tokenStream("field", text)) {

            CharTermAttribute termAttr = stream.addAttribute(CharTermAttribute.class);
            stream.reset();
            while (stream.incrementToken()) {
                tokens.add(termAttr.toString());
            }
            stream.end();
        }
        return tokens;
    }

    public static List<String> removeStopwords(List<String> tokens) {
        CharArraySet stopSet = IndonesianAnalyzer.getDefaultStopSet();
        List<String> result = new ArrayList<>();

        for (String token : tokens) {
            if (!stopSet.contains(token.toLowerCase())) {
                result.add(token);
            }
        }
        return result;
    }
    
}




