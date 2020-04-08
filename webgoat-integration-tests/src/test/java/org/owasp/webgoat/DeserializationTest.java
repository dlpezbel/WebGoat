package org.owasp.webgoat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dummy.insecure.framework.VulnerableTaskHolder;
import org.junit.Test;
import org.junit.Ignore;
import org.owasp.webgoat.deserialization.SerializationHelper;
@Ignore
public class DeserializationTest extends IntegrationTest {

	private static String OS = System.getProperty("os.name").toLowerCase();
    
    @Test
    public void runTests() throws IOException {
        startLesson("InsecureDeserialization");
        
        Map<String, Object> params = new HashMap<>();
        params.clear();
                
        if (OS.indexOf("win")>-1) {
        	params.put("token", SerializationHelper.toString(new VulnerableTaskHolder("wait", "ping localhost -n 5")));
        } else {
            params.put("token", SerializationHelper.toString(new VulnerableTaskHolder("wait", "sleep 5")));
        }
        checkAssignment(url("/WebGoat/InsecureDeserialization/task"),params,true);
        
        checkResults("/InsecureDeserialization/");
    
    }
    
    
}
