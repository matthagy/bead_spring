package bead_spring.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SubmitJobLambda implements RequestStreamHandler {

    public void handleRequest(InputStream inputStream,
                              OutputStream outputStream,
                              Context context) throws IOException {
        String input = IOUtils.toString(inputStream, "UTF-8");
        Object obj;
        try {
            obj = new JSONParser().parse(input);
        } catch (ParseException e) {
            outputStream.write(("{\"error\": \"json parse error\"}").getBytes());
            return;
        }
        outputStream.write(("Hello World - " + input).getBytes());
    }
}