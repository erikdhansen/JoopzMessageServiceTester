/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joopzmessageservicetester;

import java.io.Console;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import redis.clients.jedis.Jedis;

/**
 *
 * @author ehansen
 */
public class JoopzMessageServiceTester {

    public JoopzMessageServiceTester() {
        
    }
    
    public void prompt() {
        System.out.print("joopz-tester> ");
    }
    
    public void run() {
        Console console = System.console();
        boolean done = false;
        while(!done) {
            prompt();
            String input = console.readLine();
            if(input.startsWith("add-outgoing-message")) {
                addOutgoingMessage();
            }
        }
    }
    
    public void addOutgoingMessage() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("user_id", "12345");
        builder.add("contact_id", "12341");
        builder.add("message", "This is a test message");
        JsonObject obj = builder.build();
        
        Jedis redis = new Jedis("localhost");
        redis.lpush("messages:outgoing", obj.toString());
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JoopzMessageServiceTester tester = new JoopzMessageServiceTester();
        tester.run();
    }
    
}
