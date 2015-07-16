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
            if(input.startsWith("addmsguser")) {
                addOutgoingMessage(false);
            }
        }
    }
    
    public void addOutgoingMessage(boolean group) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("user_id", 2);
        if(group == true) {
            builder.add("group_id", "11111");
        } else {
            builder.add("contact_id", 1089341);
        }
        builder.add("message", "Test to tmobile");
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
