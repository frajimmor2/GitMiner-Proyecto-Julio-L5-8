package AISS.AISS.util;

import org.springframework.http.HttpHeaders;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class Utils {
    public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // Si no hay link header, devolvemos null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // Si la cabecera no contiene links, devolvemos null
        String links = linkHeader.get(0);
        if (links == null || links.isEmpty())
            return null;

        // Devolvemos la siguiente pagina o null si no hay
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Una vez encontrada la pagina, tiene q verse asi
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }

    public static LocalDateTime getDifferenceBetweenDates(Integer since){
        return LocalDateTime.now().plusDays(-since);
    }

}
